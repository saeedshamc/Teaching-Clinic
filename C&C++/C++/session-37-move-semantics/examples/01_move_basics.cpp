// 01_move_basics.cpp — lvalue، rvalue و std::move
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o move_basics examples/01_move_basics.cpp

#include <iostream>
#include <string>
#include <utility>
#include <vector>

int main() {
    std::cout << "--- کپی ---\n";
    std::string source = "متن";
    const std::string copy = source;
    std::cout << "  منبع: " << source << "، کپی: " << copy << "\n";

    std::cout << "--- move ---\n";
    const std::string moved = std::move(source);
    std::cout << "  مقصد: " << moved << "\n";
    std::cout << "  طول منبع بعد از move: " << source.size()
              << " (وضعیت معتبر ولی نامشخص)\n";

    std::cout << "--- move روی int اثری ندارد ---\n";
    int number = 42;
    const int other = std::move(number);
    std::cout << "  number = " << number << "، other = " << other << "\n";

    std::cout << "--- vector با move ---\n";
    std::vector<std::string> names{"Ali", "Sara", "Omid"};
    const std::vector<std::string> movedNames = std::move(names);
    std::cout << "  names.size() = " << names.size()
              << "، movedNames.size() = " << movedNames.size() << "\n";

    std::cout << "--- push_back با انتقال ---\n";
    std::vector<std::string> list;
    std::string temporary = "موقت";
    list.push_back(std::move(temporary));
    std::cout << "  list[0] = " << list[0]
              << "، طول temporary = " << temporary.size() << "\n";

    std::cout << "--- rvalue مرجع ---\n";
    std::string&& reference = std::move(list[0]);
    std::cout << "  reference = " << reference << "\n";

    std::cout << "move انتقال می‌دهد، ولی حافظه بلافاصله آزاد نمی‌شود.\n";
    return 0;
}
