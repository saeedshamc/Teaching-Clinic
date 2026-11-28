// exercise_01_solution.cpp — راه‌حل تمرین ۱: تابع توان
// کامپایل: g++ -std=c++17 -Wall -Wextra -o power exercises/exercise_01_solution.cpp

#include <iostream>

// پیش‌فرض فقط در اعلان نوشته می‌شود
long long power(int base, int exponent = 2);
double power(double base, int exponent);

long long power(int base, int exponent) {
    if (exponent < 0) {
        // نسخهٔ صحیح توان منفی ندارد؛ ۰ را به‌عنوان «نامعتبر» برمی‌گردانیم
        return 0;
    }
    long long result = 1;
    for (int i = 0; i < exponent; ++i) {
        result *= base;
    }
    return result;
}

double power(double base, int exponent) {
    if (exponent == 0) {
        return 1.0;
    }
    const bool negative = exponent < 0;
    int steps = negative ? -exponent : exponent;

    double result = 1.0;
    for (int i = 0; i < steps; ++i) {
        result *= base;
    }
    return negative ? 1.0 / result : result;
}

int main() {
    std::cout << "power(5)       = " << power(5) << "\n";
    std::cout << "power(2, 10)   = " << power(2, 10) << "\n";
    std::cout << "power(2.0, -2) = " << power(2.0, -2) << "\n";
    std::cout << "power(-3, 3)   = " << power(-3, 3) << "\n";
    std::cout << "power(9.0, 0)  = " << power(9.0, 0) << "\n";

    return 0;
}
