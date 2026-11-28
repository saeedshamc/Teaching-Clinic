// exercise_01_solution.cpp — راه‌حل تمرین ۱: swap و maxOf
// کامپایل: g++ -std=c++17 -Wall -Wextra -o swapmax exercises/exercise_01_solution.cpp

#include <cstddef>
#include <iostream>
#include <string>
#include <vector>

template <typename T>
void swapValues(T& a, T& b) {
    T temp = a;
    a = b;
    b = temp;
}

template <typename T>
T maxOf(const std::vector<T>& values) {
    if (values.empty()) {
        return T();
    }

    T result = values.front();
    for (std::size_t i = 1; i < values.size(); ++i) {
        if (result < values[i]) {
            result = values[i];
        }
    }
    return result;
}

int main() {
    int a = 10;
    int b = 20;
    swapValues(a, b);
    std::cout << "swap int: a = " << a << " ، b = " << b << "\n";

    std::string first = "سلام";
    std::string second = "دنیا";
    swapValues(first, second);
    std::cout << "swap string: first = " << first << " ، second = " << second << "\n";

    const std::vector<int> integers{3, 12, 7, -4};
    const std::vector<double> doubles{2.5, 9.5, 1.25};
    const std::vector<std::string> fruits{"apple", "peach", "banana"};
    const std::vector<int> empty;

    std::cout << "\nmax int = " << maxOf(integers) << "\n";
    std::cout << "max double = " << maxOf(doubles) << "\n";
    std::cout << "max string = " << maxOf(fruits) << "\n";
    std::cout << "max خالی = " << maxOf(empty) << "\n";

    return 0;
}
