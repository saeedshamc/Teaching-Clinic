// exercise_01.cpp — تمرین ۱: تولید شناسهٔ یکتا با عضو static
// TODO: کلاسی به نام Ticket بسازید:
//
//   - Ticket(const std::string& owner)
//       * id_ را از شمارندهٔ static می‌گیرد (شمارهٔ بعدی)
//       * owner خالی → "مهمان"
//   - int id() const;                  const std::string& owner() const;
//   - static int issued();             // تعداد بلیت‌های ساخته‌شده
//   - static int lastId();             // آخرین شناسه‌ای که صادر شده
//
// شمارنده از 1000 شروع شود و با هر ساخت یکی زیاد شود.
//
// در main:
//   ۱) سه بلیت بسازید و شناسه‌هایشان را چاپ کنید
//   ۲) تعداد صادرشده را قبل و بعد از ساخت چاپ کنید
//   ۳) نشان دهید شناسه‌ها تکرار نمی‌شوند
//
// نمونهٔ انتظاری:
//   Ticket #1000 برای Saeed
//   Ticket #1001 برای Ali
//   Ticket #1002 برای مهمان
//   issued = 3 ، lastId = 1002
//
// راهنما: شمارنده را inline static داخل کلاس تعریف کنید تا نیاز به
//          تعریف جداگانه در فایل .cpp نداشته باشید (C++17).
//          توابع static به this دسترسی ندارند.

#include <iostream>
#include <string>

// TODO: تعریف کلاس Ticket

int main() {
    // TODO: ساخت بلیت‌ها و چاپ شناسه‌ها

    return 0;
}
