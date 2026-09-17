# تمرین‌های جلسه ۱۳ — کلاس و شیء

## کلاسی (۳۰ دقیقه)

### ۱. کلاس Student
کلاس `Student` با فیلدهای `Name`, `StudentId`, `Grade` (double) بسازید.
- متد `IsPassed()` — نمره ≥ ۱۰
- متد `GetLetterGrade()` — A (≥۱۷), B (≥۱۴), C (≥۱۰), F (<۱۰)

### ۲. کلاس BankAccount
- فیلدها: `AccountNumber`, `Owner`, `Balance`
- `Deposit(amount)` — فقط مبالغ مثبت
- `Withdraw(amount)` — اگر موجودی کافی باشد، `true` برگرداند
- `Transfer(other, amount)` — انتقال به حساب دیگر

### ۳. آرایه دانشجو
آرایه‌ای از ۵ `Student` بسازید و میانگین نمرات را چاپ کنید.

---

## خانه (۴۵ دقیقه)

### ۴. سیستم کتابخانه
کلاس `Book` با `Title`, `Author`, `IsAvailable`.
متدهای `Borrow()`, `ReturnBook()`, `GetInfo()`.
۳ کتاب بسازید و سناریوی امانت/برگشت را شبیه‌سازی کنید.

### ۵. کلاس Rectangle پیشرفته
- `GetArea()`, `GetPerimeter()`, `IsSquare()`
- `CompareArea(other)` — ۱ اگر بزرگتر، ۰ اگر مساوی، -۱ اگر کوچکتر
- آرایه‌ای از مستطیل — بزرگترین مساحت را پیدا کنید

---

پاسخ نمونه: [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
