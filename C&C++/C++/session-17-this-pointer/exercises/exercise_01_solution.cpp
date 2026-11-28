// exercise_01_solution.cpp — راه‌حل تمرین ۱: StringBuilder
// کامپایل: g++ -std=c++17 -Wall -Wextra -o sb exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>

class StringBuilder {
public:
    StringBuilder& append(const std::string& text) {
        buffer_ += text;
        return *this;
    }

    StringBuilder& appendLine(const std::string& text) {
        buffer_ += text;
        buffer_ += '\n';
        return *this;
    }

    StringBuilder& clear() {
        buffer_.clear();
        return *this;
    }

    const std::string& str() const {
        return buffer_;
    }

    std::size_t size() const {
        return buffer_.size();
    }

    bool empty() const {
        return buffer_.empty();
    }

private:
    std::string buffer_;
};

int main() {
    StringBuilder builder;

    builder.append("سلام")
           .append("، ")
           .appendLine("دنیا")
           .append("پایان");

    std::cout << "متن:\n" << builder.str() << "\n";
    std::cout << "size = " << builder.size() << "\n";
    std::cout << "empty؟ " << (builder.empty() ? "بله" : "خیر") << "\n";

    // زنجیره روی همان شیء کار می‌کند، نه روی کپی
    StringBuilder& same = builder.append("!");
    std::cout << "\nبازگشت همان شیء بود؟ " << (&same == &builder ? "بله" : "خیر") << "\n";

    const StringBuilder& view = builder;
    std::cout << "از طریق reference فقط‌خواندنی: " << view.str() << "\n";

    builder.clear();
    std::cout << "\nبعد از clear: size = " << builder.size()
              << " ، empty؟ " << (builder.empty() ? "بله" : "خیر") << "\n";

    return 0;
}
