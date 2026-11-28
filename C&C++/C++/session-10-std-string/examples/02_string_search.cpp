// 02_string_search.cpp — جست‌وجو، جایگزینی و حذف در std::string
// کامپایل: g++ -std=c++17 -Wall -Wextra -o string_search examples/02_string_search.cpp

#include <iostream>
#include <string>

int main() {
    std::string text = "C++ is powerful. C++ is fast.";

    // --- find: اولین جایگاه ---
    const std::size_t firstPos = text.find("C++");
    std::cout << "text = " << text << "\n";
    std::cout << "first find(\"C++\") = " << firstPos << "\n";

    // --- find با شروع از جایگاه بعدی ---
    const std::size_t secondPos = text.find("C++", firstPos + 1);
    std::cout << "second find(\"C++\") = " << secondPos << "\n";

    // --- npos: یعنی «پیدا نشد» ---
    const std::size_t missing = text.find("Java");
    std::cout << "find(\"Java\") == std::string::npos ؟ "
              << (missing == std::string::npos ? "بله" : "خیر") << "\n";

    // --- شمارش همهٔ تکرارها ---
    int occurrences = 0;
    std::size_t pos = text.find("C++");
    while (pos != std::string::npos) {
        ++occurrences;
        pos = text.find("C++", pos + 1);
    }
    std::cout << "تعداد تکرار C++ = " << occurrences << "\n";

    // --- rfind: آخرین جایگاه ---
    std::cout << "rfind(\"is\") = " << text.rfind("is") << "\n";

    // --- find_first_of / find_last_of ---
    std::cout << "find_first_of(\" .\") = " << text.find_first_of(" .") << "\n";

    // --- replace ---
    std::string message = "I like Java.";
    const std::size_t javaPos = message.find("Java");
    if (javaPos != std::string::npos) {
        message.replace(javaPos, 4, "C++");   // ۴ کاراکتر از javaPos با "C++" عوض می‌شود
    }
    std::cout << "\nmessage = " << message << "\n";

    // --- insert و erase ---
    std::string tags = "C++";
    tags.insert(0, "[");
    tags.insert(tags.length(), "]");
    std::cout << "tags = " << tags << "\n";

    tags.erase(0, 1);                     // حذف یک کاراکتر از ابتدا
    std::cout << "بعد از erase(0, 1) = " << tags << "\n";

    // --- برش با substr برای تقسیم جمله ---
    const std::string line = "ali:85:sara:92";
    std::size_t start = 0;
    std::cout << "\nتقسیم \"" << line << "\" با ':' → ";
    while (start <= line.length()) {
        const std::size_t colon = line.find(':', start);
        if (colon == std::string::npos) {
            std::cout << line.substr(start) << "\n";
            break;
        }
        std::cout << line.substr(start, colon - start) << " | ";
        start = colon + 1;
    }

    // --- تغییر همهٔ تکرارها ---
    std::string original = "a-b-c-d";
    std::size_t dash = original.find('-');
    while (dash != std::string::npos) {
        original.replace(dash, 1, " + ");
        dash = original.find('-', dash + 3);
    }
    std::cout << "جایگزینی همهٔ '-' : " << original << "\n";

    return 0;
}
