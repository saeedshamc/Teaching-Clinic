// exercise_01_solution.cpp — راه‌حل تمرین ۱: بررسی توازن پرانتزها
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o balanced exercises/exercise_01_solution.cpp

#include <iostream>
#include <stack>
#include <string>

bool isMatchingPair(char open, char close) {
    return (open == '(' && close == ')') ||
           (open == '[' && close == ']') ||
           (open == '{' && close == '}');
}

bool isBalanced(const std::string& text) {
    std::stack<char> open;
    for (char character : text) {
        if (character == '(' || character == '[' || character == '{') {
            open.push(character);
        } else if (character == ')' || character == ']' || character == '}') {
            if (open.empty()) {
                return false;   // بسته بدون باز
            }
            const char lastOpen = open.top();
            open.pop();
            if (!isMatchingPair(lastOpen, character)) {
                return false;   // نوع یا ترتیب غلط
            }
        }
    }
    return open.empty();        // پرانتز بازِ باقی‌مانده
}

int main() {
    const std::string cases[]{"(a[b]{c})", "([)]", "(((", ""};
    for (const std::string& text : cases) {
        std::cout << "\"" << text << "\" → "
                  << (isBalanced(text) ? "متوازن" : "نامتوازن") << "\n";
    }
    return 0;
}
