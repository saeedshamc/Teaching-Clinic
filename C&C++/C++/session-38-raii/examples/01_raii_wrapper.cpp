// 01_raii_wrapper.cpp — RAII: آزادسازی در مخرب، حتی وقتی استثنا رخ می‌دهد
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o raii_wrapper examples/01_raii_wrapper.cpp

#include <iostream>
#include <stdexcept>
#include <string>

class Session {
public:
    explicit Session(const std::string& user)
        : user_(user) {
        std::cout << "  + باز کردن نشست: " << user_ << "\n";
    }

    // بدون کپی: یک نشست دو مالک ندارد
    Session(const Session&) = delete;
    Session& operator=(const Session&) = delete;

    ~Session() {
        std::cout << "  - بستن نشست: " << user_ << "\n";
    }

    void work(bool shouldFail) const {
        std::cout << "    کار در نشست " << user_ << "\n";
        if (shouldFail) {
            throw std::runtime_error("کار شکست خورد");
        }
        std::cout << "    کار تمام شد\n";
    }

private:
    std::string user_;
};

void runTask(const std::string& user, bool shouldFail) {
    const Session session(user);   // منبع همین‌جا گرفته می‌شود
    session.work(shouldFail);
}   // مخرب در هر مسیری اجرا می‌شود

int main() {
    std::cout << "--- مسیر موفق ---\n";
    runTask("Saeed", false);

    std::cout << "--- مسیر خطا ---\n";
    try {
        runTask("Ali", true);
    } catch (const std::exception& error) {
        std::cout << "  گرفته شد: " << error.what() << "\n";
    }

    std::cout << "--- خروج زودهنگام ---\n";
    runTask("Sara", false);

    std::cout << "در هر سه مسیر، نشست بسته شد.\n";
    return 0;
}
