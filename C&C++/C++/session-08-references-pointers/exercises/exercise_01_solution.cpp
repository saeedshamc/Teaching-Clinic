// exercise_01_solution.cpp — راه‌حل تمرین ۱: افزایش مقدار با دو روش
// کامپایل: g++ -std=c++17 -Wall -Wextra -o increase exercises/exercise_01_solution.cpp

#include <iostream>

void increaseByRef(int& value, int amount) {
    value += amount;
}

void increaseByPointer(int* value, int amount) {
    if (value == nullptr) {
        return;
    }
    *value += amount;
}

int main() {
    int number = 0;
    std::cout << "عدد: ";
    if (!(std::cin >> number)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }

    increaseByRef(number, 5);
    std::cout << "بعد از increaseByRef(…, 5)     : " << number << "\n";

    increaseByPointer(&number, 5);
    std::cout << "بعد از increaseByPointer(…, 5) : " << number << "\n";

    // دقت کنید: در نسخهٔ pointer باید & بدهیم، در نسخهٔ reference نه
    return 0;
}
