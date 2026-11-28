// exercise_01_solution.cpp — راه‌حل تمرین ۱: محاسبهٔ سن

#include <iostream>

int main() {
    constexpr int daysInYear = 365;  // ثابت زمان کامپایل

    int birthYear = 0;
    int currentYear = 0;

    std::cout << "سال تولد: ";
    std::cin >> birthYear;

    std::cout << "سال جاری: ";
    std::cin >> currentYear;

    const int age = currentYear - birthYear;      // سن
    const int daysAlive = age * daysInYear;       // تخمین روزهای زندگی

    std::cout << "سن شما: " << age << " سال\n";
    std::cout << "تقریباً " << daysAlive << " روز زندگی کرده‌اید\n";

    return 0;
}
