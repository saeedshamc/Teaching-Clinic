// 01_string_basics.cpp — std::string: ساخت، اتصال، طول، دسترسی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o string_basics examples/01_string_basics.cpp

#include <iostream>
#include <string>

int main() {
    // --- ساخت و مقداردهی ---
    std::string first = "Hello";
    std::string second = "C++";
    std::string empty;
    std::string repeated(3, '*');          // "***"

    std::cout << "first = " << first << "\n";
    std::cout << "repeated = " << repeated << "\n";
    std::cout << "empty.empty() = " << (empty.empty() ? "true" : "false") << "\n";

    // --- اتصال ---
    std::string greeting = first + ", " + second + "!";   // فقط با std::string
    std::cout << "\ngreeting = " << greeting << "\n";
    std::cout << "length = " << greeting.length()
              << " ، size = " << greeting.size() << "\n";

    // --- دسترسی به کاراکترها ---
    std::cout << "\nfront = " << greeting.front()
              << " ، back = " << greeting.back()
              << " ، at(1) = " << greeting.at(1) << "\n";

    greeting[0] = 'h';                     // تغییر درجا
    std::cout << "بعد از تغییر: " << greeting << "\n";

    // --- افزودن ---
    std::string name = "Saeed";
    name += " Ahmadi";                     // operator+=
    name.append(" (C++)");                 // متد append
    name.push_back('!');                   // یک کاراکتر
    std::cout << "\nname = " << name << "\n";
    std::cout << "length = " << name.length() << "\n";

    // --- برش ---
    std::cout << "\nsubstr(0, 5)  = " << name.substr(0, 5) << "\n";
    std::cout << "substr(6)     = " << name.substr(6) << "\n";

    // --- مقایسه ---
    std::string a = "apple";
    std::string b = "banana";
    std::cout << "\napple < banana ؟ " << (a < b ? "بله" : "خیر") << "\n";
    std::cout << "a == \"apple\" ؟ " << (a == "apple" ? "بله" : "خیر") << "\n";

    // --- پیمایش ---
    int letters = 0;
    for (const char& ch : name) {
        if (ch != ' ' && ch != '(' && ch != ')' && ch != '!') {
            ++letters;
        }
    }
    std::cout << "\nحروف name (بدون فاصله و نشانه) = " << letters << "\n";

    // شمارش حرف مشخص
    std::string sentence = "the quick brown fox jumps over the lazy dog";
    int countT = 0;
    for (const char& ch : sentence) {
        if (ch == 't') {
            ++countT;
        }
    }
    std::cout << "تعداد حرف t در جمله = " << countT << "\n";

    // --- نکتهٔ مهم: length() بایت می‌شمارد، نه حرف ---
    const std::string persian = "سلام";
    std::cout << "\nlength(\"سلام\") = " << persian.length() << " بایت (هر حرف فارسی ۲ بایت)\n";

    return 0;
}
