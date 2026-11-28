// 01_hello.cpp — اولین برنامهٔ C++ در این دوره
// این فایل را با دستور زیر کامپایل کنید:
//   g++ -std=c++17 -Wall -Wextra -o hello examples/01_hello.cpp

#include <iostream>  // کتابخانهٔ استاندارد ورودی/خروجی (برای std::cout)

int main() {  // تابع main: هر برنامهٔ C++ از این‌جا شروع می‌شود
    std::cout << "Hello, C++!" << std::endl;  // چاپ پیام و رفتن به خط بعد
    return 0;                                 // کد خروج ۰ یعنی «موفق»
}
