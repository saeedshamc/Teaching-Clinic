// exercise_02_solution.cpp — راه‌حل تمرین ۲: بزرگ‌ترینِ سه عدد
// کامپایل: g++ -std=c++17 -Wall -Wextra -o max3 exercises/exercise_02_solution.cpp

#include <cmath>
#include <iostream>

int maxOf(int a, int b, int c);
double maxOf(double a, double b, double c);

int maxOf(int a, int b, int c) {
    int best = a > b ? a : b;
    return best > c ? best : c;
}

double maxOf(double a, double b, double c) {
    double best = a > b ? a : b;
    return best > c ? best : c;
}

int main() {
    double x = 0.0;
    double y = 0.0;
    double z = 0.0;

    std::cout << "سه عدد را وارد کنید: ";
    if (!(std::cin >> x >> y >> z)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }

    const bool allIntegers = (x == std::floor(x)) &&
                             (y == std::floor(y)) &&
                             (z == std::floor(z));

    if (allIntegers) {
        const int result = maxOf(static_cast<int>(x),
                                 static_cast<int>(y),
                                 static_cast<int>(z));
        std::cout << "بزرگ‌ترین (صحیح): " << result << "\n";
    } else {
        std::cout << "بزرگ‌ترین (اعشاری): " << maxOf(x, y, z) << "\n";
    }

    return 0;
}
