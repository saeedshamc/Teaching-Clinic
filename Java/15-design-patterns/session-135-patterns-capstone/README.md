# جلسه ۱۳۵ — Design Patterns Capstone: پروژهٔ ترکیبی

## اهداف یادگیری
- همهٔ design patterns را در یک پروژهٔ واقعی ترکیب کنید
- Creational + Structural + Behavioral patterns را با هم استفاده کنید
- لایه‌بندی و best practices را در نظر بگیرید

## پیش‌نیازها
- جلسات ۱۳۰–۱۳۴

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: E-Commerce System با Design Patterns

یک سیستم فروشگاه با design patterns بسازید:

**Creational:**
- `Singleton` برای `DatabaseConnection`
- `Factory` برای `Product` creation
- `Builder` برای `Order` complex object

**Structural:**
- `Decorator` برای `Product` با discounts
- `Facade` برای `OrderProcessing` complex subsystem
- `Adapter` برای `PaymentGateway` integration

**Behavioral:**
- `Observer` برای `OrderStatus` notifications
- `Strategy` برای `PaymentMethod` selection
- `Command` برای `OrderActions` (cancel, refund)

### Architecture

```
OrderService (Facade)
    ├── ProductFactory (Factory)
    ├── OrderBuilder (Builder)
    ├── PaymentProcessor (Strategy)
    ├── NotificationService (Observer)
    └── OrderCommand (Command)
```

## کد مهم

`src/ECommerceSystem.java` — complete example.
`src/Product.java` — product entity.
`src/Order.java` — order entity with Builder.

## جمع‌بندی فاز ۹ (بخش Design Patterns)
- Creational, Structural, Behavioral patterns ✅
- Factory, Builder, Singleton, Observer, Strategy, Command ✅
- **بعدی: JVM Internals**
