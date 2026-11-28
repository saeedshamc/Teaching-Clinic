// 02_static_methods.cpp — تابع static و سازندهٔ کارخانه‌ای
// کامپایل: g++ -std=c++17 -Wall -Wextra -o temperature examples/02_static_methods.cpp

#include <iostream>

class Temperature {
public:
    // توابع کارخانه‌ای: راه ساخت شیء با واحدهای مختلف
    static Temperature fromCelsius(double value) {
        return Temperature(value);
    }

    static Temperature fromFahrenheit(double value) {
        return Temperature((value - 32.0) * 5.0 / 9.0);
    }

    static Temperature fromKelvin(double value) {
        return Temperature(value - 273.15);
    }

    // تابع static constexpr: قابل استفاده در زمان کامپایل
    static constexpr double absoluteZeroCelsius() {
        return -273.15;
    }

    static int created() {
        return created_;
    }

    double celsius() const {
        return celsius_;
    }

    double fahrenheit() const {
        return celsius_ * 9.0 / 5.0 + 32.0;
    }

    double kelvin() const {
        return celsius_ - absoluteZeroCelsius();
    }

    void print() const {
        std::cout << celsius_ << "°C = " << fahrenheit() << "°F = "
                  << kelvin() << "K\n";
    }

private:
    // سازنده خصوصی: از بیرون فقط با توابع کارخانه‌ای می‌شود شیء ساخت
    explicit Temperature(double celsius)
        : celsius_(celsius < absoluteZeroCelsius() ? absoluteZeroCelsius() : celsius) {
        ++created_;
    }

    double celsius_;
    inline static int created_ = 0;
};

int main() {
    const Temperature boiling = Temperature::fromCelsius(100.0);
    const Temperature freezing = Temperature::fromFahrenheit(32.0);
    const Temperature room = Temperature::fromKelvin(293.15);

    boiling.print();
    freezing.print();
    room.print();

    std::cout << "\nصفر مطلق: " << Temperature::absoluteZeroCelsius() << "°C\n";
    std::cout << "تعداد شیءهای ساخته‌شده: " << Temperature::created() << "\n";

    // سازنده خصوصی است، پس این خط کامپایل نمی‌شود:
    //   Temperature bad(50.0);

    std::cout << "\nنتیجه: تابع static می‌تواند راه ساخت را کنترل کند.\n";

    return 0;
}
