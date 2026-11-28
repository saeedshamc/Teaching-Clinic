// 03_struct_vs_class.cpp — تنها تفاوت واقعی: دسترسی پیش‌فرض
// کامپایل: g++ -std=c++17 -Wall -Wextra -o struct_vs_class examples/03_struct_vs_class.cpp

#include <iostream>

// در struct دسترسی پیش‌فرض public است
struct PublicByDefault {
    int value = 0;                 // public

    void show() const {
        std::cout << "PublicByDefault::value = " << value << "\n";
    }
};

// در class دسترسی پیش‌فرض private است
class PrivateByDefault {
    int value = 0;                 // private

public:
    void setValue(int newValue) {
        value = newValue;
    }

    int getValue() const {
        return value;
    }
};

// برعکسش هم قانونی است: struct با بخش خصوصی
struct WithPrivate {
public:
    int id = 1;

private:
    int secret = 42;

public:
    int secretValue() const {
        return secret;
    }
};

// class با همه‌چیز عمومی
class AllPublic {
public:
    int value = 0;
};

int main() {
    PublicByDefault first;
    first.value = 10;              // مجاز، چون struct پیش‌فرض public است
    first.show();

    PrivateByDefault second;
    // second.value = 10;          → خطای کامپایل: private است
    second.setValue(20);
    std::cout << "PrivateByDefault::getValue() = " << second.getValue() << "\n";

    WithPrivate third;
    third.id = 5;
    std::cout << "WithPrivate: id = " << third.id
              << " ، secretValue() = " << third.secretValue() << "\n";

    AllPublic fourth;
    fourth.value = 7;
    std::cout << "AllPublic: value = " << fourth.value << "\n";

    std::cout << "\nنتیجه: struct و class فقط در دسترسی پیش‌فرض تفاوت دارند؛\n";
    std::cout << "بقیهٔ قواعد (سازنده، وراثت، متدها) یکی است.\n";

    return 0;
}
