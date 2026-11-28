// 01_lambda_basics.cpp — لامبدا: تابع بی‌نام که همین‌جا استفاده می‌شود
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o lambda_basics examples/01_lambda_basics.cpp

#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

int main() {
    std::vector<int> values{5, 2, 8, 1, 9, 4};

    std::cout << "--- sort با لامبدا (نزولی) ---\n  ";
    std::sort(values.begin(), values.end(), [](int left, int right) {
        return left > right;
    });
    for (int value : values) {
        std::cout << value << ' ';
    }
    std::cout << "\n";

    std::cout << "--- for_each ---\n  ";
    std::for_each(values.begin(), values.end(), [](int value) {
        std::cout << value << ' ';
    });
    std::cout << "\n";

    std::cout << "--- find_if ---\n";
    const auto small = std::find_if(values.begin(), values.end(), [](int value) {
        return value < 5;
    });
    if (small != values.end()) {
        std::cout << "  اولین کمتر از 5: " << *small << "\n";
    }

    std::cout << "--- count_if ---\n  زوج‌ها: "
              << std::count_if(values.begin(), values.end(), [](int value) {
                     return value % 2 == 0;
                 })
              << "\n";

    std::cout << "--- لامبدا در متغیر ---\n";
    const auto square = [](int value) {
        return value * value;
    };
    std::cout << "  square(7) = " << square(7) << "\n";

    std::cout << "--- فراخوانی فوری ---\n";
    std::cout << "  (3 + 4) = " << [](int left, int right) { return left + right; }(3, 4) << "\n";

    std::cout << "--- لامبدای جنریک (پارامتر auto) ---\n";
    const auto twice = [](const auto& value) {
        return value + value;
    };
    std::cout << "  twice(21) = " << twice(21)
              << "، twice(\"ab\") = " << twice(std::string("ab")) << "\n";

    std::cout << "لامبدا یک شیء است: می‌توان آن را در متغیر گذاشت و صدا زد.\n";
    return 0;
}
