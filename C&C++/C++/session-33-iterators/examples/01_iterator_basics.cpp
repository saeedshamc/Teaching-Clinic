// 01_iterator_basics.cpp — begin/end، *، ++، distance، next/prev، const_iterator
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o iter_basics examples/01_iterator_basics.cpp

#include <iostream>
#include <iterator>
#include <string>
#include <vector>

int main() {
    std::vector<int> values{10, 20, 30, 40};

    std::cout << "--- پیمایش دستی با تکرارگر ---\n";
    for (auto it = values.begin(); it != values.end(); ++it) {
        std::cout << "  *it = " << *it << "\n";
    }

    std::cout << "--- end یک عنصر بعد از آخر است ---\n";
    std::cout << "  distance = " << std::distance(values.begin(), values.end())
              << "، size = " << values.size() << "\n";

    std::cout << "--- next و prev ---\n";
    auto it = values.begin();
    std::cout << "  *begin = " << *it << "\n";
    it = std::next(it, 2);
    std::cout << "  *next(begin, 2) = " << *it << "\n";
    it = std::prev(it);
    std::cout << "  *prev = " << *it << "\n";

    std::cout << "--- تغییر مقدار از طریق تکرارگر ---\n";
    auto third = values.begin();
    std::advance(third, 2);
    *third = 99;
    std::cout << "  values[2] = " << values[2] << "\n";

    std::cout << "--- const_iterator: فقط خواندن ---\n";
    for (std::vector<int>::const_iterator cit = values.cbegin(); cit != values.cend(); ++cit) {
        std::cout << "  " << *cit;
    }
    std::cout << "\n";

    std::cout << "--- پیمایش معکوس ---\n";
    for (auto rit = values.rbegin(); rit != values.rend(); ++rit) {
        std::cout << "  " << *rit;
    }
    std::cout << "\n";

    std::cout << "--- تکرارگر روی string ---\n";
    const std::string text = "STL";
    for (auto cit = text.begin(); cit != text.end(); ++cit) {
        std::cout << "  " << *cit << "\n";
    }

    return 0;
}
