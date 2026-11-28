// exercise_01.cpp — تمرین ۱: کلاس StringBuilder با زنجیره‌سازی
// TODO: کلاسی به نام StringBuilder بسازید:
//
//   - StringBuilder& append(const std::string& text);   // متن را اضافه می‌کند
//   - StringBuilder& appendLine(const std::string& text); // متن + '\n'
//   - StringBuilder& clear();
//   - const std::string& str() const;
//   - std::size_t size() const;
//   - bool empty() const;
//
// همهٔ متدهای تغییردهنده باید *this را برگردانند تا این شکل کار کند:
//
//   builder.append("سلام").append("، ").appendLine("دنیا").append("پایان");
//
// در main:
//   ۱) یک زنجیره بسازید و نتیجه را چاپ کنید
//   ۲) size را قبل و بعد از clear چاپ کنید
//   ۳) نشان دهید زنجیره روی reference کار می‌کند، نه روی کپی
//      (مثلاً StringBuilder& ref = builder.append("x"); )
//
// نمونهٔ انتظاری:
//   متن:
//   سلام، دنیا
//   پایان
//   size = ...
//
// راهنما: append باید StringBuilder& برگرداند، نه StringBuilder.
//          اگر با ارزش برگرداند، روی کپی کار می‌کنید و تغییرات گم می‌شوند.

#include <iostream>
#include <string>

// TODO: تعریف کلاس StringBuilder

int main() {
    // TODO: ساختن و استفاده از StringBuilder

    return 0;
}
