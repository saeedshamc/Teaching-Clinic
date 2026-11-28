// exercise_02_solution.cpp — راه‌حل تمرین ۲: کلاس Student
// کامپایل: g++ -std=c++17 -Wall -Wextra -o student exercises/exercise_02_solution.cpp

#include <iostream>
#include <string>

class Student {
public:
    Student(const std::string& name, int id)
        : name_(name.find_first_not_of(" \t") == std::string::npos ? "unknown" : name),
          id_(id) {
    }

    const std::string& name() const {
        return name_;
    }

    int id() const {
        return id_;
    }

    bool addGrade(double grade) {
        if (grade < 0.0 || grade > 20.0) {
            return false;
        }
        if (count_ >= kMaxGrades) {
            return false;
        }
        grades_[count_] = grade;
        ++count_;
        return true;
    }

    int gradeCount() const {
        return count_;
    }

    double average() const {
        if (count_ == 0) {
            return 0.0;
        }
        double sum = 0.0;
        for (int i = 0; i < count_; ++i) {
            sum += grades_[i];
        }
        return sum / static_cast<double>(count_);
    }

    bool isPassed() const {
        return count_ > 0 && average() >= 10.0;
    }

private:
    static constexpr int kMaxGrades = 5;

    std::string name_;
    int id_ = 0;                 // تغییرناپذیر بعد از ساخت
    double grades_[kMaxGrades] = {};
    int count_ = 0;
};

int main() {
    Student student("Saeed", 1001);
    std::cout << "Student #" << student.id() << ": " << student.name() << "\n\n";

    std::cout << "addGrade(18.5) → " << (student.addGrade(18.5) ? "موفق" : "رد شد") << "\n";
    std::cout << "addGrade(25) → " << (student.addGrade(25.0) ? "موفق" : "رد شد") << "\n";
    std::cout << "addGrade(-2) → " << (student.addGrade(-2.0) ? "موفق" : "رد شد") << "\n";
    std::cout << "addGrade(16) → " << (student.addGrade(16.0) ? "موفق" : "رد شد") << "\n";
    std::cout << "addGrade(11) → " << (student.addGrade(11.0) ? "موفق" : "رد شد") << "\n";

    std::cout << "\nتعداد نمره: " << student.gradeCount() << "\n";
    std::cout << "میانگین: " << student.average() << "\n";
    std::cout << "وضعیت: " << (student.isPassed() ? "قبول" : "مردود") << "\n";

    std::cout << "\n--- پر کردن ظرفیت ---\n";
    Student full("Ali", 1002);
    for (int i = 0; i < 6; ++i) {
        std::cout << "addGrade(" << (10 + i) << ") → "
                  << (full.addGrade(static_cast<double>(10 + i)) ? "موفق" : "رد شد (ظرفیت پر)") << "\n";
    }
    std::cout << "تعداد نمره: " << full.gradeCount() << " (حداکثر 5)\n";

    std::cout << "\n--- دانشجوی بدون نمره ---\n";
    Student empty("", 1003);
    std::cout << "نام: " << empty.name() << " ، میانگین: " << empty.average()
              << " ، قبول؟ " << (empty.isPassed() ? "بله" : "خیر") << "\n";

    // student.id_ = 99;   → خطای کامپایل، شناسه تغییرناپذیر است

    return 0;
}
