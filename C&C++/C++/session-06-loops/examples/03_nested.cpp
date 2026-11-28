// 03_nested.cpp — حلقهٔ تودرتو: جدول ضرب و الگوی ستاره‌ای
// کامپایل: g++ -std=c++17 -Wall -Wextra -o nested examples/03_nested.cpp

#include <iomanip>
#include <iostream>

int main() {
    // --- جدول ضرب ۱۰×۱۰ ---
    std::cout << "   ";
    for (int col = 1; col <= 10; ++col) {
        std::cout << std::setw(4) << col;
    }
    std::cout << "\n";

    for (int row = 1; row <= 10; ++row) {
        std::cout << std::setw(2) << row << " ";
        for (int col = 1; col <= 10; ++col) {
            std::cout << std::setw(4) << row * col;
        }
        std::cout << "\n";
    }

    // --- مثلث ستاره‌ای ---
    std::cout << "\nمثلث:\n";
    for (int row = 1; row <= 5; ++row) {
        for (int star = 1; star <= row; ++star) {
            std::cout << "*";
        }
        std::cout << "\n";
    }

    // --- مثلث معکوس با فاصله ---
    std::cout << "\nمثلث معکوس:\n";
    for (int row = 5; row >= 1; --row) {
        for (int star = 1; star <= row; ++star) {
            std::cout << "*";
        }
        std::cout << "\n";
    }

    // --- break فقط از حلقهٔ داخلی خارج می‌شود ---
    std::cout << "\nجست‌وجو در جدول 3×3 برای یافتن 6:\n";
    bool found = false;
    for (int row = 1; row <= 3 && !found; ++row) {
        for (int col = 1; col <= 3; ++col) {
            if (row * col == 6) {
                std::cout << "پیدا شد در سطر " << row << "، ستون " << col << "\n";
                found = true;   // پرچم برای خروج از حلقهٔ بیرونی
                break;          // فقط از حلقهٔ داخلی خارج می‌شود
            }
        }
    }

    return 0;
}
