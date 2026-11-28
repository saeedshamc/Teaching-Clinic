// exercise_01_solution.cpp — راه‌حل تمرین ۱: struct نقطه
// کامپایل: g++ -std=c++17 -Wall -Wextra -o point exercises/exercise_01_solution.cpp

#include <cmath>
#include <iomanip>
#include <iostream>

struct Point {
    double x = 0.0;
    double y = 0.0;

    void print() const {
        std::cout << "(" << x << ", " << y << ")\n";
    }

    double distanceTo(const Point& other) const {
        const double dx = x - other.x;
        const double dy = y - other.y;
        return std::sqrt(dx * dx + dy * dy);
    }

    Point midpoint(const Point& other) const {
        return Point{(x + other.x) / 2.0, (y + other.y) / 2.0};
    }
};

int main() {
    Point first;
    Point second;

    std::cout << "نقطهٔ اول (x y): ";
    if (!(std::cin >> first.x >> first.y)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }

    std::cout << "نقطهٔ دوم (x y): ";
    if (!(std::cin >> second.x >> second.y)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }

    std::cout << std::fixed << std::setprecision(2);
    std::cout << "فاصله: " << first.distanceTo(second) << "\n";
    std::cout << "نقطهٔ وسط: ";
    first.midpoint(second).print();

    return 0;
}
