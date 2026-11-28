// 03_unordered_map.cpp — unordered_map و هش سفارشی برای کلید struct
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o unordered_map_example examples/03_unordered_map.cpp

#include <cstddef>
#include <iostream>
#include <string>
#include <unordered_map>

struct Point {
    int x;
    int y;

    bool operator==(const Point& other) const {
        return x == other.x && y == other.y;
    }
};

// برای کلید سفارشی، هم operator== لازم است و هم تابع هش
struct PointHash {
    std::size_t operator()(const Point& point) const {
        const std::size_t hx = std::hash<int>{}(point.x);
        const std::size_t hy = std::hash<int>{}(point.y);
        return hx ^ (hy + 0x9e3779b9u + (hx << 6) + (hx >> 2));
    }
};

int main() {
    std::unordered_map<Point, std::string, PointHash> labels{
        {{0, 0}, "مبدأ"},
        {{1, 2}, "نقطهٔ A"},
        {{2, 1}, "نقطهٔ B"},
    };

    labels[{1, 2}] = "نقطهٔ A (به‌روز)";
    labels[{3, 4}] = "نقطهٔ C";

    std::cout << "--- برچسب نقطه‌ها (ترتیب تضمین‌شده نیست) ---\n";
    for (const auto& [point, label] : labels) {
        std::cout << "  (" << point.x << ", " << point.y << ") → " << label << "\n";
    }
    std::cout << "  size = " << labels.size() << "\n";

    std::cout << "--- جستجو ---\n";
    const Point target{1, 2};
    const auto it = labels.find(target);
    if (it != labels.end()) {
        std::cout << "  find((1, 2)) → " << it->second << "\n";
    }
    std::cout << "  count((9, 9)) = " << labels.count({9, 9}) << "\n";

    // unordered_map با کلید رشتی هم ترتیب حروف الفبا ندارد
    std::unordered_map<std::string, int> freq{{"cpp", 3}, {"stl", 1}, {"map", 2}};
    std::cout << "--- unordered_map<string, int> ---\n";
    for (const auto& [key, value] : freq) {
        std::cout << "  " << key << " = " << value << "\n";
    }
    std::cout << "برای ترتیب مرتب از map استفاده کنید.\n";

    return 0;
}
