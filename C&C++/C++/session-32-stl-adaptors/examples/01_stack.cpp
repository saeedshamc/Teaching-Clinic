// 01_stack.cpp — stack: آخرین ورودی، اولین خروجی (LIFO)
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o stack_example examples/01_stack.cpp

#include <iostream>
#include <stack>
#include <string>

int main() {
    std::stack<std::string> history;
    history.push("نوشتن");
    history.push("ویرایش");
    history.push("حذف");

    std::cout << "--- پشته (LIFO) ---\n";
    std::cout << "  top = " << history.top()
              << "، size = " << history.size() << "\n";

    while (!history.empty()) {
        std::cout << "  pop → " << history.top() << "\n";
        history.pop();
    }
    std::cout << "  size بعد از خالی شدن = " << history.size() << "\n";

    // معکوس کردن رشته با پشته
    std::stack<char> chars;
    for (char letter : std::string("C++")) {
        chars.push(letter);
    }
    std::cout << "--- معکوس کردن \"C++\" ---\n  ";
    while (!chars.empty()) {
        std::cout << chars.top();
        chars.pop();
    }
    std::cout << "\n";

    std::cout << "  هیچ تکرارگری ندارد: نمی‌توان پشته را پیمایش کرد، فقط top.\n";
    return 0;
}
