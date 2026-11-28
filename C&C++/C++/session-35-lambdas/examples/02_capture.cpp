// 02_capture.cpp — capture: کپی در زمان ساخت، مرجع در زمان اجرا
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o capture examples/02_capture.cpp

#include <algorithm>
#include <iostream>
#include <vector>

int main() {
    int threshold = 5;
    const std::vector<int> values{1, 7, 3, 9, 2, 8};

    // capture by value: مقدار threshold همین‌جا کپی می‌شود
    const auto byValue = [threshold](int value) {
        return value > threshold;
    };

    threshold = 100;   // تغییر بعدی روی کپی داخل لامبدا اثری ندارد

    std::cout << "--- capture by value ---\n";
    std::cout << "  threshold در زمان ساخت: 5، حالا: " << threshold << "\n";
    std::cout << "  تعداد بزرگ‌ترها: "
              << std::count_if(values.begin(), values.end(), byValue) << "\n";

    // capture by reference: هر بار مقدار فعلی خوانده می‌شود
    const auto byReference = [&threshold](int value) {
        return value > threshold;
    };
    std::cout << "  تعداد با capture مرجع: "
              << std::count_if(values.begin(), values.end(), byReference) << "\n";

    // mutable: اجازهٔ تغییر کپی داخلی
    int counter = 0;
    auto countCalls = [counter](int) mutable {
        return ++counter;
    };
    std::cout << "--- mutable ---\n";
    std::cout << "  کپی داخلی: " << countCalls(0) << "، " << countCalls(0) << "\n";
    std::cout << "  counter اصلی: " << counter << "\n";

    // capture همه با = و &
    int a = 1;
    const int b = 2;
    const auto sumAll = [=]() {
        return a + b;
    };
    const auto bump = [&]() {
        a += 10;
    };
    bump();
    std::cout << "--- capture همه ---\n";
    std::cout << "  a بعد از bump: " << a << "، sumAll() (کپی زمان ساخت): " << sumAll() << "\n";

    std::cout << "قاعده: اگر لامبدا بعد از پایان متغیر صدا زده شود، capture مرجع خطرناک است.\n";
    return 0;
}
