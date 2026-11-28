// exercise_01_solution.cpp — راه‌حل تمرین ۱: تبدیل به اشاره‌گر هوشمند
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o smart exercises/exercise_01_solution.cpp

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

    const std::string& name() const {
        return name_;
    }

private:
    std::string name_;
};

void process(std::unique_ptr<Connection> connection) {
    connection->connect();
}   // مالکیت همین‌جا آزاد می‌شود

std::unique_ptr<Connection> createConnection(const std::string& name) {
    return std::make_unique<Connection>(name);
}

int main() {
    std::cout << "--- unique_ptr در دامنه ---\n";
    {
        const auto connection = std::make_unique<Connection>("db");
        connection->connect();
    }

    std::cout << "--- انتقال مالکیت به تابع ---\n";
    auto api = std::make_unique<Connection>("api");
    process(std::move(api));
    std::cout << "  api خالی است؟ " << (api ? "نه" : "بله") << "\n";

    std::cout << "--- factory ---\n";
    const auto cache = createConnection("cache");
    cache->connect();

    std::cout << "--- shared_ptr ---\n";
    {
        const auto shared = std::make_shared<Connection>("shared");
        {
            const auto secondOwner = shared;
            std::cout << "  use_count: " << shared.use_count() << "\n";
        }
        std::cout << "  بعد از آزاد شدن مالک دوم: " << shared.use_count() << "\n";
    }

    std::cout << "  پایان برنامه\n";
    return 0;
}
