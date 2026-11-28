// 02_stack_template.cpp — پشتهٔ جنریک با std::vector
// کامپایل: g++ -std=c++17 -Wall -Wextra -o stack examples/02_stack_template.cpp

#include <cstddef>
#include <iostream>
#include <string>
#include <vector>

template <typename T>
class Stack {
public:
    void push(const T& value) {
        items_.push_back(value);
    }

    // برداشتن بدون استثنا: در پشتهٔ خالی false برمی‌گرداند
    bool tryPop(T& out) {
        if (items_.empty()) {
            return false;
        }
        out = items_.back();
        items_.pop_back();
        return true;
    }

    // دسترسی به بالای پشته؛ فقط وقتی خالی نیست صدا بزنید
    const T& top() const {
        return items_.back();
    }

    bool empty() const {
        return items_.empty();
    }

    std::size_t size() const {
        return items_.size();
    }

private:
    std::vector<T> items_;
};

int main() {
    Stack<int> numbers;
    numbers.push(1);
    numbers.push(2);
    numbers.push(3);

    std::cout << "اندازهٔ پشته: " << numbers.size() << "\n";
    std::cout << "بالای پشته: " << numbers.top() << "\n";

    int value = 0;
    std::cout << "\n--- خالی کردن ---\n";
    while (numbers.tryPop(value)) {
        std::cout << "pop → " << value << "\n";
    }
    std::cout << "خالی؟ " << (numbers.empty() ? "بله" : "خیر") << "\n";

    int ignored = 0;
    std::cout << "tryPop روی پشتهٔ خالی: "
              << (numbers.tryPop(ignored) ? "موفق" : "ناموفق") << "\n";

    // همان کلاس، نوع دیگر
    Stack<std::string> words;
    words.push("اول");
    words.push("دوم");

    std::string word;
    std::cout << "\n--- پشتهٔ رشته ---\n";
    while (words.tryPop(word)) {
        std::cout << "pop → " << word << "\n";
    }

    // Stack<int> و Stack<std::string> دو نوع کاملاً جدا هستند و
    // کد هر کدام جداگانه ساخته می‌شود (template instantiation).

    return 0;
}
