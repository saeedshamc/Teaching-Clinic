// exercise_01.cpp — تمرین ۱: اعتبارسنجی با استثنای سفارشی
// TODO: دو چیز بسازید:
//
//   ۱) استثنای سفارشی:
//      class InvalidAge : public std::runtime_error {
//      public:
//          InvalidAge(const std::string& message, int value);
//          int value() const;      // مقداری که رد شد
//      };
//
//   ۲) کلاس:
//      class Person {
//      public:
//          Person(const std::string& name, int age);   // اگر سن نامعتبر بود throw
//          void setAge(int age);                        // 0..150
//          int age() const;
//          const std::string& name() const;
//      };
//
// در main:
//   ۱) یک Person معتبر بسازید
//   ۲) setAge(200) را در try/catch صدا بزنید و پیام + مقدار رد‌شده را چاپ کنید
//   ۳) ساخت Person با سن منفی را در try/catch امتحان کنید
//   ۴) یک بار InvalidAge را با catch (const std::exception&) بگیرید
//      تا نشان دهید استثنای سفارشی با پایه هم گرفته می‌شود
//
// نمونهٔ انتظاری:
//   Saeed ، 30
//   setAge(200) → InvalidAge: سن خارج از محدوده (مقدار: 200)
//   سن هنوز: 30
//   ساخت با سن -5 → InvalidAge: سن خارج از محدوده (مقدار: -5)
//   با catch(std::exception&) هم گرفته شد: سن خارج از محدوده
//
// راهنما: سازندهٔ InvalidAge باید مقدار را در یک عضو ذخیره کند و
//          پیام را به std::runtime_error بدهد.
//          در سازندهٔ Person، setAge را صدا بزنید تا منطق یک‌جا بماند.

#include <iostream>
#include <stdexcept>
#include <string>

// TODO: تعریف InvalidAge و Person

int main() {
    // TODO: آزمایش سناریوها

    return 0;
}
