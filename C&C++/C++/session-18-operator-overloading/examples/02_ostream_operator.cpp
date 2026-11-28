// 02_ostream_operator.cpp — operator<< برای چاپ مستقیم شیء
// کامپایل: g++ -std=c++17 -Wall -Wextra -o print examples/02_ostream_operator.cpp

#include <iostream>
#include <sstream>
#include <string>

class Money {
public:
    Money(long long cents, std::string currency)
        : cents_(cents), currency_(std::move(currency)) {
    }

    long long cents() const {
        return cents_;
    }

    const std::string& currency() const {
        return currency_;
    }

    // توابع friend در جلسهٔ ۱۹ کامل توضیح داده می‌شوند؛ فعلاً فقط از
    // getterهای عمومی استفاده می‌کنیم تا encapsulation نشکند.
    std::string toString() const {
        std::ostringstream out;
        out << cents_ / 100 << ".";
        const long long remainder = cents_ % 100;
        out << (remainder < 10 ? "0" : "") << remainder;
        out << " " << currency_;
        return out.str();
    }

private:
    long long cents_;
    std::string currency_;
};

// نسخهٔ آزاد: شیء چپ، شیء جاری نیست → باید تابع آزاد باشد
std::ostream& operator<<(std::ostream& out, const Money& money) {
    return out << money.toString();
}

std::istream& operator>>(std::istream& in, Money& money) {
    long long whole = 0;
    long long fraction = 0;
    char dot = 0;
    std::string currency;

    if (in >> whole >> dot >> fraction >> currency && dot == '.') {
        money = Money(whole * 100 + fraction, currency);
    }
    return in;
}

int main() {
    const Money price(12345, "IRR");
    const Money discount(2500, "IRR");

    // چاپ مستقیم با cout — بدون toString دستی
    std::cout << "قیمت: " << price << "\n";
    std::cout << "تخفیف: " << discount << "\n";
    std::cout << "قیمت نهایی: " << Money(price.cents() - discount.cents(), "IRR") << "\n";

    // همان عملگر برای cerr هم کار می‌کند
    std::cerr << "[خطا] نمونه: " << discount << "\n";

    // خواندن با >>
    std::istringstream input("99.05 USD");
    Money parsed(0, "");
    input >> parsed;
    std::cout << "خوانده‌شده: " << parsed << "\n";

    // زنجیرهٔ << روی چند مقدار
    std::cout << "سه مقدار: " << price << " | " << discount << " | " << parsed << "\n";

    return 0;
}
