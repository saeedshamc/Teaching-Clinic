# جلسه ۵۰ — Optional

## اهداف یادگیری
- `Optional` را برای مدیریت null امن به‌کار ببرید
- `of`, `ofNullable`, `empty` را بشناسید
- `map`, `flatMap`, `filter`, `orElse` را بفهمید
- `orElseThrow` و `ifPresent` را به‌کار ببرید

## پیش‌نیازها
- جلسهٔ ۴۹ (Streams)

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Optional — wrapper برای null safety

```java
import java.util.Optional;

// ساخت Optional
Optional<String> opt = Optional.of("Hello");       // non-null
Optional<String> empty = Optional.empty();          // empty
Optional<String> nullable = Optional.ofNullable(null);  // null-safe

// بررسی
opt.isPresent();    // true/false
opt.isEmpty();      // Java 11+
opt.get();          // dangerous — اگر empty → NoSuchElementException
```

### map و flatMap

```java
Optional<String> name = Optional.of("Ali");

// map — transform
Optional<String> upper = name.map(String::toUpperCase);   // Optional[ALI]

// flatMap — برای Optional-returning functions
Optional<String> result = name.flatMap(n -> Optional.of("Hello " + n));

// زنجیره
Optional<String> processed = name
    .map(String::trim)
    .map(String::toUpperCase)
    .filter(s -> s.length() > 3)
    .map(s -> s + "!");
```

### orElse و orElseThrow

```java
// orElse — مقدار پیش‌فرض
String value = opt.orElse("default");

// orElseGet — lazy evaluation
String value2 = opt.orElseGet(() -> expensiveComputation());

// orElseThrow — اگر empty → exception
String value3 = opt.orElseThrow(() -> new IllegalStateException("No value"));

// ifPresent — فقط اگر present
opt.ifPresent(v -> System.out.println("Value: " + v));
```

### Optional در stream

```java
Optional<String> first = names.stream()
    .filter(name -> name.startsWith("A"))
    .findFirst();

String result = first.orElse("none");
```

## کد مهم

`src/OptionalDemo.java` — Optional creation، map/flatMap، orElse/orElseThrow، ifPresent.

## خطاهای رایج
- `get()` روی empty Optional → `NoSuchElementException`.
- `Optional.of(null)` → `NullPointerException` — از `ofNullable` استفاده کنید.
- Optional را برای فیلد کلاس استفاده کردن → serialization مشکل دارد — فقط برای return values.
- `orElse` با محاسبهٔ گران → `orElseGet` بهتر است (lazy).

## جمع‌بندی
- Optional برای null safety در return values.
- `map`/`flatMap` برای transform، `orElse` برای default.
- `orElseThrow` برای fail-fast.

**جلسهٔ بعد:** Records.
