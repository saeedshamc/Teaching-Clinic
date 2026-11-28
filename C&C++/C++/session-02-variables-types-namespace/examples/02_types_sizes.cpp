// 02_types_sizes.cpp — اندازه و بازهٔ انواع پایه
// کامپایل: g++ -std=c++17 -Wall -Wextra -o sizes examples/02_types_sizes.cpp

#include <iostream>
#include <limits>  // برای std::numeric_limits

int main() {
    // sizeof اندازه را بر حسب بایت می‌دهد و نوعش std::size_t است
    std::cout << "char           : " << sizeof(char) << " بایت  بازه: "
              << static_cast<int>(std::numeric_limits<char>::min()) << " تا "
              << static_cast<int>(std::numeric_limits<char>::max()) << "\n";

    std::cout << "short          : " << sizeof(short) << " بایت  بازه: "
              << std::numeric_limits<short>::min() << " تا "
              << std::numeric_limits<short>::max() << "\n";

    std::cout << "int            : " << sizeof(int) << " بایت  بازه: "
              << std::numeric_limits<int>::min() << " تا "
              << std::numeric_limits<int>::max() << "\n";

    std::cout << "long long      : " << sizeof(long long) << " بایت  بازه: "
              << std::numeric_limits<long long>::min() << " تا "
              << std::numeric_limits<long long>::max() << "\n";

    std::cout << "unsigned int   : " << sizeof(unsigned int) << " بایت  بیشینه: "
              << std::numeric_limits<unsigned int>::max() << "\n";

    std::cout << "float          : " << sizeof(float) << " بایت\n";
    std::cout << "double         : " << sizeof(double) << " بایت\n";
    std::cout << "bool           : " << sizeof(bool) << " بایت\n";

    // اندازهٔ متغیر هم همان اندازهٔ نوعش است
    int number = 42;
    std::cout << "sizeof(number) = " << sizeof(number) << " بایت\n";

    return 0;
}
