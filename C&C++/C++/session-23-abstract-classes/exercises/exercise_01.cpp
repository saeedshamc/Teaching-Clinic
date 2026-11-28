// exercise_01.cpp — تمرین ۱: اینترفیس IPayment با دو پیاده‌سازی
// TODO: سه کلاس بسازید:
//
//   class IPayment {
//   public:
//       virtual ~IPayment() = default;
//       virtual bool pay(double amount) = 0;          // موفق/ناموفق
//       virtual std::string name() const = 0;         // نام روش پرداخت
//       virtual double paidTotal() const = 0;         // مجموع پرداخت‌شده
//   };
//
//   class CardPayment : public IPayment {
//       // محدودیت اعتبار: تا 500 قبول می‌کند، بیشتر → false
//   };
//
//   class WalletPayment : public IPayment {
//       // موجودی اولیه در سازنده؛ اگر کافی نبود → false
//   };
//
// در main:
//   ۱) یک CardPayment و یک WalletPayment(100) بسازید
//   ۲) هر دو را در std::vector<IPayment*> بریزید
//   ۳) برای هر روش، pay(80) و pay(600) را امتحان کنید
//   ۴) name() و paidTotal() هر کدام را چاپ کنید
//   ۵) مجموع کل پرداخت‌ها را حساب کنید
//
// نمونهٔ انتظاری:
//   کارت: pay(80) → موفق
//   کارت: pay(600) → ناموفق (سقف)
//   کیف پول: pay(80) → موفق
//   کیف پول: pay(600) → ناموفق (موجودی)
//   ...
//   مجموع: 160
//
// راهنما: IPayment نباید هیچ داده‌ای داشته باشد؛ فقط قرارداد.
//          هر پیاده‌سازی، دادهٔ خودش را جدا نگه می‌دارد.
//          مخرب virtual را فراموش نکنید، حتی اگر delete نداریم.

#include <iostream>
#include <string>
#include <vector>

// TODO: تعریف IPayment، CardPayment و WalletPayment

int main() {
    // TODO: ساختن روش‌های پرداخت و آزمایش

    return 0;
}
