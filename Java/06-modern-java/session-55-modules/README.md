# جلسه ۵۵ — Java Modules (JPMS)

## اهداف یادگیری
- `module-info.java` را بشناسید
- `module`, `requires`, `exports` را بفهمید
- module path در برابر class path را تشخیص دهید
- `javac --module-path` و `java --module-path` را به‌کار ببرید

## پیش‌نیازها
- همهٔ جلسات قبلی

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JPMS — Java Platform Module System

```java
// module-info.java — در root هر module
module com.example.myapp {
    requires java.base;              // module سیستمی
    requires com.example.utils;      // module دیگر
    exports com.example.myapp.api;   // پکیج‌هایی که export می‌شوند
}
```

### module-info — اجزا

```java
module com.example.myapp {
    // requires — وابستگی به moduleهای دیگر
    requires java.base;
    requires java.sql;
    requires com.example.utils;

    // exports — پکیج‌هایی که برای moduleهای دیگر قابل دسترسی‌اند
    exports com.example.myapp.api;
    exports com.example.myapp.model;

    // opens — برای reflection (مثل Jackson, Spring)
    opens com.example.myapp.internal to com.example.framework;

    // uses — service consumer
    uses com.example.spi.Service;

    // provides — service provider
    provides com.example.spi.Service with com.example.impl.ServiceImpl;
}
```

### module path در برابر class path

```bash
# class path — قدیمی
javac -cp lib/* -d bin src/*.java
java -cp bin:lib/* com.example.Main

# module path — مدرن
javac --module-path lib -d out $(find src -name "*.java")
java --module-path out:lib -m com.example.myapp/com.example.myapp.Main
```

### ساختار module

```
myapp/
├── src/
│   ├── module-info.java
│   └── com/
│       └── example/
│           └── myapp/
│               ├── Main.java
│               └── api/
│                   └── Service.java
└── out/
    └── com.example.myapp/
        ├── module-info.class
        └── com/example/myapp/...
```

## کد مهم

`src/module-info.java` — نمونه module-info.
`src/SimpleModule.java` — کد ساده برای تست.

## خطاهای رایج
- `module-info.java` را در package اشتباه گذاشتن → باید در root باشد.
- `requires` برای module که در module path نیست → `module not found`.
- `exports` را فراموش کردن → package برای moduleهای دیگر قابل دسترسی نیست.
- reflection روی non-opened package → `IllegalAccessError`.

## جمع‌بندی
- JPMS برای modularization — Java 9+.
- `module-info.java` با `module`, `requires`, `exports`.
- `--module-path` برای compile و run.

**جلسهٔ بعد:** Legacy Overview.
