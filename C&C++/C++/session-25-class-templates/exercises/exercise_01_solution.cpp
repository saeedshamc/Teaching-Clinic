// exercise_01_solution.cpp — راه‌حل تمرین ۱: Pair<A, B>
// کامپایل: g++ -std=c++17 -Wall -Wextra -o pair exercises/exercise_01_solution.cpp

#include <iostream>
#include <sstream>
#include <string>
#include <utility>

template <typename A, typename B>
class Pair {
public:
    Pair(const A& first, const B& second)
        : first_(first), second_(second) {
    }

    const A& first() const {
        return first_;
    }

    const B& second() const {
        return second_;
    }

    void setFirst(const A& value) {
        first_ = value;
    }

    void setSecond(const B& value) {
        second_ = value;
    }

    // فقط وقتی معنی دارد که A و B یک نوع باشند (مثل Pair<int, int>).
    // عضوهای قالب کلاس تا زمانی که صدا زده نشوند ساخته نمی‌شوند،
    // پس وجود این متد برای Pair<int, string> خطا نمی‌دهد.
    void swap() {
        std::swap(first_, second_);
    }

    std::string toText() const {
        std::ostringstream out;
        out << "(" << first_ << ", " << second_ << ")";
        return out.str();
    }

private:
    A first_;
    B second_;
};

template <typename A, typename B>
Pair<A, B> makePair(const A& first, const B& second) {
    return Pair<A, B>(first, second);
}

int main() {
    Pair<int, std::string> pair1 = makePair(1, std::string("یک"));
    std::cout << "pair1 = " << pair1.toText() << "\n";

    const Pair<double, char> pair2 = makePair(2.5, 'x');
    std::cout << "pair2 = " << pair2.toText() << "\n";

    pair1.setSecond(std::string("یک‌بار"));
    std::cout << "بعد از setSecond: " << pair1.toText() << "\n";

    Pair<int, int> numbers(10, 20);
    std::cout << "\nقبل از swap: " << numbers.toText() << "\n";
    numbers.swap();
    std::cout << "بعد از swap: " << numbers.toText() << "\n";

    // makePair نوع‌ها را از آرگومان‌ها استنتاج می‌کند:
    //   makePair(1, "یک")  → Pair<int, const char*>  ⚠
    // پس برای رشته، std::string(...) را صریح بدهید.
    const Pair<int, std::string> typed = makePair<int, std::string>(7, "هفت");
    std::cout << "\nصراحت نوع: " << typed.toText() << "\n";

    return 0;
}
