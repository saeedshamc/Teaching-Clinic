// 02_dynamic_array.cpp — آرایه‌ای با اندازهٔ زمان اجرا
// کامپایل: g++ -std=c++17 -Wall -Wextra -o dynamic_array examples/02_dynamic_array.cpp
// اجرا: ./dynamic_array

#include <iostream>

int main() {
    int count = 0;
    std::cout << "چند عدد؟ ";
    if (!(std::cin >> count)) {
        std::cerr << "ورودی نامعتبر است.\n";
        return 1;
    }
    if (count <= 0) {
        std::cout << "تعداد باید مثبت باشد.\n";
        return 0;
    }

    // اندازه در زمان اجرا تعیین می‌شود
    int* numbers = new int[static_cast<std::size_t>(count)];

    std::cout << "عددها: ";
    for (int i = 0; i < count; ++i) {
        std::cin >> numbers[i];
    }

    // پردازش
    long long sum = 0;
    int maximum = numbers[0];
    for (int i = 0; i < count; ++i) {
        sum += numbers[i];
        if (numbers[i] > maximum) {
            maximum = numbers[i];
        }
    }

    std::cout << "مجموع: " << sum << "\n";
    std::cout << "بیشینه: " << maximum << "\n";
    std::cout << "میانگین: " << static_cast<double>(sum) / count << "\n";

    // آزادسازی: برای آرایه حتماً delete[] (با براکت)
    delete[] numbers;
    numbers = nullptr;

    // --- اشتباه‌های رایج (اجرا نمی‌شوند) ---
    //   delete numbers;        → برای آرایه غلط است (رفتار تعریف‌نشده)
    //   numbers[0] = 5;        → بعد از delete، استفاده از حافظهٔ آزادشده

    return 0;
}
