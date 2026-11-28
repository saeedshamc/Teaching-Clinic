// exercise_01_solution.cpp — راه‌حل تمرین ۱: رتبه‌بندی نمره

#include <iostream>

int main() {
    int score = 0;
    std::cout << "نمره: ";
    std::cin >> score;

    if (score < 0 || score > 100) {
        std::cerr << "نمرهٔ نامعتبر\n";
        return 1;
    }

    if (score >= 90) {
        std::cout << "رتبه: A\n";
    } else if (score >= 80) {
        std::cout << "رتبه: B\n";
    } else if (score >= 70) {
        std::cout << "رتبه: C\n";
    } else if (score >= 60) {
        std::cout << "رتبه: D\n";
    } else {
        std::cout << "رتبه: F\n";
    }

    return 0;
}
