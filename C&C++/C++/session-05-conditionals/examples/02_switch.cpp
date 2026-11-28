// 02_switch.cpp — switch روی char و enum class
// کامپایل: g++ -std=c++17 -Wall -Wextra -o sw examples/02_switch.cpp

#include <iostream>

enum class Operation { Add, Subtract, Multiply, Unknown };

Operation toOperation(char symbol) {
    switch (symbol) {
        case '+':
            return Operation::Add;
        case '-':
            return Operation::Subtract;
        case '*':
            return Operation::Multiply;
        default:
            return Operation::Unknown;
    }
}

int main() {
    // --- switch روی char ---
    char choice = ' ';
    std::cout << "گزینه (a=افزودن، d=حذف، l=فهرست، q=خروج): ";
    std::cin >> choice;

    switch (choice) {
        case 'a':
        case 'A':  // ریزش عمدی: هم a و هم A یک کار را می‌کنند
            std::cout << "افزودن مورد جدید\n";
            break;
        case 'd':
        case 'D':
            std::cout << "حذف مورد\n";
            break;
        case 'l':
        case 'L':
            std::cout << "نمایش فهرست\n";
            break;
        case 'q':
        case 'Q':
            std::cout << "خروج\n";
            break;
        default:
            std::cout << "گزینهٔ نامعتبر\n";
            break;
    }

    // --- switch روی enum class ---
    const Operation op = toOperation('*');
    switch (op) {
        case Operation::Add:
            std::cout << "عملیات: جمع\n";
            break;
        case Operation::Subtract:
            std::cout << "عملیات: تفریق\n";
            break;
        case Operation::Multiply:
            std::cout << "عملیات: ضرب\n";
            break;
        case Operation::Unknown:
            std::cout << "عملیات: ناشناخته\n";
            break;
    }

    return 0;
}
