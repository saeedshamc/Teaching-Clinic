// exercise_01.cpp — تمرین ۱: struct نقطه
// TODO: یک struct به نام Point بسازید با دو عضو double (x و y) و این متدها:
//
//   void print() const;
//   double distanceTo(const Point& other) const;   // نیاز به <cmath>
//   Point midpoint(const Point& other) const;      // نقطهٔ وسط
//
// در main دو نقطه از کاربر بگیرید (چهار عدد) و فاصله، نقطهٔ وسط و
// نقاط را با دو رقم اعشار چاپ کنید.
//
// نمونه:
//   نقطهٔ اول (x y): 0 0
//   نقطهٔ دوم (x y): 3 4
//   فاصله: 5.00
//   نقطهٔ وسط: (1.50, 2.00)
//
// راهنما:
//   - فاصله: std::sqrt(dx*dx + dy*dy)
//   - midpoint باید یک Point تازه برگرداند: Point{(x+other.x)/2, ...}
//   - برای دو رقم اعشار از std::fixed و std::setprecision از <iomanip> استفاده کنید.

#include <iostream>

// TODO: تعریف struct Point

int main() {
    // TODO: خواندن دو نقطه

    // TODO: چاپ فاصله و نقطهٔ وسط

    return 0;
}
