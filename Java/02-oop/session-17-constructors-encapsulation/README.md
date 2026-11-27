# جلسه ۱۷ — سازنده‌ها و Encapsulation

## اهداف یادگیری
- overload سازنده را بفهمید
- `this()` و `super()` chaining را بشناسید
- encapsulation کامل با `private` + getter/setter را پیاده کنید
- immutable object را بسازید

## پیش‌نیازها
- جلسهٔ ۱۶ (کلاس و آبجکت)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Overload سازنده

```java
class Person {
    private String name;
    private int age;

    // سازندهٔ کامل
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // سازندهٔ فقط نام — به سازندهٔ کامل delegate می‌کند
    Person(String name) {
        this(name, 0);   // this() = صدا زدن سازندهٔ دیگر در همین کلاس
    }

    // سازندهٔ پیش‌فرض
    Person() {
        this("Unknown", 0);
    }
}
```

### this() chaining

- `this(args)` → سازندهٔ دیگر **در همین کلاس** را صدا می‌زند.
- باید **اولین** statement در سازنده باشد.
- جلوی تکرار کد را می‌گیرد.

### Encapsulation کامل

```java
class BankAccount {
    private double balance;       // private — دسترسی مستقیم ممنوع
    private String owner;

    public BankAccount(String owner, double initialDeposit) {
        this.owner = owner;
        this.balance = initialDeposit;
    }

    public double getBalance() { return balance; }
    public String getOwner() { return owner; }

    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) return false;
        if (amount > balance) return false;
        balance -= amount;
        return true;
    }
}
```

### Immutable Object

```java
final class Point {
    private final int x;    // final + private = immutable
    private final int y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    // setter ندارد — مقدار بعد از ساخت تغییر نمی‌کند
}
```

## کد مهم

`src/EncapsulationDemo.java` — overload سازنده، this() chaining، encapsulation، immutable.

## خطاهای رایج
- `this()` باید اولین statement باشد → compile error.
- `private` بدون getter → بیرون کلاس دسترسی ندارد.
- mutable field در immutable class → دیگر immutable نیست.
- validation را در setter نذاشتن → دادهٔ نامعتبر وارد می‌شود.

## جمع‌بندی
- Overload سازنده برای امضاهای مختلف.
- `this()` برای chaining، `private`+getter/setter برای encapsulation.
- `final` fields برای immutable.

**جلسهٔ بعد:** ارث‌بری (Inheritance).
