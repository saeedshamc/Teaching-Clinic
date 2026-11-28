// 03_overloading.cpp — چند نسخه از یک نام
// کامپایل: g++ -std=c++17 -Wall -Wextra -o overloading examples/03_overloading.cpp

#include <iostream>
#include <string>

// --- همان نام، امضای متفاوت ---
int maxOf(int a, int b) {
    return a > b ? a : b;
}

double maxOf(double a, double b) {
    return a > b ? a : b;
}

int maxOf(int a, int b, int c) {
    return maxOf(maxOf(a, b), c);        // فراخوانی نسخهٔ دوتایی از داخل
}

// --- overloading روی نوع پارامتر، نه فقط تعداد ---
void show(int value) {
    std::cout << "int    : " << value << "\n";
}

void show(double value) {
    std::cout << "double : " << value << "\n";
}

void show(const std::string& value) {
    std::cout << "string : " << value << "\n";
}

int main() {
    std::cout << "maxOf(3, 9)          = " << maxOf(3, 9) << "\n";
    std::cout << "maxOf(3.5, 9.25)     = " << maxOf(3.5, 9.25) << "\n";
    std::cout << "maxOf(4, 11, 7)      = " << maxOf(4, 11, 7) << "\n";
    std::cout << "maxOf(maxOf(1,2), 3) = " << maxOf(maxOf(1, 2), 3) << "\n";

    std::cout << "\n--- انتخاب نسخهٔ show ---\n";
    show(42);
    show(3.14);
    show(std::string("C++"));

    // char به int ارتقا (promotion) پیدا می‌کند و نسخهٔ int انتخاب می‌شود
    show('A');

    std::cout << "\n--- دقت در انتخاب ---\n";
    // در اینجا برخلاف انتظار، نسخهٔ int با تبدیل double به int صدا زده نمی‌شود؛
    // اختلاف نوع int و long هر دو تبدیل استاندارد است و مبهم خواهد بود:
    //   maxOf(2, 3L);   → خطای کامپایل: ambiguous call

    return 0;
}
