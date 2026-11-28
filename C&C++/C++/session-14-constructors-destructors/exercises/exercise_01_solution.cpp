// exercise_01_solution.cpp — راه‌حل تمرین ۱: کلاس Timer
// کامپایل: g++ -std=c++17 -Wall -Wextra -o timer exercises/exercise_01_solution.cpp

#include <chrono>
#include <iostream>
#include <string>

class Timer {
public:
    explicit Timer(const std::string& label)
        : label_(label), start_(std::chrono::steady_clock::now()) {
        std::cout << "[Timer] شروع: " << label_ << "\n";
    }

    // مخرب: پایان بلوک را ثبت و مدت را چاپ می‌کند
    ~Timer() {
        const auto end = std::chrono::steady_clock::now();
        const auto microseconds =
            std::chrono::duration_cast<std::chrono::microseconds>(end - start_).count();
        std::cout << "[Timer] " << label_ << ": " << microseconds << " µs\n";
    }

    // Timer قابل کپی نیست؛ دو مخرب برای یک بازه، گزارش تکراری می‌دهد
    Timer(const Timer&) = delete;
    Timer& operator=(const Timer&) = delete;

private:
    std::string label_;
    std::chrono::steady_clock::time_point start_;
};

long long sumUpTo(int limit) {
    long long total = 0;
    for (int i = 1; i <= limit; ++i) {
        total += i;
    }
    return total;
}

int main() {
    const Timer totalTimer("کل برنامه");

    long long result = 0;
    {
        const Timer blockTimer("حلقهٔ جمع");
        result = sumUpTo(1000000);
    }   // ← مخرب blockTimer همین‌جا اجرا می‌شود

    std::cout << "حاصل جمع: " << result << "\n";
    std::cout << "پایان main — مخرب totalTimer آخرین چاپ را می‌سازد.\n";

    return 0;
}
