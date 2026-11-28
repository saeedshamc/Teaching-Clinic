// exercise_01.cpp — تمرین ۱: کلاس Product با دسترسی کنترل‌شده
// TODO: کلاسی به نام Product بسازید:
//
//   - سازنده: Product(const std::string& name, double price, int stock)
//       * قیمت منفی → 0 ذخیره شود
//       * موجودی منفی → 0 ذخیره شود
//   - const std::string& name() const;
//   - double price() const;      int stock() const;
//   - double inventoryValue() const;     // price * stock
//   - bool setPrice(double value);       // منفی → false و بدون تغییر
//   - bool setStock(int value);          // منفی → false و بدون تغییر
//   - bool sell(int count);              // count <= 0 یا بیشتر از موجودی → false
//   - void restock(int count);           // فقط count > 0
//
// همهٔ اعضای داده private باشند و هیچ setter خامی برای «نام» نباشد.
//
// در main نشان دهید:
//   ۱) قیمت نامعتبر رد می‌شود و مقدار قبلی می‌ماند
//   ۲) فروش بیش از موجودی ناموفق است
//   ۳) inventoryValue بعد از فروش و شارژ دوباره درست محاسبه می‌شود
//
// نمونهٔ انتظاری:
//   Product: Keyboard ، price = 50 ، stock = 10
//   setPrice(-5) → رد شد
//   sell(4) → موفق ، stock = 6
//   sell(100) → ناموفق
//   restock(10) → stock = 16
//   inventoryValue = 800
//
// راهنما: getterها را const بنویسید؛ وگرنه در تابعی که Product& const می‌گیرد
//          قابل استفاده نیستند.

#include <iostream>
#include <string>

// TODO: تعریف کلاس Product

int main() {
    // TODO: ساختن شیء و امتحان کردن سناریوها

    return 0;
}
