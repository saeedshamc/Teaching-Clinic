// 02_while_do.cpp — while برای اعتبارسنجی، do-while برای منو
// کامپایل: g++ -std=c++17 -Wall -Wextra -o whileloop examples/02_while_do.cpp

#include <iostream>

int main() {
    // --- while: تکرار تا وقتی ورودی معتبر شود ---
    int positive = 0;
    while (true) {
        std::cout << "یک عدد مثبت وارد کنید: ";
        if (!(std::cin >> positive)) {   // ورودی تمام شد یا نامعتبر بود
            std::cerr << "\nورودی نامعتبر.\n";
            return 1;
        }
        if (positive > 0) {
            break;                        // معتبر است، از حلقه بیرون بیا
        }
        std::cout << "باید بزرگ‌تر از صفر باشد، دوباره تلاش کنید.\n";
    }
    std::cout << "عدد پذیرفته‌شده: " << positive << "\n";

    // --- while: شمارش تا رسیدن به شرط ---
    int value = 1;
    int steps = 0;
    while (value < 100) {
        value *= 2;
        ++steps;
    }
    std::cout << "با " << steps << " بار دو برابر کردن به " << value << " رسیدیم.\n";

    // --- do-while: منو، حداقل یک‌بار نشان داده می‌شود ---
    int choice = 0;
    int total = 0;
    do {
        std::cout << "\n1) افزودن عدد  2) نمایش جمع  3) خروج\n";
        std::cout << "انتخاب: ";
        if (!(std::cin >> choice)) {
            break;   // ورودی تمام شد؛ از منو خارج شو
        }

        if (choice == 1) {
            int number = 0;
            std::cout << "عدد: ";
            if (!(std::cin >> number)) {
                break;
            }
            total += number;
            std::cout << "جمع فعلی: " << total << "\n";
        } else if (choice == 2) {
            std::cout << "جمع = " << total << "\n";
        } else if (choice != 3) {
            std::cout << "گزینهٔ نامعتبر.\n";
        }
    } while (choice != 3);

    std::cout << "خروج از منو. جمع نهایی = " << total << "\n";

    return 0;
}
