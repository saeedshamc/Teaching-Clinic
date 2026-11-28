// 01_getters_setters.cpp — getter/setter درست: خواندن آزاد، نوشتن کنترل‌شده
// کامپایل: g++ -std=c++17 -Wall -Wextra -o book examples/01_getters_setters.cpp

#include <iostream>
#include <string>

class Book {
public:
    Book(const std::string& title, double price, int stock)
        : title_(title),
          price_(price < 0.0 ? 0.0 : price),
          stock_(stock < 0 ? 0 : stock) {
    }

    // --- getterها: const، بدون تغییر وضعیت ---
    const std::string& title() const {
        return title_;
    }

    double price() const {
        return price_;
    }

    int stock() const {
        return stock_;
    }

    // --- عضو محاسبه‌شده: getter نیست، از داده استخراج می‌شود ---
    double totalValue() const {
        return price_ * static_cast<double>(stock_);
    }

    bool isAvailable() const {
        return stock_ > 0;
    }

    // --- setterها: اعتبارسنجی قبل از نوشتن ---
    bool setPrice(double value) {
        if (value < 0.0) {
            return false;
        }
        price_ = value;
        return true;
    }

    bool setStock(int value) {
        if (value < 0) {
            return false;
        }
        stock_ = value;
        return true;
    }

    // --- عملیات دامنه‌ای: فقط همین‌ها می‌توانند وضعیت را عوض کنند ---
    bool sell(int count) {
        if (count <= 0 || count > stock_) {
            return false;
        }
        stock_ -= count;
        return true;
    }

    void restock(int count) {
        if (count > 0) {
            stock_ += count;
        }
    }

private:
    std::string title_;
    double price_ = 0.0;
    int stock_ = 0;
};

int main() {
    Book book("C++ برای همه", 250.0, 4);

    std::cout << "کتاب: " << book.title() << "\n";
    std::cout << "قیمت: " << book.price() << " ، موجودی: " << book.stock() << "\n";
    std::cout << "ارزش انبار: " << book.totalValue() << "\n";
    std::cout << "قابل فروش؟ " << (book.isAvailable() ? "بله" : "خیر") << "\n";

    std::cout << "\n--- setter با مقدار نامعتبر ---\n";
    std::cout << "setPrice(-10): " << (book.setPrice(-10.0) ? "پذیرفته" : "رد شد") << "\n";
    std::cout << "setStock(-1): " << (book.setStock(-1) ? "پذیرفته" : "رد شد") << "\n";
    std::cout << "قیمت هنوز: " << book.price() << " ، موجودی هنوز: " << book.stock() << "\n";

    std::cout << "\n--- عملیات دامنه‌ای ---\n";
    std::cout << "فروش 3: " << (book.sell(3) ? "موفق" : "ناموفق") << "\n";
    std::cout << "فروش 5: " << (book.sell(5) ? "موفق" : "ناموفق (موجودی کافی نیست)") << "\n";
    book.restock(10);
    std::cout << "بعد از restock(10): " << book.stock() << "\n";
    std::cout << "ارزش انبار: " << book.totalValue() << "\n";

    // این خط‌ها کامپایل نمی‌شوند:
    //   book.stock_ = -5;
    //   book.title() = "تغییر غیرمجاز";   // const reference برمی‌گردد

    return 0;
}
