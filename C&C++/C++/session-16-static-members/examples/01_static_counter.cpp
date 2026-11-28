// 01_static_counter.cpp — عضو static داده: یک نسخه برای همهٔ اشیاء
// کامپایل: g++ -std=c++17 -Wall -Wextra -o counter examples/01_static_counter.cpp

#include <iostream>
#include <string>

class Widget {
public:
    Widget() {
        ++alive_;
        std::cout << "ساخت Widget بدون نام — زنده: " << alive_ << "\n";
    }

    explicit Widget(const std::string& name)
        : name_(name) {
        ++alive_;
        std::cout << "ساخت " << name_ << " — زنده: " << alive_ << "\n";
    }

    ~Widget() {
        --alive_;
        std::cout << "تخریب " << (name_.empty() ? "بی‌نام" : name_)
                  << " — زنده: " << alive_ << "\n";
    }

    // تابع static: به هیچ شیئی وابسته نیست، پس this ندارد
    static int alive() {
        return alive_;
    }

    const std::string& name() const {
        return name_;
    }

private:
    std::string name_;

    // یک نسخه برای کل کلاس، نه یکی برای هر شیء
    // در C++17 می‌توان همین‌جا مقدار داد (inline static)
    inline static int alive_ = 0;
};

int main() {
    std::cout << "شروع: زنده = " << Widget::alive() << "\n\n";

    Widget a("A");
    Widget b("B");
    {
        Widget c("C در بلوک");
        Widget unnamed;
        std::cout << "داخل بلوک: زنده = " << Widget::alive() << "\n";
    }
    std::cout << "بعد از بلوک: زنده = " << Widget::alive() << "\n\n";

    std::cout << "دسترسی از طریق شیء هم ممکن است: " << a.alive() << "\n";
    std::cout << "ولی درست این است: " << Widget::alive() << "\n";

    return 0;
}
