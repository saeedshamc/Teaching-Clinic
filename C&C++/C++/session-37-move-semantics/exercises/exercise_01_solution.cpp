// exercise_01_solution.cpp — راه‌حل تمرین ۱: کلاس بافر با انتقال
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o buffer exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>
#include <utility>
#include <vector>

class Buffer {
public:
    explicit Buffer(std::size_t size)
        : data_(size, 0) {
        std::cout << "ساخت " << data_.size() << "\n";
    }

    Buffer(const Buffer& other)
        : data_(other.data_) {
        std::cout << "کپی: " << data_.size() << "\n";
    }

    Buffer(Buffer&& other) noexcept
        : data_(std::move(other.data_)) {
        std::cout << "انتقال: " << data_.size() << "\n";
    }

    Buffer& operator=(const Buffer& other) {
        data_ = other.data_;
        std::cout << "کپی=\n";
        return *this;
    }

    Buffer& operator=(Buffer&& other) noexcept {
        data_ = std::move(other.data_);
        std::cout << "انتقال=\n";
        return *this;
    }

    ~Buffer() = default;

    std::size_t size() const {
        return data_.size();
    }

    bool empty() const {
        return data_.empty();
    }

private:
    std::vector<int> data_;
};

int main() {
    Buffer a(5);
    const Buffer b = a;
    Buffer c = std::move(a);

    std::cout << "a.size() = " << a.size() << "\n";
    std::cout << "c.size() = " << c.size() << "\n";

    Buffer d(3);
    d = b;
    d = std::move(c);

    std::cout << "پایانی: " << d.size() << " عنصر، d.empty() = "
              << (d.empty() ? "بله" : "خیر") << "\n";

    return 0;
}
