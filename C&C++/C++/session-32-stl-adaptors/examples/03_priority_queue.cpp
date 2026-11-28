// 03_priority_queue.cpp — priority_queue: همیشه بزرگ‌ترین (یا کوچک‌ترین) اول
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o pq examples/03_priority_queue.cpp

#include <functional>
#include <iostream>
#include <queue>
#include <string>
#include <vector>

struct Task {
    std::string name;
    int priority;
};

// کامپارator: کم‌ترین اولویت پایین‌تر می‌ماند، پس بالاترین اولویت top است
struct TaskCompare {
    bool operator()(const Task& left, const Task& right) const {
        return left.priority < right.priority;
    }
};

int main() {
    std::priority_queue<int> maxHeap;
    for (int value : {3, 1, 5, 2}) {
        maxHeap.push(value);
    }
    std::cout << "--- max-heap (پیش‌فرض) ---\n  ";
    while (!maxHeap.empty()) {
        std::cout << maxHeap.top() << ' ';
        maxHeap.pop();
    }
    std::cout << "\n";

    std::priority_queue<int, std::vector<int>, std::greater<int>> minHeap;
    for (int value : {3, 1, 5, 2}) {
        minHeap.push(value);
    }
    std::cout << "--- min-heap با greater<int> ---\n  ";
    while (!minHeap.empty()) {
        std::cout << minHeap.top() << ' ';
        minHeap.pop();
    }
    std::cout << "\n";

    std::priority_queue<Task, std::vector<Task>, TaskCompare> tasks;
    tasks.push({"مستندات", 1});
    tasks.push({"رفع باگ", 5});
    tasks.push({"جلسه", 3});
    std::cout << "--- صف اولویت‌دار کارها ---\n";
    while (!tasks.empty()) {
        std::cout << "  " << tasks.top().priority << " → " << tasks.top().name << "\n";
        tasks.pop();
    }

    std::cout << "priority_queue روی vector ساخته می‌شود و یک heap نگه می‌دارد.\n";
    return 0;
}
