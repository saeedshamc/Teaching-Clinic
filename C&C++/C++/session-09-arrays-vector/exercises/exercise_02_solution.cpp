// exercise_02_solution.cpp — راه‌حل تمرین ۲: دسترسی امن با at
// کامپایل: g++ -std=c++17 -Wall -Wextra -o safe_at exercises/exercise_02_solution.cpp

#include <iostream>
#include <stdexcept>
#include <vector>

bool isValidIndex(const std::vector<int>& data, int index) {
    if (index < 0) {
        return false;
    }
    return static_cast<std::size_t>(index) < data.size();
}

int main() {
    const std::vector<int> data{3, 8, 15, 22};

    int index = 0;
    std::cout << "اندیس: ";
    if (!(std::cin >> index)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }

    if (isValidIndex(data, index)) {
        std::cout << "مقدار: " << data.at(static_cast<std::size_t>(index)) << "\n";
    } else {
        std::cout << "اندیس نامعتبر است.\n";
    }

    std::cout << "---\n";

    index = 0;
    std::cout << "اندیس: ";
    if (!(std::cin >> index)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }

    if (isValidIndex(data, index)) {
        std::cout << "مقدار: " << data.at(static_cast<std::size_t>(index)) << "\n";
    } else {
        std::cout << "اندیس نامعتبر است.\n";
    }

    // at() خودش هم بررسی می‌کند؛ در اینجا عمداً از محدوده بیرون می‌رویم
    std::cout << "---\n";
    try {
        std::cout << "مقدار: " << data.at(50) << "\n";
    } catch (const std::out_of_range& error) {
        std::cout << "at(50) استثنا داد: " << error.what() << "\n";
    }

    // برخلاف at()، عملگر [] هیچ بررسی‌ای نمی‌کند و رفتار تعریف‌نشده دارد:
    //   data[50]   → احتمالاً مقدار بی‌معنی، شاید crash

    return 0;
}
