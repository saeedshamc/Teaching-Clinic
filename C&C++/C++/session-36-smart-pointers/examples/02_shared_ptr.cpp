// 02_shared_ptr.cpp — shared_ptr: مالکیت مشترک و شمارش مراجع
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o shared_ptr_example examples/02_shared_ptr.cpp

#include <iostream>
#include <memory>
#include <string>

struct Node {
    std::string name;

    explicit Node(const std::string& nodeName)
        : name(nodeName) {
        std::cout << "  + ساخت: " << name << "\n";
    }

    ~Node() {
        std::cout << "  - تخریب: " << name << "\n";
    }
};

void inspect(const std::shared_ptr<Node>& pointer) {
    std::cout << "    use_count داخل تابع: " << pointer.use_count() << "\n";
}

int main() {
    auto shared = std::make_shared<Node>("مشترک");
    std::cout << "--- use_count ---\n";
    std::cout << "  بعد از ساخت: " << shared.use_count() << "\n";

    {
        const auto copy = shared;
        std::cout << "  بعد از کپی: " << shared.use_count() << "\n";
        inspect(shared);
        std::cout << "  قبل از خروج از دامنه: " << shared.use_count() << "\n";
    }

    std::cout << "  بعد از خروج از دامنه: " << shared.use_count() << "\n";
    std::cout << "  فقط یک مالک است؟ " << (shared.unique() ? "بله" : "خیر") << "\n";

    std::cout << "--- reset ---\n";
    shared.reset();
    std::cout << "  بعد از reset: " << (shared ? "معتبر" : "خالی") << "\n";

    std::cout << "--- make_shared در برابر shared_ptr<T>(new T) ---\n";
    const auto a = std::make_shared<Node>("یک تخصیص");
    const std::shared_ptr<Node> b(new Node("دو تخصیص"));
    std::cout << "  هر دو کار می‌کنند، ولی make_shared یک تخصیص دارد.\n";

    return 0;
}
