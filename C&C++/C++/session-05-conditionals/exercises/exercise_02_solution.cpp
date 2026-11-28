// exercise_02_solution.cpp — راه‌حل تمرین ۲: منوی مساحت

#include <iomanip>
#include <iostream>

int main() {
    constexpr double pi = 3.14159;

    char choice = ' ';
    std::cout << "انتخاب (c/r/t): ";
    std::cin >> choice;

    std::cout << std::fixed << std::setprecision(2);

    switch (choice) {
        case 'c': {
            double radius = 0.0;
            std::cout << "شعاع: ";
            std::cin >> radius;
            std::cout << "مساحت دایره = " << pi * radius * radius << "\n";
            break;
        }
        case 'r': {
            double width = 0.0;
            double height = 0.0;
            std::cout << "طول: ";
            std::cin >> width;
            std::cout << "عرض: ";
            std::cin >> height;
            std::cout << "مساحت مستطیل = " << width * height << "\n";
            break;
        }
        case 't': {
            double base = 0.0;
            double height = 0.0;
            std::cout << "قاعده: ";
            std::cin >> base;
            std::cout << "ارتفاع: ";
            std::cin >> height;
            std::cout << "مساحت مثلث = " << base * height / 2.0 << "\n";
            break;
        }
        default:
            std::cerr << "گزینهٔ نامعتبر\n";
            return 1;
    }

    return 0;
}
