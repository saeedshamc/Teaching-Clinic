// exercise_01_solution.cpp — راه‌حل تمرین ۱: دریافت نام کامل

#include <iostream>
#include <string>

int main() {
    std::string fullName;
    int age = 0;

    std::cout << "نام و نام خانوادگی: ";
    std::getline(std::cin, fullName);

    std::cout << "سن: ";
    std::cin >> age;

    std::cout << "سلام " << fullName << "، شما " << age << " سال دارید.\n";
    std::cout << "طول نام کامل شما " << fullName.size() << " کاراکتر است.\n";

    return 0;
}
