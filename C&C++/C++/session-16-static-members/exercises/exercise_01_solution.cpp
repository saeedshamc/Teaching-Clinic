// exercise_01_solution.cpp — راه‌حل تمرین ۱: تولید شناسهٔ یکتا
// کامپایل: g++ -std=c++17 -Wall -Wextra -o ticket exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>

class Ticket {
public:
    explicit Ticket(const std::string& owner)
        : id_(nextId_++),
          owner_(owner.empty() ? "مهمان" : owner) {
        ++issued_;
    }

    int id() const {
        return id_;
    }

    const std::string& owner() const {
        return owner_;
    }

    static int issued() {
        return issued_;
    }

    static int lastId() {
        return nextId_ - 1;
    }

private:
    int id_ = 0;
    std::string owner_;

    // C++17: تعریف و مقداردهی در همان‌جا، بدون نیاز به فایل .cpp
    inline static int nextId_ = 1000;
    inline static int issued_ = 0;
};

int main() {
    std::cout << "قبل از ساخت: issued = " << Ticket::issued()
              << " ، lastId = " << Ticket::lastId() << "\n\n";

    const Ticket first("Saeed");
    const Ticket second("Ali");
    const Ticket third("");

    std::cout << "Ticket #" << first.id() << " برای " << first.owner() << "\n";
    std::cout << "Ticket #" << second.id() << " برای " << second.owner() << "\n";
    std::cout << "Ticket #" << third.id() << " برای " << third.owner() << "\n\n";

    std::cout << "issued = " << Ticket::issued()
              << " ، lastId = " << Ticket::lastId() << "\n";

    const bool unique = first.id() != second.id() &&
                        second.id() != third.id() &&
                        first.id() != third.id();
    std::cout << "شناسه‌ها یکتا هستند؟ " << (unique ? "بله" : "خیر") << "\n";

    return 0;
}
