// exercise_01.cpp — تمرین ۱: حذف اعداد زوج از vector
//
// TODO: تابع removeEvens را پیاده کنید تا همهٔ اعداد زوج را از vector حذف کند
//       و تعداد حذف‌شده‌ها را برگرداند.
//
// نکته: وقتی با erase حذف می‌کنید، تکرارگر جاری نامعتبر می‌شود؛
//       از مقدار بازگشتی erase استفاده کنید تا به عنصر بعدی بروید.
//
// نمونهٔ انتظاری:
//   قبل: [3, 8, 12, 5, 7, 10, 21, 4]
//   حذف‌شده: 4
//   بعد: [3, 5, 7, 21]
//   capacity بعد از shrink_to_fit: 4
//
// راهنما:
//   int removeEvens(std::vector<int>& data) {
//       int removed = 0;
//       for (auto it = data.begin(); it != data.end();) {
//           if (...) {
//               it = data.erase(it);
//               ++removed;
//           } else {
//               ++it;
//           }
//       }
//       return removed;
//   }

#include <iostream>
#include <vector>

// TODO: تابع removeEvens

int main() {
    // TODO: vector را بسازید، قبل/بعد را چاپ کنید و capacity را بررسی کنید

    return 0;
}
