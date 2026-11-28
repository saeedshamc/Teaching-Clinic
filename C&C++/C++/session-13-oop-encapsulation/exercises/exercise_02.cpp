// exercise_02.cpp — تمرین ۲: کلاس Student با اعتبارسنجی
// TODO: کلاس Student بسازید که همیشه در وضعیت معتبر بماند:
//
//   - name_ : غیرخالی
//   - score_ : بین 0 و 20
//   - bool setName(const std::string& value);   // رشتهٔ خالی یا فقط فاصله رد شود
//   - bool setScore(double value);              // خارج از 0..20 رد شود
//   - const std::string& name() const;
//   - double score() const;
//   - bool isPassed() const;   // نمره >= 10
//   - void print() const;      // "Saeed: 18.5 → قبول"
//
// در main چند مقدار نامعتبر و معتبر امتحان کنید و ببینید وضعیت قبلی حفظ می‌شود.
//
// نمونه:
//   setScore(25) پذیرفته شد؟ خیر
//   setScore(18.5) پذیرفته شد؟ بله
//   Saeed: 18.50 → قبول
//
// راهنما:
//   - برای بررسی «رشتهٔ خالی یا فقط فاصله»، با find_first_not_of(" \t") کار کنید:
//     اگر نتیجه npos بود، رشته هیچ کاراکتر غیرفاصله‌ای ندارد.

#include <iostream>
#include <string>

// TODO: تعریف کلاس Student

int main() {
    // TODO: امتحان مقادیر نامعتبر و معتبر

    return 0;
}
