// 02_method_chaining.cpp — method chaining با بازگشت *this
// کامپایل: g++ -std=c++17 -Wall -Wextra -o builder examples/02_method_chaining.cpp

#include <iostream>
#include <string>
#include <vector>

class QueryBuilder {
public:
    QueryBuilder& table(const std::string& name) {
        table_ = name;
        return *this;                 // همان شیء، برای فراخوانی بعدی
    }

    QueryBuilder& where(const std::string& condition) {
        conditions_.push_back(condition);
        return *this;
    }

    QueryBuilder& orderBy(const std::string& column) {
        orderBy_ = column;
        return *this;
    }

    QueryBuilder& limit(int count) {
        limit_ = count;
        return *this;
    }

    // ساخت رشتهٔ نهایی؛ const چون وضعیت را تغییر نمی‌دهد
    std::string build() const {
        std::string query = "SELECT * FROM " + table_;

        if (!conditions_.empty()) {
            query += " WHERE ";
            for (std::size_t i = 0; i < conditions_.size(); ++i) {
                if (i > 0) {
                    query += " AND ";
                }
                query += conditions_[i];
            }
        }

        if (!orderBy_.empty()) {
            query += " ORDER BY " + orderBy_;
        }

        if (limit_ > 0) {
            query += " LIMIT " + std::to_string(limit_);
        }

        return query;
    }

private:
    std::string table_;
    std::vector<std::string> conditions_;
    std::string orderBy_;
    int limit_ = 0;
};

int main() {
    QueryBuilder builder;

    // هر فراخوانی یک reference برمی‌گرداند → همه در یک عبارت زنجیر می‌شوند
    const std::string query = builder
        .table("students")
        .where("age >= 18")
        .where("passed = 1")
        .orderBy("name")
        .limit(10)
        .build();

    std::cout << "کوئری ساخته‌شده:\n  " << query << "\n";

    // زنجیره با شیء تازه و شرط کمتر
    QueryBuilder other;
    std::cout << "\nکوئری دوم:\n  "
              << other.table("courses").orderBy("title").build() << "\n";

    // بدون زنجیره هم کار می‌کند (فراخوانی جدا)
    QueryBuilder step;
    step.table("books");
    step.where("price < 100");
    std::cout << "\nکوئری سوم:\n  " << step.build() << "\n";

    // نکته: build() را روی شیء const صدا زدیم — چون build خودش const است
    return 0;
}
