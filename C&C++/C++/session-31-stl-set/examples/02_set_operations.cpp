// 02_set_operations.cpp — اشتراک، اجتماع، تفاضل و تفاضل متقارن
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o set_ops examples/02_set_operations.cpp

#include <algorithm>
#include <iostream>
#include <iterator>
#include <set>
#include <string>

void print(const std::string& label, const std::set<int>& data) {
    std::cout << "  " << label << " = {";
    bool first = true;
    for (int value : data) {
        if (!first) {
            std::cout << ", ";
        }
        std::cout << value;
        first = false;
    }
    std::cout << "}\n";
}

int main() {
    const std::set<int> a{1, 2, 3, 4, 5};
    const std::set<int> b{4, 5, 6, 7};

    std::cout << "--- ورودی‌ها ---\n";
    print("A", a);
    print("B", b);

    std::set<int> intersection;
    std::set_intersection(a.begin(), a.end(), b.begin(), b.end(),
                          std::inserter(intersection, intersection.end()));

    std::set<int> combined;
    std::set_union(a.begin(), a.end(), b.begin(), b.end(),
                   std::inserter(combined, combined.end()));

    std::set<int> onlyA;
    std::set_difference(a.begin(), a.end(), b.begin(), b.end(),
                        std::inserter(onlyA, onlyA.end()));

    std::set<int> symmetric;
    std::set_symmetric_difference(a.begin(), a.end(), b.begin(), b.end(),
                                  std::inserter(symmetric, symmetric.end()));

    std::cout << "--- نتیجه ---\n";
    print("A ∩ B", intersection);
    print("A ∪ B", combined);
    print("A − B", onlyA);
    print("A △ B", symmetric);

    std::cout << "--- عضویت ---\n";
    std::cout << "  5 در A؟ " << (a.count(5) > 0 ? "بله" : "خیر") << "\n";
    std::cout << "  6 در A؟ " << (a.find(6) != a.end() ? "بله" : "خیر") << "\n";
    std::cout << "برای set_* هر دو ورودی باید مرتب باشند؛ set همیشه مرتب است.\n";

    return 0;
}
