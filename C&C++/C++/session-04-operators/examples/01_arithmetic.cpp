// 01_arithmetic.cpp — عملگرهای حسابی و فرم‌های کوتاه
// کامپایل: g++ -std=c++17 -Wall -Wextra -o ops examples/01_arithmetic.cpp

#include <iostream>

int main() {
    const int a = 17;
    const int b = 5;

    std::cout << a << " + " << b << " = " << a + b << "\n";
    std::cout << a << " - " << b << " = " << a - b << "\n";
    std::cout << a << " * " << b << " = " << a * b << "\n";
    std::cout << a << " / " << b << " = " << a / b << "   (تقسیم صحیح)\n";
    std::cout << a << " % " << b << " = " << a % b << "   (باقی‌مانده)\n";

    // تقسیم اعشاری با cast صریح
    std::cout << "اعشاری: " << static_cast<double>(a) / b << "\n";

    // فرم‌های کوتاه انتساب
    int total = 10;
    std::cout << "\ntotal = " << total << "\n";
    total += 5;
    std::cout << "بعد از += 5 : " << total << "\n";
    total -= 3;
    std::cout << "بعد از -= 3 : " << total << "\n";
    total *= 2;
    std::cout << "بعد از *= 2 : " << total << "\n";
    total /= 5;
    std::cout << "بعد از /= 5 : " << total << "\n";
    total %= 3;
    std::cout << "بعد از %= 3 : " << total << "\n";

    // پیش و پس‌افزایش
    int i = 5;
    const int post = i++;
    const int pre = ++i;
    std::cout << "\npost = " << post << " (i بعد از i++ = 6)\n";
    std::cout << "pre  = " << pre << " (i بعد از ++i = 7)\n";

    // درصد
    const int obtained = 42;
    const int maximum = 50;
    const double percent = static_cast<double>(obtained) / maximum * 100.0;
    std::cout << "درصد = " << percent << "%\n";

    return 0;
}
