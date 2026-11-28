// 02_move_ctor.cpp — سازندهٔ انتقال در برابر سازندهٔ کپی
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o move_ctor examples/02_move_ctor.cpp

#include <iostream>
#include <string>
#include <utility>
#include <vector>

class Buffer {
public:
    explicit Buffer(std::size_t size)
        : data_(size, 0) {
        report("ساخت", size);
    }

    Buffer(const Buffer& other)
        : data_(other.data_) {
        report("کپی", data_.size());
    }

    Buffer(Buffer&& other) noexcept
        : data_(std::move(other.data_)) {
        report("انتقال", data_.size());
    }

    Buffer& operator=(const Buffer& other) {
        data_ = other.data_;
        report("کپی=", data_.size());
        return *this;
    }

    Buffer& operator=(Buffer&& other) noexcept {
        data_ = std::move(other.data_);
        report("انتقال=", data_.size());
        return *this;
    }

    ~Buffer() {
        report("تخریب", data_.size());
    }

    std::size_t size() const {
        return data_.size();
    }

private:
    void report(const std::string& label, std::size_t size) const {
        std::cout << "  " << label << ": " << size << " عنصر\n";
    }

    std::vector<int> data_;
};

Buffer makeBuffer(std::size_t size) {
    return Buffer(size);   // بدون کپی و انتقال به لطف elision
}

void consume(Buffer buffer) {
    std::cout << "  داخل consume: " << buffer.size() << " عنصر\n";
}

int main() {
    std::cout << "--- ساخت ---\n";
    const Buffer original(5);

    std::cout << "--- کپی صریح ---\n";
    Buffer copied = original;

    std::cout << "--- انتقال از شیء غیر const ---\n";
    Buffer moved = std::move(copied);
    std::cout << "  size بعد از انتقال: " << moved.size() << "\n";

    std::cout << "--- انتقال از شیء const: move به کپی تبدیل می‌شود ---\n";
    const Buffer forcedCopy = std::move(original);
    std::cout << "  forcedCopy.size() = " << forcedCopy.size() << "\n";

    std::cout << "--- مقدار برگشتی ---\n";
    std::cout << "  makeBuffer(4) صدا زده می‌شود\n";
    const Buffer returned = makeBuffer(4);
    std::cout << "  returned.size() = " << returned.size() << "\n";

    std::cout << "--- ارسال با ارزش به تابع ---\n";
    Buffer local(3);
    consume(std::move(local));

    std::cout << "--- vector و انتقال ---\n";
    std::vector<Buffer> buffers;
    buffers.reserve(3);
    std::cout << "  push_back(Buffer(2)):\n";
    buffers.push_back(Buffer(2));
    std::cout << "  emplace_back(3):\n";
    buffers.emplace_back(3);
    std::cout << "  push_back(std::move(local)):\n";
    buffers.push_back(std::move(local));
    std::cout << "  تعداد: " << buffers.size() << "\n";

    std::cout << "--- پایان ---\n";
    return 0;
}
