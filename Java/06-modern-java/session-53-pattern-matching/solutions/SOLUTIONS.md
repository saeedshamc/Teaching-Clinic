# پاسخ تمرین‌های جلسه ۵۳

## آسان

**۱ و ۲.**
```java
if (obj instanceof String s) {
    System.out.println(s.length());
}

if (obj instanceof Person p) {
    System.out.println(p.name());
}
```

## متوسط

**۳.**
```java
if (obj instanceof Person p && p.age() >= 18) {
    System.out.println("Adult: " + p.name());
}
```

**۴.**
```java
sealed interface Shape permits Circle, Square {}
record Circle(double r) implements Shape {}
record Square(double s) implements Shape {}

if (shape instanceof Circle c) { ... }
else if (shape instanceof Square sq) { ... }
```

## سخت

**۵ و ۶.** (نیازمند Java 21+)
```java
// switch pattern
String describe(Shape s) {
    return switch (s) {
        case Circle c -> "Circle";
        case Square sq -> "Square";
    };
}

// record pattern
if (obj instanceof Point(int x, int y)) {
    System.out.println("Point at " + x + "," + y);
}
```
