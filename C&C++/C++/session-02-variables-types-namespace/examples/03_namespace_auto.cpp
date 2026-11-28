// 03_namespace_auto.cpp — namespace سفارشی، auto و static_cast
// کامپایل: g++ -std=c++17 -Wall -Wextra -o ns examples/03_namespace_auto.cpp

#include <iostream>

namespace geometry {
    constexpr double pi = 3.14159;

    double circleArea(double radius) {
        return pi * radius * radius;
    }

    double circlePerimeter(double radius) {
        return 2 * pi * radius;
    }
}  // namespace geometry

int main() {
    // --- دسترسی با عملگر :: ---
    auto radius = 2.0;  // auto نوع را double تشخیص می‌دهد
    auto area = geometry::circleArea(radius);
    auto perimeter = geometry::circlePerimeter(radius);

    std::cout << "شعاع = " << radius << "\n";
    std::cout << "مساحت = " << area << "\n";
    std::cout << "محیط = " << perimeter << "\n";

    // --- auto در انواع دیگر ---
    auto count = 10;        // int
    auto price = 9.99;      // double
    auto letter = 'B';      // char
    std::cout << "count = " << count << ", price = " << price
              << ", letter = " << letter << "\n";

    // --- تقسیم صحیح در برابر تقسیم اعشاری ---
    int totalScore = 17;
    int students = 2;

    auto wrong = totalScore / students;                                  // 8 (صحیح)
    auto right = static_cast<double>(totalScore) / students;             // 8.5

    std::cout << "تقسیم صحیح = " << wrong << "\n";
    std::cout << "تقسیم اعشاری = " << right << "\n";

    return 0;
}
