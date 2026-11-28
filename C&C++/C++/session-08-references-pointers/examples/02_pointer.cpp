// 02_pointer.cpp — pointer: آدرس ذخیره‌شده و مدیریت صریح
// کامپایل: g++ -std=c++17 -Wall -Wextra -o pointer examples/02_pointer.cpp

#include <iostream>

// پارامتر pointer: می‌تواند nullptr باشد، پس باید بررسی شود
void addBonus(int* score, int bonus) {
    if (score == nullptr) {        // همیشه بررسی کنید
        std::cout << "امتیاز داده نشده است.\n";
        return;
    }
    *score += bonus;
}

int main() {
    int value = 42;
    int* ptr = &value;             // ptr آدرس value را نگه می‌دارد

    std::cout << "value      = " << value << "\n";
    std::cout << "*ptr       = " << *ptr << "\n";        // محتوای آدرس
    std::cout << "ptr == &value؟ " << (ptr == &value ? "بله" : "خیر") << "\n";

    *ptr = 100;                    // نوشتن از طریق pointer
    std::cout << "بعد از *ptr = 100 → value = " << value << "\n";

    std::cout << "\n--- nullptr ---\n";
    int* nothing = nullptr;        // pointer بدون شیء؛ هرگز مقدار تصادفی نگذارید
    if (nothing == nullptr) {
        std::cout << "nothing به هیچ شیئی اشاره نمی‌کند.\n";
    }
    if (!nothing) {                // همان بررسی، کوتاه‌تر
        std::cout << "و در شرط، nullptr مثل false رفتار می‌کند.\n";
    }
    // *nothing = 5;   → رفتار تعریف‌نشده (crash) — هرگز!

    std::cout << "\n--- pointer به‌عنوان پارامتر ---\n";
    int score = 50;
    addBonus(&score, 10);
    std::cout << "score = " << score << "\n";
    addBonus(nullptr, 10);         // اینجا بررسی nullptr نجات‌مان می‌دهد

    std::cout << "\n--- const و pointer ---\n";
    int fixed = 8;
    const int* toConst = &fixed;   // می‌تواند آدرس عوض کند، ولی مقدار را نمی‌شود تغییر داد
    std::cout << "*toConst = " << *toConst << "\n";
    // *toConst = 9;   → خطای کامپایل

    int* const constPtr = &fixed;  // آدرس ثابت است، مقدار را می‌شود تغییر داد
    *constPtr = 9;
    std::cout << "fixed = " << fixed << "\n";
    // constPtr = nullptr;   → خطای کامپایل

    // خواندن این دو اعلان از راست به چپ:
    //   const int* p   → pointer به int ثابت
    //   int* const p   → pointer ثابت به int

    std::cout << "\n--- پیمایش با pointer (آرایه در جلسهٔ ۹) ---\n";
    int numbers[3] = {10, 20, 30};
    int* cursor = numbers;         // نام آرایه به اولین عنصر تبدیل می‌شود
    for (int i = 0; i < 3; ++i) {
        std::cout << "عنصر " << i << " = " << *cursor << "\n";
        ++cursor;                  // یک عنصر جلو می‌رود (به اندازهٔ sizeof(int))
    }

    return 0;
}
