// exercise_01_solution.cpp — راه‌حل تمرین ۱: شمارش تکرار کلمات با unordered_map
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o word_freq exercises/exercise_01_solution.cpp

#include <iostream>
#include <map>
#include <sstream>
#include <string>
#include <unordered_map>

int main() {
    const std::string text = "vector map vector set map vector list";

    std::unordered_map<std::string, int> counts;
    std::istringstream stream(text);
    std::string word;
    while (stream >> word) {
        ++counts[word];
    }

    // چاپ قطعی: کپی به map (کلیدها مرتب می‌شوند)
    const std::map<std::string, int> sorted(counts.begin(), counts.end());
    std::cout << "شمارش (بر اساس حروف):\n";
    for (const auto& [key, count] : sorted) {
        std::cout << "  " << key << " = " << count << "\n";
    }
    std::cout << "یکتا: " << counts.size() << "\n";

    std::string topWord;
    int topCount = 0;
    for (const auto& entry : counts) {
        if (entry.second > topCount) {
            topCount = entry.second;
            topWord = entry.first;
        }
    }
    std::cout << "پرتکرار: " << topWord << " (" << topCount << ")\n";
    std::cout << "count(\"set\") = " << counts.count("set") << "\n";

    return 0;
}
