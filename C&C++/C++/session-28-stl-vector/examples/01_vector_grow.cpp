// 01_vector_grow.cpp — size در برابر capacity و اثر reserve
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o grow examples/01_vector_grow.cpp

#include <iostream>
#include <vector>

void printState(const std::vector<int>& data) {
    std::cout << "  size = " << data.size()
              << "، capacity = " << data.capacity() << "\n";
}

int main() {
    std::cout << "--- رشد خودکار بدون reserve ---\n";
    std::vector<int> numbers;
    for (int i = 1; i <= 5; ++i) {
        numbers.push_back(i * 10);
        std::cout << "push_back(" << i * 10 << ")\n";
        printState(numbers);
    }

    std::cout << "--- با reserve از قبل ---\n";
    std::vector<int> reserved;
    reserved.reserve(5);
    printState(reserved);
    for (int i = 1; i <= 5; ++i) {
        reserved.push_back(i * 10);
        printState(reserved);
    }

    std::cout << "--- shrink_to_fit یک درخواست است، نه تضمین ---\n";
    std::vector<int> big;
    big.reserve(100);
    big.push_back(7);
    printState(big);
    big.shrink_to_fit();
    printState(big);

    std::cout << "--- آزمایش reserve بیشتر از نیاز ---\n";
    std::vector<int> over;
    over.reserve(16);
    over.push_back(1);
    printState(over);

    return 0;
}
