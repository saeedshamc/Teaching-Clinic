// 02_precedence.cpp — اولویت عملگرها و نقش پرانتز
// کامپایل: g++ -std=c++17 -Wall -Wextra -o prec examples/02_precedence.cpp

#include <iostream>

int main() {
    // ضرب قبل از جمع
    std::cout << "2 + 3 * 4       = " << 2 + 3 * 4
              << "   (معادل 2 + (3 * 4))\n";
    std::cout << "(2 + 3) * 4     = " << (2 + 3) * 4 << "\n";

    // تفریق چپ‌به‌راست است
    std::cout << "10 - 4 - 3      = " << 10 - 4 - 3
              << "   (معادل (10 - 4) - 3)\n";
    std::cout << "10 - (4 - 3)    = " << 10 - (4 - 3) << "\n";

    // مقایسه قبل از منطقی
    const int age = 20;
    const bool adult = age >= 18 && age < 65;
    std::cout << "age >= 18 && age < 65 = " << std::boolalpha << adult << "\n";

    // نقیض و پرانتز
    const bool hasTicket = false;
    const bool canEnter = !hasTicket == false;
    std::cout << "!hasTicket == false = " << canEnter << "\n";

    // باقی‌مانده و جمع
    std::cout << "10 + 7 % 3      = " << 10 + 7 % 3
              << "   (معادل 10 + (7 % 3))\n";

    // هم‌ارزی و انتساب در عبارت (بدون نوشتن اشتباه رایج)
    const int x = 5;
    const bool isFive = (x == 5);
    std::cout << "x == 5 : " << isFive << "\n";

    // ارزیابی کوتاه‌مدار: سمت راست وقتی چپ غلط است اجرا نمی‌شود
    const int count = 0;
    const int total = 100;
    const bool bigAverage = count != 0 && total / count > 10;
    std::cout << "count != 0 && total / count > 10 : " << bigAverage
              << "   (تقسیم بر صفر اجرا نشد)\n";

    return 0;
}
