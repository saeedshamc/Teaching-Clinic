# جلسه ۵۲ — Sealed Classes

## اهداف یادگیری
- `sealed` class/interface را برای محدود کردن inheritance بشناسید
- `permits` clause را بفهمید
- `non-sealed` و `final` subclasses را به‌کار ببرید
- exhaustive pattern matching را با sealed بشناسید

## پیش‌نیازها
- جلسهٔ ۵۱ (Records)

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Sealed — کنترل inheritance

```java
// بدون sealed — هر کسی می‌تواند extend کند
class Shape {}
class Circle extends Shape {}
class Square extends Shape {}
class Triangle extends Shape {}
// ... هر subclass دیگری هم ممکن است

// با sealed — فقط subclasses مشخص‌شده
sealed class Shape permits Circle, Square, Triangle {}
final class Circle extends Shape {}
final class Square extends Shape {}
final class Triangle extends Shape {}
// هیچ subclass دیگری نمی‌تواند Shape را extend کند!
```

### permits — لیست مجاز

```java
sealed interface Shape permits Circle, Rectangle, Triangle {}

final class Circle implements Shape {}
final class Rectangle implements Shape {}
non-sealed class Triangle implements Shape {}   // می‌تواند extend شود
```

### non-sealed — subclass باز

```java
sealed class Vehicle permits Car, Truck, Motorcycle {}

final class Car extends Vehicle {}
final class Truck extends Vehicle {}
non-sealed class Motorcycle extends Vehicle {}   // می‌تواند subclass داشته باشد

class SportBike extends Motorcycle {}   // مجاز چون Motorcycle non-sealed است
```

### Sealed + Record

```java
sealed interface Result permits Success, Failure {}

record Success(String data) implements Result {}
record Failure(String error) implements Result {}

// exhaustive switch — Java 17+
String process(Result result) {
    return switch (result) {
        case Success s -> "OK: " + s.data();
        case Failure f -> "Error: " + f.error();
    };
}
```

## کد مهم

`src/SealedDemo.java` — sealed class، permits، non-sealed، sealed + record.

## خطاهای رایج
- subclass را بدون `permits` در sealed تعریف کردن → compile error.
- `non-sealed` را فراموش کردن برای subclass که باید extend شود → compile error.
- sealed class را `abstract` کردن → sealed خودش abstract است.
- permits را با ترتیب اشتباه نوشتن → باید alphabetical باشد (Java 15+).

## جمع‌بندی
- Sealed برای محدود کردن inheritance — Java 17+.
- `permits` برای لیست مجاز، `non-sealed` برای subclass باز.
- Sealed + exhaustive switch برای type-safe processing.

**جلسهٔ بعد:** Pattern Matching.
