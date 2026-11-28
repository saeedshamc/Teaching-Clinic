// 01_abstract_shape.cpp — کلاس انتزاعی و تابع مجازی خالص
// کامپایل: g++ -std=c++17 -Wall -Wextra -o abstract examples/01_abstract_shape.cpp

#include <iostream>
#include <string>
#include <vector>

class Shape {
public:
    virtual ~Shape() = default;

    // = 0 → تابع مجازی خالص؛ Shape انتزاعی می‌شود
    virtual double area() const = 0;
    virtual std::string name() const = 0;

    // پیاده‌سازی مشترک برای همهٔ شکل‌ها
    void describe() const {
        std::cout << name() << ": " << area() << "\n";
    }

    bool isBiggerThan(const Shape& other) const {
        return area() > other.area();
    }
};

class Circle : public Shape {
public:
    explicit Circle(double radius)
        : radius_(radius) {
    }

    double area() const override {
        return 3.14159265358979 * radius_ * radius_;
    }

    std::string name() const override {
        return "دایره";
    }

private:
    double radius_;
};

class Rectangle : public Shape {
public:
    Rectangle(double width, double height)
        : width_(width), height_(height) {
    }

    double area() const override {
        return width_ * height_;
    }

    std::string name() const override {
        return "مستطیل";
    }

private:
    double width_;
    double height_;
};

// final → هیچ کلاسی نمی‌تواند از Square ارث ببرد
class Square final : public Shape {
public:
    explicit Square(double side)
        : side_(side) {
    }

    double area() const override {
        return side_ * side_;
    }

    std::string name() const override {
        return "مربع";
    }

private:
    double side_;
};

int main() {
    const Circle circle(2.0);
    const Rectangle rectangle(3.0, 4.0);
    const Square square(5.0);

    const std::vector<const Shape*> shapes{&circle, &rectangle, &square};

    std::cout << "--- همهٔ شکل‌ها ---\n";
    for (const Shape* shape : shapes) {
        shape->describe();
    }

    const Shape* largest = shapes.front();
    for (const Shape* shape : shapes) {
        if (shape->isBiggerThan(*largest)) {
            largest = shape;
        }
    }
    std::cout << "\nبزرگ‌ترین: " << largest->name() << "\n";

    // Shape شیء نیست، مفهوم است:
    //   Shape shape;        // ❌ error: cannot declare variable to be of abstract type
    //   Shape* p = new Shape();   // ❌ همان خطا

    // ولی اشاره‌گر/مرجع به کلاس انتزاعی مجاز است:
    const Shape& ref = square;
    std::cout << "از طریق Shape& : " << ref.name() << "\n";

    return 0;
}
