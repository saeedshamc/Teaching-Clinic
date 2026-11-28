// 02_read_lines.cpp — ifstream: getline، کلمه‌شمار و بررسی وضعیت جریان
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o read_lines examples/02_read_lines.cpp

#include <filesystem>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>

namespace fs = std::filesystem;

int main() {
    const fs::path path = fs::temp_directory_path() / "session39_read.txt";
    {
        std::ofstream output(path);
        output << "cpp is fast\n";
        output << "stl gives tools\n";
        output << "practice every day\n";
    }

    std::ifstream input(path);
    if (!input) {
        std::cout << "  فایل باز نشد\n";
        return 1;
    }

    std::cout << "--- خواندن خط‌به‌خط ---\n";
    std::string line;
    int lineCount = 0;
    int totalWords = 0;
    while (std::getline(input, line)) {
        ++lineCount;
        std::istringstream words(line);
        std::string word;
        int wordsInLine = 0;
        while (words >> word) {
            ++wordsInLine;
        }
        totalWords += wordsInLine;
        std::cout << "  " << lineCount << ": " << line << " (" << wordsInLine << " کلمه)\n";
    }

    std::cout << "  خطوط: " << lineCount << "، کلمات: " << totalWords << "\n";
    std::cout << "  eof: " << (input.eof() ? "بله" : "خیر")
              << "، fail: " << (input.fail() ? "بله" : "خیر") << "\n";

    std::cout << "--- خواندن کلمه‌به‌کلمه با >> ---\n";
    input.clear();                 // پاک کردن وضعیت eof
    input.seekg(0);                // برگشت به ابتدای فایل
    std::string word;
    int firstThree = 0;
    while (firstThree < 3 && (input >> word)) {
        ++firstThree;
        std::cout << "  " << firstThree << ": " << word << "\n";
    }

    std::cout << "--- فایل ناموجود ---\n";
    std::ifstream missing(fs::temp_directory_path() / "session39_no_such_file.txt");
    std::cout << "  باز شد: " << (missing ? "بله" : "خیر")
              << "، fail: " << (missing.fail() ? "بله" : "خیر") << "\n";

    fs::remove(path);
    return 0;
}
