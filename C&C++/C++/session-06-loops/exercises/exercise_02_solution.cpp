// exercise_02_solution.cpp — راه‌حل تمرین ۲: معکوس عدد و جمع رقم‌ها

#include <iostream>

int main() {
    long long number = 0;
    std::cout << "عدد: ";
    std::cin >> number;

    if (number < 0) {
        std::cout << "لطفاً عدد مثبت وارد کنید.\n";
        return 1;
    }

    long long remaining = number;
    long long reversed = 0;
    int digitSum = 0;

    while (remaining > 0) {
        const int digit = static_cast<int>(remaining % 10);
        reversed = reversed * 10 + digit;
        digitSum += digit;
        remaining /= 10;
    }

    std::cout << "معکوس: " << reversed << "\n";
    std::cout << "جمع رقم‌ها: " << digitSum << "\n";

    return 0;
}
