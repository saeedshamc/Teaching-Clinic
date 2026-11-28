// exercise_01_solution.cpp — راه‌حل تمرین ۱: کلاس Fraction
// کامپایل: g++ -std=c++17 -Wall -Wextra -o fraction exercises/exercise_01_solution.cpp

#include <cstdlib>
#include <iostream>
#include <numeric>

class Fraction {
public:
    Fraction(int numerator, int denominator)
        : numerator_(numerator), denominator_(denominator) {
        if (denominator_ == 0) {
            denominator_ = 1;              // جایگزین امن؛ در جلسهٔ ۲۶ استثنا می‌دهیم
        }
        normalize();
    }

    int numerator() const {
        return numerator_;
    }

    int denominator() const {
        return denominator_;
    }

    double value() const {
        return static_cast<double>(numerator_) / static_cast<double>(denominator_);
    }

    Fraction operator+(const Fraction& other) const {
        return Fraction(numerator_ * other.denominator_ + other.numerator_ * denominator_,
                        denominator_ * other.denominator_);
    }

    Fraction operator*(const Fraction& other) const {
        return Fraction(numerator_ * other.numerator_,
                        denominator_ * other.denominator_);
    }

    bool operator==(const Fraction& other) const {
        return numerator_ == other.numerator_ && denominator_ == other.denominator_;
    }

    bool operator<(const Fraction& other) const {
        return value() < other.value();
    }

    friend std::ostream& operator<<(std::ostream& out, const Fraction& fraction);

private:
    void normalize() {
        if (denominator_ < 0) {
            numerator_ = -numerator_;
            denominator_ = -denominator_;
        }
        const int divisor = std::gcd(std::abs(numerator_), denominator_);
        if (divisor > 1) {
            numerator_ /= divisor;
            denominator_ /= divisor;
        }
    }

    int numerator_;
    int denominator_;
};

std::ostream& operator<<(std::ostream& out, const Fraction& fraction) {
    if (fraction.denominator_ == 1) {
        return out << fraction.numerator_;
    }
    return out << fraction.numerator_ << "/" << fraction.denominator_;
}

int main() {
    const Fraction sixEighths(6, 8);
    std::cout << "6/8 ساده‌شده → " << sixEighths << "\n";

    const Fraction negative(1, -2);
    std::cout << "1/-2 → " << negative << "\n";

    const Fraction half(1, 2);
    const Fraction third(1, 3);
    const Fraction twoThirds(2, 3);

    std::cout << "\n" << half << " + " << third << " = " << (half + third) << "\n";
    std::cout << half << " * " << twoThirds << " = " << (half * twoThirds) << "\n";

    const Fraction twoQuarters(2, 4);
    std::cout << "\n" << half << " == " << twoQuarters << " ؟ "
              << (half == twoQuarters ? "بله" : "خیر") << "\n";
    std::cout << third << " < " << half << " ؟ "
              << (third < half ? "بله" : "خیر") << "\n";

    std::cout << "\nمقدار اعشاری " << third << " = " << third.value() << "\n";
    std::cout << "کسر با مخرج صفر → " << Fraction(5, 0) << " (مقدار پیش‌فرض ایمن)\n";

    return 0;
}
