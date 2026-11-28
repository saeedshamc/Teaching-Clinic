// 03_binary_file.cpp — ios::binary و نوشتن ساختار خام
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o binary_file examples/03_binary_file.cpp

#include <cstring>
#include <filesystem>
#include <fstream>
#include <iostream>
#include <string>

namespace fs = std::filesystem;

struct Record {
    int id;
    double value;
    char tag[8];
};

int main() {
    const fs::path path = fs::temp_directory_path() / "session39_binary.dat";

    Record original{};
    original.id = 7;
    original.value = 3.5;
    std::strncpy(original.tag, "abc", sizeof(original.tag) - 1);

    {
        std::ofstream output(path, std::ios::binary);
        output.write(reinterpret_cast<const char*>(&original), sizeof(Record));
    }
    std::cout << "  sizeof(Record) = " << sizeof(Record) << "\n";
    std::cout << "  اندازهٔ فایل = " << fs::file_size(path) << " بایت\n";

    Record loaded{};
    {
        std::ifstream input(path, std::ios::binary);
        input.read(reinterpret_cast<char*>(&loaded), sizeof(Record));
        std::cout << "  خوانده شد: " << input.gcount() << " بایت\n";
    }

    std::cout << "--- مقایسه ---\n";
    std::cout << "  id: " << loaded.id << " (قبلی " << original.id << ")\n";
    std::cout << "  value: " << loaded.value << " (قبلی " << original.value << ")\n";
    std::cout << "  tag: " << loaded.tag << "\n";
    const bool same = loaded.id == original.id &&
                      loaded.value == original.value &&
                      std::strcmp(loaded.tag, original.tag) == 0;
    std::cout << "  یکسان است؟ " << (same ? "بله" : "خیر") << "\n";

    std::cout << "--- چرا باینری برای انتقال بین سیستم‌ها خطرناک است ---\n";
    std::cout << "  اندازهٔ int و double و ترتیب بایت‌ها بین معماری‌ها یکسان نیست.\n";
    std::cout << "  برای فایل قابل‌انتقال، متن یا قالب استاندارد (JSON/Protobuf) بهتر است.\n";

    fs::remove(path);
    return 0;
}
