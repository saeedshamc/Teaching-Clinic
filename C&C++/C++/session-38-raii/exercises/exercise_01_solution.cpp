// exercise_01_solution.cpp — راه‌حل تمرین ۱: کلاس اندازه‌گیری زمان
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o timer exercises/exercise_01_solution.cpp

#include <chrono>
#include <iostream>
#include <string>
#include <thread>

class Timer {
public:
    explicit Timer(const std::string& label)
        : label_(label), start_(std::chrono::steady_clock::now()) {
        std::cout << "    شروع: " << label_ << "\n";
    }

    Timer(const Timer&) = delete;
    Timer& operator=(const Timer&) = delete;

    ~Timer() {
        const auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(
            std::chrono::steady_clock::now() - start_).count();
        std::cout << "    پایان: " << label_ << " → "
                  << (elapsed >= 50 ? "کند" : "سریع") << "\n";
    }

private:
    std::string label_;
    std::chrono::steady_clock::time_point start_;
};

int main() {
    std::cout << "--- عملیات کند ---\n";
    {
        const Timer timer("پردازش سنگین");
        std::this_thread::sleep_for(std::chrono::milliseconds(80));
        std::cout << "    ... کار انجام شد\n";
    }

    std::cout << "--- عملیات سریع ---\n";
    {
        const Timer timer("پردازش سبک");
        std::cout << "    ... کار انجام شد\n";
    }

    std::cout << "زمان‌سنجی با تخریب خودکار: حتی اگر در دامنه استثنا رخ دهد، گزارش چاپ می‌شود.\n";
    return 0;
}
