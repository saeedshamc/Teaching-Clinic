// 02_raii_file_guard.cpp — نگهبان فایل: بستن تضمین‌شده حتی با استثنا
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o file_guard examples/02_raii_file_guard.cpp

#include <filesystem>
#include <fstream>
#include <iostream>
#include <stdexcept>
#include <string>

namespace fs = std::filesystem;

class FileWriter {
public:
    explicit FileWriter(const fs::path& path)
        : path_(path), stream_(path) {
        if (!stream_) {
            throw std::runtime_error("باز کردن فایل ناموفق بود");
        }
        std::cout << "  + فایل باز شد\n";
    }

    FileWriter(const FileWriter&) = delete;
    FileWriter& operator=(const FileWriter&) = delete;

    ~FileWriter() {
        if (stream_.is_open()) {
            stream_.close();
            std::cout << "  - فایل بسته شد\n";
        }
    }

    void writeLine(const std::string& line) {
        stream_ << line << '\n';
        if (!stream_) {
            throw std::runtime_error("نوشتن در فایل شکست خورد");
        }
    }

    void flush() {
        stream_.flush();
    }

private:
    fs::path path_;
    std::ofstream stream_;
};

int main() {
    const fs::path path = fs::temp_directory_path() / "session38_guard.txt";

    std::cout << "--- نوشتن موفق ---\n";
    {
        FileWriter writer(path);
        writer.writeLine("خط اول");
        writer.writeLine("خط دوم");
        writer.flush();
    }

    std::cout << "--- خطا در میانهٔ کار ---\n";
    try {
        FileWriter writer(path);
        writer.writeLine("خط سوم");
        throw std::runtime_error("خطای شبیه‌سازی‌شده");
    } catch (const std::exception& error) {
        std::cout << "  گرفته شد: " << error.what() << "\n";
    }

    std::cout << "--- خواندن محتوای فایل ---\n";
    {
        std::ifstream reader(path);
        std::string line;
        int count = 0;
        while (std::getline(reader, line)) {
            ++count;
            std::cout << "  " << count << ": " << line << "\n";
        }
        std::cout << "  تعداد خطوط: " << count << "\n";
    }

    fs::remove(path);
    return 0;
}
