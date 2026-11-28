// 01_set_basics.cpp — set: یکتایی، ترتیب، insert نتیجه‌دار
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o set_basics examples/01_set_basics.cpp

#include <iostream>
#include <set>
#include <string>
#include <unordered_set>

void print(const std::set<int>& data) {
    std::cout << "  {";
    bool first = true;
    for (int value : data) {
        if (!first) {
            std::cout << ", ";
        }
        std::cout << value;
        first = false;
    }
    std::cout << "}  size = " << data.size() << "\n";
}

int main() {
    std::set<int> values{5, 1, 4, 1, 5};
    std::cout << "--- ساخت از فهرست با تکرار ---\n";
    print(values);

    const auto [existing, insertedExisting] = values.insert(4);
    std::cout << "  insert(4) → inserted = " << (insertedExisting ? "بله" : "خیر")
              << "، مقدار در تکرارگر: " << *existing << "\n";

    const auto [added, insertedNew] = values.insert(7);
    std::cout << "  insert(7) → inserted = " << (insertedNew ? "بله" : "خیر")
              << "، مقدار: " << *added << "\n";
    print(values);

    std::cout << "--- count و find ---\n";
    std::cout << "  count(5) = " << values.count(5)
              << "، count(9) = " << values.count(9) << "\n";
    if (values.find(7) != values.end()) {
        std::cout << "  7 پیدا شد\n";
    }

    std::cout << "--- erase با مقدار ---\n";
    values.erase(5);
    print(values);

    std::cout << "--- set<string> مرتب بر اساس حروف ---\n";
    const std::set<std::string> names{"Sara", "Ali", "Saeed"};
    for (const auto& name : names) {
        std::cout << "  " << name << "\n";
    }

    std::cout << "--- unordered_set ---\n";
    const std::unordered_set<int> fast{3, 1, 2, 1};
    std::cout << "  size = " << fast.size()
              << " (ترتیب چاپ تضمین‌شده نیست)\n";
    std::cout << "  count(2) = " << fast.count(2) << "\n";

    return 0;
}
