// 03_ctor_exception.cpp — استثنا در سازنده و امنیت استثنا
// کامپایل: g++ -std=c++17 -Wall -Wextra -o ctor examples/03_ctor_exception.cpp

#include <iostream>
#include <stdexcept>
#include <string>

// عضو کمکی که ساخت و تخریبش را می‌بینیم
class Resource {
public:
    explicit Resource(const std::string& name)
        : name_(name) {
        std::cout << "    تخصیص منبع: " << name_ << "\n";
    }

    ~Resource() {
        std::cout << "    آزادسازی منبع: " << name_ << "\n";
    }

private:
    std::string name_;
};

class Temperature {
public:
    explicit Temperature(double celsius)
        : sensor_("دماسنج"), celsius_(celsius) {
        // اعضا ساخته شده‌اند؛ اگر این‌جا throw کنیم، مخرب اعضا اجرا می‌شود
        if (celsius < kAbsoluteZero) {
            throw std::invalid_argument("دما زیر صفر مطلق است");
        }
        std::cout << "    بدنهٔ سازنده: دما پذیرفته شد\n";
    }

    ~Temperature() {
        std::cout << "    مخرب Temperature\n";
    }

    double celsius() const {
        return celsius_;
    }

    static constexpr double absoluteZero() {
        return kAbsoluteZero;
    }

private:
    static constexpr double kAbsoluteZero = -273.15;
    Resource sensor_;
    double celsius_;
};

// noexcept: تعهد می‌دهد استثنا پرتاب نمی‌کند
double safeDivide(double a, double b) noexcept {
    if (b == 0.0) {
        return 0.0;                    // بدون throw
    }
    return a / b;
}

int main() {
    std::cout << "--- ساخت معتبر ---\n";
    try {
        const Temperature ok(25.0);
        std::cout << "  celsius = " << ok.celsius() << "\n";
    } catch (const std::exception& error) {
        std::cout << "  خطا: " << error.what() << "\n";
    }

    std::cout << "\n--- ساخت نامعتبر ---\n";
    try {
        const Temperature bad(-300.0);
        std::cout << "این خط اجرا نمی‌شود\n";
    } catch (const std::invalid_argument& error) {
        std::cout << "  گرفته شد: " << error.what() << "\n";
    }

    std::cout << "\n--- noexcept ---\n";
    std::cout << "  safeDivide(10, 4) = " << safeDivide(10.0, 4.0) << "\n";
    std::cout << "  safeDivide(10, 0) = " << safeDivide(10.0, 0.0)
              << " (بدون استثنا)\n";

    // نکته‌ها:
    // ۱) اگر سازنده throw کند، شیء ساخته نمی‌شود و مخرب خود شیء اجرا نمی‌شود.
    // ۲) ولی اعضایی که ساخته شده‌اند (sensor_) تخریب می‌شوند → هیچ منبعی نشت نمی‌کند.
    // ۳) در مخرب هرگز throw نکنید (مخرب پیش‌فرض noexcept است).
    // ۴) noexcept روی توابعی که واقعاً throw نمی‌کنند، بهینه‌سازی را ممکن می‌کند.

    std::cout << "\nصفر مطلق: " << Temperature::absoluteZero() << "°C\n";

    return 0;
}
