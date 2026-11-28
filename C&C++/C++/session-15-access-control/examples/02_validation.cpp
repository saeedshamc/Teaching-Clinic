// 02_validation.cpp — اعتبارسنجی در setter و رعایت const در رابط
// کامپایل: g++ -std=c++17 -Wall -Wextra -o person examples/02_validation.cpp

#include <iostream>
#include <string>

class Person {
public:
    // سازنده همهٔ قواعد را از همان ابتدا اعمال می‌کند
    Person(const std::string& name, int age)
        : name_("ناشناس"), age_(0) {
        setName(name);
        setAge(age);
    }

    // نام باید حداقل یک کاراکتر غیر از فاصله داشته باشد
    bool setName(const std::string& value) {
        if (value.find_first_not_of(" \t") == std::string::npos) {
            return false;
        }
        name_ = value;
        return true;
    }

    bool setAge(int value) {
        if (value < 0 || value > 150) {
            return false;
        }
        age_ = value;
        return true;
    }

    // getterها const — لازمهٔ استفاده از شیء در توابع const&
    const std::string& name() const {
        return name_;
    }

    int age() const {
        return age_;
    }

    bool isAdult() const {
        return age_ >= 18;
    }

private:
    std::string name_;
    int age_ = 0;
};

// const& یعنی: بدون کپی، بدون امکان تغییر
void printPerson(const Person& person) {
    std::cout << person.name() << " (" << person.age() << ")"
              << (person.isAdult() ? " — بزرگسال" : " — کم‌سن") << "\n";
    // person.setAge(5);   // ❌ خطای کامپایل: شیء const است
}

int main() {
    std::cout << "--- ورودی نامعتبر در سازنده ---\n";
    Person bad("   ", -20);
    std::cout << "نام: «" << bad.name() << "» ، سن: " << bad.age()
              << " (مقادیر پیش‌فرض) \n";

    std::cout << "\n--- setter با مقدار معتبر ---\n";
    std::cout << "setName(\"Saeed\"): " << (bad.setName("Saeed") ? "پذیرفته" : "رد شد") << "\n";
    std::cout << "setAge(30): " << (bad.setAge(30) ? "پذیرفته" : "رد شد") << "\n";
    printPerson(bad);

    std::cout << "\n--- setter با مقدار نامعتبر ---\n";
    std::cout << "setName(\"\"): " << (bad.setName("") ? "پذیرفته" : "رد شد") << "\n";
    std::cout << "setAge(200): " << (bad.setAge(200) ? "پذیرفته" : "رد شد") << "\n";
    printPerson(bad);

    std::cout << "\n--- شیء const ---\n";
    const Person reader("Ali", 16);
    printPerson(reader);

    // دو خط زیر ممکن نیستند و همین هدف ماست:
    //   reader.age() = 99;             // getter مقدار برمی‌گرداند، نه مرجع
    //   reader.setAge(99);             // متد غیر const روی شیء const

    return 0;
}
