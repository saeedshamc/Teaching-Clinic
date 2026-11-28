// 03_division_and_overflow.cpp — تقسیم صحیح، باقی‌ماندهٔ منفی، سرریز و اعشاری
// کامپایل: g++ -std=c++17 -Wall -Wextra -o div examples/03_division_and_overflow.cpp

#include <cmath>
#include <iostream>
#include <limits>

int main() {
    // --- تقسیم صحیح ---
    std::cout << "7 / 2   = " << 7 / 2 << "   (بخش اعشاری حذف می‌شود)\n";
    std::cout << "7.0 / 2 = " << 7.0 / 2 << "\n";
    std::cout << "7 % 2   = " << 7 % 2 << "\n";

    // باقی‌مانده با عدد منفی: علامتِ تقسیم‌شونده را می‌گیرد
    std::cout << "-7 / 2  = " << -7 / 2 << "\n";
    std::cout << "-7 % 2  = " << -7 % 2 << "\n";

    // --- سرریز: مقایسهٔ ایمن ---
    const int big1 = 100000;
    const int big2 = 100000;
    // int bad = big1 * big2;  // بیرون از بازهٔ int → رفتار تعریف‌نشده
    const long long safe = static_cast<long long>(big1) * big2;
    std::cout << "\nضرب ایمن = " << safe << "\n";

    std::cout << "بیشینهٔ int = " << std::numeric_limits<int>::max() << "\n";

    // بررسی قبل از جمع، به‌جای اجازه دادن به سرریز
    const int nearMax = std::numeric_limits<int>::max() - 5;
    constexpr int step = 10;
    if (nearMax > std::numeric_limits<int>::max() - step) {
        std::cout << "هشدار: جمع " << step << " باعث سرریز می‌شود؛ انجام نشد.\n";
    }

    // --- سرریز unsigned: تعریف‌شده اما خطرناک ---
    const unsigned int zero = 0u;
    std::cout << "0u - 1u = " << zero - 1u << "   (دور می‌زند)\n";

    // --- مقایسهٔ اعشاری ---
    const double sum = 0.1 + 0.2;
    std::cout << "\n0.1 + 0.2 = " << sum << "\n";
    std::cout << "0.1 + 0.2 == 0.3 : " << std::boolalpha << (sum == 0.3) << "\n";

    constexpr double eps = 1e-9;
    const bool almostEqual = std::fabs(sum - 0.3) < eps;
    std::cout << "مقایسه با اختلاف کوچک : " << almostEqual << "\n";

    return 0;
}
