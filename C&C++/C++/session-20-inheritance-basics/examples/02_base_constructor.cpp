// 02_base_constructor.cpp — سازندهٔ پایه، ترتیب ساخت و انواع وراثت
// کامپایل: g++ -std=c++17 -Wall -Wextra -o base examples/02_base_constructor.cpp

#include <iostream>
#include <string>

class Base {
public:
    explicit Base(const std::string& tag)
        : tag_(tag) {
        std::cout << "  ساخت Base(" << tag_ << ")\n";
    }

    Base(const Base& other)
        : tag_(other.tag_ + "-copy") {
        std::cout << "  کپی Base(" << tag_ << ")\n";
    }

    ~Base() {
        std::cout << "  تخریب Base(" << tag_ << ")\n";
    }

    const std::string& tag() const {
        return tag_;
    }

protected:
    // برای زیرکلاس قابل دسترسی است
    std::string tag_;

private:
    int secret_ = 42;      // حتی زیرکلاس هم نمی‌بیند
};

class Derived : public Base {
public:
    // سازندهٔ پایه باید صریح در لیست مقداردهی صدا زده شود
    explicit Derived(const std::string& tag)
        : Base("پایه:" + tag),     // ← اول پایه
          payload_(tag.size()) {   // ← بعد اعضای مشتق
        std::cout << "  ساخت بدنهٔ Derived(" << tag << ")\n";
        std::cout << "  دسترسی به tag_ از protected: " << tag_ << "\n";
    }

    ~Derived() {
        std::cout << "  تخریب بدنهٔ Derived\n";
    }

    std::size_t payload() const {
        return payload_;
    }

private:
    std::size_t payload_;
};

int main() {
    std::cout << "--- شروع ---\n";
    {
        const Derived object("abc");
        std::cout << "\ntag از بیرون: " << object.tag()
                  << " ، payload: " << object.payload() << "\n";
        std::cout << "\n--- پایان بلوک ---\n";
    }
    std::cout << "--- بعد از بلوک ---\n";

    // نکتهٔ دسترسی:
    //   tag_ protected است → Derived می‌بیند، main نمی‌بیند:
    //     object.tag_ = "x";        ❌
    //   secret_ private است → هیچ زیرکلاسی نمی‌بیند:
    //     object.secret_            ❌

    return 0;
}
