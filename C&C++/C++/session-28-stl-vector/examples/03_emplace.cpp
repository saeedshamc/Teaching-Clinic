// 03_emplace.cpp — emplace_back شیء را درجا می‌سازد، push_back کپی/انتقال می‌کند
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o emplace examples/03_emplace.cpp

#include <iostream>
#include <string>
#include <vector>

struct Item {
    std::string name;
    int quantity;

    Item(const std::string& itemName, int itemQuantity)
        : name(itemName), quantity(itemQuantity) {
        std::cout << "  ساخت: " << name << "\n";
    }

    Item(const Item& other)
        : name(other.name), quantity(other.quantity) {
        std::cout << "  کپی: " << name << "\n";
    }
};

int main() {
    std::vector<Item> items;
    items.reserve(4);

    std::cout << "--- push_back ---\n";
    items.push_back(Item("خودکار", 10));

    std::cout << "--- emplace_back ---\n";
    items.emplace_back("دفتر", 5);

    std::cout << "--- push_back با شیء موجود (کپی) ---\n";
    Item pen("پاک‌کن", 2);
    items.push_back(pen);

    std::cout << "--- محتوا ---\n";
    for (const Item& item : items) {
        std::cout << "  " << item.name << " × " << item.quantity << "\n";
    }
    std::cout << "تعداد: " << items.size() << "\n";

    return 0;
}
