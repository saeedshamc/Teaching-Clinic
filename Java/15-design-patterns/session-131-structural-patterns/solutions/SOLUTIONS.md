# پاسخ تمرین‌های جلسه ۱۳۱

## آسان

**۱ و ۲.**
```java
class PrinterAdapter implements NewPrinter {
    private final OldPrinter oldPrinter;
    public PrinterAdapter(OldPrinter oldPrinter) { this.oldPrinter = oldPrinter; }
    @Override public void printNew(String text) { oldPrinter.printOld(text); }
}

class MilkDecorator implements Coffee {
    private final Coffee coffee;
    public MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    @Override public double cost() { return coffee.cost() + 1.5; }
    @Override public String description() { return coffee.description() + " with milk"; }
}
```

## متوسط

**۳ و ۴.**
```java
class SimpleFacade {
    private final SubsystemA a = new SubsystemA();
    private final SubsystemB b = new SubsystemB();
    public void simpleOperation() { a.operationA(); b.operationB(); }
}

class Composite implements Component {
    private final List<Component> children = new ArrayList<>();
    public void add(Component c) { children.add(c); }
    @Override public void operation() { children.forEach(Component::operation); }
}
```

## سخت

**۵ و ۶.**
```java
// Proxy
class ImageProxy implements Image {
    private RealImage realImage;
    public void display() {
        if (realImage == null) realImage = new RealImage();
        realImage.display();
    }
}

// Bridge
abstract class Shape { protected Renderer renderer; }
class Circle extends Shape { /* uses renderer */ }
```
