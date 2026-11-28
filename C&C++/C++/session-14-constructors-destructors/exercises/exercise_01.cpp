// exercise_01.cpp — تمرین ۱: کلاس Timer با سازنده و مخرب
// TODO: کلاسی به نام Timer بسازید که زمان اجرای یک بلوک کد را اندازه بگیرد:
//
//   - سازنده: Timer(const std::string& label)
//       * label را نگه می‌دارد
//       * زمان شروع را با std::chrono::steady_clock::now() ثبت می‌کند
//   - مخرب: ~Timer()
//       * زمان پایان را ثبت می‌کند
//       * مدت را به میکروثانیه چاپ می‌کند، مثلاً:
//           [Timer] مرحلهٔ سنگین: 1234 µs
//
//   نکته: هیچ متد دستی «stop» لازم نیست؛ مخرب خودش هنگام خروج از بلوک اجرا می‌شود.
//
// در main:
//   ۱) یک Timer برای کل برنامه بسازید
//   ۲) داخل یک بلوک {} یک حلقهٔ جمع‌زننده بنویسید و با Timer جدا زمانش را بگیرید
//   ۳) مطمئن شوید ترتیب چاپ، پایان بلوک را نشان می‌دهد
//
// راهنما:
//   #include <chrono>
//   auto start = std::chrono::steady_clock::now();
//   auto end   = std::chrono::steady_clock::now();
//   auto us    = std::chrono::duration_cast<std::chrono::microseconds>(end - start).count();

#include <chrono>
#include <iostream>
#include <string>

// TODO: تعریف کلاس Timer

int main() {
    // TODO: اندازه‌گیری زمان کل و زمان یک بلوک

    return 0;
}
