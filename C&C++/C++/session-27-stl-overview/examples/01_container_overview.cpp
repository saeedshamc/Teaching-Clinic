// 01_container_overview.cpp — خانوادهٔ کانتینرهای STL در یک نگاه
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o overview examples/01_container_overview.cpp

#include <deque>
#include <iostream>
#include <list>
#include <map>
#include <set>
#include <string>
#include <vector>

int main() {
    // vector: آرایهٔ پویا، دسترسی تصادفی، افزودن در انتها
    std::vector<std::string> names{"Saeed", "Sara", "Ali"};
    std::cout << "vector: ";
    for (const auto& name : names) {
        std::cout << name << ' ';
    }
    std::cout << "\n  اندازه: " << names.size()
              << "، دسترسی names[1] = " << names[1] << "\n";

    // list: فهرست پیوندی دوطرفه، درج/حذف ارزان در هر نقطه
    std::list<int> scores{90, 85, 70};
    scores.push_front(100);
    std::cout << "list: ";
    for (int score : scores) {
        std::cout << score << ' ';
    }
    std::cout << "\n  درج در ابتدا با هزینهٔ ثابت\n";

    // deque: صف دوطرفه، دسترسی تصادفی + درج در دو سر
    std::deque<int> values{1, 2, 3};
    values.push_front(0);
    values.push_back(4);
    std::cout << "deque: ";
    for (int value : values) {
        std::cout << value << ' ';
    }
    std::cout << "\n  دسترسی تصادفی و درج در دو سر\n";

    // map: کلید→مقدار، کلیدها یکتا و مرتب
    std::map<std::string, int> ages{{"Saeed", 30}, {"Sara", 25}};
    ages["Ali"] = 20;
    std::cout << "map (کلید مرتب):\n";
    for (const auto& [name, age] : ages) {
        std::cout << "  " << name << " = " << age << "\n";
    }

    // set: مجموعهٔ یکتا و مرتب
    std::set<int> unique{3, 1, 2, 3, 1};
    std::cout << "set: ";
    for (int value : unique) {
        std::cout << value << ' ';
    }
    std::cout << "\n  اندازه پس از حذف تکراری‌ها: " << unique.size() << "\n";

    return 0;
}
