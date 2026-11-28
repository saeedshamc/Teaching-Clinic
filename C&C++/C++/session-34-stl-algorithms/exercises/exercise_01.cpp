// exercise_01.cpp — تمرین ۱: مرتب‌سازی دانشجوها با کامپارator سفارشی
//
// TODO: سه چیز بسازید:
//   ۱) تابع کامپارator:
//        bool byGradeDesc(const Student& left, const Student& right);
//      اولویت اول: نمرهٔ بالاتر؛ در نمرهٔ برابر: نام به ترتیب حروف الفبا
//   ۲) functor برای شمارش دانشجوهای با نمرهٔ ۲۰:
//        struct HasGrade20 { bool operator()(const Student& student) const; };
//   ۳) main که مرتب‌سازی و شمارش را انجام دهد
//
// نمونهٔ انتظاری:
//   قبل از مرتب‌سازی:
//     Ali 17
//     Sara 20
//     Omid 20
//     Mina 15
//   بعد از مرتب‌سازی:
//     Omid 20
//     Sara 20
//     Ali 17
//     Mina 15
//   بهترین نمره: 20
//   دانشجوهای با نمرهٔ ۲۰: 2
//
// راهنما:
//   bool byGradeDesc(const Student& left, const Student& right) {
//       if (left.grade != right.grade) return left.grade > right.grade;
//       return left.name < right.name;
//   }
//   std::sort(students.begin(), students.end(), byGradeDesc);
//   std::count_if(students.begin(), students.end(), HasGrade20{});

#include <algorithm>
#include <iostream>
#include <string>
#include <vector>

struct Student {
    std::string name;
    int grade;
};

// TODO: byGradeDesc و HasGrade20

void print(const std::string& label, const std::vector<Student>& students) {
    std::cout << label << ":\n";
    for (const auto& student : students) {
        std::cout << "  " << student.name << " " << student.grade << "\n";
    }
}

int main() {
    std::vector<Student> students{{"Ali", 17}, {"Sara", 20}, {"Omid", 20}, {"Mina", 15}};

    // TODO: چاپ قبل، sort، چاپ بعد، بهترین نمره، شمارش نمرهٔ ۲۰

    return 0;
}
