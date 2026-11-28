# جلسه ۱۳۱ — Structural Patterns: Adapter, Decorator, Facade

## اهداف یادبری
- Structural patterns را برای class/object composition بفهمید
- `Adapter`, `Decorator`, `Facade`, `Composite`, `Proxy` را پیاده کنید
- `Bridge`, `Flyweight` را بشناسید
- Composition را به‌جای inheritance ترجیح دهید

## پیش‌نیازها
- جلسهٔ ۱۳۰ (Creational Patterns)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Structural Patterns — چرا؟

Structural patterns object composition را organize می‌کنند — flexible, maintainable design.

### Adapter — incompatible interfaces

```java
// Existing interface
interface OldPrinter {
    void printOld(String text);
}

// New interface
interface NewPrinter {
    void printNew(String text);
}

// Adapter — makes OldPrinter work with NewPrinter
class PrinterAdapter implements NewPrinter {
    private final OldPrinter oldPrinter;

    public PrinterAdapter(OldPrinter oldPrinter) {
        this.oldPrinter = oldPrinter;
    }

    @Override
    public void printNew(String text) {
        oldPrinter.printOld(text);   // adapt
    }
}
```

### Decorator — add behavior dynamically

```java
interface Coffee {
    double cost();
    String description();
}

class SimpleCoffee implements Coffee {
    public double cost() { return 5.0; }
    public String description() { return "Simple coffee"; }
}

// Decorator — adds milk
class MilkDecorator implements Coffee {
    private final Coffee coffee;

    public MilkDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public double cost() { return coffee.cost() + 1.5; }
    @Override
    public String description() { return coffee.description() + " with milk"; }
}

// Usage
Coffee coffee = new SimpleCoffee();
coffee = new MilkDecorator(coffee);   // add milk
coffee = new SugarDecorator(coffee);  // add sugar
System.out.println(coffee.description());  // "Simple coffee with milk with sugar"
System.out.println(coffee.cost());         // 5.0 + 1.5 + 0.5 = 7.0
```

### Facade — simplified interface

```java
class ComplexSubsystemA {
    public void operationA() { System.out.println("Subsystem A"); }
}

class ComplexSubsystemB {
    public void operationB() { System.out.println("Subsystem B"); }
}

class ComplexSubsystemC {
    public void operationC() { System.out.println("Subsystem C"); }
}

// Facade — simplifies complex subsystem
class SimpleFacade {
    private final ComplexSubsystemA a = new ComplexSubsystemA();
    private final ComplexSubsystemB b = new ComplexSubsystemB();
    private final ComplexSubsystemC c = new ComplexSubsystemC();

    public void simpleOperation() {
        a.operationA();
        b.operationB();
        c.operationC();
    }
}

// Usage
new SimpleFacade().simpleOperation();   // one call instead of three
```

### Composite — tree structure

```java
interface Component {
    void operation();
}

class Leaf implements Component {
    private final String name;
    Leaf(String name) { this.name = name; }
    @Override public void operation() { System.out.println("Leaf: " + name); }
}

class Composite implements Component {
    private final List<Component> children = new ArrayList<>();

    public void add(Component component) { children.add(component); }

    @Override
    public void operation() {
        for (Component child : children) {
            child.operation();
        }
    }
}
```

## کد مهم

`src/AdapterExample.java` — Adapter pattern.
`src/DecoratorExample.java` — Decorator pattern.
`src/FacadeExample.java` — Facade pattern.
`src/CompositeExample.java` — Composite pattern.

## خطاهای رایج
- Adapter را بدون interface adaptation استفاده کردن → no purpose.
- Decorator را بدون `extends`/`implements` استفاده کردن → not decorator.
- Facade را بدون simplifying interface استفاده کردن → just wrapper.
- Composite را بدون tree structure استفاده کردن → just collection.

## جمع‌بندی
- Adapter برای incompatible interfaces.
- Decorator برای dynamic behavior addition.
- Facade برای simplified interface.
- Composite برای tree structure.

**جلسهٔ بعد:** Behavioral Patterns.
