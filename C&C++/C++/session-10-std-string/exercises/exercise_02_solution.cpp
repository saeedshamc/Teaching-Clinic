// exercise_02_solution.cpp — راه‌حل تمرین ۲: بررسی پالیندروم
// کامپایل: g++ -std=c++17 -Wall -Wextra -o palindrome exercises/exercise_02_solution.cpp

#include <cctype>
#include <iostream>
#include <string>

bool isPalindrome(const std::string& text) {
    std::string clean;
    clean.reserve(text.length());

    for (const char& ch : text) {
        const unsigned char raw = static_cast<unsigned char>(ch);
        if (std::isalnum(raw) != 0) {
            clean.push_back(static_cast<char>(std::tolower(raw)));
        }
    }

    if (clean.empty()) {
        return false;
    }

    std::size_t left = 0;
    std::size_t right = clean.length() - 1;
    while (left < right) {
        if (clean[left] != clean[right]) {
            return false;
        }
        ++left;
        --right;
    }
    return true;
}

int main() {
    std::cout << "متن: ";
    std::string line;
    std::getline(std::cin, line);

    if (line.empty()) {
        std::cout << "متنی وارد نشده است.\n";
        return 0;
    }

    if (isPalindrome(line)) {
        std::cout << "پالیندروم است.\n";
    } else {
        std::cout << "پالیندروم نیست.\n";
    }

    return 0;
}
