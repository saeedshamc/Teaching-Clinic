// 03_weak_ptr_cycle.cpp — weak_ptr: شکستن چرخهٔ مالکیت
// کامپایل: g++ -std=c++17 -Wall -Wextra -Wpedantic -o weak_ptr_example examples/03_weak_ptr_cycle.cpp

#include <iostream>
#include <memory>
#include <string>

struct Person {
    std::string name;
    std::shared_ptr<Person> friendShared;   // مالکیت قوی
    std::weak_ptr<Person> friendWeak;       // فقط مشاهده

    explicit Person(const std::string& personName)
        : name(personName) {
        std::cout << "  + ساخت: " << name << "\n";
    }

    ~Person() {
        std::cout << "  - تخریب: " << name << "\n";
    }
};

int main() {
    std::cout << "--- چرخه با shared_ptr (نشت) ---\n";
    {
        const auto first = std::make_shared<Person>("الف");
        const auto second = std::make_shared<Person>("ب");
        first->friendShared = second;
        second->friendShared = first;
        std::cout << "  use_count الف: " << first.use_count()
                  << "، ب: " << second.use_count() << "\n";
    }
    std::cout << "  بعد از خروج از دامنه: هیچ «تخریب»ی چاپ نشد → حافظه نشت کرد\n";

    std::cout << "--- با weak_ptr (بدون نشت) ---\n";
    {
        const auto first = std::make_shared<Person>("ج");
        const auto second = std::make_shared<Person>("د");
        first->friendShared = second;
        second->friendWeak = first;
        std::cout << "  use_count ج: " << first.use_count()
                  << "، د: " << second.use_count() << "\n";

        if (const auto locked = second->friendWeak.lock()) {
            std::cout << "  weak با lock() قابل استفاده شد: " << locked->name << "\n";
        }
        std::cout << "  weak expired است؟ "
                  << (second->friendWeak.expired() ? "بله" : "نه") << "\n";
    }
    std::cout << "  بعد از دامنه: هر دو تخریب شدند\n";

    std::cout << "--- weak به شیء آزادشده ---\n";
    {
        std::weak_ptr<Person> observer;
        {
            const auto temporary = std::make_shared<Person>("موقت");
            observer = temporary;
            std::cout << "  داخل دامنه، expired: "
                      << (observer.expired() ? "بله" : "نه") << "\n";
        }
        std::cout << "  بعد از دامنه، expired: "
                  << (observer.expired() ? "بله" : "نه") << "\n";
        std::cout << "  lock() خالی است؟ "
                  << (observer.lock() ? "نه" : "بله") << "\n";
    }

    return 0;
}
