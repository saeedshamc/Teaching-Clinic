// exercise_01_solution.cpp — راه‌حل تمرین ۱: حذف تکراری‌ها با set
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o dedupe exercises/exercise_01_solution.cpp

#include <iostream>
#include <set>
#include <string>
#include <utility>
#include <vector>

void print(const std::string& label, const std::vector<std::string>& data) {
    std::cout << label << ": [";
    for (std::size_t i = 0; i < data.size(); ++i) {
        if (i > 0) {
            std::cout << ", ";
        }
        std::cout << data[i];
    }
    std::cout << "]\n";
}

std::pair<std::vector<std::string>, int> dedupe(const std::vector<std::string>& input) {
    const std::set<std::string> unique(input.begin(), input.end());
    const std::vector<std::string> result(unique.begin(), unique.end());
    const int removed = static_cast<int>(input.size() - result.size());
    return {result, removed};
}

int main() {
    const std::vector<std::string> input{"cpp", "stl", "cpp", "map", "stl", "vector"};
    print("قبل", input);

    const auto [result, removed] = dedupe(input);
    print("بعد", result);
    std::cout << "حذف‌شده: " << removed << "\n";

    return 0;
}
