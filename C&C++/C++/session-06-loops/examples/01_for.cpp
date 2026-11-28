// 01_for.cpp — حلقهٔ for: شمارش، جمع، فاکتوریل، جدول ضرب
// کامپایل: g++ -std=c++17 -Wall -Wextra -o loops examples/01_for.cpp

#include <iomanip>
#include <iostream>

int main() {
    // --- شمارش ساده ---
    std::cout << "اعداد 1 تا 5: ";
    for (int i = 1; i <= 5; ++i) {
        std::cout << i << " ";
    }
    std::cout << "\n";

    // --- شمارش از صفر (رایج‌ترین شکل) ---
    const int count = 5;
    std::cout << "پنج بار اجرا: ";
    for (int i = 0; i < count; ++i) {
        std::cout << i << " ";
    }
    std::cout << "\n";

    // --- جمع اعداد ۱ تا n ---
    const int n = 10;
    int sum = 0;
    for (int i = 1; i <= n; ++i) {
        sum += i;
    }
    std::cout << "جمع 1 تا " << n << " = " << sum << "\n";

    // --- فاکتوریل با long long (int در 13! سرریز می‌کند) ---
    long long factorial = 1;
    for (int i = 1; i <= 10; ++i) {
        factorial *= i;
    }
    std::cout << "10! = " << factorial << "\n";

    // --- گام غیر یک ---
    std::cout << "اعداد زوج 2 تا 10: ";
    for (int i = 2; i <= 10; i += 2) {
        std::cout << i << " ";
    }
    std::cout << "\n";

    // --- شمارش معکوس ---
    std::cout << "شمارش معکوس: ";
    for (int i = 5; i >= 1; --i) {
        std::cout << i << " ";
    }
    std::cout << "\n";

    // --- جدول ضرب یک عدد ---
    const int table = 7;
    std::cout << "\nجدول ضرب " << table << ":\n";
    for (int i = 1; i <= 10; ++i) {
        std::cout << std::setw(2) << table << " × " << std::setw(2) << i
                  << " = " << std::setw(3) << table * i << "\n";
    }

    return 0;
}
