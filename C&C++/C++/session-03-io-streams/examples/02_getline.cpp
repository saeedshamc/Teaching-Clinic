// 02_getline.cpp — خواندن نام کامل و مشکل خط باقی‌مانده
// کامپایل: g++ -std=c++17 -Wall -Wextra -o getline examples/02_getline.cpp

#include <iostream>
#include <limits>   // برای std::numeric_limits
#include <string>

int main() {
    int age = 0;
    std::cout << "سن: ";
    std::cin >> age;

    // بدون این خط، getline خط جدیدِ باقی‌مانده را می‌خواند و رشتهٔ خالی می‌دهد
    std::cin.ignore(std::numeric_limits<std::streamsize>::max(), '\n');

    std::string fullName;
    std::cout << "نام و نام خانوادگی: ";
    std::getline(std::cin, fullName);

    std::cout << "سلام " << fullName << "، شما " << age << " سال دارید.\n";
    std::cout << "طول نام کامل شما " << fullName.size() << " کاراکتر است.\n";

    // خواندن یک خط کامل دیگر هم با getline ساده است
    std::string city;
    std::cout << "شهر: ";
    std::getline(std::cin, city);
    std::cout << "شهر شما: " << city << "\n";

    return 0;
}
