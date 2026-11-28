// 02_word_count.cpp — شمارش کلمات با map: الگوی ++counts[word]
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o word_count examples/02_word_count.cpp

#include <iostream>
#include <map>
#include <sstream>
#include <string>

int main() {
    const std::string text = "cpp stl cpp map cpp vector map set";
    std::istringstream stream(text);

    std::map<std::string, int> counts;
    std::string word;
    int totalWords = 0;
    while (stream >> word) {
        ++counts[word];   // اگر نبود صفر می‌سازد، بعد یکی اضافه می‌کند
        ++totalWords;
    }

    std::cout << "شمارش کلمات (کلید مرتب):\n";
    for (const auto& [key, count] : counts) {
        std::cout << "  " << key << " = " << count << "\n";
    }
    std::cout << "تعداد کلمات یکتا: " << counts.size() << "\n";
    std::cout << "تعداد کل کلمات: " << totalWords << "\n";

    // پرتکرارترین کلمه بدون استفاده از الگوریتم
    std::string topWord;
    int topCount = 0;
    for (const auto& entry : counts) {
        if (entry.second > topCount) {
            topCount = entry.second;
            topWord = entry.first;
        }
    }
    std::cout << "پرتکرارترین: " << topWord << " (" << topCount << ")\n";

    // find برای پرس‌وجوی بدون درج
    const auto it = counts.find("stl");
    if (it != counts.end()) {
        std::cout << "stl = " << it->second << "\n";
    }

    return 0;
}
