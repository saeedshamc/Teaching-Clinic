// DecoratorExample.java — Decorator pattern demo
// قابل کامپایل با javac — بدون dependency
public class DecoratorExample {

    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);
        coffee = new WhippedCreamDecorator(coffee);

        System.out.println("Description: " + coffee.description());
        System.out.println("Cost: " + coffee.cost());
    }

    interface Coffee {
        double cost();
        String description();
    }

    static class SimpleCoffee implements Coffee {
        @Override public double cost() { return 5.0; }
        @Override public String description() { return "Simple coffee"; }
    }

    static class MilkDecorator implements Coffee {
        private final Coffee coffee;
        MilkDecorator(Coffee coffee) { this.coffee = coffee; }
        @Override public double cost() { return coffee.cost() + 1.5; }
        @Override public String description() { return coffee.description() + " with milk"; }
    }

    static class SugarDecorator implements Coffee {
        private final Coffee coffee;
        SugarDecorator(Coffee coffee) { this.coffee = coffee; }
        @Override public double cost() { return coffee.cost() + 0.5; }
        @Override public String description() { return coffee.description() + " with sugar"; }
    }

    static class WhippedCreamDecorator implements Coffee {
        private final Coffee coffee;
        WhippedCreamDecorator(Coffee coffee) { this.coffee = coffee; }
        @Override public double cost() { return coffee.cost() + 2.0; }
        @Override public String description() { return coffee.description() + " with whipped cream"; }
    }
}
