// exercise_01.cpp — تمرین ۱: حذف تکراری‌ها با set
//
// TODO: تابع dedupe را پیاده کنید که:
//   - یک std::vector<std::string> می‌گیرد
//   - با std::set عناصر یکتا را استخراج می‌کند
//   - خروجی مرتب‌شده و تعداد حذف‌شده‌ها را با std::pair برمی‌گرداند
//
// امضا:
//   std::pair<std::vector<std::string>, int> dedupe(const std::vector<std::string>& input);
//
// نمونهٔ انتظاری:
//   قبل: [cpp, stl, cpp, map, stl, vector]
//   بعد: [cpp, map, stl, vector]
//   حذف‌شده: 2
//
// راهنما:
//   std::set<std::string> unique(input.begin(), input.end());
//   std::vector<std::string> result(unique.begin(), unique.end());
//   int removed = static_cast<int>(input.size() - result.size());

#include <iostream>
#include <set>
#include <string>
#include <utility>
#include <vector>

// TODO: تابع dedupe

int main() {
    // TODO: ورودی را بسازید، dedupe را صدا بزنید و قبل/بعد/تعداد حذف‌شده را چاپ کنید

    return 0;
}
