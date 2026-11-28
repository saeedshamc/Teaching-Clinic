// exercise_01.cpp — تمرین ۱: کلاس بافر با سازندهٔ انتقال
//
// TODO: کلاس Buffer را کامل کنید:
//   - سازندهٔ صریح با اندازه (data_ را با آن اندازه پر از صفر بسازید)
//   - سازندهٔ کپی که "کپی" چاپ کند
//   - سازندهٔ انتقال noexcept که "انتقال" چاپ کند و منبع را خالی بگذارد
//   - عملگر انتساب کپی ("کپی=") و انتقال ("انتقال=")
//   - ()size و ()empty
//
// نمونهٔ انتظاری:
//   ساخت 5
//   کپی: 5
//   انتقال: 5
//   a.size() = 0
//   c.size() = 5
//   ساخت 3
//   کپی=
//   انتقال=
//   پایانی: 5 عنصر، d.empty() = خیر
//
// نکته: بعد از انتقال، اندازهٔ منبع باید صفر باشد — این تعریف «انتقال» است.
//
// راهنما:
//   Buffer(Buffer&& other) noexcept : data_(std::move(other.data_)) { ... }

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

    // TODO: چهار عضو دیگر

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
    // TODO: سناریوی نمونهٔ انتظاری را بنویسید

    return 0;
}
