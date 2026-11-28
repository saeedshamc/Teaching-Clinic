// 02_invariants.cpp — قاعدهٔ همیشگی (invariant) و تفاوت با struct باز
// کامپایل: g++ -std=c++17 -Wall -Wextra -o invariants examples/02_invariants.cpp

#include <iostream>
#include <string>

// --- نسخهٔ بی‌محافظ: هر چیزی مجاز است ---
struct NaiveTemperature {
    double celsius = 0.0;
};

// --- نسخهٔ محافظت‌شده: invariant حفظ می‌شود ---
class Temperature {
public:
    // تنها راه تغییر: از این دروازه عبور می‌کند
    bool setCelsius(double value) {
        if (value < kAbsoluteZero) {
            return false;              // زیر صفر مطلق فیزیکاً ممکن نیست
        }
        celsius_ = value;
        return true;
    }

    double celsius() const {
        return celsius_;
    }

    double fahrenheit() const {
        return (celsius_ * 9.0 / 5.0) + 32.0;
    }

    bool isFreezing() const {
        return celsius_ <= 0.0;
    }

private:
    static constexpr double kAbsoluteZero = -273.15;
    double celsius_ = 0.0;
};

// پارامتر const& فقط می‌تواند متد const صدا بزند
void printCelsius(const Temperature& temperature);

class Person {
public:
    bool setName(const std::string& newName) {
        if (newName.empty()) {
            return false;
        }
        name_ = newName;
        return true;
    }

    bool setAge(int newAge) {
        if (newAge < 0 || newAge > 150) {
            return false;
        }
        age_ = newAge;
        return true;
    }

    const std::string& name() const {
        return name_;
    }

    int age() const {
        return age_;
    }

private:
    std::string name_ = "unknown";
    int age_ = 0;
};

void printCelsius(const Temperature& temperature) {
    std::cout << temperature.celsius() << "°C\n";
    // temperature.setCelsius(5.0);   → خطای کامپایل: شیء const است
}

int main() {
    // --- نسخهٔ بدون محافظ ---
    NaiveTemperature naive;
    naive.celsius = -500.0;            // کسی جلوی ما را نمی‌گیرد
    std::cout << "Naive: " << naive.celsius << "°C = "
              << (naive.celsius * 9.0 / 5.0) + 32.0 << "°F  → بی‌معنی\n";

    // --- نسخهٔ محافظت‌شده ---
    Temperature temperature;
    std::cout << "\nمقدار اولیه: " << temperature.celsius() << "°C\n";
    std::cout << "فارنهایت: " << temperature.fahrenheit() << "\n";

    const bool accepted = temperature.setCelsius(-300.0);
    std::cout << "\nsetCelsius(-300) پذیرفته شد؟ " << (accepted ? "بله" : "خیر") << "\n";
    std::cout << "مقدار هنوز " << temperature.celsius() << "°C است (تغییر نکرد)\n";

    temperature.setCelsius(-40.0);
    std::cout << "-40°C = " << temperature.fahrenheit() << "°F\n";
    std::cout << "زیر صفر انجماد؟ " << (temperature.isFreezing() ? "بله" : "خیر") << "\n";

    // --- از دید تابع const، فقط متدهای const در دسترس‌اند ---
    Temperature outside;
    outside.setCelsius(-10.0);
    std::cout << "\nاز داخل تابع const: ";
    printCelsius(outside);
    // outside داخل تابع const است؛ setCelsius در دسترس نیست

    // --- اعتبارسنجی چند عضوی ---
    Person person;
    std::cout << "\nنام اولیه: " << person.name() << " ، سن: " << person.age() << "\n";
    std::cout << "setName(\"\") پذیرفته شد؟ " << (person.setName("") ? "بله" : "خیر") << "\n";
    std::cout << "setAge(200) پذیرفته شد؟ " << (person.setAge(200) ? "بله" : "خیر") << "\n";
    person.setName("Saeed");
    person.setAge(30);
    std::cout << "بعد از مقادیر معتبر: " << person.name() << " ، " << person.age() << "\n";

    std::cout << "\nنتیجه: کلاس خوب، ورودی نامعتبر را همان‌جا رد می‌کند.\n";

    return 0;
}
