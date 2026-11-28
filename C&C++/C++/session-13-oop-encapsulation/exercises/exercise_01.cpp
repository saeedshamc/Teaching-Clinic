// exercise_01.cpp — تمرین ۱: کلاس Counter با محدوده
// TODO: کلاسی به نام Counter بسازید که:
//
//   - مقدار داخلی را private نگه دارد (شروع از 0)
//   - void setLimits(int minimum, int maximum);  // اگر minimum > maximum بود،
//     محدوده تغییر نکند و محدودهٔ پیش‌فرض (0 تا 100) باقی بماند
//   - bool increment();   // اگر به maximum رسیده بود false و بدون تغییر
//   - bool decrement();   // اگر به minimum رسیده بود false و بدون تغییر
//   - int value() const;
//   - int minimum() const;  int maximum() const;
//   - void reset();       // برگشت به minimum
//
// در main محدودهٔ 0..3 را تنظیم کنید، چهار بار increment بزنید،
// پنجمین را امتحان کنید، سپس decrement تا پایین و یک بار بیشتر.
//
// نمونه:
//   ۱) increment → 1
//   ۲) increment → 2
//   ۳) increment → 3
//   ۴) increment → ناموفق (سقف)
//   decrement → 2
//   ...
//
// راهنما:
//   - invariant: value همیشه بین minimum و maximum است.
//   - هیچ setter عمومی برای value نگذارید؛ فقط increment/decrement/reset.

#include <iostream>

// TODO: تعریف کلاس Counter

int main() {
    // TODO: تمرین کردن سناریوها

    return 0;
}
