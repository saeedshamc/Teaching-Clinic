// exercise_01_solution.cpp — راه‌حل تمرین ۱: تشخیص عدد اول

#include <iostream>

int main() {
    long long number = 0;
    std::cout << "عدد: ";
    std::cin >> number;

    if (number < 2) {
        std::cout << number << " عدد اول نیست.\n";
        return 0;
    }

    bool isPrime = true;
    for (long long divisor = 2; divisor * divisor <= number; ++divisor) {
        if (number % divisor == 0) {
            isPrime = false;
            break;   // بخش‌پذیر پیدا شد، ادامه لازم نیست
        }
    }

    if (isPrime) {
        std::cout << number << " عدد اول است.\n";
    } else {
        std::cout << number << " عدد اول نیست.\n";
    }

    return 0;
}
