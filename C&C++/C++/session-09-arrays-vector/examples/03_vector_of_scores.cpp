// 03_vector_of_scores.cpp — آمار نمرات با vector
// کامپایل: g++ -std=c++17 -Wall -Wextra -o scores examples/03_vector_of_scores.cpp

#include <iomanip>
#include <iostream>
#include <string>
#include <vector>

int main() {
    // دو vector هم‌طول: نام و نمره
    const std::vector<std::string> names{"Ali", "Sara", "Reza", "Mina", "Omid"};
    const std::vector<double> scores{18.5, 12.0, 9.75, 20.0, 15.25};

    if (names.size() != scores.size()) {
        std::cerr << "داده‌ها هم‌طول نیستند.\n";
        return 1;
    }
    if (scores.empty()) {
        std::cout << "هیچ نمره‌ای ثبت نشده است.\n";
        return 0;
    }

    // مجموع
    double sum = 0.0;
    for (const double& score : scores) {
        sum += score;
    }
    const double average = sum / static_cast<double>(scores.size());

    // کمینه و بیشینه با ردیابی اندیس
    std::size_t bestIndex = 0;
    std::size_t worstIndex = 0;
    for (std::size_t i = 1; i < scores.size(); ++i) {
        if (scores[i] > scores[bestIndex]) {
            bestIndex = i;
        }
        if (scores[i] < scores[worstIndex]) {
            worstIndex = i;
        }
    }

    // شمارش قبول‌ها (نمرهٔ ۱۰ به بالا)
    int passed = 0;
    for (const double& score : scores) {
        if (score >= 10.0) {
            ++passed;
        }
    }

    std::cout << std::fixed << std::setprecision(2);
    std::cout << "--- کارنامه ---\n";
    for (std::size_t i = 0; i < names.size(); ++i) {
        std::cout << std::left << std::setw(6) << names[i]
                  << std::right << std::setw(7) << scores[i]
                  << (scores[i] >= 10.0 ? "  قبول" : "  مردود") << "\n";
    }

    std::cout << "\nمجموع      : " << sum << "\n";
    std::cout << "میانگین    : " << average << "\n";
    std::cout << "بیشترین    : " << scores[bestIndex] << " (" << names[bestIndex] << ")\n";
    std::cout << "کمترین     : " << scores[worstIndex] << " (" << names[worstIndex] << ")\n";
    std::cout << "قبولی‌ها    : " << passed << " از " << scores.size() << "\n";

    return 0;
}
