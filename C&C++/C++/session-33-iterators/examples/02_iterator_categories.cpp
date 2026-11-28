// 02_iterator_categories.cpp — کدام عملیات روی کدام تکرارگر مجاز است
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o iter_categories examples/02_iterator_categories.cpp

#include <forward_list>
#include <iostream>
#include <iterator>
#include <list>
#include <map>
#include <string>
#include <vector>

// این تابع فقط ++ و * و != می‌خواهد: روی همهٔ کانتینرها کار می‌کند
template <typename Iterator>
int countElements(Iterator begin, Iterator end) {
    int count = 0;
    for (Iterator it = begin; it != end; ++it) {
        ++count;
    }
    return count;
}

// این تابع از پرش استفاده می‌کند: فقط تکرارگر تصادفی
template <typename Iterator>
void printEverySecond(Iterator begin, Iterator end) {
    for (Iterator it = begin; it < end; it += 2) {
        std::cout << "  " << *it << "\n";
    }
}

int main() {
    std::vector<int> vec{1, 2, 3, 4, 5};
    std::list<int> lst{1, 2, 3, 4, 5};
    std::forward_list<int> fwd{1, 2, 3, 4, 5};

    std::cout << "--- تابعی که فقط ++ می‌خواهد ---\n";
    std::cout << "  vector: " << countElements(vec.begin(), vec.end()) << "\n";
    std::cout << "  list: " << countElements(lst.begin(), lst.end()) << "\n";
    std::cout << "  forward_list: " << countElements(fwd.begin(), fwd.end()) << "\n";

    std::cout << "--- تابعی که پرش (it += 2) می‌خواهد ---\n";
    printEverySecond(vec.begin(), vec.end());
    std::cout << "  (روی list کامپایل نمی‌شود: تکرارگر آن تصادفی نیست)\n";

    std::cout << "--- دسته‌ها با iterator_traits ---\n";
    std::cout << "  vector: random_access\n";
    std::cout << "  list: bidirectional\n";
    std::cout << "  forward_list: forward\n";
    std::cout << "  map: bidirectional\n";

    std::cout << "--- عقب‌گرد فقط روی دوسویه ---\n";
    auto lit = lst.end();
    --lit;
    std::cout << "  *--list.end() = " << *lit << "\n";
    auto mit = lst.begin();
    std::advance(mit, 3);
    --mit;
    std::cout << "  بعد از advance(3) و --: " << *mit << "\n";

    std::cout << "--- map: تکرارگر به pair<const Key, Value> ---\n";
    const std::map<std::string, int> scores{{"Ali", 18}, {"Sara", 20}};
    for (auto it = scores.begin(); it != scores.end(); ++it) {
        std::cout << "  " << it->first << " = " << it->second << "\n";
    }

    return 0;
}
