// exercise_01_solution.cpp — راه‌حل تمرین ۱: اعتبارسنجی با استثنای سفارشی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o invalidage exercises/exercise_01_solution.cpp

#include <iostream>
#include <stdexcept>
#include <string>

class InvalidAge : public std::runtime_error {
public:
    InvalidAge(const std::string& message, int value)
        : std::runtime_error(message), value_(value) {
    }

    int value() const {
        return value_;
    }

private:
    int value_;
};

class Person {
public:
    Person(const std::string& name, int age)
        : name_(name) {
        setAge(age);
    }

    void setAge(int age) {
        if (age < 0 || age > 150) {
            throw InvalidAge("سن خارج از محدوده", age);
        }
        age_ = age;
    }

    int age() const {
        return age_;
    }

    const std::string& name() const {
        return name_;
    }

private:
    std::string name_;
    int age_ = 0;
};

int main() {
    Person person("Saeed", 30);
    std::cout << person.name() << " ، " << person.age() << "\n";

    try {
        person.setAge(200);
    } catch (const InvalidAge& error) {
        std::cout << "setAge(200) → InvalidAge: " << error.what()
                  << " (مقدار: " << error.value() << ")\n";
    }
    std::cout << "سن هنوز: " << person.age() << "\n";

    try {
        const Person tooYoung("Ali", -5);
        std::cout << "این خط اجرا نمی‌شود\n";
    } catch (const InvalidAge& error) {
        std::cout << "ساخت با سن -5 → InvalidAge: " << error.what()
                  << " (مقدار: " << error.value() << ")\n";
    }

    try {
        Person other("Sara", 20);
        other.setAge(999);
    } catch (const std::exception& error) {
        std::cout << "با catch(std::exception&) هم گرفته شد: " << error.what() << "\n";
    }

    return 0;
}
