// exercise_01_solution.cpp — راه‌حل تمرین ۱: کلاس Counter با محدوده
// کامپایل: g++ -std=c++17 -Wall -Wextra -o counter exercises/exercise_01_solution.cpp

#include <iostream>

class Counter {
public:
    void setLimits(int minimum, int maximum) {
        if (minimum > maximum) {
            return;                    // محدودهٔ نامعتبر پذیرفته نمی‌شود
        }
        minimum_ = minimum;
        maximum_ = maximum;
        if (value_ < minimum_) {
            value_ = minimum_;
        }
        if (value_ > maximum_) {
            value_ = maximum_;
        }
    }

    bool increment() {
        if (value_ >= maximum_) {
            return false;
        }
        ++value_;
        return true;
    }

    bool decrement() {
        if (value_ <= minimum_) {
            return false;
        }
        --value_;
        return true;
    }

    int value() const {
        return value_;
    }

    int minimum() const {
        return minimum_;
    }

    int maximum() const {
        return maximum_;
    }

    void reset() {
        value_ = minimum_;
    }

private:
    int value_ = 0;
    int minimum_ = 0;
    int maximum_ = 100;
};

int main() {
    Counter counter;
    counter.setLimits(0, 3);
    std::cout << "محدوده: " << counter.minimum() << ".." << counter.maximum() << "\n";

    for (int step = 1; step <= 4; ++step) {
        const bool ok = counter.increment();
        std::cout << step << ") increment → "
                  << (ok ? "موفق" : "ناموفق (سقف)")
                  << " ، value = " << counter.value() << "\n";
    }

    std::cout << "\n--- پایین رفتن ---\n";
    while (counter.decrement()) {
        std::cout << "decrement → value = " << counter.value() << "\n";
    }
    std::cout << "decrement دیگر ممکن نیست (کف) ، value = " << counter.value() << "\n";

    std::cout << "\n--- reset ---\n";
    counter.increment();
    counter.increment();
    std::cout << "value قبل از reset = " << counter.value() << "\n";
    counter.reset();
    std::cout << "value بعد از reset = " << counter.value() << "\n";

    return 0;
}
