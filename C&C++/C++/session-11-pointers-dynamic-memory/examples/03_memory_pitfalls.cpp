// 03_memory_pitfalls.cpp — دام‌های حافظه و جایگزین مدرن
// کامپایل: g++ -std=c++17 -Wall -Wextra -o pitfalls examples/03_memory_pitfalls.cpp

#include <iostream>
#include <vector>

// --- درست: مالک مشخص است و آزادسازی انجام می‌شود ---
int sumOfSquares(int count) {
    std::vector<int> values;              // مالکیت خودکار
    values.reserve(static_cast<std::size_t>(count));

    for (int i = 1; i <= count; ++i) {
        values.push_back(i * i);
    }

    int total = 0;
    for (const int& value : values) {
        total += value;
    }
    return total;                         // vector خودش آزاد می‌شود
}

int main() {
    // --- دام ۱: استفاده بعد از آزادسازی (dangling pointer) ---
    int* value = new int(7);
    delete value;
    value = nullptr;                      // اگر این خط نبود، pointer «آویزان» می‌ماند
    if (value == nullptr) {
        std::cout << "۱) پس از delete، pointer را nullptr کردیم و بررسی می‌کنیم.\n";
    }
    // *value = 10;   → اگر nullptr نبود، نوشتن در حافظهٔ آزادشده: رفتار تعریف‌نشده

    // --- دام ۲: نشتی حافظه (memory leak) ---
    // الگوی غلط:
    //   void leaky() {
    //       int* data = new int[100];
    //       // ... استفاده ...
    //   }   // بدون delete[] → این بلوک تا پایان برنامه گم می‌شود
    //
    // الگوی درست: مالکیت را در یک شیء بگذارید تا با پایان دامنه آزاد شود.
    std::cout << "۲) مجموع مربع‌ها با vector = " << sumOfSquares(5) << "\n";

    // --- دام ۳: double free ---
    //   int* p = new int(1);
    //   delete p;
    //   delete p;      → آزادسازی دوبارهٔ همان حافظه: crash یا خرابی heap

    // --- دام ۴: delete به‌جای delete[] ---
    //   int* arr = new int[10];
    //   delete arr;    → برای آرایه باید delete[] بیاید

    // --- دام ۵: نشت در مسیر خطا ---
    //   int* data = new int[10];
    //   if (somethingFailed) {
    //       return;    → delete[] اجرا نمی‌شود
    //   }
    // راه‌حل: RAII (جلسهٔ ۳۸) یا vector/unique_ptr

    // --- جایگزین مدرن ---
    std::vector<int> numbers{4, 8, 15, 16, 23, 42};
    int total = 0;
    for (const int& number : numbers) {
        total += number;
    }
    std::cout << "۳) مجموع با vector = " << total
              << " ، اندازه = " << numbers.size() << "\n";
    std::cout << "   نه new، نه delete، نه نشتی، نه اندازهٔ ثابت.\n";

    std::cout << "\nنتیجه: در کد روزمرهٔ C++ مدرن، new و delete خام لازم نیستند.\n";

    return 0;
}
