// exercise_01.cpp — تمرین ۱: تبدیل new/delete خام به اشاره‌گر هوشمند
//
// TODO: کدهای زیر را که با new/delete خام نوشته شده‌اند بازنویسی کنید:
//
//   // نسخهٔ قدیمی
//   Connection* connection = new Connection("db");
//   connection->connect();
//   delete connection;
//
//   // نسخهٔ قدیمی با انتقال مالکیت به تابع
//   Connection* api = new Connection("api");
//   process(api);
//
//   // نسخهٔ قدیمی با مالکیت مشترک
//   Connection* shared = new Connection("shared");
//   Connection* second = shared;
//   delete shared;
//   delete second;   // ❌ double free
//
// خواسته‌ها:
//   ۱) بخش اول را با std::make_unique بنویسید (بدون delete)
//   ۲) تابع process را طوری بنویسید که std::unique_ptr<Connection> بگیرد
//   ۳) factory ای بسازید که std::unique_ptr<Connection> برگرداند
//   ۴) بخش مشترک را با std::make_shared بنویسید و use_count را چاپ کنید
//
// نمونهٔ انتظاری:
//   --- unique_ptr در دامنه ---
//     + ساخت اتصال: db
//     اتصال db برقرار شد
//     - بستن اتصال: db
//   --- انتقال مالکیت به تابع ---
//     + ساخت اتصال: api
//     اتصال api برقرار شد
//     - بستن اتصال: api
//     api خالی است؟ بله
//   --- factory ---
//     + ساخت اتصال: cache
//     اتصال cache برقرار شد
//   --- shared_ptr ---
//     + ساخت اتصال: shared
//     use_count: 2
//     بعد از آزاد شدن مالک دوم: 1
//     - بستن اتصال: shared
//     پایان برنامه
//     - بستن اتصال: cache
//
// راهنما:
//   void process(std::unique_ptr<Connection> connection) { connection->connect(); }
//   process(std::move(api));
//   std::unique_ptr<Connection> createConnection(const std::string& name) {
//       return std::make_unique<Connection>(name);
//   }

#include <iostream>
#include <memory>
#include <string>
#include <utility>

class Connection {
public:
    explicit Connection(const std::string& name)
        : name_(name) {
        std::cout << "  + ساخت اتصال: " << name_ << "\n";
    }

    ~Connection() {
        std::cout << "  - بستن اتصال: " << name_ << "\n";
    }

    void connect() const {
        std::cout << "  اتصال " << name_ << " برقرار شد\n";
    }

private:
    std::string name_;
};

// TODO: تابع process و createConnection

int main() {
    // TODO: چهار بخش نمونهٔ انتظاری را بازنویسی کنید

    return 0;
}
