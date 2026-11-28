// exercise_01.cpp — تمرین ۱: سلسله‌مراتب Shape با area()
// TODO: کلاس پایه و دو زیرکلاس بسازید:
//
//   class Shape {
//   public:
//       virtual ~Shape() = default;
//       virtual double area() const;            // پیش‌فرض 0
//       virtual std::string name() const;        // "شکل"
//       double scaledArea(double factor) const;  // area() * factor * factor
//   };
//
//   class Circle : public Shape {
//   public:
//       explicit Circle(double radius);
//       double area() const override;
//       std::string name() const override;       // "دایره"
//   };
//
//   class Square : public Shape {
//   public:
//       explicit Square(double side);
//       double area() const override;
//       std::string name() const override;       // "مربع"
//   };
//
// در main:
//   ۱) یک Circle(2) و یک Square(3) بسازید
//   ۲) هر دو را در std::vector<Shape*> بریزید
//   ۳) در حلقه، name()، area() و scaledArea(2) را چاپ کنید
//   ۴) بزرگ‌ترین شکل را پیدا کنید (مقایسهٔ area)
//
// نمونهٔ انتظاری:
//   دایره: area = 12.5664 ، doubled = 50.2655
//   مربع: area = 9 ، doubled = 36
//   بزرگ‌ترین: دایره
//
// راهنما: scaledArea در کلاس پایه تعریف می‌شود ولی area() را صدا می‌زند؛
//          چون area() virtual است، نسخهٔ درست هر شکل اجرا می‌شود.
//          مخرب پایه را virtual بگذارید، حتی اگر در این تمرین delete نداریم.

#include <iostream>
#include <string>
#include <vector>

// TODO: تعریف Shape، Circle و Square

int main() {
    // TODO: ساختن شکل‌ها و پیمایش

    return 0;
}
