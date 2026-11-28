// 01_struct.cpp — struct در C++: داده و رفتار در یک واحد
// کامپایل: g++ -std=c++17 -Wall -Wextra -o struct_basic examples/01_struct.cpp

#include <cmath>
#include <iostream>
#include <string>

// struct در C++ برخلاف C می‌تواند تابع عضو داشته باشد
struct Point {
    // مقدار پیش‌فرض عضو (C++11)
    double x = 0.0;
    double y = 0.0;

    // متد const: قول می‌دهد شیء را تغییر ندهد
    void print() const {
        std::cout << "(" << x << ", " << y << ")\n";
    }

    double distanceTo(const Point& other) const {
        const double dx = x - other.x;
        const double dy = y - other.y;
        return std::sqrt(dx * dx + dy * dy);
    }

    // بازگرداندن یک Point تازه، بدون تغییر شیء فعلی
    Point movedBy(double dx, double dy) const {
        return Point{x + dx, y + dy};
    }
};

struct Student {
    std::string name;
    int score = 0;
};

int main() {
    // --- ساخت با مقداردهی آگریگیت ---
    Point origin{};
    Point a{3.0, 4.0};
    Point b{1.0};              // y مقدار پیش‌فرض 0.0 را می‌گیرد

    std::cout << "origin = ";
    origin.print();
    std::cout << "a = ";
    a.print();
    std::cout << "b = ";
    b.print();

    std::cout << "\nفاصلهٔ a تا مبدأ: " << a.distanceTo(origin) << "\n";
    std::cout << "فاصلهٔ a تا b    : " << a.distanceTo(b) << "\n";

    const Point moved = a.movedBy(1.0, -2.0);
    std::cout << "a جابه‌جا‌شده با (1, -2) = ";
    moved.print();

    // --- کپی مستقل است ---
    Point copy = a;
    copy.x = 100.0;
    std::cout << "\nبعد از تغییر copy:\n  a    = ";
    a.print();
    std::cout << "  copy = ";
    copy.print();

    // --- struct با اعضای غیر عددی ---
    Student student{"Ali", 18};
    Student another = student;      // کپی کامل همهٔ اعضا
    another.score = 20;

    std::cout << "\n" << student.name << ": " << student.score << "\n";
    std::cout << another.name << ": " << another.score << "\n";

    std::cout << "\nsizeof(Point) = " << sizeof(Point) << " بایت (دو double)\n";

    return 0;
}
