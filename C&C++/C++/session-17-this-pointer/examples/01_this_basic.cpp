// 01_this_basic.cpp — this: اشاره‌گری به شیء جاری
// کامپایل: g++ -std=c++17 -Wall -Wextra -o first examples/01_this_basic.cpp

#include <iostream>
#include <string>

class Counter {
public:
    Counter(int value, int step)
        : value_(value), step_(step) {
    }

    // ابهام نام: پارامتر و عضو هم‌نام‌اند → this-> لازم است
    void setValue(int value) {
        this->value_ = value;
    }

    void setStep(int step) {
        this->step_ = step;
    }

    int value() const {
        return value_;
    }

    int step() const {
        return step_;
    }

    void increment() {
        value_ += step_;
    }

    // مقایسهٔ شیء با شیء دیگر: *this همان شیء جاری است
    bool isLargerThan(const Counter& other) const {
        return value_ > other.value_;
    }

    // بازگشت *this به‌صورت reference → زنجیره‌سازی (جلسهٔ ۱۷.۲)
    Counter& addStep() {
        value_ += step_;
        return *this;
    }

    // در متد const، this از نوع const Counter* است
    const Counter& self() const {
        return *this;
    }

private:
    int value_ = 0;
    int step_ = 1;
};

int main() {
    Counter a(10, 5);
    Counter b(12, 1);

    std::cout << "a: value = " << a.value() << " ، step = " << a.step() << "\n";
    std::cout << "b: value = " << b.value() << " ، step = " << b.step() << "\n";

    a.setValue(20);
    a.setStep(2);
    std::cout << "\nafter setValue(20)/setStep(2): value = " << a.value()
              << " ، step = " << a.step() << "\n";

    a.increment();
    std::cout << "after increment: " << a.value() << "\n";

    std::cout << "\na > b ؟ " << (a.isLargerThan(b) ? "بله" : "خیر") << "\n";

    // زنجیره‌سازی: هر فراخوانی همان شیء را برمی‌گرداند
    a.addStep().addStep().addStep();
    std::cout << "بعد از سه addStep: " << a.value() << "\n";

    // آدرس شیء جاری و آدرس خود شیء یکی است
    std::cout << "\n&a          = " << static_cast<const void*>(&a) << "\n";
    std::cout << "&a.self()   = " << static_cast<const void*>(&a.self()) << "\n";
    std::cout << "same object؟ " << (&a == &a.self() ? "بله" : "خیر") << "\n";

    return 0;
}
