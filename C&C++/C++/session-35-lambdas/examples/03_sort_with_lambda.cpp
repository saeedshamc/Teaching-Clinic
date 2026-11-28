// 03_sort_with_lambda.cpp — لامبدا در sort و ذخیره در std::function
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o sort_lambda examples/03_sort_with_lambda.cpp

#include <algorithm>
#include <functional>
#include <iostream>
#include <string>
#include <vector>

struct Product {
    std::string name;
    double price;
    int stock;
};

void print(const std::string& label, const std::vector<Product>& products) {
    std::cout << label << ":\n";
    for (const auto& product : products) {
        std::cout << "  " << product.name << " — قیمت " << product.price
                  << "، موجودی " << product.stock << "\n";
    }
}

int main() {
    std::vector<Product> products{
        {"Keyboard", 120.0, 5},
        {"Mouse", 45.5, 20},
        {"Monitor", 300.0, 2},
    };

    std::sort(products.begin(), products.end(), [](const Product& left, const Product& right) {
        return left.price < right.price;
    });
    print("--- مرتب بر اساس قیمت (صعودی)", products);

    std::sort(products.begin(), products.end(), [](const Product& left, const Product& right) {
        return left.stock > right.stock;
    });
    print("--- مرتب بر اساس موجودی (نزولی)", products);

    // نگه داشتن لامبدا در std::function
    std::function<bool(const Product&, const Product&)> byName =
        [](const Product& left, const Product& right) {
            return left.name < right.name;
        };
    std::sort(products.begin(), products.end(), byName);
    print("--- مرتب بر اساس نام", products);

    // capture by reference در محاسبهٔ مجموع
    double totalValue = 0.0;
    std::for_each(products.begin(), products.end(), [&totalValue](const Product& product) {
        totalValue += product.price * product.stock;
    });
    std::cout << "--- ارزش کل انبار ---\n  " << totalValue << "\n";

    return 0;
}
