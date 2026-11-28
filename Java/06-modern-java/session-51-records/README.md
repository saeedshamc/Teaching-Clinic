# جلسه ۵۱ — Records

## اهداف یادگیری
- `record` را برای immutable data class بشناسید
- compact constructor را بفهمید
- canonical constructor را به‌کار ببرید
- record با methods و static methods را بسازید

## پیش‌نیازها
- فازهای ۱–۳ و جلسات ۴۶–۵۰

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Record — immutable data class

```java
// کلاس ساده برای data — قبل از record
class Person {
    private final String name;
    private final int age;
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String toString() { return "Person[name=" + name + ", age=" + age + "]"; }
    public boolean equals(Object o) { /* ... */ }
    public int hashCode() { /* ... */ }
}

// record — همه‌چیز خودکار (Java 16+)
record Person(String name, int age) {}
```

### Record features

```java
record Point(int x, int y) {}

// automatic:
// - private final fields
// - canonical constructor
// - accessors: x(), y() — بدون get prefix!
// - toString(), equals(), hashCode()

Point p = new Point(3, 4);
System.out.println(p.x());   // 3 — نه getX()!
System.out.println(p);       // Point[x=3, y=4]
```

### Compact constructor

```java
record Person(String name, int age) {
    // compact constructor — validation بدون تکرار assign
    Person {
        if (age < 0) throw new IllegalArgumentException("Age must be >= 0");
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name required");
        }
        // name و age خودکار assign می‌شوند
    }
}
```

### Record با methods

```java
record Circle(double radius) {
    // static factory
    static Circle of(double radius) {
        return new Circle(radius);
    }

    // instance method
    double area() {
        return Math.PI * radius * radius;
    }

    // computed accessor
    double diameter() {
        return radius * 2;
    }
}
```

## کد مهم

`src/RecordDemo.java` — record basics، compact constructor، methods، static factory.

## خطاهای رایج
- `getName()` را صدا زدن → record accessor `name()` است نه `getName()`.
- record را extend کردن → record final است، extend نمی‌شود.
- fieldهای غیر final → record همه fieldها را final می‌کند.
- compact constructor بدون assign → خودکار assign می‌شود ولی validation می‌توانید بگذارید.

## جمع‌بندی
- Record برای immutable data class — Java 16+.
- Canonical constructor خودکار، accessor بدون get.
- Compact constructor برای validation.

**جلسهٔ بعد:** Sealed Classes.
