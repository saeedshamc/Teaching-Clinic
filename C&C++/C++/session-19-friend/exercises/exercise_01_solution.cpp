// exercise_01_solution.cpp — راه‌حل تمرین ۱: Vector2D با عملگر friend
// کامپایل: g++ -std=c++17 -Wall -Wextra -o vector2d exercises/exercise_01_solution.cpp

#include <cmath>
#include <iostream>

class Vector2D {
public:
    Vector2D(double x, double y)
        : x_(x), y_(y) {
    }

    double x() const {
        return x_;
    }

    double y() const {
        return y_;
    }

    double length() const {
        return std::sqrt(x_ * x_ + y_ * y_);
    }

    Vector2D operator+(const Vector2D& other) const {
        return Vector2D(x_ + other.x_, y_ + other.y_);
    }

    // ضرب داخلی: member، چون شیء چپ همیشه Vector2D است
    double operator*(const Vector2D& other) const {
        return x_ * other.x_ + y_ * other.y_;
    }

    // ضرب در عدد از سمت چپ: باید آزاد باشد
    friend Vector2D operator*(double factor, const Vector2D& vector);

    // چاپ، نیاز به دسترسی به اعضا دارد
    friend std::ostream& operator<<(std::ostream& out, const Vector2D& vector);

private:
    double x_;
    double y_;
};

Vector2D operator*(double factor, const Vector2D& vector) {
    return Vector2D(vector.x_ * factor, vector.y_ * factor);
}

std::ostream& operator<<(std::ostream& out, const Vector2D& vector) {
    return out << "(" << vector.x_ << ", " << vector.y_ << ")";
}

int main() {
    const Vector2D a(3.0, 4.0);
    const Vector2D b(1.0, 2.0);

    std::cout << "a = " << a << " ، |a| = " << a.length() << "\n";
    std::cout << "b = " << b << "\n";
    std::cout << "a + b = " << (a + b) << "\n";
    std::cout << "a · b = " << (a * b) << "\n";
    std::cout << "2.5 * a = " << (2.5 * a) << "\n";
    std::cout << "3 * b = " << (3.0 * b) << "\n";

    // اگر a * 2.5 هم لازم باشد، یک overload دیگر لازم است؛
    // عملگر friend فقط جهت (double, Vector2D) را پوشش می‌دهد.
    // std::cout << (a * 2.5);   // ❌ خطای کامپایل

    return 0;
}
