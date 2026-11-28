// 02_interface.cpp — اینترفیس در C++ و تزریق وابستگی
// کامپایل: g++ -std=c++17 -Wall -Wextra -o interface examples/02_interface.cpp

#include <iostream>
#include <string>
#include <vector>

// اینترفیس: فقط قرارداد، بدون داده و بدون پیاده‌سازی
class ILogger {
public:
    virtual ~ILogger() = default;
    virtual void log(const std::string& level, const std::string& message) = 0;
};

class ConsoleLogger : public ILogger {
public:
    void log(const std::string& level, const std::string& message) override {
        std::cout << "[" << level << "] " << message << "\n";
    }
};

// پیاده‌سازی دوم: فقط جمع می‌کند (مثلاً برای تست)
class CountingLogger : public ILogger {
public:
    void log(const std::string& level, const std::string& message) override {
        entries_.push_back(level + ": " + message);
    }

    int count() const {
        return static_cast<int>(entries_.size());
    }

    const std::vector<std::string>& entries() const {
        return entries_;
    }

private:
    std::vector<std::string> entries_;
};

// مصرف‌کننده، به اینترفیس وابسته است نه به پیاده‌سازی
class UserService {
public:
    explicit UserService(ILogger& logger)
        : logger_(logger) {
    }

    bool registerUser(const std::string& name) {
        if (name.empty()) {
            logger_.log("WARN", "نام خالی است؛ ثبت انجام نشد");
            return false;
        }
        logger_.log("INFO", "کاربر ثبت شد: " + name);
        return true;
    }

private:
    ILogger& logger_;
};

int main() {
    ConsoleLogger console;
    CountingLogger counter;

    std::cout << "--- با ConsoleLogger ---\n";
    UserService production(console);
    production.registerUser("Saeed");
    production.registerUser("");

    std::cout << "\n--- با CountingLogger (بدون خروجی) ---\n";
    UserService test(counter);
    test.registerUser("Ali");
    test.registerUser("Sara");
    test.registerUser("");

    std::cout << "تعداد لاگ‌های ثبت‌شده: " << counter.count() << "\n";
    for (const std::string& entry : counter.entries()) {
        std::cout << "  " << entry << "\n";
    }

    std::cout << "\nنتیجه: UserService نمی‌داند لاگ کجا می‌رود — همان تزریق وابستگی.\n";

    return 0;
}
