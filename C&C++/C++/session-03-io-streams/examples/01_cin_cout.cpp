// 01_cin_cout.cpp — خواندن چند مقدار، محاسبه و گزارش خطا
// کامپایل: g++ -std=c++17 -Wall -Wextra -o io examples/01_cin_cout.cpp

#include <iostream>

int main() {
    double a = 0.0;
    double b = 0.0;

    std::cout << "دو عدد وارد کنید: ";

    // cin در شرط if به bool تبدیل می‌شود: موفقیت خواندن
    if (!(std::cin >> a >> b)) {
        std::cerr << "خطا: ورودی نامعتبر است (باید عدد باشد).\n";
        return 1;
    }

    std::cout << "جمع      = " << a + b << "\n";
    std::cout << "تفاضل    = " << a - b << "\n";
    std::cout << "ضرب      = " << a * b << "\n";

    if (b != 0.0) {
        std::cout << "تقسیم    = " << a / b << "\n";
    } else {
        std::cerr << "هشدار: تقسیم بر صفر ممکن نیست.\n";
    }

    // خواندن در حلقه تا وقتی ورودی تمام شود (مثلاً ورودی از فایل)
    std::cout << "چند عدد دیگر بدهید (پایان: Ctrl+D):\n";
    double sum = 0.0;
    int count = 0;
    double value = 0.0;

    while (std::cin >> value) {
        sum += value;
        ++count;
    }

    std::cout << "تعداد = " << count << "\n";
    std::cout << "مجموع = " << sum << "\n";

    return 0;
}
