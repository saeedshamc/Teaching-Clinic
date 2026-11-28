// 02_default_args.cpp — پارامترهای پیش‌فرض
// کامپایل: g++ -std=c++17 -Wall -Wextra -o default_args examples/02_default_args.cpp

#include <iomanip>
#include <iostream>
#include <string>

// پارامترهای پیش‌فرض باید از راست به چپ باشند:
// بعد از اولین پارامتر پیش‌فرض، همهٔ پارامترهای بعدی هم باید پیش‌فرض داشته باشند.
std::string makeGreeting(const std::string& name,
                         const std::string& greeting = "سلام",
                         const std::string& suffix = "!");

// مقدار پیش‌فرض می‌تواند یک عبارت ثابت باشد
void printSeparator(int width = 24, char fill = '-');

// یک پارامتر پیش‌فرض در محاسبهٔ مالی
double withTax(double price, double rate = 0.09);

std::string makeGreeting(const std::string& name,
                         const std::string& greeting,
                         const std::string& suffix) {
    return greeting + " " + name + suffix;
}

void printSeparator(int width, char fill) {
    for (int i = 0; i < width; ++i) {
        std::cout << fill;
    }
    std::cout << "\n";
}

double withTax(double price, double rate) {
    return price * (1.0 + rate);
}

int main() {
    // سه فراخوانی متفاوت، یک تابع
    std::cout << makeGreeting("Saeed") << "\n";
    std::cout << makeGreeting("Saeed", "درود") << "\n";
    std::cout << makeGreeting("Saeed", "درود", ".") << "\n";

    printSeparator();

    printSeparator(40, '=');

    std::cout << std::fixed << std::setprecision(2);
    std::cout << "قیمت بدون مالیات : " << withTax(250000.0, 0.0) << "\n";
    std::cout << "قیمت با مالیات ۹٪ : " << withTax(250000.0) << "\n";
    std::cout << "قیمت با مالیات ۱۵٪: " << withTax(250000.0, 0.15) << "\n";

    printSeparator(40, '-');

    // نمی‌توان پارامتر وسطی را جا انداخت و آخری را داد:
    //   makeGreeting("Saeed", , "?")   → خطای کامپایل
    // راه‌حل: نام پارامتر را با std::nullopt نمی‌شود دور زد؛ یا همه را بدهید
    // یا برای مقدار «خالی» یک قرارداد مشخص (مثلاً رشتهٔ تهی) تعیین کنید.
    std::cout << makeGreeting("Saeed", "سلام", "!") << "\n";

    return 0;
}
