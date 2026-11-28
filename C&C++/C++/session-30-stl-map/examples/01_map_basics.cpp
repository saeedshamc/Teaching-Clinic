// 01_map_basics.cpp — map: operator[] در برابر at، insert/emplace، find، erase
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o map_basics examples/01_map_basics.cpp

#include <iostream>
#include <map>
#include <string>

void print(const std::map<std::string, int>& data) {
    std::cout << "  {";
    bool first = true;
    for (const auto& [key, value] : data) {
        if (!first) {
            std::cout << ", ";
        }
        std::cout << key << ": " << value;
        first = false;
    }
    std::cout << "}  size = " << data.size() << "\n";
}

int main() {
    std::map<std::string, int> ages{{"Sara", 25}, {"Ali", 20}};
    ages["Saeed"] = 30;   // درج
    ages["Sara"] = 26;    // به‌روزرسانی
    std::cout << "--- بعد از درج و به‌روزرسانی ---\n";
    print(ages);

    std::cout << "--- operator[] روی کلید ناموجود ---\n";
    const int missing = ages["Reza"];   // کلید با مقدار پیش‌فرض ۰ درج می‌شود
    std::cout << "  ages[\"Reza\"] = " << missing
              << " (کلید ناخواسته درج شد)\n";
    std::cout << "  size = " << ages.size() << "\n";

    std::cout << "--- at با کلید ناموجود ---\n";
    try {
        std::cout << "  " << ages.at("Nobody") << "\n";
    } catch (const std::out_of_range& error) {
        std::cout << "  out_of_range: " << error.what() << "\n";
    }

    std::cout << "--- insert و emplace ---\n";
    const auto [itNew, insertedNew] = ages.insert({"Mina", 28});
    const auto [itOld, insertedOld] = ages.insert({"Mina", 99});   // کلید تکراری
    std::cout << "  insert(\"Mina\", 28) → inserted = "
              << (insertedNew ? "بله" : "خیر") << "\n";
    std::cout << "  insert تکراری → inserted = "
              << (insertedOld ? "بله" : "خیر")
              << "، مقدار فعلی: " << itOld->second << "\n";
    ages.emplace("Omid", 35);
    print(ages);

    std::cout << "--- find و count ---\n";
    const auto it = ages.find("Sara");
    if (it != ages.end()) {
        std::cout << "  پیدا شد: " << it->first << " = " << it->second << "\n";
    }
    if (ages.count("Nobody") == 0) {
        std::cout << "  \"Nobody\" وجود ندارد (count = 0)\n";
    }

    std::cout << "--- erase ---\n";
    ages.erase("Reza");
    print(ages);

    return 0;
}
