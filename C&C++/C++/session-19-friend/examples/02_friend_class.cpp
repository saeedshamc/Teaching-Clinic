// 02_friend_class.cpp — کلاس friend و هزینه‌های آن
// کامپایل: g++ -std=c++17 -Wall -Wextra -o friendclass examples/02_friend_class.cpp

#include <iostream>
#include <string>
#include <vector>

class Engine {
public:
    explicit Engine(const std::string& model)
        : model_(model) {
    }

    void run(int seconds) {
        ++runCount_;
        totalSeconds_ += seconds;
        std::cout << "  موتور " << model_ << " به مدت " << seconds << " ثانیه کار کرد\n";
    }

    const std::string& model() const {
        return model_;
    }

private:
    std::string model_;
    int runCount_ = 0;
    int totalSeconds_ = 0;

    // کلاس Doctor اجازهٔ خواندن گزارش کامل را دارد
    friend class Doctor;
};

class Doctor {
public:
    // دسترسی مستقیم به اعضای خصوصی Engine
    void diagnose(const Engine& engine) const {
        std::cout << "گزارش " << engine.model_ << ": "
                  << engine.runCount_ << " بار روشن شده، "
                  << engine.totalSeconds_ << " ثانیه کار کرده\n";
        if (engine.totalSeconds_ > 100) {
            std::cout << "  → نیاز به بازبینی\n";
        }
    }
};

// نسخهٔ بدون friend: همان اطلاعات از رابط عمومی
class Mechanic {
public:
    void inspect(const Engine& engine) const {
        std::cout << "بازرسی " << engine.model()
                  << " (فقط از رابط عمومی — بدون دسترسی به شمارنده‌ها)\n";
    }
};

int main() {
    Engine engine("V8");
    engine.run(60);
    engine.run(50);

    std::cout << "\n";
    Doctor doctor;
    doctor.diagnose(engine);

    std::cout << "\n";
    Mechanic mechanic;
    mechanic.inspect(engine);

    // نکته: friend بودن یک‌طرفه است. Engine نمی‌تواند اعضای خصوصی Doctor را ببیند.
    // نکته: friend ارث‌بری نمی‌شود و transitive هم نیست.
    //   (اگر A دوست B باشد و B دوست C، آن‌گاه C دوست A نیست.)

    return 0;
}
