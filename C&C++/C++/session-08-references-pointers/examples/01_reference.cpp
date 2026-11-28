// 01_reference.cpp — reference: نام دوم برای یک متغیر موجود
// کامپایل: g++ -std=c++17 -Wall -Wextra -o reference examples/01_reference.cpp

#include <iostream>
#include <string>

// پارامتر مرجع: تغییر داخل تابع روی متغیر اصلی اثر می‌گذارد
void scale(int& value, int factor) {
    value *= factor;
}

// پارامتر مرجع ثابت: بدون کپی و بدون امکان تغییر
void printOnly(const std::string& text) {
    // text += "!";   → خطای کامپایل: text ثابت است
    std::cout << "متن (" << text.size() << " بایت): " << text << "\n";
}

// بازگرداندن reference به یکی از پارامترها (هر دو از قبل زنده‌اند)
int& pickLarger(int& a, int& b) {
    return a >= b ? a : b;
}

int main() {
    int value = 10;
    int& ref = value;              // ref نام دومِ value است، نه کپی آن

    std::cout << "value = " << value << ", ref = " << ref << "\n";

    ref = 20;                      // از طریق ref همان value تغییر می‌کند
    std::cout << "بعد از ref = 20  → value = " << value << "\n";

    ++value;
    std::cout << "بعد از ++value   → ref = " << ref << "\n";

    // آدرس هر دو یکی است: شیء فیزیکی جدیدی ساخته نشده
    std::cout << "آدرس یکسان است؟ " << (&value == &ref ? "بله" : "خیر") << "\n";

    std::cout << "\n--- reference به‌عنوان پارامتر ---\n";
    int number = 7;
    scale(number, 3);
    std::cout << "scale(7, 3) → number = " << number << "\n";

    const std::string message = "Saeed";
    printOnly(message);            // رشته کپی نمی‌شود

    std::cout << "\n--- بازگرداندن reference ---\n";
    int left = 1;
    int right = 2;
    int& chosen = pickLarger(left, right);
    chosen = 99;
    std::cout << "left = " << left << ", right = " << right << "\n";

    // نکتهٔ مهم: reference باید همان لحظه مقدار بگیرد و بعداً به متغیر دیگری
    // «چسبانده» نمی‌شود؛ این کار مقدار را کپی می‌کند نه اتصال را:
    //   int& other = left;
    //   other = right;      → مقدار right داخل left نوشته می‌شود

    return 0;
}
