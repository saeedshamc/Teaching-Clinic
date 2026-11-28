// 01_box_template.cpp — قالب کلاس: یک کلاس برای چند نوع
// کامپایل: g++ -std=c++17 -Wall -Wextra -o box examples/01_box_template.cpp

#include <iostream>
#include <string>

template <typename T>
class Box {
public:
    explicit Box(const T& value)
        : value_(value) {
    }

    const T& get() const {
        return value_;
    }

    void set(const T& value) {
        value_ = value;
    }

    // تابع عضو که خودش هم قالب است
    template <typename U>
    Box<U> map(U (*function)(const T&)) const {
        return Box<U>(function(value_));
    }

private:
    T value_;
};

// تابع کمکی برای map
int lengthOf(const std::string& text) {
    return static_cast<int>(text.size());
}

int doubled(const int& number) {
    return number * 2;
}

int main() {
    Box<int> number(42);
    std::cout << "Box<int> = " << number.get() << "\n";
    number.set(50);
    std::cout << "بعد از set(50) = " << number.get() << "\n";

    Box<std::string> text("سلام");
    std::cout << "\nBox<string> = " << text.get() << "\n";

    Box<double> ratio(0.75);
    std::cout << "Box<double> = " << ratio.get() << "\n";

    // هر Box<T> یک نوع مستقل است — هیچ رابطهٔ وراثتی بین آن‌ها نیست
    std::cout << "\nBox<int> و Box<double> دو نوع جدا هستند.\n";

    // map: خروجی از نوع دیگر
    const Box<int> mapped = number.map(doubled);
    std::cout << "number.map(doubled) = " << mapped.get() << "\n";

    const Box<int> length = text.map(lengthOf);
    std::cout << "text.map(lengthOf) = " << length.get() << "\n";

    // Box در Box
    Box<Box<int>> nested(Box<int>(7));
    std::cout << "\nnested.get().get() = " << nested.get().get() << "\n";

    // alias template — نام کوتاه‌تر
    using IntBox = Box<int>;
    const IntBox alias(99);
    std::cout << "IntBox alias = " << alias.get() << "\n";

    // Box بی‌نوع ممکن نیست:
    //   Box box(5);     // ❌ در C++17 استنتاج نوع کلاس قالب لازم است:
    //   Box box(5);     // با CTAD در C++17 کار می‌کند، ولی در C++14 نه.
    // برای صراحت، همیشه Box<int> بنویسید.

    return 0;
}
