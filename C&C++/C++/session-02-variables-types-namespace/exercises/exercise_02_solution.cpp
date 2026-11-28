// exercise_02_solution.cpp — راه‌حل تمرین ۲: تبدیل سلسیوس به فارنهایت

#include <iostream>

int main() {
    double celsius = 0.0;

    std::cout << "دما به سلسیوس: ";
    std::cin >> celsius;

    // 9.0 / 5.0 تقسیم اعشاری است؛ نوشتن 9 / 5 نتیجه را 1 می‌کرد
    const double fahrenheit = celsius * 9.0 / 5.0 + 32.0;

    std::cout << celsius << " سلسیوس = " << fahrenheit << " فارنهایت\n";

    return 0;
}
