// exercise_01.cpp — تمرین ۱: صف دوطرفهٔ کارها با deque
//
// TODO: برنامه را کامل کنید تا یک صف دوطرفه از کارها بسازید:
//       - کارهای معمولی به انتها اضافه می‌شوند
//       - کارهای فوری به ابتدا اضافه می‌شوند
//       - پردازش همیشه از ابتدا انجام می‌شود
//
// نمونهٔ انتظاری:
//   صف: [طراحی, جلسه, گزارش, ایمیل]
//   پردازش: طراحی
//   پردازش: جلسه
//   پردازش: گزارش
//   پردازش: ایمیل
//   باقی‌مانده: 0 (empty = بله)
//
// نکته: "گزارش" و "ایمیل" با push_back، و "جلسه" و "طراحی" با push_front
//       اضافه می‌شوند؛ پس فوری‌ها جلوی صف می‌ایستند و آخرین فوری اول اجرا می‌شود.
//
// راهنما:
//   std::deque<std::string> tasks;
//   tasks.push_back("گزارش");
//   tasks.push_front("جلسه");
//   while (!tasks.empty()) {
//       std::cout << "پردازش: " << tasks.front() << "\n";
//       tasks.pop_front();
//   }

#include <deque>
#include <iostream>
#include <string>

void print(const std::deque<std::string>& tasks) {
    std::cout << "صف: [";
    bool first = true;
    for (const auto& task : tasks) {
        if (!first) {
            std::cout << ", ";
        }
        std::cout << task;
        first = false;
    }
    std::cout << "]\n";
}

int main() {
    // TODO: صف را بسازید، چهار کار اضافه کنید، چاپ کنید و پردازش کنید

    return 0;
}
