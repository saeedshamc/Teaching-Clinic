// 03_ternary.cpp — عملگر سه‌گانه و مقایسهٔ خوانایی با if
// کامپایل: g++ -std=c++17 -Wall -Wextra -o tern examples/03_ternary.cpp

#include <iostream>

int main() {
    const int a = 12;
    const int b = 7;
    const int c = 19;

    // انتخاب بین دو مقدار ساده
    const int maxOfTwo = (a > b) ? a : b;
    std::cout << "بیشینهٔ " << a << " و " << b << " = " << maxOfTwo << "\n";

    // بیشینهٔ سه عدد با تودرتوی ?: (کار می‌کند، ولی کمی سخت‌خوان است)
    const int maxOfThree = (a > b) ? ((a > c) ? a : c) : ((b > c) ? b : c);
    std::cout << "بیشینهٔ سه عدد = " << maxOfThree << "\n";

    // همان کار با if: خواناتر برای منطق چندشاخه
    int maxByIf = a;
    if (b > maxByIf) {
        maxByIf = b;
    }
    if (c > maxByIf) {
        maxByIf = c;
    }
    std::cout << "همان نتیجه با if = " << maxByIf << "\n";

    // زوج یا فرد
    const int number = 42;
    std::cout << number << " عدد "
              << ((number % 2 == 0) ? "زوج" : "فرد") << " است.\n";

    // رشتهٔ وضعیت
    const int score = 65;
    const std::string status = (score >= 50) ? "قبول" : "مردود";
    std::cout << "وضعیت: " << status << "\n";

    // ?: داخل cout
    std::cout << "آیا قبول است؟ " << (score >= 50 ? "بله" : "خیر") << "\n";

    return 0;
}
