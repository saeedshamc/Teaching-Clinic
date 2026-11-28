// exercise_02_solution.cpp — راه‌حل تمرین ۲: struct مستطیل
// کامپایل: g++ -std=c++17 -Wall -Wextra -o rectangle exercises/exercise_02_solution.cpp

#include <cmath>
#include <iomanip>
#include <iostream>

struct Rectangle {
    double width = 0.0;
    double height = 0.0;

    double area() const {
        return width * height;
    }

    double perimeter() const {
        return 2.0 * (width + height);
    }

    bool isSquare() const {
        return std::abs(width - height) < 1e-9;
    }

    void print() const {
        std::cout << width << " x " << height
                  << " → مساحت " << area()
                  << " ، محیط " << perimeter() << "\n";
    }
};

int main() {
    Rectangle rectangle;
    std::cout << "عرض و ارتفاع: ";
    if (!(std::cin >> rectangle.width >> rectangle.height)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }

    if (rectangle.width <= 0.0 || rectangle.height <= 0.0) {
        std::cout << "عرض و ارتفاع باید مثبت باشند.\n";
        return 0;
    }

    std::cout << std::fixed << std::setprecision(2);
    rectangle.print();
    std::cout << "مربع است؟ " << (rectangle.isSquare() ? "بله" : "خیر") << "\n";

    return 0;
}
