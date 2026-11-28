// 01_animal_dog.cpp — وراثت پایه: طبقه‌بندی is-a
// کامپایل: g++ -std=c++17 -Wall -Wextra -o animal examples/01_animal_dog.cpp

#include <iostream>
#include <string>
#include <vector>

class Animal {
public:
    Animal(const std::string& name, int age)
        : name_(name), age_(age) {
    }

    // اعضای عمومی پایه، در مشتق هم در دسترس‌اند
    const std::string& name() const {
        return name_;
    }

    int age() const {
        return age_;
    }

    void describe() const {
        std::cout << name_ << " (" << age_ << " ساله)";
    }

    // هر حیوان صدای خودش را دارد؛ در کلاس پایه پیش‌فرض می‌گذاریم
    std::string sound() const {
        return "...";
    }

protected:
    // برای زیرکلاس‌ها قابل دسترسی، ولی از بیرون نه
    void setAge(int age) {
        if (age >= 0) {
            age_ = age;
        }
    }

private:
    std::string name_;
    int age_;
};

class Dog : public Animal {
public:
    // سازندهٔ مشتق، سازندهٔ پایه را صدا می‌زند
    Dog(const std::string& name, int age, const std::string& breed)
        : Animal(name, age), breed_(breed) {
    }

    std::string sound() const {
        return "واق واق";
    }

    const std::string& breed() const {
        return breed_;
    }

    void celebrateBirthday() {
        setAge(age() + 1);        // setAge از بخش protected پایه
    }

private:
    std::string breed_;
};

class Cat : public Animal {
public:
    Cat(const std::string& name, int age)
        : Animal(name, age) {
    }

    std::string sound() const {
        return "میو";
    }
};

int main() {
    const Dog dog("رکس", 3, "ژرمن شپرد");
    const Cat cat("ملوس", 2);

    dog.describe();
    std::cout << " — صدا: " << dog.sound() << "\n";
    cat.describe();
    std::cout << " — صدا: " << cat.sound() << "\n";

    std::cout << "\nنژاد سگ: " << dog.breed() << "\n";

    Dog puppy("پاپی", 1, "مخلوط");
    std::cout << "\nقبل از تولد: " << puppy.age() << "\n";
    puppy.celebrateBirthday();
    std::cout << "بعد از تولد: " << puppy.age() << "\n";

    // is-a: هر Dog یک Animal است
    std::cout << "\n--- از طریق Animal& ---\n";
    const Animal& asAnimal = dog;
    std::cout << asAnimal.name() << " صدا: " << asAnimal.sound() << "\n";
    std::cout << "  (توجه: sound() اینجا هنوز نسخهٔ Animal است — جلسهٔ ۲۲)\n";

    return 0;
}
