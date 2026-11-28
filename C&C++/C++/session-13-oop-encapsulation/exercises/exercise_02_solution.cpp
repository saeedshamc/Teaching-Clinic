// exercise_02_solution.cpp — راه‌حل تمرین ۲: کلاس Student با اعتبارسنجی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o student exercises/exercise_02_solution.cpp

#include <iomanip>
#include <iostream>
#include <string>

class Student {
public:
    bool setName(const std::string& value) {
        if (value.find_first_not_of(" \t") == std::string::npos) {
            return false;              // خالی یا فقط فاصله
        }
        name_ = value;
        return true;
    }

    bool setScore(double value) {
        if (value < 0.0 || value > 20.0) {
            return false;
        }
        score_ = value;
        return true;
    }

    const std::string& name() const {
        return name_;
    }

    double score() const {
        return score_;
    }

    bool isPassed() const {
        return score_ >= 10.0;
    }

    void print() const {
        std::cout << name_ << ": " << score_ << " → "
                  << (isPassed() ? "قبول" : "مردود") << "\n";
    }

private:
    std::string name_ = "بدون نام";
    double score_ = 0.0;
};

int main() {
    Student student;

    std::cout << "setName(\"   \") پذیرفته شد؟ "
              << (student.setName("   ") ? "بله" : "خیر") << "\n";
    std::cout << "setScore(25) پذیرفته شد؟ "
              << (student.setScore(25.0) ? "بله" : "خیر") << "\n";
    std::cout << "setScore(-3) پذیرفته شد؟ "
              << (student.setScore(-3.0) ? "بله" : "خیر") << "\n";

    std::cout << "\nsetScore(18.5) پذیرفته شد؟ "
              << (student.setScore(18.5) ? "بله" : "خیر") << "\n";
    student.setName("Saeed");

    std::cout << std::fixed << std::setprecision(2);
    student.print();

    std::cout << "\nنام فعلی: " << student.name()
              << " ، نمره: " << student.score()
              << " ، قبول: " << (student.isPassed() ? "بله" : "خیر") << "\n";

    // student.score_ = 100.0;   → خطای کامپایل
    return 0;
}
