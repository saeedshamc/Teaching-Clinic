// exercise_02_solution.cpp — راه‌حل تمرین ۲: چاپ سه خط با یک دستور std::cout

#include <iostream>

int main() {
    // هر << قطعهٔ بعدی را به خروجی اضافه می‌کند؛ \n فقط خط را می‌شکند.
    std::cout << "1" << "\n"
              << "22" << "\n"
              << "333" << "\n";
    return 0;
}
