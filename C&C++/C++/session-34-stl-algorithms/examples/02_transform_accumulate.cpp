// 02_transform_accumulate.cpp — transform، accumulate، count_if
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o transform_acc examples/02_transform_accumulate.cpp

#include <algorithm>
#include <cctype>
#include <functional>
#include <iostream>
#include <numeric>
#include <string>
#include <vector>

int doubled(int value) {
    return value * 2;
}

char toUpperChar(char letter) {
    return static_cast<char>(std::toupper(static_cast<unsigned char>(letter)));
}

bool isPositive(int value) {
    return value > 0;
}

int main() {
    const std::vector<int> values{1, 2, 3, 4, 5};

    std::vector<int> doubledValues(values.size());
    std::transform(values.begin(), values.end(), doubledValues.begin(), doubled);
    std::cout << "--- transform ---\n  ";
    for (int value : doubledValues) {
        std::cout << value << ' ';
    }
    std::cout << "\n";

    std::cout << "--- accumulate ---\n";
    const int sum = std::accumulate(values.begin(), values.end(), 0);
    const int product = std::accumulate(values.begin(), values.end(), 1, std::multiplies<int>());
    std::cout << "  مجموع = " << sum << "، حاصل‌ضرب = " << product << "\n";

    const double average = static_cast<double>(sum) / static_cast<double>(values.size());
    std::cout << "  میانگین = " << average << "\n";

    std::cout << "--- transform روی رشته ---\n";
    std::string text = "hello stl";
    std::transform(text.begin(), text.end(), text.begin(), toUpperChar);
    std::cout << "  " << text << "\n";

    std::cout << "--- transform درجا ---\n";
    std::vector<int> inPlace{1, -2, 3, -4};
    std::transform(inPlace.begin(), inPlace.end(), inPlace.begin(), doubled);
    std::cout << "  ";
    for (int value : inPlace) {
        std::cout << value << ' ';
    }
    std::cout << "\n";

    std::cout << "--- count_if ---\n";
    std::cout << "  مثبت‌ها: " << std::count_if(inPlace.begin(), inPlace.end(), isPositive) << "\n";

    // توجه: accumulate با مقدار شروع ۰ روی double مشکل دقت دارد
    const std::vector<double> prices{0.1, 0.2, 0.3};
    const double wrong = std::accumulate(prices.begin(), prices.end(), 0);
    const double right = std::accumulate(prices.begin(), prices.end(), 0.0);
    std::cout << "--- دقت accumulate ---\n";
    std::cout << "  با شروع int: " << wrong << "، با شروع double: " << right << "\n";

    return 0;
}
