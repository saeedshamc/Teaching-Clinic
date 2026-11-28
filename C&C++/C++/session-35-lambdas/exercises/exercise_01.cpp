// exercise_01.cpp — تمرین ۱: فیلتر کردن با لامبدا
//
// TODO: تابع filter را پیاده کنید:
//   std::vector<int> filter(const std::vector<int>& data,
//                           const std::function<bool(int)>& predicate);
//   که عناصری را برمی‌گرداند که predicate برایشان true است.
//
// سپس سه فیلتر با لامبدا بسازید:
//   ۱) اعداد زوج
//   ۲) بزرگ‌تر از threshold که با capture by value گرفته می‌شود
//   ۳) خارج از بازهٔ [3, 8]
//
// نمونهٔ انتظاری:
//   زوج‌ها: 2 4 6 8 10
//   بزرگ‌تر از 6: 7 8 9 10
//   خارج از [3, 8]: 1 2 9 10
//
// راهنما:
//   std::vector<int> result;
//   for (int value : data) {
//       if (predicate(value)) result.push_back(value);
//   }
//   return result;
//
//   int threshold = 6;
//   const auto above = [threshold](int value) { return value > threshold; };

#include <algorithm>
#include <functional>
#include <iostream>
#include <vector>

// TODO: تابع filter

int main() {
    const std::vector<int> values{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    // TODO: سه فیلتر و چاپ نتیجه

    return 0;
}
