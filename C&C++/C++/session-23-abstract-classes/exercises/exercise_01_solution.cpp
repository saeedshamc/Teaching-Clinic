// exercise_01_solution.cpp — راه‌حل تمرین ۱: IPayment
// کامپایل: g++ -std=c++17 -Wall -Wextra -o payment exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>
#include <vector>

class IPayment {
public:
    virtual ~IPayment() = default;
    virtual bool pay(double amount) = 0;
    virtual std::string name() const = 0;
    virtual double paidTotal() const = 0;
};

class CardPayment : public IPayment {
public:
    bool pay(double amount) override {
        if (amount <= 0.0 || amount > kLimit) {
            return false;
        }
        total_ += amount;
        return true;
    }

    std::string name() const override {
        return "کارت";
    }

    double paidTotal() const override {
        return total_;
    }

private:
    static constexpr double kLimit = 500.0;
    double total_ = 0.0;
};

class WalletPayment : public IPayment {
public:
    explicit WalletPayment(double balance)
        : balance_(balance < 0.0 ? 0.0 : balance) {
    }

    bool pay(double amount) override {
        if (amount <= 0.0 || amount > balance_) {
            return false;
        }
        balance_ -= amount;
        total_ += amount;
        return true;
    }

    std::string name() const override {
        return "کیف پول";
    }

    double paidTotal() const override {
        return total_;
    }

    double balance() const {
        return balance_;
    }

private:
    double balance_ = 0.0;
    double total_ = 0.0;
};

int main() {
    CardPayment card;
    WalletPayment wallet(100.0);

    std::vector<IPayment*> methods{&card, &wallet};

    for (IPayment* method : methods) {
        std::cout << method->name() << ": pay(80) → "
                  << (method->pay(80.0) ? "موفق" : "ناموفق") << "\n";
        std::cout << method->name() << ": pay(600) → "
                  << (method->pay(600.0) ? "موفق" : "ناموفق (سقف یا موجودی)") << "\n";
    }

    std::cout << "\n--- گزارش ---\n";
    double grandTotal = 0.0;
    for (const IPayment* method : methods) {
        std::cout << method->name() << ": paid = " << method->paidTotal() << "\n";
        grandTotal += method->paidTotal();
    }

    std::cout << "موجودی کیف پول: " << wallet.balance() << "\n";
    std::cout << "مجموع: " << grandTotal << "\n";

    // IPayment قرارداد است، نه شیء:
    //   IPayment method;   // ❌ error: abstract type

    return 0;
}
