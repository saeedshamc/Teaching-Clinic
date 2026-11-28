// 01_variables.cpp — تعریف متغیر، مقداردهی و ثابت‌ها
// کامپایل: g++ -std=c++17 -Wall -Wextra -o vars examples/01_variables.cpp

#include <iostream>
#include <string>

int main() {
    // ---- تعریف با مقدار اولیه ----
    int age = 25;                 // عدد صحیح
    double height = 1.82;         // عدد اعشاری
    char grade = 'A';             // یک کاراکتر
    bool isStudent = true;        // منطقی
    std::string name = "Saeed";   // رشته (جزئیاتش در جلسهٔ ۱۰)

    // ---- ثابت‌ها ----
    const int daysInWeek = 7;         // ثابت زمان اجرا
    constexpr int hoursInDay = 24;    // ثابت زمان کامپایل

    std::cout << "name = " << name << "\n";
    std::cout << "age = " << age << "\n";

    // متغیر عادی را می‌شود تغییر داد
    age = 26;
    std::cout << "age بعد از تغییر = " << age << "\n";

    // خط زیر عمداً کامنت شده است؛ اگر کامنت را بردارید، کامپایلر خطا می‌دهد:
    // daysInWeek = 8;   // error: assignment of read-only variable

    std::cout << "height = " << height << "\n";
    std::cout << "grade = " << grade << "\n";
    std::cout << "isStudent = " << isStudent << "\n";  // bool به‌صورت 1/0 چاپ می‌شود
    std::cout << "daysInWeek = " << daysInWeek
              << ", hoursInDay = " << hoursInDay << "\n";

    // مقداردهی یکنواخت (uniform initialization) — سبک مدرن
    int score{18};
    double ratio{0.75};
    std::cout << "score = " << score << ", ratio = " << ratio << "\n";

    return 0;
}
