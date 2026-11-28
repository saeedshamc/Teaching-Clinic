// exercise_02_solution.cpp — راه‌حل تمرین ۲: تقسیم صحیح با دو خروجی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o divide exercises/exercise_02_solution.cpp

#include <iostream>

bool divide(int dividend, int divisor, int& quotient, int& remainder) {
    if (divisor == 0) {
        return false;              // خروجی‌ها دست‌نخورده می‌مانند
    }
    quotient = dividend / divisor;
    remainder = dividend % divisor;
    return true;
}

int main() {
    int a = 0;
    int b = 0;
    std::cout << "دو عدد (مثلاً 17 5): ";
    if (!(std::cin >> a >> b)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }

    int quotient = 0;
    int remainder = 0;

    if (divide(a, b, quotient, remainder)) {
        std::cout << "خارج‌قسمت: " << quotient << " ، باقیمانده: " << remainder << "\n";
    } else {
        std::cout << "تقسیم بر صفر مجاز نیست.\n";
    }

    // چون خروجی‌ها با reference پر می‌شوند، مقدارشان بعد از فراخوانی در دسترس است
    return 0;
}
