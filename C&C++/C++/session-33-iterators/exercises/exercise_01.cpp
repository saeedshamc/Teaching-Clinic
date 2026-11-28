// exercise_01.cpp — تمرین ۱: پیمایش معکوس با rbegin/rend
//
// TODO: دو کار انجام دهید:
//   ۱) تابع printReverse که یک vector<int> می‌گیرد و با rbegin/rend معکوس چاپ می‌کند
//   ۲) تابع lastGreater که با reverse_iterator آخرین عنصر بزرگ‌تر از حد را
//      پیدا می‌کند و اندیس واقعی آن را برمی‌گرداند (-1 اگر نبود)
//
// نکته: reverse_iterator با base() به تکرارگر رو به جلو تبدیل می‌شود؛
//       اندیس واقعی = distance(begin, rit.base()) - 1
//
// نمونهٔ انتظاری:
//   معکوس: 5 4 3 2 1
//   آخرین بزرگ‌تر از 3 → اندیس 4 (مقدار 5)
//   آخرین بزرگ‌تر از 10 → پیدا نشد (-1)
//   distance(rbegin, rend) = 5
//
// راهنما:
//   for (auto rit = data.rbegin(); rit != data.rend(); ++rit) { ... }
//   for (auto rit = data.rbegin(); rit != data.rend(); ++rit) {
//       if (*rit > threshold) { return static_cast<int>(std::distance(data.begin(), rit.base())) - 1; }
//   }

#include <iostream>
#include <iterator>
#include <vector>

// TODO: دو تابع

int main() {
    // TODO: vector {1,2,3,4,5} را بسازید و چهار خط خروجی را تولید کنید

    return 0;
}
