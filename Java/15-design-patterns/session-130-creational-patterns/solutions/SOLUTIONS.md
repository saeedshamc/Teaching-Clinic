# پاسخ تمرین‌های جلسه ۱۳۰

## آسان

**۱ و ۲.**
```java
public class Singleton {
    private static Singleton instance;
    private Singleton() {}
    public static synchronized Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }
}

public class ShapeFactory {
    public static Shape createShape(String type) {
        return switch (type) {
            case "circle" -> new Circle();
            case "square" -> new Square();
            default -> throw new IllegalArgumentException();
        };
    }
}
```

## متوسط

**۳ و ۴.**
```java
public static class Builder {
    private String name;
    public Builder name(String name) { this.name = name; return this; }
    public User build() {
        if (name == null) throw new IllegalStateException("Name required");
        return new User(this);
    }
}
```

## سخت

**۵ و ۶.**
```java
// Abstract Factory
public interface UIFactory {
    Button createButton();
    CheckBox createCheckBox();
}

// Prototype
public class Document implements Cloneable {
    public Document clone() throws CloneNotSupportedException {
        return (Document) super.clone();
    }
}
```
