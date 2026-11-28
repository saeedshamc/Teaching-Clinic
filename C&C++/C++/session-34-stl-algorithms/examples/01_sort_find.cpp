// 01_sort_find.cpp — sort، find، find_if، binary_search
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o sort_find examples/01_sort_find.cpp

#include <algorithm>
#include <functional>
#include <iostream>
#include <iterator>
#include <string>
#include <vector>

bool isEven(int value) {
    return value % 2 == 0;
}

void print(const std::string& label, const std::vector<int>& data) {
    std::cout << "  " << label << ": ";
    for (int value : data) {
        std::cout << value << ' ';
    }
    std::cout << "\n";
}

int main() {
    std::vector<int> values{42, 7, 19, 8, 23, 4, 16};
    print("قبل", values);

    std::sort(values.begin(), values.end());
    print("صعودی", values);

    std::sort(values.begin(), values.end(), std::greater<int>());
    print("نزولی", values);

    std::sort(values.begin(), values.end());

    std::cout << "--- find ---\n";
    const auto it19 = std::find(values.begin(), values.end(), 19);
    if (it19 != values.end()) {
        std::cout << "  19 در اندیس " << std::distance(values.begin(), it19) << "\n";
    }
    if (std::find(values.begin(), values.end(), 100) == values.end()) {
        std::cout << "  100 پیدا نشد\n";
    }

    std::cout << "--- find_if ---\n";
    const auto firstEven = std::find_if(values.begin(), values.end(), isEven);
    if (firstEven != values.end()) {
        std::cout << "  اولین زوج: " << *firstEven
                  << " در اندیس " << std::distance(values.begin(), firstEven) << "\n";
    }

    std::cout << "--- count و count_if ---\n";
    std::cout << "  count(8) = " << std::count(values.begin(), values.end(), 8) << "\n";
    std::cout << "  count_if(isEven) = "
              << std::count_if(values.begin(), values.end(), isEven) << "\n";

    std::cout << "--- binary_search روی دادهٔ مرتب ---\n";
    std::cout << "  binary_search(23): "
              << (std::binary_search(values.begin(), values.end(), 23) ? "هست" : "نیست") << "\n";
    std::cout << "  binary_search(50): "
              << (std::binary_search(values.begin(), values.end(), 50) ? "هست" : "نیست") << "\n";

    const auto bounds = std::equal_range(values.begin(), values.end(), 16);
    std::cout << "  equal_range(16): از اندیس " << std::distance(values.begin(), bounds.first)
              << " تا " << std::distance(values.begin(), bounds.second) << "\n";

    std::cout << "--- min و max ---\n";
    std::cout << "  min = " << *std::min_element(values.begin(), values.end())
              << "، max = " << *std::max_element(values.begin(), values.end()) << "\n";

    return 0;
}
