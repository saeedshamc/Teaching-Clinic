// 03_index_operator.cpp — operator[] در دو نسخهٔ const و غیر const
// کامپایل: g++ -std=c++17 -Wall -Wextra -o index examples/03_index_operator.cpp

#include <cstddef>
#include <iostream>
#include <stdexcept>
#include <string>
#include <vector>

class Shelf {
public:
    void add(const std::string& title) {
        titles_.push_back(title);
    }

    std::size_t size() const {
        return titles_.size();
    }

    // نسخهٔ غیر const: اجازهٔ نوشتن
    std::string& operator[](std::size_t index) {
        return titles_.at(index);        // at → استثنا در اندیس نامعتبر
    }

    // نسخهٔ const: فقط خواندن
    const std::string& operator[](std::size_t index) const {
        return titles_.at(index);
    }

private:
    std::vector<std::string> titles_;
};

void printShelf(const Shelf& shelf) {
    std::cout << "قفسه (" << shelf.size() << " کتاب):\n";
    for (std::size_t i = 0; i < shelf.size(); ++i) {
        std::cout << "  [" << i << "] " << shelf[i] << "\n";   // نسخهٔ const
    }
}

int main() {
    Shelf shelf;
    shelf.add("کتاب اول");
    shelf.add("کتاب دوم");
    shelf.add("کتاب سوم");

    printShelf(shelf);

    // نسخهٔ غیر const → قابل انتساب
    shelf[1] = "کتاب دوم (ویرایش جدید)";
    std::cout << "\nبعد از تغییر shelf[1]:\n";
    printShelf(shelf);

    // خواندن با []
    std::cout << "\nاولین کتاب: " << shelf[0] << "\n";

    // اندیس نامعتبر → استثنای at
    try {
        std::cout << shelf[10] << "\n";
    } catch (const std::out_of_range& error) {
        std::cout << "\nاندیس 10 نامعتبر است: " << error.what() << "\n";
    }

    // shelf[0] روی شیء const:
    const Shelf& readOnly = shelf;
    std::cout << "از طریق const& : " << readOnly[0] << "\n";
    // readOnly[0] = "تغییر";   // ❌ خطای کامپایل: نسخهٔ const برمی‌گردد

    return 0;
}
