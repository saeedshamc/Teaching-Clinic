// exercise_01_solution.cpp — راه‌حل تمرین ۱: شمارش کلمات یک جمله
// کامپایل: g++ -std=c++17 -Wall -Wextra -o word_count exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>

int main() {
    std::cout << "جمله: ";
    std::string line;
    std::getline(std::cin, line);

    int words = 0;
    std::size_t longest = 0;
    std::size_t currentLength = 0;
    bool insideWord = false;

    for (const char& ch : line) {
        const bool isSpace = (ch == ' ' || ch == '\t');

        if (isSpace) {
            insideWord = false;
            currentLength = 0;
            continue;
        }

        ++currentLength;
        if (currentLength > longest) {
            longest = currentLength;
        }
        if (!insideWord) {
            insideWord = true;
            ++words;
        }
    }

    std::cout << "تعداد کلمات: " << words << "\n";
    if (words > 0) {
        std::cout << "طولانی‌ترین کلمه: " << longest << " کاراکتر\n";
    }

    return 0;
}
