// 01_max_template.cpp — قالب تابع: یک تابع برای چند نوع
// کامپایل: g++ -std=c++17 -Wall -Wextra -o max examples/01_max_template.cpp

#include <cstddef>
#include <iostream>
#include <string>
#include <vector>

// T یک «نوع جای‌نگهدار» است؛ کامپایلر آن را از روی آرگومان‌ها استنتاج می‌کند
template <typename T>
T maxOf(const T& a, const T& b) {
    return a < b ? b : a;
}

template <typename T>
void swapValues(T& a, T& b) {
    T temp = a;
    a = b;
    b = temp;
}

// قالب با کانتینر: هر چیزی که size() و [] دارد
template <typename T>
void printAll(const std::vector<T>& values) {
    std::cout << "[";
    for (std::size_t i = 0; i < values.size(); ++i) {
        if (i > 0) {
            std::cout << ", ";
        }
        std::cout << values[i];
    }
    std::cout << "]\n";
}

// دو نوع مختلف: نوع بازگشتی را کامپایلر استنتاج می‌کند
template <typename A, typename B>
auto addDifferent(const A& a, const B& b) {
    return a + b;
}

int main() {
    std::cout << "maxOf(3, 7) = " << maxOf(3, 7) << "\n";
    std::cout << "maxOf(2.5, 1.5) = " << maxOf(2.5, 1.5) << "\n";
    std::cout << "maxOf(\"apple\", \"banana\") = " << maxOf<std::string>("apple", "banana") << "\n";

    int a = 10;
    int b = 20;
    swapValues(a, b);
    std::cout << "\nafter swap: a = " << a << " ، b = " << b << "\n";

    std::string first = "سلام";
    std::string second = "دنیا";
    swapValues(first, second);
    std::cout << "after swap: " << first << " " << second << "\n";

    const std::vector<int> numbers{3, 1, 4, 1, 5};
    const std::vector<std::string> words{"C++", "قالب", "تابع"};
    std::cout << "\nاعداد: ";
    printAll(numbers);
    std::cout << "کلمات: ";
    printAll(words);

    std::cout << "\naddDifferent(2, 3.5) = " << addDifferent(2, 3.5) << "\n";
    std::cout << "addDifferent(string, char) = "
              << addDifferent(std::string("C++"), '!') << "\n";

    // deducing: هر دو آرگومان باید یک نوع باشند
    //   maxOf(3, 7.5);   // ❌ خطای کامپایل: T هم int و هم double نمی‌شود
    // راه‌حل ۱: maxOf<double>(3, 7.5)
    // راه‌حل ۲: دو پارامتر نوع جدا
    std::cout << "\nmaxOf<double>(3, 7.5) = " << maxOf<double>(3, 7.5) << "\n";

    return 0;
}
