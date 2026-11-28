// 02_queue.cpp — queue: اولین ورودی، اولین خروجی (FIFO)
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o queue_example examples/02_queue.cpp

#include <iostream>
#include <queue>
#include <string>

int main() {
    std::queue<std::string> jobs;
    jobs.push("کامپایل");
    jobs.push("تست");
    jobs.push("بسته‌بندی");

    std::cout << "--- صف (FIFO) ---\n";
    std::cout << "  front = " << jobs.front()
              << "، back = " << jobs.back()
              << "، size = " << jobs.size() << "\n";

    int step = 0;
    while (!jobs.empty()) {
        ++step;
        std::cout << "  " << step << ") " << jobs.front() << "\n";
        jobs.pop();
    }
    std::cout << "  صف خالی است: " << (jobs.empty() ? "بله" : "خیر") << "\n";

    std::cout << "queue هم تکرارگر ندارد؛ فقط front/back و pop.\n";
    std::cout << "پیش‌فرض روی deque ساخته می‌شود و می‌توان روی list هم ساختش.\n";

    return 0;
}
