// 03_swap_by_ref.cpp — چرا reference لازم است: swap واقعی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o swap_by_ref examples/03_swap_by_ref.cpp

#include <iostream>
#include <utility>

// --- ۱. با مقدار: فقط کپی‌ها جابه‌جا می‌شوند ---
void swapByValue(int a, int b) {
    int temp = a;
    a = b;
    b = temp;
    std::cout << "  داخل swapByValue: a = " << a << ", b = " << b << "\n";
}

// --- ۲. با reference: خود متغیرها جابه‌جا می‌شوند ---
void swapByRef(int& a, int& b) {
    int temp = a;
    a = b;
    b = temp;
}

// --- ۳. با pointer: همان کار، ولی با بررسی nullptr ---
void swapByPointer(int* a, int* b) {
    if (a == nullptr || b == nullptr) {
        return;
    }
    int temp = *a;
    *a = *b;
    *b = temp;
}

int main() {
    int x = 1;
    int y = 2;

    std::cout << "شروع: x = " << x << ", y = " << y << "\n\n";

    std::cout << "۱) swapByValue:\n";
    swapByValue(x, y);
    std::cout << "  بعد از فراخوانی: x = " << x << ", y = " << y << "  → تغییری نکرد\n\n";

    std::cout << "۲) swapByRef:\n";
    swapByRef(x, y);
    std::cout << "  بعد از فراخوانی: x = " << x << ", y = " << y << "\n\n";

    std::cout << "۳) swapByPointer:\n";
    swapByPointer(&x, &y);
    std::cout << "  بعد از فراخوانی: x = " << x << ", y = " << y << "\n\n";

    std::cout << "۴) std::swap از <utility>:\n";
    std::swap(x, y);
    std::cout << "  بعد از فراخوانی: x = " << x << ", y = " << y << "\n\n";

    std::cout << "۵) با سه مقدار (متغیر موقت لازم است):\n";
    int a = 1;
    int b = 2;
    int c = 3;
    std::swap(a, b);
    std::swap(b, c);
    std::cout << "  a = " << a << ", b = " << b << ", c = " << c << "\n";

    return 0;
}
