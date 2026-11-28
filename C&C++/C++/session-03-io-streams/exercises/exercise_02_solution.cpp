// exercise_02_solution.cpp — راه‌حل تمرین ۲: کارنامهٔ مرتب

#include <iomanip>
#include <iostream>

int main() {
    double math = 0.0;
    double physics = 0.0;
    double programming = 0.0;

    std::cout << "نمرهٔ ریاضی: ";
    std::cin >> math;
    std::cout << "نمرهٔ فیزیک: ";
    std::cin >> physics;
    std::cout << "نمرهٔ برنامه‌نویسی: ";
    std::cin >> programming;

    std::cout << std::fixed << std::setprecision(2);

    std::cout << "\n";
    std::cout << std::setw(14) << std::left << "درس"
              << std::setw(8) << std::right << "نمره" << "\n";
    std::cout << std::setw(14) << std::left << "ریاضی"
              << std::setw(8) << std::right << math << "\n";
    std::cout << std::setw(14) << std::left << "فیزیک"
              << std::setw(8) << std::right << physics << "\n";
    std::cout << std::setw(14) << std::left << "برنامه‌نویسی"
              << std::setw(8) << std::right << programming << "\n";

    std::cout << std::setfill('-') << std::setw(22) << "" << std::setfill(' ') << "\n";

    const double average = (math + physics + programming) / 3.0;
    std::cout << std::setw(14) << std::left << "میانگین"
              << std::setw(8) << std::right << average << "\n";

    return 0;
}
