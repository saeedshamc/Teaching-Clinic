// 03_number_string.cpp — تبدیل عدد و رشته
// کامپایل: g++ -std=c++17 -Wall -Wextra -o number_string examples/03_number_string.cpp

#include <iomanip>
#include <iostream>
#include <string>

int main() {
    // --- عدد → رشته ---
    const int age = 42;
    const double pi = 3.14159;
    const long long big = 9000000000LL;

    std::cout << "std::to_string(42)      = " << std::to_string(age) << "\n";
    std::cout << "std::to_string(3.14159) = " << std::to_string(pi)
              << "  (۶ رقم اعشار پیش‌فرض)\n";
    std::cout << "std::to_string(big)     = " << std::to_string(big) << "\n";

    // با to_string نمی‌توان تعداد اعشار و قالب را کنترل کرد؛ برای آن از stringstream
    // یا std::format (C++20) استفاده می‌شود. اینجا خروجی با cout قالب می‌دهیم:
    std::cout << std::fixed << std::setprecision(2)
              << "با cout: " << pi << "\n";

    // --- رشته → عدد ---
    const std::string textNumber = "1234";
    const int parsed = std::stoi(textNumber);
    std::cout << "\nstd::stoi(\"1234\") = " << parsed
              << " ، دو برابر = " << parsed * 2 << "\n";

    // std::stod برای اعشاری
    const std::string textDouble = "2.75";
    std::cout << "std::stod(\"2.75\") * 4 = " << std::stod(textDouble) * 4.0 << "\n";

    // stoi از ابتدای رشته می‌خواند و بقیه را نادیده می‌گیرد
    const std::string mixed = "42abc";
    std::cout << "std::stoi(\"42abc\") = " << std::stoi(mixed) << "\n";

    // --- خطاها: استثنا می‌دهند ---
    try {
        const int bad = std::stoi("abc");
        std::cout << bad << "\n";
    } catch (const std::invalid_argument& error) {
        std::cout << "\nstoi(\"abc\") → invalid_argument: " << error.what() << "\n";
    }

    try {
        const int tooBig = std::stoi("99999999999999999999");
        std::cout << tooBig << "\n";
    } catch (const std::out_of_range& error) {
        std::cout << "stoi(عدد بسیار بزرگ) → out_of_range: " << error.what() << "\n";
    }

    // --- اعتبارسنجی قبل از تبدیل: فقط رقم‌ها مجاز باشند ---
    const std::string input = "2026";
    bool allDigits = !input.empty();
    for (const char& ch : input) {
        if (ch < '0' || ch > '9') {
            allDigits = false;
        }
    }
    std::cout << "\n\"" << input << "\" فقط رقم است؟ "
              << (allDigits ? "بله" : "خیر") << "\n";

    // --- ساخت رشته با اعداد در حلقه ---
    std::string report;
    for (int i = 1; i <= 3; ++i) {
        report += "بخش" + std::to_string(i);
        if (i < 3) {
            report += " ، ";
        }
    }
    std::cout << "report = " << report << "\n";

    return 0;
}
