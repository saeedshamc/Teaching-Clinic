// StructuralPatterns.java — پاسخ کامل پروژهٔ جلسه ۱۳۱
// Adapter + Decorator + Facade + Composite examples
public class StructuralPatterns {

    public static void main(String[] args) {
        // Adapter
        OldPrinter old = new OldPrinterImpl();
        NewPrinter adapted = new PrinterAdapter(old);
        adapted.printNew("Adapter demo");

        // Decorator
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        System.out.println("Coffee: " + coffee.description() + " — " + coffee.cost());

        // Facade
        new SimpleFacade().simpleOperation();

        // Composite
        Composite root = new Composite("Root");
        root.add(new Leaf("A"));
        Composite branch = new Composite("Branch");
        branch.add(new Leaf("B"));
        root.add(branch);
        root.operation();
    }

    // ---------- Adapter ----------
    interface OldPrinter { void printOld(String text); }
    interface NewPrinter { void printNew(String text); }
    static class OldPrinterImpl implements OldPrinter {
        @Override public void printOld(String text) { System.out.println("[OLD] " + text); }
    }
    static class PrinterAdapter implements NewPrinter {
        private final OldPrinter old;
        PrinterAdapter(OldPrinter old) { this.old = old; }
        @Override public void printNew(String text) { old.printOld(text); }
    }

    // ---------- Decorator ----------
    interface Coffee { double cost(); String description(); }
    static class SimpleCoffee implements Coffee {
        @Override public double cost() { return 5.0; }
        @Override public String description() { return "Simple coffee"; }
    }
    static class MilkDecorator implements Coffee {
        private final Coffee c;
        MilkDecorator(Coffee c) { this.c = c; }
        @Override public double cost() { return c.cost() + 1.5; }
        @Override public String description() { return c.description() + " + milk"; }
    }
    static class SugarDecorator implements Coffee {
        private final Coffee c;
        SugarDecorator(Coffee c) { this.c = c; }
        @Override public double cost() { return c.cost() + 0.5; }
        @Override public String description() { return c.description() + " + sugar"; }
    }

    // ---------- Facade ----------
    static class SubsystemA { void operationA() { System.out.println("A"); } }
    static class SubsystemB { void operationB() { System.out.println("B"); } }
    static class SimpleFacade {
        private final SubsystemA a = new SubsystemA();
        private final SubsystemB b = new SubsystemB();
        void simpleOperation() { a.operationA(); b.operationB(); }
    }

    // ---------- Composite ----------
    interface Component { void operation(); }
    static class Leaf implements Component {
        private final String name;
        Leaf(String name) { this.name = name; }
        @Override public void operation() { System.out.println("Leaf: " + name); }
    }
    static class Composite implements Component {
        private final String name;
        private final java.util.List<Component> children = new java.util.ArrayList<>();
        Composite(String name) { this.name = name; }
        void add(Component c) { children.add(c); }
        @Override public void operation() {
            System.out.println("Composite: " + name);
            children.forEach(Component::operation);
        }
    }
}
