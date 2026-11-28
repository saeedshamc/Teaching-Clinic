// 01_c_array.cpp — آرایهٔ خام C: محدودیت‌ها و خطرها
// کامپایل: g++ -std=c++17 -Wall -Wextra -o c_array examples/01_c_array.cpp

#include <iostream>

// آرایه به‌عنوان پارامتر: اندازه‌اش از دست می‌رود و به pointer تبدیل می‌شود!
// نوشتن int data[] در پارامتر دقیقاً همان int* data است.
void printWrong(int* data) {
    // sizeof(int*) اندازهٔ اشاره‌گر است، نه اندازهٔ آرایه؛ اگر بنویسید
    // sizeof(data) کامپایلر هشدار -Wsizeof-array-argument می‌دهد.
    std::cout << "  sizeof(int*) = " << sizeof(int*) << " بایت؛ اندازهٔ آرایه گم شده است\n";
    std::cout << "  اولین عنصر از طریق pointer: " << *data << "\n";
}

int main() {
    // اندازه باید ثابت زمان کامپایل باشد
    int scores[5] = {92, 87, 75, 68, 99};

    // تعداد عناصر را با تقسیم اندازه‌ها حساب می‌کنیم
    const std::size_t count = sizeof(scores) / sizeof(scores[0]);
    std::cout << "تعداد عناصر: " << count << "\n";

    std::cout << "نمرات: ";
    for (std::size_t i = 0; i < count; ++i) {
        std::cout << scores[i] << " ";
    }
    std::cout << "\n";

    // مقداردهی جزئی: بقیه صفر می‌شوند
    int partial[5] = {1, 2};
    std::cout << "مقداردهی جزئی: ";
    for (std::size_t i = 0; i < 5; ++i) {
        std::cout << partial[i] << " ";
    }
    std::cout << "\n";

    // محاسبهٔ مجموع و میانگین
    int sum = 0;
    int maxScore = scores[0];
    for (std::size_t i = 0; i < count; ++i) {
        sum += scores[i];
        if (scores[i] > maxScore) {
            maxScore = scores[i];
        }
    }
    std::cout << "مجموع: " << sum << " ، بیشترین: " << maxScore << "\n";
    std::cout << "میانگین: " << static_cast<double>(sum) / static_cast<double>(count) << "\n";

    // --- خطر شمارهٔ ۱: خارج از محدوده ---
    // کامپایلر جلوی این را نمی‌گیرد و بررسی هم در زمان اجرا انجام نمی‌شود:
    //   scores[5] = 100;   → نوشتن در حافظهٔ همسایه، رفتار تعریف‌نشده
    std::cout << "\nهیچ بررسی‌ای برای scores[10] وجود ندارد؛ این آرایه امن نیست.\n";

    // --- خطر شمارهٔ ۲: تبدیل به pointer در پارامتر ---
    // نام آرایه به pointer اولین عنصر تبدیل می‌شود (array decay)
    printWrong(scores);

    // --- آرایهٔ دوبعدی ---
    int matrix[2][3] = {{1, 2, 3}, {4, 5, 6}};
    std::cout << "\nماتریس 2x3:\n";
    for (std::size_t row = 0; row < 2; ++row) {
        for (std::size_t col = 0; col < 3; ++col) {
            std::cout << matrix[row][col] << " ";
        }
        std::cout << "\n";
    }

    return 0;
}
