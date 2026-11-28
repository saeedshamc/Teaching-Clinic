// exercise_01_solution.cpp — راه‌حل تمرین ۱: آمار نمرات
// کامپایل: g++ -std=c++17 -Wall -Wextra -o stats exercises/exercise_01_solution.cpp

#include <iomanip>
#include <iostream>
#include <vector>

int main() {
    int count = 0;
    std::cout << "چند نمره؟ ";
    if (!(std::cin >> count)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }
    if (count <= 0) {
        std::cout << "نمره‌ای برای بررسی وجود ندارد.\n";
        return 0;
    }

    std::vector<double> scores;
    scores.reserve(static_cast<std::size_t>(count));

    std::cout << "نمره‌ها: ";
    for (int i = 0; i < count; ++i) {
        double score = 0.0;
        if (!(std::cin >> score)) {
            std::cerr << "ورودی نامعتبر است.\n";
            return 1;
        }
        scores.push_back(score);
    }

    double sum = 0.0;
    double minimum = scores[0];
    double maximum = scores[0];
    int passed = 0;

    for (const double& score : scores) {
        sum += score;
        if (score < minimum) {
            minimum = score;
        }
        if (score > maximum) {
            maximum = score;
        }
        if (score >= 10.0) {
            ++passed;
        }
    }

    std::cout << std::fixed << std::setprecision(2);
    std::cout << "تعداد: " << scores.size() << "\n";
    std::cout << "مجموع: " << sum << "\n";
    std::cout << "میانگین: " << sum / static_cast<double>(scores.size()) << "\n";
    std::cout << "کمینه: " << minimum << "\n";
    std::cout << "بیشینه: " << maximum << "\n";
    std::cout << "قبولی‌ها: " << passed << "\n";

    return 0;
}
