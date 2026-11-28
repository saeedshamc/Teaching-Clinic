// 02_erase_insert.cpp — درج و حذف در میانهٔ vector و هزینهٔ جابه‌جایی
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o eraseinsert examples/02_erase_insert.cpp

#include <iostream>
#include <vector>

void print(const std::vector<int>& data) {
    std::cout << "  [";
    for (std::size_t i = 0; i < data.size(); ++i) {
        if (i > 0) {
            std::cout << ", ";
        }
        std::cout << data[i];
    }
    std::cout << "]  size = " << data.size() << "\n";
}

int main() {
    std::vector<int> values{10, 20, 30, 40, 50};
    std::cout << "--- شروع ---\n";
    print(values);

    std::cout << "--- insert در اندیس ۱ ---\n";
    values.insert(values.begin() + 1, 15);
    print(values);

    std::cout << "--- erase اندیس ۳ ---\n";
    values.erase(values.begin() + 3);
    print(values);

    std::cout << "--- حذف همهٔ اعداد بزرگ‌تر از ۳۰ ---\n";
    for (auto it = values.begin(); it != values.end();) {
        if (*it > 30) {
            it = values.erase(it);   // erase تکرارگر جدید برمی‌گرداند
        } else {
            ++it;
        }
    }
    print(values);

    std::cout << "--- دسترسی با at و بررسی محدوده ---\n";
    std::cout << "  at(1) = " << values.at(1) << "\n";
    try {
        std::cout << "  at(99) = " << values.at(99) << "\n";
    } catch (const std::out_of_range& error) {
        std::cout << "  out_of_range: " << error.what() << "\n";
    }

    return 0;
}
