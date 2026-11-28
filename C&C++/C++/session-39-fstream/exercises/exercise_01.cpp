// exercise_01.cpp — تمرین ۱: ذخیره و بازیابی رکوردها در فایل متنی
//
// TODO: دو تابع بنویسید:
//   void saveRecords(const fs::path& path, const std::vector<Record>& records);
//   std::vector<Record> loadRecords(const fs::path& path);
//
// قالب خط: name;score   (جداکنندهٔ نقطه‌ویرگول)
//
// نمونهٔ انتظاری:
//   ذخیره شد: 3 رکورد
//   اندازهٔ فایل: 23 بایت  (Sara;95 و Omid;72 هر کدام ۸ بایت، Ali;88 هفت بایت)
//   --- بازخوانی ---
//     Sara: 95
//     Ali: 88
//     Omid: 72
//   تعداد خوانده‌شده: 3
//   رکورد با بالاترین نمره: Sara (95)
//
// نکته: اندازهٔ فایل واقعی ممکن است متفاوت باشد؛ فقط تعداد و مقادیر را بررسی کنید.
//
// راهنما:
//   برای هر رکورد: output << record.name << ';' << record.score << '\n';
//   برای خواندن: getline(input, line) سپس istringstream و getline(parts, name, ';')

#include <filesystem>
#include <fstream>
#include <iostream>
#include <sstream>
#include <string>
#include <vector>

namespace fs = std::filesystem;

struct Record {
    std::string name;
    int score;
};

// TODO: saveRecords و loadRecords

void print(const std::vector<Record>& records) {
    for (const auto& record : records) {
        std::cout << "  " << record.name << ": " << record.score << "\n";
    }
}

int main() {
    // TODO: ذخیره، خواندن، چاپ و پیدا کردن بالاترین نمره در فایل موقت

    return 0;
}
