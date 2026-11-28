// 03_deque.cpp — deque: دسترسی تصادفی + درج و حذف در دو سر
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o deque_example examples/03_deque.cpp

#include <deque>
#include <iostream>
#include <string>

void print(const std::deque<std::string>& data) {
    std::cout << "  [";
    bool first = true;
    for (const auto& value : data) {
        if (!first) {
            std::cout << ", ";
        }
        std::cout << value;
        first = false;
    }
    std::cout << "]  size = " << data.size() << "\n";
}

int main() {
    // تاریخچهٔ فعالیت‌ها: فقط سه عمل آخر نگه داشته می‌شود
    std::deque<std::string> history;
    history.push_back("نوشتن");
    history.push_back("ویرایش");
    history.push_back("اجرا");

    std::cout << "--- تاریخچه ---\n";
    print(history);
    std::cout << "  history[1] = " << history[1] << "  (دسترسی تصادفی)\n";

    history.push_back("ذخیره");
    while (history.size() > 3) {
        history.pop_front();   // قدیمی‌ترین از ابتدا حذف می‌شود
    }
    std::cout << "--- بعد از افزودن «ذخیره» و کوتاه کردن ---\n";
    print(history);

    history.push_front("بازگشت");
    std::cout << "--- push_front(\"بازگشت\") ---\n";
    print(history);
    std::cout << "  جلو: " << history.front() << "، عقب: " << history.back() << "\n";

    history.pop_back();
    std::cout << "--- pop_back ---\n";
    print(history);

    std::cout << "چرا deque؟ درج/حذف در دو سر O(1) و دسترسی تصادفی O(1) است؛\n"
                 "در vector درج در ابتدا O(n) می‌شد.\n";

    return 0;
}
