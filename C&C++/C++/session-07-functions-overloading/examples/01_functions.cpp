// 01_functions.cpp — از تابع void تا تابع با مقدار بازگشتی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o functions examples/01_functions.cpp

#include <iostream>

// --- تعریف پیش از استفاده: نیازی به اعلان جداگانه نیست ---
int square(int value) {
    return value * value;
}

// --- اعلان (prototype): تعریف در پایین فایل می‌آید ---
double average(double a, double b);
int sumTo(int limit);

// --- تابع void: چیزی برنمی‌گرداند، فقط کار می‌کند ---
void printLine(int count, char fill) {
    for (int i = 0; i < count; ++i) {
        std::cout << fill;
    }
    std::cout << "\n";
}

// --- خروج زودهنگام با return در تابع void ---
void printSign(int value) {
    if (value > 0) {
        std::cout << value << " مثبت است.\n";
        return;                     // بقیهٔ بدنه اجرا نمی‌شود
    }
    if (value < 0) {
        std::cout << value << " منفی است.\n";
        return;
    }
    std::cout << "صفر است.\n";
}

// --- دامنهٔ محلی: هر فراخوانی متغیرهای خودش را دارد ---
int nextId() {
    int id = 0;                     // هر بار از صفر ساخته می‌شود
    ++id;
    return id;
}

double average(double a, double b) {
    return (a + b) / 2.0;
}

int sumTo(int limit) {
    int total = 0;
    for (int i = 1; i <= limit; ++i) {
        total += i;
    }
    return total;
}

int main() {
    std::cout << "square(7)     = " << square(7) << "\n";
    std::cout << "square(-4)    = " << square(-4) << "\n";

    printLine(28, '-');

    std::cout << "average(12.5, 17.5) = " << average(12.5, 17.5) << "\n";
    std::cout << "sumTo(10)           = " << sumTo(10) << "\n";

    printLine(28, '=');

    // مقدار بازگشتی را می‌توان در عبارت هم استفاده کرد
    std::cout << "square(square(2)) = " << square(square(2)) << "\n";

    printLine(28, '-');

    printSign(12);
    printSign(-7);
    printSign(0);

    printLine(28, '-');

    // هر فراخوانی id تازه می‌سازد؛ حافظهٔ فراخوانی قبلی باقی نمی‌ماند
    std::cout << "nextId() = " << nextId() << "\n";
    std::cout << "nextId() = " << nextId() << "\n";

    return 0;
}
