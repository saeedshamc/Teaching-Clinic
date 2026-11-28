// 02_initializer_list.cpp — لیست مقداردهی اولیه در برابر انتساب در بدنه
// کامپایل: g++ -std=c++17 -Wall -Wextra -o init examples/02_initializer_list.cpp

#include <iostream>
#include <string>

// ---- الف) مقداردهی در بدنه: هر عضو اول ساخته می‌شود، بعد مقدار می‌گیرد ----
class ServerByAssignment {
public:
    ServerByAssignment(const std::string& name, int port) {
        name_ = name;                                            // انتساب، نه ساخت
        label_ = name + ":" + std::to_string(port);              // تخصیص حافظهٔ اضافه
        port_ = port;
    }

    const std::string& label() const {
        return label_;
    }

private:
    std::string name_;
    std::string label_;
    int port_ = 0;
};

// ---- ب) لیست مقداردهی اولیه: هر عضو مستقیماً با مقدارش ساخته می‌شود ----
class ServerByInitList {
public:
    ServerByInitList(const std::string& name, int port)
        : name_(name),                       // ساخت مستقیم
          label_(name + ":" + std::to_string(port)),
          port_(port) {
    }

    const std::string& label() const {
        return label_;
    }

private:
    std::string name_;
    std::string label_;
    int port_ = 0;
};

// ---- پ) اعضای const و reference فقط با لیست مقداردهی قابل ساختن‌اند ----
class Measurement {
public:
    Measurement(const std::string& sensor, double value)
        : sensor_(sensor),      // reference به پارامتر const& معتبر است
          unit_('C'),
          value_(value) {
    }

    void print() const {
        std::cout << sensor_ << ": " << value_ << "°" << unit_ << "\n";
    }

private:
    const std::string& sensor_;   // reference — در بدنه قابل انتساب نیست
    const char unit_;             // const — در بدنه قابل انتساب نیست
    double value_;
};

int main() {
    const ServerByAssignment a("api.example.com", 8080);
    const ServerByInitList b("api.example.com", 8080);

    std::cout << "با انتساب در بدنه : " << a.label() << "\n";
    std::cout << "با لیست مقداردهی  : " << b.label() << "\n";

    const std::string sensorName = "حسگر ۱";
    const Measurement m(sensorName, 21.5);
    m.print();

    // مثال‌هایی که کامپایل نمی‌شوند (دلیلش در README):
    //   ServerByInitList bad("x", 1);
    //   // const char unit_;  →  unit_ = 'C';  در بدنه خطا می‌دهد

    std::cout << "\nنتیجه: لیست مقداردهی، ساخت اضافه و انتساب دوباره را حذف می‌کند.\n";

    return 0;
}
