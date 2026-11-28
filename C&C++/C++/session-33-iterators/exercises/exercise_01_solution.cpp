// exercise_01_solution.cpp — راه‌حل تمرین ۱: پیمایش معکوس
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o reverse_iter exercises/exercise_01_solution.cpp

#include <iostream>
#include <iterator>
#include <vector>

void printReverse(const std::vector<int>& data) {
    std::cout << "معکوس: ";
    for (auto rit = data.rbegin(); rit != data.rend(); ++rit) {
        std::cout << *rit << ' ';
    }
    std::cout << "\n";
}

int lastGreater(const std::vector<int>& data, int threshold) {
    for (auto rit = data.rbegin(); rit != data.rend(); ++rit) {
        if (*rit > threshold) {
            return static_cast<int>(std::distance(data.begin(), rit.base())) - 1;
        }
    }
    return -1;
}

int main() {
    const std::vector<int> values{1, 2, 3, 4, 5};

    printReverse(values);

    const int index = lastGreater(values, 3);
    std::cout << "آخرین بزرگ‌تر از 3 → اندیس " << index
              << " (مقدار " << values[static_cast<std::size_t>(index)] << ")\n";

    std::cout << "آخرین بزرگ‌تر از 10 → "
              << (lastGreater(values, 10) == -1 ? "پیدا نشد (-1)" : "پیدا شد") << "\n";

    std::cout << "distance(rbegin, rend) = "
              << std::distance(values.rbegin(), values.rend()) << "\n";

    return 0;
}
