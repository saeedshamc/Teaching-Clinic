// exercise_01.cpp — تمرین ۱: کلاس Vehicle و Car
// TODO: دو کلاس بسازید:
//
//   class Vehicle {
//   public:
//       Vehicle(const std::string& brand, int wheels);
//       const std::string& brand() const;
//       int wheels() const;
//       std::string type() const;          // "وسیلهٔ نقلیه"
//       void print() const;                // برند، چرخ‌ها، نوع
//   protected:
//       void setWheels(int count);         // فقط مقادیر > 0
//   };
//
//   class Car : public Vehicle {
//   public:
//       Car(const std::string& brand, int doors);
//           // سازندهٔ پایه را با 4 چرخ صدا بزنید
//       int doors() const;
//       std::string type() const;          // "خودرو"
//   };
//
// در main:
//   ۱) یک Vehicle و یک Car بسازید و print() هر دو را صدا بزنید
//   ۲) نشان دهید Car از طریق protected می‌تواند چرخ‌ها را تغییر دهد
//   ۳) با Vehicle& به Car نگاه کنید و type() را چاپ کنید
//
// نمونهٔ انتظاری:
//   وسیلهٔ نقلیه: دوچرخ ، چرخ = 2 ، نوع = وسیلهٔ نقلیه
//   وسیلهٔ نقلیه: پژو ، چرخ = 4 ، نوع = وسیلهٔ نقلیه   ← !
//   تعداد درها: 4
//   چرخ‌های خودرو بعد از تغییر: 5
//   از طریق Vehicle& : نوع = وسیلهٔ نقلیه
//
// چرا خط دوم «وسیلهٔ نقلیه» است؟ چون print() در کلاس پایه تعریف شده و
// type() را از دید خودش (بدون virtual) صدا می‌زند. خود car.type() از بیرون
// «خودرو» می‌دهد؛ این تفاوت را در main نشان دهید. جلسهٔ ۲۲ آن را حل می‌کند.
//
// راهنما: در Vehicle، اعضا را private بگذارید و setWheels را protected.

#include <iostream>
#include <string>

// TODO: تعریف Vehicle و Car

int main() {
    // TODO: ساختن اشیاء و آزمایش

    return 0;
}
