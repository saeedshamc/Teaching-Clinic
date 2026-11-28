// exercise_01.cpp — تمرین ۱: قالب swap و maxOf روی vector
// TODO: دو قالب تابع بنویسید:
//
//   ۱) template <typename T>
//      void swapValues(T& a, T& b);
//         مقدار دو متغیر را جابه‌جا می‌کند.
//
//   ۲) template <typename T>
//      T maxOf(const std::vector<T>& values);
//         بزرگ‌ترین عضو را برمی‌گرداند. اگر بردار خالی بود،
//         T() (مقدار پیش‌فرض نوع) را برگردانید.
//         فرض کنید T قابل مقایسه با < است.
//
// در main:
//   ۱) swap دو int و دو std::string را نشان دهید
//   ۲) maxOf را برای vector<int>، vector<double> و vector<std::string> صدا بزنید
//   ۳) یک بردار خالی از int بسازید و نتیجهٔ maxOf را چاپ کنید
//
// نمونهٔ انتظاری:
//   swap int: a = 20 ، b = 10
//   swap string: first = دنیا ، second = سلام
//   max int = 12
//   max double = 9.5
//   max string = peach
//   max خالی = 0
//
// راهنما: در maxOf، از values.front() شروع کنید و از index 1 جلو بروید.
//          هرگز inside حلقه از size() - 1 استفاده نکنید (اگر خالی باشد،
//          عدد منفی و مشکل ایجاد می‌شود).
//          برای رشته‌ها، مقایسهٔ < ترتیب الفبایی می‌دهد.

#include <iostream>
#include <string>
#include <vector>

// TODO: تعریف قالب‌های swapValues و maxOf

int main() {
    // TODO: آزمایش با int، double و string

    return 0;
}
