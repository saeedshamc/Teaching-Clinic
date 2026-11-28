// exercise_01.cpp — تمرین ۱: عملگر * به‌صورت friend
// TODO: کلاسی به نام Vector2D بسازید و ضرب داخلی را با friend پیاده کنید:
//
//   - Vector2D(double x, double y)
//   - double x() const;  double y() const;
//   - double length() const;                 // sqrt(x*x + y*y)
//   - Vector2D operator+(const Vector2D& other) const;
//   - double operator*(const Vector2D& other) const;   // ضرب داخلی
//   - friend Vector2D operator*(double factor, const Vector2D& v);
//   - friend std::ostream& operator<<(std::ostream& out, const Vector2D& v);
//
// چرا friend؟ چون در `2.0 * v` شیء سمت چپ double است، نه Vector2D؛
// پس عملگر نمی‌تواند عضو کلاس باشد.
//
// در main:
//   ۱) دو بردار بسازید: a(3,4) و b(1,2)
//   ۲) a + b را چاپ کنید
//   ۳) ضرب داخلی a * b را چاپ کنید
//   ۴) 2.5 * a را چاپ کنید (توجه: عدد سمت چپ)
//   ۵) طول a را چاپ کنید
//
// نمونهٔ انتظاری:
//   a = (3, 4) ، |a| = 5
//   b = (1, 2)
//   a + b = (4, 6)
//   a · b = 11
//   2.5 * a = (7.5, 10)
//
// راهنما: عملگر member `a * b` و عملگر friend `2.5 * a` می‌توانند هم‌نام باشند،
//          چون نوع پارامترها متفاوت است (overloading).

#include <iostream>

// TODO: تعریف کلاس Vector2D

int main() {
    // TODO: ساختن بردارها و آزمایش عملگرها

    return 0;
}
