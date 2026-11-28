// exercise_01_solution.cpp — راه‌حل تمرین ۱: مرتب‌سازی دانشجوها
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o students exercises/exercise_01_solution.cpp

#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

struct Student {
    std::string name;
    int grade;
};

bool byGradeDesc(const Student& left, const Student& right) {
    if (left.grade != right.grade) {
        return left.grade > right.grade;
    }
    return left.name < right.name;
}

struct HasGrade20 {
    bool operator()(const Student& student) const {
        return student.grade == 20;
    }
};

void print(const std::string& label, const std::vector<Student>& students) {
    std::cout << label << ":\n";
    for (const auto& student : students) {
        std::cout << "  " << student.name << " " << student.grade << "\n";
    }
}

int main() {
    std::vector<Student> students{{"Ali", 17}, {"Sara", 20}, {"Omid", 20}, {"Mina", 15}};

    print("قبل از مرتب‌سازی", students);

    std::sort(students.begin(), students.end(), byGradeDesc);
    print("بعد از مرتب‌سازی", students);

    int best = 0;
    for (const auto& student : students) {
        if (student.grade > best) {
            best = student.grade;
        }
    }
    std::cout << "بهترین نمره: " << best << "\n";

    const auto topCount = std::count_if(students.begin(), students.end(), HasGrade20{});
    std::cout << "دانشجوهای با نمرهٔ ۲۰: " << topCount << "\n";

    return 0;
}
