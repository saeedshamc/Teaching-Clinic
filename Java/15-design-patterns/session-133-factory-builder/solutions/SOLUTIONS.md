# پاسخ تمرین‌های جلسه ۱۳۳

## آسان

**۱ و ۲.**
```java
public Document createDocument(String type) {
    return switch (type.toLowerCase()) {
        case "pdf" -> new PdfDocument();
        case "word" -> new WordDocument();
        default -> throw new IllegalArgumentException();
    };
}

public static class Builder {
    private String cpu = "Intel i5";
    public Builder cpu(String cpu) { this.cpu = cpu; return this; }
    public Computer build() { return new Computer(this); }
}
```

## متوسط

**۳ و ۴.**
```java
abstract class UIFactory {
    public abstract Button createButton();
    public abstract CheckBox createCheckBox();
    public void createUI() { createButton().render(); createCheckBox().render(); }
}

public Computer build() {
    if (cpu == null) throw new IllegalStateException("CPU required");
    return new Computer(this);
}
```

## سخت

**۵ و ۶.**
```java
@Override
public Document clone() throws CloneNotSupportedException {
    Document clone = (Document) super.clone();
    clone.content = new String(this.content);   // deep copy
    return clone;
}
```
