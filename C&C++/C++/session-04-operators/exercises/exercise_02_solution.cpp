// exercise_02_solution.cpp — راه‌حل تمرین ۲: معدل و درصد

#include <iomanip>
#include <iostream>

int main() {
    double score1 = 0.0;
    double score2 = 0.0;
    double score3 = 0.0;

    std::cout << "نمرهٔ اول: ";
    std::cin >> score1;
    std::cout << "نمرهٔ دوم: ";
    std::cin >> score2;
    std::cout << "نمرهٔ سوم: ";
    std::cin >> score3;

    const double total = score1 + score2 + score3;
    const double average = total / 3.0;
    const double maximum = 60.0;                       // 3 × 20
    const double percent = total / maximum * 100.0;

    std::cout << std::fixed << std::setprecision(2);
    std::cout << "جمع = " << total << "\n";
    std::cout << "معدل = " << average << "\n";
    std::cout << std::setprecision(2);
    std::cout << "درصد = " << percent << "%\n";

    return 0;
}
