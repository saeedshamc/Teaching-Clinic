// exercise_01_solution.cpp — راه‌حل تمرین ۱: فیلتر کردن با لامبدا
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o filter exercises/exercise_01_solution.cpp

#include <algorithm>
#include <functional>
#include <iostream>
#include <iterator>
#include <vector>

std::vector<int> filter(const std::vector<int>& data,
                        const std::function<bool(int)>& predicate) {
    std::vector<int> result;
    for (int value : data) {
        if (predicate(value)) {
            result.push_back(value);
        }
    }
    return result;
}

void print(const std::string& label, const std::vector<int>& data) {
    std::cout << label << ": ";
    for (int value : data) {
        std::cout << value << ' ';
    }
    std::cout << "\n";
}

int main() {
    const std::vector<int> values{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    print("زوج‌ها", filter(values, [](int value) {
        return value % 2 == 0;
    }));

    const int threshold = 6;
    print("بزرگ‌تر از 6", filter(values, [threshold](int value) {
        return value > threshold;
    }));

    print("خارج از [3, 8]", filter(values, [](int value) {
        return value < 3 || value > 8;
    }));

    // نسخهٔ کوتاه‌تر با copy_if
    std::vector<int> odds;
    std::copy_if(values.begin(), values.end(), std::back_inserter(odds), [](int value) {
        return value % 2 != 0;
    });
    print("فرد با copy_if", odds);

    return 0;
}
