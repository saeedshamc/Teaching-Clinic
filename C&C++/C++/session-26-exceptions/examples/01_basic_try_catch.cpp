// 01_basic_try_catch.cpp — throw، try و catch
// کامپایل: g++ -std=c++17 -Wall -Wextra -o basic examples/01_basic_try_catch.cpp

#include <iostream>
#include <stdexcept>
#include <string>

double divide(double numerator, double denominator) {
    if (denominator == 0.0) {
        throw std::invalid_argument("تقسیم بر صفر مجاز نیست");
    }
    return numerator / denominator;
}

void riskyOperation(int code) {
    if (code == 1) {
        throw std::runtime_error("خطای زمان اجرا");
    }
    if (code == 2) {
        throw 42;                       // پرتاب int — مجاز ولی بد
    }
    std::cout << "  عملیات با کد " << code << " موفق بود\n";
}

int main() {
    std::cout << "--- تقسیم درست ---\n";
    try {
        std::cout << "10 / 2 = " << divide(10.0, 2.0) << "\n";
    } catch (const std::exception& error) {
        std::cout << "خطا: " << error.what() << "\n";
    }

    std::cout << "\n--- تقسیم بر صفر ---\n";
    try {
        std::cout << divide(1.0, 0.0) << "\n";
        std::cout << "این خط اجرا نمی‌شود\n";
    } catch (const std::invalid_argument& error) {
        std::cout << "گرفته شد (invalid_argument): " << error.what() << "\n";
    } catch (const std::exception& error) {
        std::cout << "گرفته شد (exception): " << error.what() << "\n";
    }

    std::cout << "\n--- انواع مختلف ---\n";
    for (int code = 0; code <= 2; ++code) {
        try {
            riskyOperation(code);
        } catch (const std::runtime_error& error) {
            std::cout << "  runtime_error: " << error.what() << "\n";
        } catch (int value) {
            std::cout << "  int گرفته شد: " << value << "\n";
        } catch (...) {
            std::cout << "  نوع ناشناخته\n";
        }
    }

    // ترتیب catch مهم است: type مشتق باید قبل از پایه بیاید.
    //   catch (const std::exception&) قبل از catch (const std::invalid_argument&)
    //   باعث می‌شود شاخهٔ دوم هرگز اجرا نشود.

    std::cout << "\nبرنامه ادامه پیدا کرد — استثنا برنامه را نکشت.\n";

    return 0;
}
