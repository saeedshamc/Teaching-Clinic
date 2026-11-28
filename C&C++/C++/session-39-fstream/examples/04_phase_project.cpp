// 04_phase_project.cpp — پروژهٔ ارزیابی فاز ۶:
// ذخیره و بازخوانی vector اشیاء در فایل متنی و باینری با مدیریت RAII
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o phase_project examples/04_phase_project.cpp

#include <cstdint>
#include <filesystem>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace fs = std::filesystem;

struct Book {
    int id;
    std::string title;
    int year;
};

// ---------- متنی: هر کتاب یک خط با جداکنندهٔ | ----------
void saveText(const fs::path& path, const std::vector<Book>& books) {
    std::ofstream output(path);   // RAII: بستن خودکار
    if (!output) {
        std::cerr << "نوشتن فایل متنی ناموفق بود\n";
        return;
    }
    for (const auto& book : books) {
        output << book.id << '|' << book.title << '|' << book.year << '\n';
    }
}

std::vector<Book> loadText(const fs::path& path) {
    std::vector<Book> books;
    std::ifstream input(path);
    if (!input) {
        return books;
    }
    std::string line;
    while (std::getline(input, line)) {
        std::istringstream parts(line);
        std::string idPart;
        std::string titlePart;
        std::string yearPart;
        if (!std::getline(parts, idPart, '|') ||
            !std::getline(parts, titlePart, '|') ||
            !std::getline(parts, yearPart, '|')) {
            continue;   // خط خراب را رد کن
        }
        books.push_back({std::stoi(idPart), titlePart, std::stoi(yearPart)});
    }
    return books;
}

// ---------- باینری: id، طول عنوان، خود عنوان، سال ----------
void saveBinary(const fs::path& path, const std::vector<Book>& books) {
    std::ofstream output(path, std::ios::binary);
    if (!output) {
        std::cerr << "نوشتن فایل باینری ناموفق بود\n";
        return;
    }
    for (const auto& book : books) {
        output.write(reinterpret_cast<const char*>(&book.id), sizeof(book.id));
        const auto length = static_cast<std::uint32_t>(book.title.size());
        output.write(reinterpret_cast<const char*>(&length), sizeof(length));
        output.write(book.title.data(), static_cast<std::streamsize>(length));
        output.write(reinterpret_cast<const char*>(&book.year), sizeof(book.year));
    }
}

std::vector<Book> loadBinary(const fs::path& path) {
    std::vector<Book> books;
    std::ifstream input(path, std::ios::binary);
    if (!input) {
        return books;
    }
    while (true) {
        Book book{0, "", 0};
        std::uint32_t length = 0;
        input.read(reinterpret_cast<char*>(&book.id), sizeof(book.id));
        if (!input) {
            break;   // پایان فایل
        }
        input.read(reinterpret_cast<char*>(&length), sizeof(length));
        if (!input) {
            break;
        }
        book.title.resize(length);
        if (length > 0) {
            input.read(&book.title[0], static_cast<std::streamsize>(length));
            if (!input) {
                break;
            }
        }
        input.read(reinterpret_cast<char*>(&book.year), sizeof(book.year));
        if (!input) {
            break;
        }
        books.push_back(book);
    }
    return books;
}

void print(const std::string& label, const std::vector<Book>& books) {
    std::cout << label << " (" << books.size() << " کتاب):\n";
    for (const auto& book : books) {
        std::cout << "  " << book.id << " — " << book.title << " — " << book.year << "\n";
    }
}

int main() {
    const std::vector<Book> original{
        {1, "C++ Primer", 2013},
        {2, "Effective Modern C++", 2014},
        {3, "The C++ Programming Language", 2013},
    };
    print("--- اصلی", original);

    const fs::path textPath = fs::temp_directory_path() / "session39_books.txt";
    const fs::path binaryPath = fs::temp_directory_path() / "session39_books.bin";

    saveText(textPath, original);
    saveBinary(binaryPath, original);
    std::cout << "--- فایل‌ها ---\n";
    std::cout << "  متن: " << fs::file_size(textPath) << " بایت\n";
    std::cout << "  باینری: " << fs::file_size(binaryPath) << " بایت\n";

    print("--- بازخوانی متنی", loadText(textPath));
    print("--- بازخوانی باینری", loadBinary(binaryPath));

    std::cout << "--- بازخوانی از فایل ناموجود ---\n";
    std::cout << "  کتاب‌ها: "
              << loadText(fs::temp_directory_path() / "session39_missing.txt").size()
              << " (بدون خطا، vector خالی)\n";

    fs::remove(textPath);
    fs::remove(binaryPath);
    std::cout << "فایل‌های موقت پاک شدند.\n";
    return 0;
}
