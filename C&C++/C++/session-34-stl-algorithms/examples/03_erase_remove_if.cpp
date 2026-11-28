// 03_erase_remove_if.cpp — چرا remove_if به‌تنهایی حذف نمی‌کند
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o erase_remove examples/03_erase_remove_if.cpp

#include <algorithm>
#include <cctype>
#include <iostream>
#include <string>
#include <vector>

bool isNegative(int value) {
    return value < 0;
}

bool isPositive(int value) {
    return value > 0;
}

bool isSpace(char letter) {
    return std::isspace(static_cast<unsigned char>(letter)) != 0;
}

void print(const std::string& label, const std::vector<int>& data) {
    std::cout << "  " << label << " [";
    for (std::size_t i = 0; i < data.size(); ++i) {
        if (i > 0) {
            std::cout << ", ";
        }
        std::cout << data[i];
    }
    std::cout << "]  size = " << data.size() << "\n";
}

int main() {
    std::vector<int> values{5, -3, 8, -1, 0, 12, -7};
    print("قبل:", values);

    const auto newEnd = std::remove_if(values.begin(), values.end(), isNegative);
    std::cout << "--- بعد از remove_if، قبل از erase ---\n";
    print("داده:", values);
    std::cout << "  عناصر منطقی: " << std::distance(values.begin(), newEnd) << "\n";

    values.erase(newEnd, values.end());
    std::cout << "--- بعد از erase ---\n";
    print("داده:", values);

    std::cout << "--- all_of / any_of / none_of ---\n";
    std::cout << "  all_of(>0): " << (std::all_of(values.begin(), values.end(), isPositive) ? "بله" : "خیر") << "\n";
    std::cout << "  any_of(>0): " << (std::any_of(values.begin(), values.end(), isPositive) ? "بله" : "خیر") << "\n";
    std::cout << "  none_of(>0): " << (std::none_of(values.begin(), values.end(), isPositive) ? "بله" : "خیر") << "\n";

    std::cout << "--- remove_if روی رشته ---\n";
    std::string text = "Saeed Sham";
    text.erase(std::remove_if(text.begin(), text.end(), isSpace), text.end());
    std::cout << "  بدون فاصله: " << text << "\n";

    std::cout << "remove_if فقط عناصر را جابه‌جا می‌کند؛ حذف واقعی کار erase است.\n";
    return 0;
}
