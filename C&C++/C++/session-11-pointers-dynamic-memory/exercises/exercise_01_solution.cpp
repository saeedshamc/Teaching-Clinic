// exercise_01_solution.cpp — راه‌حل تمرین ۱: آرایهٔ پویا و آزادسازی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o dynamic_stats exercises/exercise_01_solution.cpp

#include <iostream>

int main() {
    int count = 0;
    std::cout << "چند عدد؟ ";
    if (!(std::cin >> count)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }
    if (count <= 0) {
        std::cout << "تعداد باید مثبت باشد.\n";
        return 0;
    }

    const std::size_t size = static_cast<std::size_t>(count);
    int* numbers = new int[size];

    std::cout << "عددها: ";
    for (std::size_t i = 0; i < size; ++i) {
        if (!(std::cin >> numbers[i])) {
            std::cerr << "ورودی نامعتبر است.\n";
            delete[] numbers;          // آزادسازی در همهٔ مسیرهای خروج
            return 1;
        }
    }

    long long sum = 0;
    for (std::size_t i = 0; i < size; ++i) {
        sum += numbers[i];
    }

    std::cout << "مجموع: " << sum << "\n";
    std::cout << "میانگین: " << static_cast<double>(sum) / static_cast<double>(size) << "\n";

    delete[] numbers;
    numbers = nullptr;

    return 0;
}
