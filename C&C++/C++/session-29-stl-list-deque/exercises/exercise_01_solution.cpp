// exercise_01_solution.cpp — راه‌حل تمرین ۱: صف دوطرفهٔ کارها
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o tasks exercises/exercise_01_solution.cpp

#include <deque>
#include <iostream>
#include <string>

void print(const std::deque<std::string>& tasks) {
    std::cout << "صف: [";
    bool first = true;
    for (const auto& task : tasks) {
        if (!first) {
            std::cout << ", ";
        }
        std::cout << task;
        first = false;
    }
    std::cout << "]\n";
}

int main() {
    std::deque<std::string> tasks;

    // کارهای معمولی: انتهای صف
    tasks.push_back("گزارش");
    tasks.push_back("ایمیل");

    // کارهای فوری: ابتدای صف
    tasks.push_front("جلسه");
    tasks.push_front("طراحی");

    print(tasks);

    while (!tasks.empty()) {
        std::cout << "پردازش: " << tasks.front() << "\n";
        tasks.pop_front();
    }

    std::cout << "باقی‌مانده: " << tasks.size()
              << " (empty = " << (tasks.empty() ? "بله" : "خیر") << ")\n";

    return 0;
}
