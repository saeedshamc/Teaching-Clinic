// exercise_01.cpp — تمرین ۱: بررسی توازن پرانتزها با stack
//
// TODO: تابع isBalanced را پیاده کنید که بررسی کند پرانتزها، کروشه‌ها و آکولادها
//       در متن درست باز و بسته شده‌اند.
//
// امضا:
//   bool isBalanced(const std::string& text);
//
// قواعد:
//   - هر پرانتز باز باید با همان نوع بسته شود
//   - ترتیب باید درست باشد: "([)]" نامتوازن است
//   - پرانتز بسته اضافه هم نامتوازن است: "())" نامتوازن است
//
// نمونهٔ انتظاری:
//   "(a[b]{c})"  → متوازن
//   "([)]"       → نامتوازن
//   "((("        → نامتوازن
//   ""           → متوازن
//
// راهنما:
//   std::stack<char> open;
//   for (char c : text) {
//       if (c == '(' || c == '[' || c == '{') open.push(c);
//       else if (c == ')' || c == ']' || c == '}') {
//           if (open.empty()) return false;
//           char top = open.top();
//           open.pop();
//           // بررسی جفت بودن top و c
//       }
//   }
//   return open.empty();

#include <iostream>
#include <stack>
#include <string>

// TODO: تابع isBalanced

int main() {
    // TODO: چهار رشتهٔ نمونه را بررسی و نتیجه را چاپ کنید

    return 0;
}
