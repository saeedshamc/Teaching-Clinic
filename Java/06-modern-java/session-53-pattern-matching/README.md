# جلسه ۵۳ — Pattern Matching

## اهداف یادگیری
- `instanceof` pattern matching را بفهمید (Java 16+)
- type pattern را به‌کار ببرید
- record pattern را بشناسید (Java 21+)
- `switch` pattern matching را درک کنید (Java 21+)

## پیش‌نیازها
- جلسات ۵۱ و ۵۲ (records, sealed)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### instanceof Pattern — Java 16+

```java
// قبل از Java 16 — باید cast دستی
if (obj instanceof String) {
    String s = (String) obj;
    System.out.println(s.length());
}

// Java 16+ — pattern matching در instanceof
if (obj instanceof String s) {
    System.out.println(s.length());   // s خودکار cast شده
}
```

### Pattern matching با sealed types

```java
sealed interface Shape permits Circle, Square {}
record Circle(double radius) implements Shape {}
record Square(double side) implements Shape {}

// instanceof pattern
static String describe(Shape shape) {
    if (shape instanceof Circle c) {
        return "Circle r=" + c.radius();
    } else if (shape instanceof Square s) {
        return "Square s=" + s.side();
    }
    return "Unknown";
}
```

### Record Pattern — Java 21+

```java
// Java 21+ — destructure record در instanceof
if (obj instanceof Point(int x, int y)) {
    System.out.println("Point at " + x + "," + y);
}

// یا در switch
String describe(Object obj) {
    return switch (obj) {
        case Point(int x, int y) -> "Point(" + x + "," + y + ")";
        case Circle(double r) -> "Circle r=" + r;
        case Square(double s) -> "Square s=" + s;
        default -> "Unknown";
    };
}
```

### switch Pattern — Java 21+

```java
// Java 21+ — switch با pattern
String typeOf(Object obj) {
    return switch (obj) {
        case null -> "null";
        case String s -> "String";
        case Integer i -> "Integer";
        case Double d -> "Double";
        default -> "Other";
    };
}
```

## کد مهم

`src/PatternMatchingDemo.java` — instanceof pattern، sealed + pattern، record pattern.

## خطاهای رایج
- `instanceof` بدون pattern → cast دستی لازم است.
- switch pattern در Java 17 → preview feature — در Java 21+ final است.
- record pattern در Java 17 → وجود ندارد — Java 21+.
- `default` در exhaustive switch → ممکن است لازم نباشد با sealed.

## جمع‌بندی
- instanceof pattern (Java 16+) — type pattern.
- record pattern (Java 21+) — destructure record.
- switch pattern (Java 21+) — exhaustive type switching.

**جلسهٔ بعد:** Text Blocks و Switch Expressions.
