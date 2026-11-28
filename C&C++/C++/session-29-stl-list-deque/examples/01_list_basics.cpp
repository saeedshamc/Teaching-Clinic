// 01_list_basics.cpp — list: درج و حذف ارزان در هر نقطه، پیمایش دوسویه
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o list_basics examples/01_list_basics.cpp

#include <iostream>
#include <list>

void print(const std::list<int>& data) {
    std::cout << "  [";
    bool first = true;
    for (int value : data) {
        if (!first) {
            std::cout << ", ";
        }
        std::cout << value;
        first = false;
    }
    std::cout << "]  size = " << data.size() << "\n";
}

int main() {
    std::list<int> values{30, 10, 20};
    std::cout << "--- شروع ---\n";
    print(values);

    values.push_front(5);
    values.push_back(40);
    std::cout << "--- push_front(5) و push_back(40) ---\n";
    print(values);

    // درج در میانه: فقط اشاره‌گرهای دو گرهٔ همسایه عوض می‌شوند
    auto it = values.begin();
    std::advance(it, 3);
    values.insert(it, 15);
    std::cout << "--- insert(15) قبل از عنصر چهارم ---\n";
    print(values);

    it = values.begin();
    ++it;
    values.erase(it);
    std::cout << "--- erase عنصر دوم ---\n";
    print(values);

    // list عضو sort خودش را دارد؛ std::sort به تکرارگر تصادفی نیاز دارد
    values.sort();
    std::cout << "--- sort ---\n";
    print(values);

    values.push_back(20);
    values.push_back(20);
    std::cout << "--- push_back(20) دو بار ---\n";
    print(values);
    values.unique();
    std::cout << "--- unique (فقط همسایه‌های تکراری) ---\n";
    print(values);

    values.remove(40);
    std::cout << "--- remove(40) ---\n";
    print(values);

    values.clear();
    std::cout << "--- clear ---\n";
    print(values);
    std::cout << "  empty = " << (values.empty() ? "بله" : "خیر") << "\n";

    return 0;
}
