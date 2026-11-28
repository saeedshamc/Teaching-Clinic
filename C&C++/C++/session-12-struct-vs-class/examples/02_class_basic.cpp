// 02_class_basic.cpp — کلاس ساده با اعضای خصوصی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o class_basic examples/02_class_basic.cpp

#include <iostream>
#include <vector>

class Counter {
public:
    // رابط عمومی: تنها راه تعامل با شیء
    void increment() {
        ++count_;
    }

    void add(int amount) {
        count_ += amount;
    }

    int value() const {
        return count_;
    }

    void reset() {
        count_ = 0;
    }

private:
    // وضعیت داخلی: از بیرون قابل دسترسی نیست
    int count_ = 0;
};

class LightSwitch {
public:
    void toggle() {
        on_ = !on_;
    }

    bool isOn() const {
        return on_;
    }

private:
    bool on_ = false;
};

int main() {
    Counter counter;
    counter.increment();
    counter.increment();
    counter.add(5);
    std::cout << "counter.value() = " << counter.value() << "\n";

    counter.reset();
    std::cout << "بعد از reset() = " << counter.value() << "\n";

    // counter.count_ = 100;   → خطای کامپایل: count_ خصوصی است

    std::cout << "\n--- چراغ ---\n";
    LightSwitch light;
    std::cout << "وضعیت: " << (light.isOn() ? "روشن" : "خاموش") << "\n";
    light.toggle();
    std::cout << "بعد از toggle: " << (light.isOn() ? "روشن" : "خاموش") << "\n";

    std::cout << "\n--- چند شیء مستقل ---\n";
    std::vector<Counter> counters(3);
    counters.at(0).add(10);
    counters.at(1).add(20);

    for (std::size_t i = 0; i < counters.size(); ++i) {
        std::cout << "counters[" << i << "] = " << counters.at(i).value() << "\n";
    }

    std::cout << "\nهر شیء حافظهٔ خودش را دارد؛ کلاس مثل یک قالب است.\n";

    return 0;
}
