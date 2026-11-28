// 01_virtual_function.cpp — virtual: انتخاب تابع در زمان اجرا
// کامپایل: g++ -std=c++17 -Wall -Wextra -o shapes examples/01_virtual_function.cpp

#include <iostream>
#include <string>
#include <vector>

class Shape {
public:
    virtual ~Shape() = default;      // در فایل ۰۲ توضیح داده می‌شود

    virtual double area() const {
        return 0.0;
    }

    virtual std::string name() const {
        return "شکل";
    }

    // متد غیر virtual که از متد virtual استفاده می‌کند
    void describe() const {
        std::cout << name() << " با مساحت " << area() << "\n";
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

class Triangle : public Shape {
public:
    Triangle(double base, double height)
        : base_(base), height_(height) {
    }

    double area() const override {
        return base_ * height_ / 2.0;
    }

    std::string name() const override {
        return "مثلث";
    }

private:
    double base_;
    double height_;
};

int main() {
    const Circle circle(2.0);
    const Rectangle rectangle(3.0, 4.0);
    const Triangle triangle(6.0, 5.0);

    // اشیاء روی پشته‌اند؛ فقط اشاره‌گر پایه نگه می‌داریم (بدون new)
    const std::vector<const Shape*> shapes{&circle, &rectangle, &triangle};

    double total = 0.0;
    std::cout << "--- از طریق Shape* ---\n";
    for (const Shape* shape : shapes) {
        std::cout << shape->name() << ": " << shape->area() << "\n";
        total += shape->area();
    }

    std::cout << "\nمجموع مساحت‌ها: " << total << "\n";

    std::cout << "\n--- describe() که area() را صدا می‌زند ---\n";
    for (const Shape* shape : shapes) {
        shape->describe();
    }

    // بدون virtual، همهٔ این خطوط «شکل: 0» می‌دادند.
    // با virtual، تابع بر اساس نوع واقعی شیء انتخاب می‌شود.

    return 0;
}
