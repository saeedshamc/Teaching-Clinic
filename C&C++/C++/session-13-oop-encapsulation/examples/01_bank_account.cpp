// 01_bank_account.cpp — کپسوله‌سازی: تنها راه تغییر، از مسیر متدها
// کامپایل: g++ -std=c++17 -Wall -Wextra -o bank examples/01_bank_account.cpp

#include <iostream>
#include <string>

class BankAccount {
public:
    // آماده‌سازی حساب (در جلسهٔ ۱۴ این کار را به سازنده می‌سپاریم)
    void open(const std::string& ownerName, double initialAmount) {
        if (initialAmount < 0.0) {
            return;
        }
        owner_ = ownerName;
        balance_ = initialAmount;
        isOpen_ = true;
    }

    bool deposit(double amount) {
        if (!isOpen_ || amount <= 0.0) {
            return false;
        }
        balance_ += amount;
        return true;
    }

    bool withdraw(double amount) {
        if (!isOpen_ || amount <= 0.0) {
            return false;
        }
        if (amount > balance_) {
            return false;              // قاعدهٔ اصلی: موجودی منفی نمی‌شود
        }
        balance_ -= amount;
        return true;
    }

    // فقط خواندن: const
    double balance() const {
        return balance_;
    }

    const std::string& owner() const {
        return owner_;
    }

    bool isOpen() const {
        return isOpen_;
    }

private:
    // وضعیت داخلی؛ از بیرون قابل دسترسی نیست
    std::string owner_;
    double balance_ = 0.0;
    bool isOpen_ = false;
};

int main() {
    BankAccount account;
    account.open("Saeed", 100.0);

    std::cout << "صاحب حساب: " << account.owner()
              << " ، موجودی: " << account.balance() << "\n";

    std::cout << "\nواریز 50: " << (account.deposit(50.0) ? "موفق" : "ناموفق") << "\n";
    std::cout << "موجودی: " << account.balance() << "\n";

    std::cout << "\nبرداشت 30: " << (account.withdraw(30.0) ? "موفق" : "ناموفق") << "\n";
    std::cout << "موجودی: " << account.balance() << "\n";

    std::cout << "\nبرداشت 500: " << (account.withdraw(500.0) ? "موفق" : "ناموفق") << "\n";
    std::cout << "موجودی: " << account.balance() << " (تغییر نکرد)\n";

    std::cout << "\nواریز -10: " << (account.deposit(-10.0) ? "موفق" : "ناموفق") << "\n";
    std::cout << "موجودی: " << account.balance() << "\n";

    // این خط‌ها کامپایل نمی‌شوند — دقیقاً همان چیزی که می‌خواهیم:
    //   account.balance_ = 1000000.0;
    //   account.balance() = 0.0;      // مقدار بازگشتی هم قابل انتساب نیست

    std::cout << "\nنتیجه: هیچ راهی برای خراب کردن موجودی از بیرون وجود ندارد.\n";

    return 0;
}
