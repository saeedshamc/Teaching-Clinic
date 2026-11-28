// 02_diamond_problem.cpp — مشکل الماس و وراثت مجازی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o diamond examples/02_diamond_problem.cpp

#include <iostream>
#include <string>

class Person {
public:
    explicit Person(const std::string& name)
        : name_(name) {
        std::cout << "  ساخت Person(" << name_ << ")\n";
    }

    const std::string& name() const {
        return name_;
    }

    void introduce() const {
        std::cout << "  من " << name_ << " هستم\n";
    }

private:
    std::string name_;
};

// virtual → فقط یک Person مشترک در سلسله‌مراتب وجود دارد
class Student : public virtual Person {
public:
    explicit Student(const std::string& name)
        : Person(name) {
        std::cout << "  ساخت Student\n";
    }

    std::string role() const {
        return "دانشجو";
    }
};

class Teacher : public virtual Person {
public:
    explicit Teacher(const std::string& name)
        : Person(name) {
        std::cout << "  ساخت Teacher\n";
    }

    std::string role() const {
        return "استاد";
    }
};

class TeachingAssistant : public Student, public Teacher {
public:
    // سازندهٔ کلاس مشتق‌ترین، Person مشترک را مستقیم می‌سازد
    explicit TeachingAssistant(const std::string& name)
        : Person(name), Student(name), Teacher(name) {
        std::cout << "  ساخت TeachingAssistant\n";
    }

    std::string role() const {
        return Student::role() + " + " + Teacher::role();
    }
};

int main() {
    std::cout << "--- ساخت ---\n";
    const TeachingAssistant assistant("Saeed");

    std::cout << "\n--- استفاده ---\n";
    assistant.introduce();                 // فقط یک Person وجود دارد
    std::cout << "  نقش: " << assistant.role() << "\n";
    std::cout << "  نام از Student: " << assistant.Student::name() << "\n";
    std::cout << "  نام از Teacher: " << assistant.Teacher::name() << "\n";

    // بدون virtual، دو نسخهٔ Person وجود داشت و این خط خطای کامپایل می‌داد:
    //   assistant.name();          // ❌ ambiguous
    //   assistant.introduce();     // ❌ ambiguous
    // و برای دسترسی، باید می‌نوشتیم assistant.Student::name().

    // نکته: در وراثت مجازی، سازندهٔ کلاس‌های میانی Person را نمی‌سازند؛
    // فقط مشتق‌ترین کلاس آن را می‌سازد. به همین دلیل در سازندهٔ
    // TeachingAssistant، Person(name) را صریح نوشتیم.

    return 0;
}
