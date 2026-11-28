// 03_rule_of_five.cpp — قانون پنج و دام مخرب بدون move
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o rule_of_five examples/03_rule_of_five.cpp

#include <iostream>
#include <string>
#include <utility>
#include <vector>

// ۱) فقط مخرب و کپی اعلام شده‌اند → move خودکار تولید نمی‌شود
class CopyOnly {
public:
    CopyOnly() = default;
    CopyOnly(const CopyOnly&) {
        std::cout << "  CopyOnly: کپی\n";
    }
    CopyOnly& operator=(const CopyOnly&) {
        std::cout << "  CopyOnly: کپی=\n";
        return *this;
    }
    ~CopyOnly() { }

private:
    std::string tag_ = "copy-only";
};

// ۲) همهٔ پنج عضو اعلام شده‌اند → move واقعی انجام می‌شود
class Movable {
public:
    Movable() = default;
    Movable(const Movable&) {
        std::cout << "  Movable: کپی\n";
    }
    Movable(Movable&&) noexcept {
        std::cout << "  Movable: انتقال\n";
    }
    Movable& operator=(const Movable&) {
        std::cout << "  Movable: کپی=\n";
        return *this;
    }
    Movable& operator=(Movable&&) noexcept {
        std::cout << "  Movable: انتقال=\n";
        return *this;
    }
    ~Movable() { }

private:
    std::string tag_ = "movable";
};

int main() {
    std::cout << "--- CopyOnly و std::move ---\n";
    CopyOnly first;
    const CopyOnly second = std::move(first);
    (void)second;

    std::cout << "--- Movable و std::move ---\n";
    Movable third;
    Movable fourth = std::move(third);
    std::cout << "  بعد از انتقال ساخته شد\n";

    std::cout << "--- انتساب ---\n";
    Movable fifth;
    fifth = fourth;
    fifth = std::move(fourth);

    std::cout << "--- noexcept و vector ---\n";
    // اگر سازندهٔ انتقال noexcept نباشد، vector برای امنیت استثنا کپی می‌کند
    std::vector<Movable> items;
    items.reserve(4);
    std::cout << "  push_back(Movable()):\n";
    items.push_back(Movable());
    std::cout << "  تعداد: " << items.size() << "\n";

    std::cout << "--- پایان ---\n";
    return 0;
}
