# تمرین‌های جلسه ۱۷ — Interface

## کلاسی (۳۰ دقیقه)

### ۱. IShape
`GetArea()`, `GetPerimeter()` — `Circle`, `Rectangle`, `Triangle`.

### ۲. INotifier
`Send(message)` — `EmailNotifier`, `SmsNotifier`, `PushNotifier`.

### ۳. IComparable
کلاس `Student` با `IComparable<Student>` — مرتب‌سازی بر اساس GPA.

---

## خانه (۴۵ دقیقه)

### ۴. IStorage
`Save(key, data)`, `Load(key)`, `Delete(key)` — `FileStorage`, `MemoryStorage`.

### ۵. Payment Gateway
`IPaymentGateway` با `Process(amount)` — `ZarinpalGateway`, `MockGateway`.
کلاس `CheckoutService` با DI.

---

پاسخ: [exercise_01.cs](./exercise_01.cs) | [exercise_02.cs](./exercise_02.cs)
