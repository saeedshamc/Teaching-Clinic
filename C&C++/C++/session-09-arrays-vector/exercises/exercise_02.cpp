// exercise_02.cpp — تمرین ۲: دسترسی امن با at
// TODO: یک vector<int> با اعداد {3, 8, 15, 22} بسازید و سپس:
//
//   ۱) تابعی بنویسید که اندیس را بگیرد و «آیا معتبر است» را برگرداند:
//        bool isValidIndex(const std::vector<int>& data, int index);
//
//   ۲) اگر معتبر بود با data.at(index) مقدار را چاپ کنید،
//      وگرنه پیام «اندیس نامعتبر» بدهید.
//
//   ۳) در پایان، یک دسترسی عمداً خارج از محدوده با at() انجام دهید و
//      استثنای std::out_of_range را در try/catch بگیرید و what() را چاپ کنید.
//
// نمونه:
//   اندیس: 2
//   مقدار: 15
//   ---
//   اندیس: 9
//   اندیس نامعتبر است.
//   ---
//   at(50) استثنا داد: vector::_M_range_check: __n (which is 50) >= this->size() ...
//
// راهنما:
//   - index منفی هم نامعتبر است؛ به همین دلیل پارامتر را int بگیرید نه size_t.
//   - برای مقایسه با size() از static_cast<std::size_t>(index) استفاده کنید.

#include <iostream>
#include <vector>

// TODO: تابع isValidIndex

int main() {
    // TODO: دسترسی معتبر، نامعتبر و نمایش استثنا

    return 0;
}
