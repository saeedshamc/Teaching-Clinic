// 02_vector_basics.cpp — std::vector: آرایهٔ پویا و امن
// کامپایل: g++ -std=c++17 -Wall -Wextra -o vector_basics examples/02_vector_basics.cpp

#include <iostream>
#include <string>
#include <vector>

int main() {
    // سه راه ساخت
    std::vector<int> empty;                    // خالی
    std::vector<int> zeros(4);                 // چهار صفر
    std::vector<int> numbers{10, 20, 30};      // مقدار اولیه

    std::cout << "numbers.size() = " << numbers.size() << "\n";
    std::cout << "zeros.size()   = " << zeros.size() << "\n";
    std::cout << "empty.size()   = " << empty.size() << "\n";

    // افزودن عنصر: اندازه خودش بزرگ می‌شود
    numbers.push_back(40);
    numbers.push_back(50);
    std::cout << "بعد از دو push_back → size = " << numbers.size() << "\n";

    // پیمایش با range-based for (ساده‌ترین و امن‌ترین راه)
    std::cout << "اعداد: ";
    for (int value : numbers) {
        std::cout << value << " ";
    }
    std::cout << "\n";

    // پیمایش با مرجع ثابت: بدون کپی
    std::cout << "دوبرابر: ";
    for (const int& value : numbers) {
        std::cout << value * 2 << " ";
    }
    std::cout << "\n";

    // پیمایش با مرجع غیرثابت: عناصر واقعاً تغییر می‌کنند
    for (int& value : numbers) {
        value += 1;
    }
    std::cout << "بعد از value += 1: ";
    for (const int& value : numbers) {
        std::cout << value << " ";
    }
    std::cout << "\n";

    // دسترسی: [] بررسی نمی‌کند، at() بررسی می‌کند
    std::cout << "\nnumbers[0] = " << numbers[0] << "\n";
    std::cout << "numbers.at(0) = " << numbers.at(0) << "\n";
    std::cout << "numbers.front() = " << numbers.front()
              << " ، numbers.back() = " << numbers.back() << "\n";

    // at() خارج از محدوده استثنا می‌دهد (try/catch در جلسهٔ ۲۶)
    try {
        std::cout << numbers.at(100) << "\n";
    } catch (const std::out_of_range& error) {
        std::cout << "at(100) استثنا داد: " << error.what() << "\n";
    }

    // حذف آخرین عنصر و خالی کردن
    numbers.pop_back();
    std::cout << "\nبعد از pop_back → back = " << numbers.back()
              << " ، size = " << numbers.size() << "\n";

    // vector رشته‌ها
    std::vector<std::string> fruits{"سیب", "موز"};
    fruits.push_back("انار");
    std::cout << "\nمیوه‌ها (" << fruits.size() << "): ";
    for (const std::string& fruit : fruits) {
        std::cout << fruit << " ";
    }
    std::cout << "\n";

    fruits.clear();
    std::cout << "بعد از clear → size = " << fruits.size()
              << " ، empty = " << (fruits.empty() ? "true" : "false") << "\n";

    return 0;
}
