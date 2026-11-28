// exercise_01_solution.cpp — راه‌حل تمرین ۱: انتخاب کانتینر مناسب
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o choose exercises/exercise_01_solution.cpp

#include <deque>
#include <iostream>
#include <map>
#include <set>
#include <string>
#include <vector>

int main() {
    // ۱) یکتایی مهم است و ترتیب خاصی لازم نیست → set
    std::set<int> uniqueIds{101, 205, 101, 300, 205};
    std::cout << "۱) set → شناسه‌های یکتا: ";
    for (int id : uniqueIds) {
        std::cout << id << ' ';
    }
    std::cout << "(تعداد: " << uniqueIds.size() << ")\n";

    // ۲) نگاشت کلید→شمارنده → map
    std::map<std::string, int> counts;
    const std::vector<std::string> words{"cpp", "stl", "cpp", "map", "cpp"};
    for (const std::string& word : words) {
        ++counts[word];
    }
    std::cout << "۲) map → شمارش کلمات:\n";
    for (const auto& [word, count] : counts) {
        std::cout << "   " << word << " = " << count << "\n";
    }

    // ۳) دسترسی تصادفی + افزودن در انتها → vector
    std::vector<int> scores{70, 85, 90};
    scores.push_back(100);
    std::cout << "۳) vector → نمرات: ";
    for (int score : scores) {
        std::cout << score << ' ';
    }
    std::cout << "| scores[2] = " << scores[2] << "\n";

    // ۴) درج/حذف از دو سر → deque
    std::deque<std::string> tasks;
    tasks.push_back("کامپایل");
    tasks.push_back("تست");
    tasks.push_front("طراحی");
    std::cout << "۴) deque → ترتیب کارها: ";
    for (const auto& task : tasks) {
        std::cout << task << ' ';
    }
    std::cout << "| بعدی: " << tasks.front() << "\n";

    return 0;
}
