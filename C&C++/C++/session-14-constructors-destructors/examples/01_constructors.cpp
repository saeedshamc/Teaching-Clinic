// 01_constructors.cpp — سازنده: شیء از لحظهٔ تولد معتبر است
// کامپایل: g++ -std=c++17 -Wall -Wextra -o bank examples/01_constructors.cpp

#include <iostream>
#include <string>

class BankAccount {
public:
    // ۱) سازندهٔ پیش‌فرض — بدون پارامتر
    BankAccount() {
        std::cout << "[ساخت] حساب پیش‌فرض برای " << owner_
                  << " با موجودی " << balance_ << "\n";
    }

    // ۲) سازندهٔ پارامتری — مقدار نامعتبر همان‌جا اصلاح می‌شود
    BankAccount(const std::string& ownerName, double initialAmount)
        : owner_(ownerName), balance_(initialAmount < 0.0 ? 0.0 : initialAmount) {
        std::cout << "[ساخت] " << owner_ << " با موجودی " << balance_ << "\n";
    }

    // ۳) سازندهٔ واگذارکننده (delegating) — کار را به سازندهٔ دیگر می‌سپارد
    BankAccount(const std::string& ownerName)
        : BankAccount(ownerName, 0.0) {
        std::cout << "[ساخت] واگذارشده برای " << owner_ << "\n";
    }

    bool deposit(double amount) {
        if (amount <= 0.0) {
            return false;
        }
        balance_ += amount;
        return true;
    }

    bool withdraw(double amount) {
        if (amount <= 0.0 || amount > balance_) {
            return false;
        }
        balance_ -= amount;
        return true;
    }

    double balance() const {
        return balance_;
    }

    const std::string& owner() const {
        return owner_;
    }

private:
    // مقداردهندهٔ عضو (member initializer): پیش‌فرض هر سازنده
    std::string owner_ = "بدون صاحب";
    double balance_ = 0.0;
};

int main() {
    // هر سه شیء از اولین لحظه وضعیت معتبر دارند
    BankAccount anonymous;
    BankAccount saeed("Saeed", 100.0);
    BankAccount empty("Ali");

    std::cout << "\n--- عملیات ---\n";
    std::cout << "واریز 50 به Saeed: "
              << (saeed.deposit(50.0) ? "موفق" : "ناموفق") << "\n";
    std::cout << "موجودی Saeed: " << saeed.balance() << "\n";

    std::cout << "برداشت 20 از Ali: "
              << (empty.withdraw(20.0) ? "موفق" : "ناموفق") << "\n";
    std::cout << "موجودی Ali: " << empty.balance() << "\n";

    std::cout << "موجودی حساب پیش‌فرض: " << anonymous.balance()
              << " (صاحب: " << anonymous.owner() << ")\n";

    std::cout << "\nنتیجه: شیء «بازنشده» دیگر وجود ندارد؛ هر حساب از تولد معتبر است.\n";

    return 0;
}
