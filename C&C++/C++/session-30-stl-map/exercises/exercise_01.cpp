// exercise_01.cpp — تمرین ۱: شمارش تکرار کلمات با unordered_map
//
// TODO: برنامه را کامل کنید تا:
//   ۱) یک متن ثابت را کلمه‌به‌کلمه بخواند
//   ۲) تکرار هر کلمه را در std::unordered_map<std::string, int> بشمارد
//   ۳) برای چاپ قطعی، نتیجه را به std::map کپی کند تا بر اساس حروف مرتب باشد
//   ۴) پرتکرارترین کلمه و تعدادش را پیدا کند
//
// نمونهٔ انتظاری:
//   شمارش (بر اساس حروف):
//     list = 1
//     map = 2
//     set = 1
//     vector = 3
//   یکتا: 4
//   پرتکرار: vector (3)
//   count("set") = 1
//
// متن: "vector map vector set map vector list"
//
// راهنما:
//   std::istringstream stream(text);
//   while (stream >> word) { ++counts[word]; }
//   std::map<std::string, int> sorted(counts.begin(), counts.end());

#include <iostream>
#include <map>
#include <sstream>
#include <string>
#include <unordered_map>

int main() {
    const std::string text = "vector map vector set map vector list";

    // TODO: شمارش، چاپ مرتب، پیدا کردن پرتکرار، و count("set")

    return 0;
}
