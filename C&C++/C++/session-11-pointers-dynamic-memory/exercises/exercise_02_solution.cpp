// exercise_02_solution.cpp — راه‌حل تمرین ۲: تابعی که حافظه برمی‌گرداند
// کامپایل: g++ -std=c++17 -Wall -Wextra -o sequence exercises/exercise_02_solution.cpp

#include <iostream>

int* makeSequence(int count, int start, int step) {
    if (count <= 0) {
        return nullptr;
    }
    int* data = new int[static_cast<std::size_t>(count)];
    for (int i = 0; i < count; ++i) {
        data[i] = start + (i * step);
    }
    return data;
}

int main() {
    // --- سناریوی ۱ ---
    const int count = 5;
    int* sequence = makeSequence(count, 10, 3);
    if (sequence != nullptr) {
        std::cout << "دنباله: ";
        for (int i = 0; i < count; ++i) {
            std::cout << sequence[i] << " ";
        }
        std::cout << "\n";
        delete[] sequence;
        sequence = nullptr;
    }

    // --- سناریوی ۲ ---
    int* empty = makeSequence(0, 1, 1);
    std::cout << "makeSequence(0, ...) == nullptr ؟ "
              << (empty == nullptr ? "بله" : "خیر") << "\n";
    // برای nullptr نیازی به delete نیست؛ delete nullptr هم بی‌خطر است
    delete[] empty;
    empty = nullptr;

    return 0;
}
