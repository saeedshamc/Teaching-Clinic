// 01_write_file.cpp — ofstream: نوشتن متن و بررسی موفقیت
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o write_file examples/01_write_file.cpp

#include <filesystem>
#include <fstream>
#include <iostream>
#include <string>

namespace fs = std::filesystem;

int main() {
    const fs::path path = fs::temp_directory_path() / "session39_write.txt";

    std::ofstream output(path);   // حالت پیش‌فرض: بازنویسی
    if (!output) {
        std::cout << "  باز کردن فایل ناموفق بود\n";
        return 1;
    }

    output << "خط اول\n";
    output << "خط دوم\n";
    output << "خط سوم\n";

    std::cout << "  is_open قبل از close: " << (output.is_open() ? "بله" : "خیر") << "\n";
    output.close();
    std::cout << "  is_open بعد از close: " << (output.is_open() ? "بله" : "خیر") << "\n";

    std::cout << "  فایل ساخته شد: " << (fs::exists(path) ? "بله" : "خیر") << "\n";
    std::cout << "  اندازه: " << fs::file_size(path) << " بایت\n";

    std::cout << "--- افزودن به فایل با ios::app ---\n";
    {
        std::ofstream append(path, std::ios::app);
        append << "خط چهارم\n";
    }
    std::cout << "  اندازه بعد از افزودن: " << fs::file_size(path) << " بایت\n";

    std::cout << "--- تلاش برای نوشتن در مسیر نامعتبر ---\n";
    {
        std::ofstream invalid("this-directory-does-not-exist/file.txt");
        std::cout << "  is_open: " << (invalid.is_open() ? "بله" : "خیر")
                  << "، operator bool: " << (invalid ? "درست" : "غلط") << "\n";
    }

    fs::remove(path);
    std::cout << "فایل موقت پاک شد.\n";
    return 0;
}
