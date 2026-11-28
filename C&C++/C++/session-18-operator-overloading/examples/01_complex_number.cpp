// 01_complex_number.cpp — سربارگذاری +، +=، == و <
// کامپایل: g++ -std=c++17 -Wall -Wextra -o complex examples/01_complex_number.cpp

#include <cmath>
#include <iostream>

class Complex {
public:
    Complex(double real = 0.0, double imaginary = 0.0)
        : real_(real), imaginary_(imaginary) {
    }

    double real() const {
        return real_;
    }

    double imaginary() const {
        return imaginary_;
    }

    double magnitude() const {
        return std::sqrt(real_ * real_ + imaginary_ * imaginary_);
    }

    // += به‌صورت عضو: شیء چپ را تغییر می‌دهد و reference برمی‌گرداند
    Complex& operator+=(const Complex& other) {
        real_ += other.real_;
        imaginary_ += other.imaginary_;
        return *this;
    }

    // + به‌صورت عضو: کپی می‌سازد و تغییر نمی‌دهد → const
    Complex operator+(const Complex& other) const {
        return Complex(real_ + other.real_, imaginary_ + other.imaginary_);
    }

    Complex operator-(const Complex& other) const {
        return Complex(real_ - other.real_, imaginary_ - other.imaginary_);
    }

    // ضرب در عدد حقیقی
    Complex operator*(double factor) const {
        return Complex(real_ * factor, imaginary_ * factor);
    }

    bool operator==(const Complex& other) const {
        return real_ == other.real_ && imaginary_ == other.imaginary_;
    }

    bool operator!=(const Complex& other) const {
        return !(*this == other);
    }

    // مقایسه بر اساس اندازه؛ برای مرتب‌سازی مفید است
    bool operator<(const Complex& other) const {
        return magnitude() < other.magnitude();
    }

private:
    double real_;
    double imaginary_;
};

// نسخهٔ آزاد ضرب: 2.0 * z هم کار کند
Complex operator*(double factor, const Complex& value) {
    return value * factor;
}

void print(const Complex& value) {
    std::cout << value.real();
    if (value.imaginary() >= 0.0) {
        std::cout << " + " << value.imaginary() << "i";
    } else {
        std::cout << " - " << -value.imaginary() << "i";
    }
}

int main() {
    const Complex a(3.0, 4.0);
    const Complex b(1.0, -2.0);

    std::cout << "a = ";
    print(a);
    std::cout << " ، |a| = " << a.magnitude() << "\n";

    std::cout << "b = ";
    print(b);
    std::cout << " ، |b| = " << b.magnitude() << "\n\n";

    std::cout << "a + b = ";
    print(a + b);
    std::cout << "\n";

    std::cout << "a - b = ";
    print(a - b);
    std::cout << "\n";

    std::cout << "a * 2 = ";
    print(a * 2.0);
    std::cout << "\n";

    std::cout << "2 * b = ";
    print(2.0 * b);
    std::cout << "\n\n";

    Complex c = a;
    c += b;
    std::cout << "c = a; c += b → ";
    print(c);
    std::cout << "\n";

    std::cout << "a == c ؟ " << (a == c ? "بله" : "خیر") << "\n";
    std::cout << "a != b ؟ " << (a != b ? "بله" : "خیر") << "\n";
    std::cout << "|a| < |b| ؟ " << (a < b ? "بله" : "خیر") << "\n";

    return 0;
}
