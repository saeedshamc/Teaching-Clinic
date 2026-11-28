// 03_ctor_dtor_order.cpp — ترتیب ساخت و تخریب + سازندهٔ کپی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o order examples/03_ctor_dtor_order.cpp

#include <iostream>
#include <string>

class Part {
public:
    explicit Part(const std::string& name)
        : name_(name) {
        std::cout << "    ساخت قطعه: " << name_ << "\n";
    }

    // سازندهٔ کپی: وقتی از روی شیء موجود، شیء تازه می‌سازیم
    Part(const Part& other)
        : name_(other.name_) {
        std::cout << "    کپی قطعه: " << name_ << "\n";
    }

    ~Part() {
        std::cout << "    تخریب قطعه: " << name_ << "\n";
    }

    const std::string& name() const {
        return name_;
    }

private:
    std::string name_;
};

class Machine {
public:
    Machine(const std::string& name)
        : name_(name), engine_("موتور"), wheel_("چرخ") {
        std::cout << "  ساخت بدنهٔ " << name_ << "\n";
    }

    // اعضای پایه/عضو، قبل از بدنهٔ سازندهٔ کپی هم کپی می‌شوند
    Machine(const Machine& other)
        : name_(other.name_ + " (کپی)"),
          engine_(other.engine_),
          wheel_(other.wheel_) {
        std::cout << "  ساخت بدنهٔ " << name_ << "\n";
    }

    ~Machine() {
        std::cout << "  تخریب بدنهٔ " << name_ << "\n";
    }

private:
    // ترتیب اعلان، ترتیب ساخت و تخریب را تعیین می‌کند
    std::string name_;
    Part engine_;
    Part wheel_;
};

int main() {
    std::cout << "--- شروع بلوک ---\n";
    {
        Machine original("ماشین الف");
        std::cout << "\n--- ساخت کپی ---\n";
        Machine copy = original;      // سازندهٔ کپی فراخوانی می‌شود
        std::cout << "\n--- پایان بلوک ---\n";
    }
    std::cout << "--- بعد از بلوک ---\n";

    std::cout << "\nنتیجه: ساخت از درون به بیرون، تخریب دقیقاً برعکس.\n";

    return 0;
}
