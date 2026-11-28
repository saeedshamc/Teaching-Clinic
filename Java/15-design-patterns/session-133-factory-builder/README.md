# جلسه ۱۳۳ — Factory & Builder: Deep Dive

## اهداف یادگیری
- `Factory` patterns را برای object creation عمیق‌تر بفهمید
- `Builder` pattern را برای complex objects پیاده کنید
- `Abstract Factory` و `Factory Method` را مقایسه کنید
- `Prototype` pattern را برای object cloning استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۳۲ (Behavioral Patterns)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Factory Method — encapsulate creation

```java
abstract class Document {
    public abstract void open();
}

class PdfDocument extends Document {
    @Override public void open() { System.out.println("Opening PDF"); }
}

class WordDocument extends Document {
    @Override public void open() { System.out.println("Opening Word"); }
}

// Factory Method — create documents
class DocumentFactory {
    public Document createDocument(String type) {
        return switch (type.toLowerCase()) {
            case "pdf" -> new PdfDocument();
            case "word" -> new WordDocument();
            default -> throw new IllegalArgumentException("Unknown type");
        };
    }
}
```

### Abstract Factory — families of objects

```java
// UI components
interface Button { void render(); }
interface CheckBox { void render(); }

// Windows factory
class WindowsButton implements Button {
    @Override public void render() { System.out.println("Windows Button"); }
}
class WindowsCheckBox implements CheckBox {
    @Override public void render() { System.out.println("Windows CheckBox"); }
}

// Mac factory
class MacButton implements Button {
    @Override public void render() { System.out.println("Mac Button"); }
}
class MacCheckBox implements CheckBox {
    @Override public void render() { System.out.println("Mac CheckBox"); }
}

// Abstract Factory
interface UIFactory {
    Button createButton();
    CheckBox createCheckBox();
}

class WindowsFactory implements UIFactory {
    @Override public Button createButton() { return new WindowsButton(); }
    @Override public CheckBox createCheckBox() { return new WindowsCheckBox(); }
}

class MacFactory implements UIFactory {
    @Override public Button createButton() { return new MacButton(); }
    @Override public CheckBox createCheckBox() { return new MacCheckBox(); }
}
```

### Builder — complex object step-by-step

```java
public class Computer {
    private final String cpu;
    private final int ram;
    private final int storage;
    private final boolean hasGPU;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasGPU = builder.hasGPU;
    }

    public static class Builder {
        private String cpu = "Intel i5";
        private int ram = 8;
        private int storage = 256;
        private boolean hasGPU = false;

        public Builder cpu(String cpu) { this.cpu = cpu; return this; }
        public Builder ram(int ram) { this.ram = ram; return this; }
        public Builder storage(int storage) { this.storage = storage; return this; }
        public Builder hasGPU(boolean hasGPU) { this.hasGPU = hasGPU; return this; }

        public Computer build() {
            return new Computer(this);
        }
    }
}

// Usage
Computer gamingPC = new Computer.Builder()
    .cpu("Intel i9")
    .ram(32)
    .storage(1000)
    .hasGPU(true)
    .build();
```

### Prototype — clone objects

```java
public class Document implements Cloneable {
    private String content;
    private String author;

    public Document(String content, String author) {
        this.content = content;
        this.author = author;
    }

    @Override
    public Document clone() throws CloneNotSupportedException {
        Document clone = (Document) super.clone();
        clone.content = new String(this.content);   // deep copy
        return clone;
    }
}

// Usage
Document original = new Document("Hello", "Author");
Document copy = original.clone();
```

## کد مهم

`src/DocumentFactory.java` — Factory Method.
`src/UIFactory.java` — Abstract Factory.
`src/ComputerBuilder.java` — Builder.
`src/DocumentPrototype.java` — Prototype.

## خطاهای رایج
- `new` را به‌جای Factory استفاده کردن → tight coupling.
- Builder را بدون `build()` استفاده کردن → incomplete object.
- `clone()` را بدون `Cloneable` استفاده کردن → `CloneNotSupportedException`.
- Abstract Factory را بدون interface استفاده کردن → not abstract.

## جمع‌بندی
- Factory Method برای single object creation.
- Abstract Factory برای families of objects.
- Builder برای complex objects — fluent API.
- Prototype برای object cloning.

**جلسهٔ بعد:** Singleton & Observer & Strategy — deep dive.
