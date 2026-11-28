// 03_formatting.cpp — قالب‌بندی خروجی با iomanip
// کامپایل: g++ -std=c++17 -Wall -Wextra -o fmt examples/03_formatting.cpp

#include <iomanip>   // setw, fixed, setprecision, setfill, left, right
#include <iostream>
#include <string>

int main() {
    // --- مقدار اعشاری با دو رقم ---
    constexpr double pi = 3.14159265;
    std::cout << "بدون تنظیم: " << pi << "\n";

    std::cout << std::fixed << std::setprecision(2);
    std::cout << "با fixed و precision(2): " << pi << "\n";

    // --- جدول کالاها ---
    struct Item {
        std::string name;
        double price;
        int discount;  // درصد
    };

    const Item items[] = {
        {"کیبورد", 1200.0, 10},
        {"ماوس", 450.5, 5},
        {"مانیتور", 8500.0, 15},
    };

    std::cout << "\n";
    std::cout << std::setw(12) << std::left << "کالا"
              << std::setw(10) << std::right << "قیمت"
              << std::setw(10) << "تخفیف"
              << std::setw(12) << "قیمت نهایی" << "\n";

    std::cout << std::setfill('-') << std::setw(44) << "" << std::setfill(' ') << "\n";

    for (const auto& item : items) {
        const double finalPrice = item.price * (100 - item.discount) / 100.0;

        std::cout << std::setw(12) << std::left << item.name
                  << std::setw(10) << std::right << item.price
                  << std::setw(9) << item.discount << "%"
                  << std::setw(12) << finalPrice << "\n";
    }

    // --- درصد و bool ---
    const int passed = 17;
    const int total = 20;
    std::cout << "\nدرصد قبولی: "
              << std::fixed << std::setprecision(1)
              << (static_cast<double>(passed) / total * 100) << "%\n";

    std::cout << std::boolalpha << "آیا همه قبول شدند؟ " << false << "\n";

    return 0;
}
