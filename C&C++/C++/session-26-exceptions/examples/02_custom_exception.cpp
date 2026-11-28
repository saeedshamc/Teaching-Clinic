// 02_custom_exception.cpp — استثنای سفارشی با اطلاعات اضافه
// کامپایل: g++ -std=c++17 -Wall -Wextra -o custom examples/02_custom_exception.cpp

#include <iostream>
#include <stdexcept>
#include <string>

// استثنای سفارشی: از std::runtime_error مشتق می‌شود تا
// catch(std::exception&) هم آن را بگیرد
class ValidationError : public std::runtime_error {
public:
    ValidationError(const std::string& message, const std::string& field)
        : std::runtime_error(message), field_(field) {
    }

    const std::string& field() const {
        return field_;
    }

private:
    std::string field_;
};

class Person {
public:
    Person(const std::string& name, int age)
        : name_(name) {
        setAge(age);                  // ممکن است throw کند
    }

    void setAge(int age) {
        if (age < 0 || age > 150) {
            throw ValidationError("سن باید بین 0 و 150 باشد", "age");
        }
        age_ = age;
    }

    void setName(const std::string& name) {
        if (name.empty()) {
            throw ValidationError("نام نباید خالی باشد", "name");
        }
        name_ = name;
    }

    const std::string& name() const {
        return name_;
    }

    int age() const {
        return age_;
    }

private:
    std::string name_;
    int age_ = 0;
};

int main() {
    std::cout << "--- ساخت معتبر ---\n";
    Person person("Saeed", 30);
    std::cout << person.name() << " ، " << person.age() << "\n";

    std::cout << "\n--- سن نامعتبر ---\n";
    try {
        person.setAge(200);
    } catch (const ValidationError& error) {
        std::cout << "ValidationError: " << error.what()
                  << " (فیلد: " << error.field() << ")\n";
    }
    std::cout << "سن هنوز: " << person.age() << " (تغییر نکرد)\n";

    std::cout << "\n--- نام خالی ---\n";
    try {
        person.setName("");
    } catch (const std::exception& error) {
        std::cout << "از طریق std::exception: " << error.what() << "\n";
    }

    std::cout << "\n--- سازندهٔ نامعتبر ---\n";
    try {
        const Person invalid("Ali", -5);
        std::cout << "این خط اجرا نمی‌شود\n";
    } catch (const ValidationError& error) {
        std::cout << "ساخت شیء شکست خورد: " << error.what()
                  << " (فیلد: " << error.field() << ")\n";
    }

    std::cout << "\nنتیجه: شیء نامعتبر هرگز ساخته نشد.\n";

    return 0;
}
