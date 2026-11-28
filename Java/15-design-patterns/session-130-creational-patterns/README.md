# جلسه ۱۳۰ — Creational Patterns: Singleton, Factory, Builder

## اهداف یادگیری
- Creational patterns را برای object creation بفهمید
- `Singleton`, `Factory Method`, `Builder` را پیاده کنید
- `Abstract Factory`, `Prototype` را بشناسید
- Anti-patterns و best practices را در نظر بگیرید

## پیش‌نیازها
- جلسهٔ ۱۲۹ (Testing Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Creational Patterns — چرا؟

Creational patterns object creation را encapsulate می‌کنند — decouple instantiation از usage.

### Singleton — یک instance فقط

```java
public class DatabaseConnection {
    private static DatabaseConnection instance;

    private DatabaseConnection() {}   // private constructor

    public static synchronized DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
```

**Better — Bill Pugh Singleton:**
```java
public class DatabaseConnection {
    private static class Holder {
        static final DatabaseConnection INSTANCE = new DatabaseConnection();
    }

    public static DatabaseConnection getInstance() {
        return Holder.INSTANCE;   // lazy + thread-safe
    }
}
```

### Factory Method — create objects

```java
public interface Shape {
    void draw();
}

public class Circle implements Shape {
    @Override public void draw() { System.out.println("Drawing circle"); }
}

public class Square implements Shape {
    @Override public void draw() { System.out.println("Drawing square"); }
}

public class ShapeFactory {
    public static Shape createShape(String type) {
        return switch (type.toLowerCase()) {
            case "circle" -> new Circle();
            case "square" -> new Square();
            default -> throw new IllegalArgumentException("Unknown shape");
        };
    }
}
```

### Builder — complex object creation

```java
public class User {
    private final String name;
    private final String email;
    private final int age;
    private final String phone;

    private User(Builder builder) {
        this.name = builder.name;
        this.email = builder.email;
        this.age = builder.age;
        this.phone = builder.phone;
    }

    public static class Builder {
        private String name;
        private String email;
        private int age;
        private String phone;

        public Builder name(String name) { this.name = name; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder age(int age) { this.age = age; return this; }
        public Builder phone(String phone) { this.phone = phone; return this; }

        public User build() {
            return new User(this);
        }
    }
}

// Usage
User user = new User.Builder()
    .name("Ali")
    .email("ali@mail.com")
    .age(25)
    .build();
```

## کد مهم

`src/SingletonExample.java` — Singleton pattern.
`src/ShapeFactory.java` — Factory pattern.
`src/UserBuilder.java` — Builder pattern.

## خطاهای رایج
- `new` را به‌جای `getInstance` استفاده کردن → multiple instances.
- `synchronized` را بدون reason استفاده کردن → performance hit.
- Builder را بدون `build()` method استفاده کردن → incomplete object.
- Factory را بدون `switch`/`if` استفاده کردن → no polymorphism.

## جمع‌بندی
- Singleton برای single instance — lazy + thread-safe.
- Factory برای object creation — decouple instantiation.
- Builder برای complex objects — fluent API.
- Abstract Factory و Prototype برای advanced cases.

**جلسهٔ بعد:** Structural Patterns.
