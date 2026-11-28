// exercise_01_solution.cpp — راه‌حل تمرین ۱: ماشین‌حساب ساده

#include <iostream>

int main() {
    double first = 0.0;
    double second = 0.0;
    char op = ' ';

    std::cout << "عدد اول: ";
    std::cin >> first;
    std::cout << "عملگر: ";
    std::cin >> op;
    std::cout << "عدد دوم: ";
    std::cin >> second;

    if (op == '+') {
        std::cout << "نتیجه: " << first + second << "\n";
    } else if (op == '-') {
        std::cout << "نتیجه: " << first - second << "\n";
    } else if (op == '*') {
        std::cout << "نتیجه: " << first * second << "\n";
    } else if (op == '/') {
        if (second == 0.0) {
            std::cerr << "خطا: تقسیم بر صفر مجاز نیست.\n";
            return 1;
        }
        std::cout << "نتیجه: " << first / second << "\n";
    } else {
        std::cerr << "خطا: عملگر ناشناخته «" << op << "»\n";
        return 1;
    }

    return 0;
}
