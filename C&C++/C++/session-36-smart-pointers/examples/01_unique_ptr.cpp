// 01_unique_ptr.cpp — unique_ptr: مالکیت انحصاری و انتقال آن
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o unique_ptr_example examples/01_unique_ptr.cpp

#include <iostream>
#include <memory>
#include <string>
#include <utility>
#include <vector>

struct Resource {
    std::string name;

    explicit Resource(const std::string& resourceName)
        : name(resourceName) {
        std::cout << "  + تخصیص: " << name << "\n";
    }

    ~Resource() {
        std::cout << "  - آزادسازی: " << name << "\n";
    }

    void use() const {
        std::cout << "    استفاده از " << name << "\n";
    }
};

int main() {
    std::cout << "--- make_unique و دامنه ---\n";
    {
        const auto resource = std::make_unique<Resource>("فایل");
        resource->use();
        std::cout << "  اشاره‌گر خالی است؟ " << (resource ? "نه" : "بله") << "\n";
    }
    std::cout << "  بعد از خروج از دامنه\n";

    std::cout << "--- انتقال مالکیت با std::move ---\n";
    auto first = std::make_unique<Resource>("دیتابیس");
    auto second = std::move(first);
    std::cout << "  first خالی است؟ " << (first ? "نه" : "بله") << "\n";
    second->use();

    std::cout << "--- unique_ptr در vector ---\n";
    std::vector<std::unique_ptr<Resource>> items;
    items.push_back(std::make_unique<Resource>("شبکه"));
    items.push_back(std::make_unique<Resource>("کش"));
    for (const auto& item : items) {
        item->use();
    }

    std::cout << "--- پایان دامنهٔ main ---\n";
    return 0;
}
