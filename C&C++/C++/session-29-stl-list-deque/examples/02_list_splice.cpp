// 02_list_splice.cpp — splice: انتقال گره‌ها بین listها بدون کپی
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o splice examples/02_list_splice.cpp

#include <iostream>
#include <list>
#include <string>

void print(const std::string& label, const std::list<std::string>& data) {
    std::cout << "  " << label << ": [";
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
    std::list<std::string> urgent{"سرور", "پرداخت"};
    std::list<std::string> normal{"مستندات", "ایمیل", "گزارش"};

    std::cout << "--- شروع ---\n";
    print("urgent", urgent);
    print("normal", normal);

    // ۱) کل normal به انتهای urgent منتقل می‌شود
    urgent.splice(urgent.end(), normal);
    std::cout << "--- splice کل لیست ---\n";
    print("urgent", urgent);
    print("normal", normal);

    // ۲) فقط یک گره از میانه
    auto it = urgent.begin();
    std::advance(it, 2);
    std::cout << "--- انتقال یک گره («" << *it << "») ---\n";
    normal.splice(normal.begin(), urgent, it);
    print("urgent", urgent);
    print("normal", normal);

    std::cout << "هیچ عنصری کپی نشد؛ فقط اشاره‌گر گره‌ها عوض شد.\n";

    return 0;
}
