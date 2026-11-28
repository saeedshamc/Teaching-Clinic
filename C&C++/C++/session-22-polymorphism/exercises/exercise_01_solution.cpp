// exercise_01_solution.cpp — راه‌حل تمرین ۱: سلسله‌مراتب Shape
// کامپایل: g++ -std=c++17 -Wall -Wextra -o shape exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>
#include <vector>

class Shape {
public:
    virtual ~Shape() = default;

    virtual double area() const {
        return 0.0;
    }

    virtual std::string name() const {
        return "شکل";
    }

    // غیر virtual، ولی چون area() را صدا می‌زند، رفتار چندریخت دارد
    double scaledArea(double factor) const {
        return area() * factor * factor;
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

class Square : public Shape {
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
    const Square square(3.0);

    const std::vector<const Shape*> shapes{&circle, &square};

    const Shape* largest = nullptr;
    for (const Shape* shape : shapes) {
        std::cout << shape->name()
                  << ": area = " << shape->area()
                  << " ، doubled = " << shape->scaledArea(2.0) << "\n";

        if (largest == nullptr || shape->area() > largest->area()) {
            largest = shape;
        }
    }

    std::cout << "\nبزرگ‌ترین: " << largest->name() << "\n";

    return 0;
}
