// exercise_01_solution.cpp — راه‌حل تمرین ۱: Vehicle و Car
// کامپایل: g++ -std=c++17 -Wall -Wextra -o vehicle exercises/exercise_01_solution.cpp

#include <iostream>
#include <string>

class Vehicle {
public:
    Vehicle(const std::string& brand, int wheels)
        : brand_(brand), wheels_(wheels > 0 ? wheels : 1) {
    }

    const std::string& brand() const {
        return brand_;
    }

    int wheels() const {
        return wheels_;
    }

    std::string type() const {
        return "وسیلهٔ نقلیه";
    }

    void print() const {
        std::cout << "وسیلهٔ نقلیه: " << brand_
                  << " ، چرخ = " << wheels_
                  << " ، نوع = " << type() << "\n";
    }

protected:
    void setWheels(int count) {
        if (count > 0) {
            wheels_ = count;
        }
    }

private:
    std::string brand_;
    int wheels_;
};

class Car : public Vehicle {
public:
    Car(const std::string& brand, int doors)
        : Vehicle(brand, 4), doors_(doors) {
    }

    int doors() const {
        return doors_;
    }

    std::string type() const {
        return "خودرو";
    }

    void addSpareWheel() {
        setWheels(wheels() + 1);      // دسترسی protected
    }

private:
    int doors_;
};

int main() {
    const Vehicle bike("دوچرخ", 2);
    bike.print();

    Car car("پژو", 4);
    car.print();

    std::cout << "تعداد درها: " << car.doors() << "\n";

    car.addSpareWheel();
    std::cout << "چرخ‌های خودرو بعد از تغییر: " << car.wheels() << "\n";

    // نوع از طریق reference پایه: بدون virtual، نسخهٔ پایه صدا زده می‌شود
    const Vehicle& asVehicle = car;
    std::cout << "از طریق Vehicle& : نوع = " << asVehicle.type() << "\n";

    // این خط‌ها کامپایل نمی‌شوند:
    //   bike.setWheels(3);      // protected است
    //   car.brand_ = "x";       // private است

    return 0;
}
