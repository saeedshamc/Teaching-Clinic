// 02_complexity.cpp — پیچیدگی زمانی را با شمارش واقعی مقایسه کنید، نه با حس
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o complexity examples/02_complexity.cpp

#include <iostream>
#include <set>
#include <vector>

// جستجوی خطی: بدترین حالت n مقایسه
int linearSearchSteps(const std::vector<int>& data, int target) {
    int steps = 0;
    for (int value : data) {
        ++steps;
        if (value == target) {
            return steps;
        }
    }
    return steps;
}

// جستجوی دودویی روی دادهٔ مرتب: بدترین حالت log2(n) مقایسه
int binarySearchSteps(const std::vector<int>& data, int target) {
    int low = 0;
    int high = static_cast<int>(data.size()) - 1;
    int steps = 0;
    while (low <= high) {
        ++steps;
        const int mid = low + (high - low) / 2;
        if (data[mid] == target) {
            return steps;
        }
        if (data[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }
    return steps;
}

int main() {
    const int size = 1024;
    std::vector<int> data;
    data.reserve(static_cast<std::size_t>(size));
    for (int i = 0; i < size; ++i) {
        data.push_back(i * 2);   // 0, 2, 4, ... : مرتب
    }

    const int target = 2000;     // عنصر یک‌به‌آخر: بدترین حالت برای جستجوی خطی
    std::cout << "اندازهٔ داده: " << data.size() << "\n";
    std::cout << "جستجوی خطی برای " << target << ": "
              << linearSearchSteps(data, target) << " مقایسه\n";
    std::cout << "جستجوی دودویی برای " << target << ": "
              << binarySearchSteps(data, target) << " مقایسه\n";

    // set خودش درخت متوازن است: find هم لگاریتمی است
    std::set<int> tree(data.begin(), data.end());
    std::cout << "set::count(" << target << ") در مجموعهٔ "
              << tree.size() << " عضوی: "
              << (tree.count(target) > 0 ? "پیدا شد" : "نبود") << "\n";
    std::cout << "log2(1024) = 10\n";

    return 0;
}
