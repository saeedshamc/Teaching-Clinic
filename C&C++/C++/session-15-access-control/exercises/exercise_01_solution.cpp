// exercise_01_solution.cpp — راه‌حل تمرین ۱: کلاس Product
// کامپایل: g++ -std=c++17 -Wall -Wextra -o product exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>

class Product {
public:
    Product(const std::string& name, double price, int stock)
        : name_(name),
          price_(price < 0.0 ? 0.0 : price),
          stock_(stock < 0 ? 0 : stock) {
    }

    const std::string& name() const {
        return name_;
    }

    double price() const {
        return price_;
    }

    int stock() const {
        return stock_;
    }

    double inventoryValue() const {
        return price_ * static_cast<double>(stock_);
    }

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
    std::string name_;
    double price_ = 0.0;
    int stock_ = 0;
};

int main() {
    Product product("Keyboard", 50.0, 10);
    std::cout << "Product: " << product.name()
              << " ، price = " << product.price()
              << " ، stock = " << product.stock() << "\n";

    std::cout << "\nsetPrice(-5) → " << (product.setPrice(-5.0) ? "پذیرفته" : "رد شد") << "\n";
    std::cout << "قیمت هنوز: " << product.price() << "\n";

    std::cout << "setStock(-3) → " << (product.setStock(-3) ? "پذیرفته" : "رد شد") << "\n";
    std::cout << "موجودی هنوز: " << product.stock() << "\n";

    std::cout << "\nsell(4) → " << (product.sell(4) ? "موفق" : "ناموفق")
              << " ، stock = " << product.stock() << "\n";
    std::cout << "sell(100) → " << (product.sell(100) ? "موفق" : "ناموفق") << "\n";
    std::cout << "sell(0) → " << (product.sell(0) ? "موفق" : "ناموفق") << "\n";

    product.restock(10);
    std::cout << "restock(10) → stock = " << product.stock() << "\n";
    std::cout << "inventoryValue = " << product.inventoryValue() << "\n";

    return 0;
}
