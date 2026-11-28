// exercise_02.cpp — تمرین ۲: کلاس Student با شناسهٔ فقط‌خواندنی
// TODO: کلاسی به نام Student بسازید:
//
//   - سازنده: Student(const std::string& name, int id)
//       * id از سازنده می‌آید و **هیچ setter ندارد** — تغییرناپذیر است
//       * نام خالی (فقط فاصله) → "unknown"
//   - const std::string& name() const;
//   - int id() const;
//   - bool addGrade(double grade);    // فقط 0..20 پذیرفته شود
//   - int gradeCount() const;
//   - double average() const;         // اگر نمره‌ای نیست → 0.0
//   - bool isPassed() const;          // average >= 10
//
// محدودیت: نمره‌ها در یک آرایهٔ ثابت به اندازهٔ ۵ نگه داشته شوند
// و از آرایهٔ بیرون از کلاس استفاده نشود.
//
// نمونهٔ انتظاری:
//   Student #1001: Saeed
//   addGrade(18.5) → موفق
//   addGrade(25) → رد شد
//   ...
//   میانگین: 15.17 ، وضعیت: قبول
//
// راهنما: میانگین را از مجموع و تعداد حساب کنید، نه با ذخیرهٔ میانگین.
//          اگر تعداد نمره‌ها 0 بود، تقسیم بر صفر نکنید.

#include <iostream>
#include <string>

// TODO: تعریف کلاس Student

int main() {
    // TODO: ساختن شیء، افزودن نمره‌های معتبر و نامعتبر، چاپ میانگین

    return 0;
}
