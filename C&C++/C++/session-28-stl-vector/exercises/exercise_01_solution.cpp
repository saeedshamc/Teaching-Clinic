// exercise_01_solution.cpp — راه‌حل تمرین ۱: حذف اعداد زوج
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o evens exercises/exercise_01_solution.cpp

#include <iostream>
#include <vector>

void print(const std::vector<int>& data) {
    std::cout << "[";
    for (std::size_t i = 0; i < data.size(); ++i) {
        if (i > 0) {
            std::cout << ", ";
        }
        std::cout << data[i];
    }
    std::cout << "]\n";
}

int removeEvens(std::vector<int>& data) {
    int removed = 0;
    for (auto it = data.begin(); it != data.end();) {
        if (*it % 2 == 0) {
            it = data.erase(it);   // عنصر حذف شد؛ it به عنصر بعدی اشاره می‌کند
            ++removed;
        } else {
            ++it;
        }
    }
    return removed;
}

int main() {
    std::vector<int> values{3, 8, 12, 5, 7, 10, 21, 4};

    std::cout << "قبل: ";
    print(values);

    const int removed = removeEvens(values);
    std::cout << "حذف‌شده: " << removed << "\n";

    std::cout << "بعد: ";
    print(values);

    values.shrink_to_fit();
    std::cout << "capacity بعد از shrink_to_fit: " << values.capacity() << "\n";

    return 0;
}
