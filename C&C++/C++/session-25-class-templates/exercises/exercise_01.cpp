// exercise_01.cpp — تمرین ۱: قالب کلاس Pair<A, B>
// TODO: کلاسی قالبی بسازید که دو مقدار از انواع مختلف نگه دارد:
//
//   template <typename A, typename B>
//   class Pair {
//   public:
//       Pair(const A& first, const B& second);
//       const A& first() const;
//       const B& second() const;
//       void setFirst(const A& value);
//       void setSecond(const B& value);
//       void swap();                  // جای first و second را عوض می‌کند
//       std::string toText() const;    // "(first, second)"
//   };
//
// همچنین یک تابع کارخانه‌ای بسازید تا نوع‌ها استنتاج شوند:
//
//   template <typename A, typename B>
//   Pair<A, B> makePair(const A& first, const B& second);
//
// در main:
//   ۱) makePair(1, "یک") بسازید و toText را چاپ کنید
//   ۲) makePair(2.5, 'x') بسازید
//   ۳) setSecond را روی اولی صدا بزنید
//   ۴) یک Pair<int, int> بسازید و swap را امتحان کنید
//
// نمونهٔ انتظاری:
//   pair1 = (1, یک)
//   pair2 = (2.5, x)
//   بعد از setSecond: (1, یک‌بار)
//   قبل از swap: (10, 20)
//   بعد از swap: (20, 10)
//
// راهنما: swap() ساده است اما برای انواع مختلف باید از یک متغیر موقت
//          از نوع A و B استفاده کنید (یا std::swap).
//          toText را با ostringstream بنویسید تا با هر نوعی کار کند.

#include <iostream>
#include <string>

// TODO: تعریف Pair و makePair

int main() {
    // TODO: ساختن Pair‌ها و آزمایش

    return 0;
}
