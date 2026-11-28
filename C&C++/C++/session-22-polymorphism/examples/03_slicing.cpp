// 03_slicing.cpp — برش شیء (object slicing)
// کامپایل: g++ -std=c++17 -Wall -Wextra -o slicing examples/03_slicing.cpp

#include <iostream>
#include <string>

class Shape {
public:
    virtual ~Shape() = default;

    virtual double area() const {
        return 0.0;
    }

    virtual std::string name() const {
        return "شکل";
    }
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

// ❌ با ارزش: فقط بخش Shape کپی می‌شود، side_ از بین می‌رود
void printByValue(Shape shape) {
    std::cout << "  by value    → " << shape.name() << " ، area = " << shape.area() << "\n";
}

// ✅ با reference: هیچ کپی‌ای رخ نمی‌دهد
void printByReference(const Shape& shape) {
    std::cout << "  by reference → " << shape.name() << " ، area = " << shape.area() << "\n";
}

// ✅ با pointer: همان نتیجه
void printByPointer(const Shape* shape) {
    std::cout << "  by pointer   → " << shape->name() << " ، area = " << shape->area() << "\n";
}

int main() {
    const Square square(5.0);

    std::cout << "شیء اصلی: " << square.name() << " ، area = " << square.area() << "\n\n";

    std::cout << "سه روش پاس دادن:\n";
    printByValue(square);
    printByReference(square);
    printByPointer(&square);

    // کپی مستقیم هم برش می‌دهد:
    Shape sliced = square;          // فقط بخش Shape کپی شد
    std::cout << "\nShape sliced = square → " << sliced.name()
              << " ، area = " << sliced.area() << "\n";

    // مجموعهٔ اشیاء با ارزش: هیچ polymorphism ای وجود ندارد
    // std::vector<Shape> shapes{square};   // ❌ برش

    std::cout << "\nنتیجه: برای چندریختی، همیشه reference یا pointer پاس بدهید.\n";

    return 0;
}
