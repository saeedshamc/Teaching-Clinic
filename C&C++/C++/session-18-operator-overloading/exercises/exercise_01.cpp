// exercise_01.cpp — تمرین ۱: کلاس Fraction با عملگرها
// TODO: کلاسی به نام Fraction بسازید:
//
//   - سازنده: Fraction(int numerator, int denominator)
//       * مخرج صفر → خطا (می‌توانید مقدار پیش‌فرض 1 بگذارید)
//       * کسر باید همیشه ساده باشد: 6/8 → 3/4  (ب.م.م)
//       * علامت منفی همیشه روی صورت باشد: 1/-2 → -1/2
//   - int numerator() const;  int denominator() const;
//   - double value() const;
//   - Fraction operator+(const Fraction& other) const;
//   - Fraction operator*(const Fraction& other) const;
//   - bool operator==(const Fraction& other) const;
//   - bool operator<(const Fraction& other) const;
//   - friend std::ostream& operator<<(std::ostream& out, const Fraction& f);
//
// در main:
//   ۱) دو کسر بسازید و ساده شدن را نشان دهید (6/8 → 3/4)
//   ۲) جمع و ضرب را چاپ کنید
//   ۳) مقایسهٔ == و < را چاپ کنید
//   ۴) چاپ مستقیم: std::cout << f;
//
// نمونهٔ انتظاری:
//   6/8 ساده‌شده → 3/4
//   1/2 + 1/3 = 5/6
//   1/2 * 2/3 = 1/3
//   1/2 == 2/4 ؟ بله
//   1/3 < 1/2 ؟ بله
//
// راهنما: تابع کمکی gcd بسازید (یا از std::gcd در <numeric> استفاده کنید).
//          جمع: a/b + c/d = (a*d + c*b) / (b*d) و بعد ساده‌سازی.

#include <iostream>

// TODO: تعریف کلاس Fraction

int main() {
    // TODO: ساختن کسرها و آزمایش عملگرها

    return 0;
}
