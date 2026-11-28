// exercise_01.cpp — تمرین ۱: کلاس اندازه‌گیری زمان با RAII
//
// TODO: کلاس Timer را کامل کنید:
//   - در سازنده برچسب را ذخیره کند و زمان شروع را بگیرد
//   - در مخرب زمان پایان را بگیرد و حکم «کند» یا «سریع» را چاپ کند
//       (کند اگر >= 50 میلی‌ثانیه، وگرنه سریع)
//   - کپی ممنوع باشد
//
// نمونهٔ انتظاری:
//   --- عملیات کند ---
//     شروع: پردازش سنگین
//     ... کار ...
//     پایان: پردازش سنگین → کند
//   --- عملیات سریع ---
//     شروع: پردازش سبک
//     پایان: پردازش سبک → سریع
//
// راهنما:
//   #include <chrono>
//   #include <thread>
//   const auto start = std::chrono::steady_clock::now();
//   const auto elapsed = std::chrono::duration_cast<std::chrono::milliseconds>(
//                            std::chrono::steady_clock::now() - start).count();
//   std::this_thread::sleep_for(std::chrono::milliseconds(80));

#include <chrono>
#include <iostream>
#include <string>
#include <thread>

class Timer {
public:
    explicit Timer(const std::string& label)
        : label_(label), start_(std::chrono::steady_clock::now()) {
        std::cout << "    شروع: " << label_ << "\n";
    }

    // TODO: مخرب و ممنوعیت کپی

private:
    std::string label_;
    std::chrono::steady_clock::time_point start_;
};

int main() {
    // TODO: یک عملیات کند (حدود ۸۰ میلی‌ثانیه) و یک عملیات سریع را اندازه بگیرید

    return 0;
}
