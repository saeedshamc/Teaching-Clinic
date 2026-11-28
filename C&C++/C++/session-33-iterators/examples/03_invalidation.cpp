// 03_invalidation.cpp — کدام عملیات تکرارگر را باطل می‌کند
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o invalidation examples/03_invalidation.cpp

#include <iostream>
#include <list>
#include <vector>

int main() {
    std::cout << "--- vector: رشد حافظه را عوض می‌کند ---\n";
    std::vector<int> values{1, 2, 3};
    const int* before = values.data();
    values.push_back(4);
    const int* after = values.data();
    std::cout << "  آدرس حافظه " << (before == after ? "ثابت ماند" : "عوض شد")
              << " (size = " << values.size()
              << "، capacity = " << values.capacity() << ")\n";

    std::cout << "--- با reserve از قبل ---\n";
    std::vector<int> reserved;
    reserved.reserve(4);
    reserved.push_back(1);
    const int* reservedBefore = reserved.data();
    reserved.push_back(2);
    std::cout << "  آدرس حافظه "
              << (reservedBefore == reserved.data() ? "ثابت ماند" : "عوض شد")
              << " (capacity = " << reserved.capacity() << ")\n";

    std::cout << "--- list: درج در جای دیگر تکرارگر را باطل نمی‌کند ---\n";
    std::list<int> numbers{10, 20, 30};
    auto middle = std::next(numbers.begin());
    const int valueBefore = *middle;
    numbers.push_front(99);
    numbers.push_back(-1);
    std::cout << "  مقدار قبلی: " << valueBefore
              << "، مقدار فعلی از همان تکرارگر: " << *middle << "\n";

    std::cout << "--- erase در حلقه: تکرارگر بعدی را از erase بگیرید ---\n";
    std::vector<int> data{1, 2, 3, 4, 5, 6};
    for (auto it = data.begin(); it != data.end();) {
        if (*it % 2 == 0) {
            it = data.erase(it);
        } else {
            ++it;
        }
    }
    std::cout << "  بعد از حذف زوج‌ها: ";
    for (int value : data) {
        std::cout << value << ' ';
    }
    std::cout << "\n";

    std::cout << "قاعده: هر عملیاتی که capacity را عوض کند، همهٔ تکرارگرهای vector را باطل می‌کند.\n";
    return 0;
}
