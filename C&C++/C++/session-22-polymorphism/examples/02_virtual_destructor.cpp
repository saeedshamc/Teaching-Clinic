// 02_virtual_destructor.cpp — چرا مخرب پایه باید virtual باشد
// کامپایل: g++ -std=c++17 -Wall -Wextra -o destroy examples/02_virtual_destructor.cpp

#include <iostream>
#include <string>
#include <vector>

class Connection {
public:
    explicit Connection(const std::string& endpoint)
        : endpoint_(endpoint) {
        std::cout << "  باز کردن اتصال به " << endpoint_ << "\n";
    }

    // virtual → هنگام delete از طریق Connection*، مخرب مشتق هم اجرا می‌شود
    virtual ~Connection() {
        std::cout << "  بستن اتصال پایه\n";
    }

    virtual std::string describe() const {
        return "اتصال ساده";
    }

protected:
    std::string endpoint_;
};

class SecureConnection : public Connection {
public:
    explicit SecureConnection(const std::string& endpoint)
        : Connection(endpoint), token_("secret-token") {
        std::cout << "  تخصیص توکن امنیتی\n";
    }

    ~SecureConnection() override {
        std::cout << "  آزاد کردن توکن امنیتی\n";
    }

    std::string describe() const override {
        return "اتصال امن (TLS)";
    }

private:
    std::string token_;
};

int main() {
    std::cout << "--- شیء روی پشته ---\n";
    {
        const SecureConnection local("db.local");
        std::cout << local.describe() << "\n";
    }

    std::cout << "\n--- از طریق pointer پایه ---\n";
    std::vector<Connection*> connections;
    connections.push_back(new SecureConnection("db.remote:5432"));
    connections.push_back(new Connection("cache.local"));

    for (const Connection* connection : connections) {
        std::cout << connection->describe() << "\n";
    }

    std::cout << "\n--- حذف از طریق Connection* ---\n";
    for (Connection* connection : connections) {
        delete connection;      // مخرب virtual → هر دو سطح اجرا می‌شوند
    }

    // اگر مخرب پایه virtual نبود:
    //   - فقط ~Connection اجرا می‌شد
    //   - «آزاد کردن توکن» هرگز چاپ نمی‌شد
    //   - در کد واقعی یعنی نشتی منبع (فایل، قفل، حافظه)
    // و کامپایلر با -Wall هشدار می‌داد:
    //   warning: deleting object of polymorphic class type
    //            which has non-virtual destructor [-Wdelete-non-virtual-dtor]

    return 0;
}
