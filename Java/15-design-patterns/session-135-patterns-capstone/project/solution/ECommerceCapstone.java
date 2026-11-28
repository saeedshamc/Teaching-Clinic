// ECommerceCapstone.java — پاسخ کامل capstone
// همهٔ design patterns ترکیب‌شده در یک سیستم
public class ECommerceCapstone {

    public static void main(String[] args) {
        // Singleton
        DatabaseConnection.getInstance().connect();

        // Factory + Decorator
        ProductFactory factory = new ProductFactory();
        Product laptop = factory.createProduct("laptop", "Gaming Laptop", 1500.0);
        Product discounted = new DiscountDecorator(laptop, 10);

        // Builder
        Order order = new Order.Builder()
            .userId(1L)
            .addItem(discounted, 1)
            .build();

        // Strategy
        PaymentProcessor processor = new PaymentProcessor();
        processor.setStrategy(new CreditCardPayment());
        processor.processPayment(order.getTotal());

        // Observer
        NotificationService notifications = new NotificationService();
        notifications.addListener(msg -> System.out.println("[Email] " + msg));
        notifications.notify("Order confirmed!");

        // Command
        new CancelOrderCommand(order).execute();

        // Facade
        new OrderFacade().processOrder(order);

        DatabaseConnection.getInstance().disconnect();
    }

    // Singleton
    static class DatabaseConnection {
        private static class Holder { static final DatabaseConnection I = new DatabaseConnection(); }
        private DatabaseConnection() {}
        public static DatabaseConnection getInstance() { return Holder.I; }
        public void connect() { System.out.println("[DB] Connected"); }
        public void disconnect() { System.out.println("[DB] Disconnected"); }
    }

    // Factory
    static class Product {
        protected String type, name;
        protected double price;
        Product(String t, String n, double p) { type = t; name = n; price = p; }
        public double getPrice() { return price; }
    }
    static class Laptop extends Product { Laptop(String n, double p) { super("laptop", n, p); } }
    static class ProductFactory {
        public Product createProduct(String type, String name, double price) {
            return switch (type) {
                case "laptop" -> new Laptop(name, price);
                default -> throw new IllegalArgumentException();
            };
        }
    }

    // Decorator
    static class DiscountDecorator extends Product {
        private final Product product;
        private final double discount;
        DiscountDecorator(Product p, double d) { super(p.type, p.name, p.price); product = p; discount = d; }
        @Override public double getPrice() { return product.getPrice() * (1 - discount / 100); }
    }

    // Builder
    static class OrderItem {
        final Product product;
        final int quantity;
        OrderItem(Product p, int q) { product = p; quantity = q; }
        double getTotal() { return product.getPrice() * quantity; }
    }
    static class Order {
        private Long id = System.currentTimeMillis();
        private Long userId;
        private final java.util.List<OrderItem> items = new java.util.ArrayList<>();
        private String status = "PENDING";
        private Order(Builder b) { userId = b.userId; items.addAll(b.items); }
        public Long getId() { return id; }
        public double getTotal() { return items.stream().mapToDouble(OrderItem::getTotal).sum(); }
        public String getStatus() { return status; }
        public void setStatus(String s) { status = s; }
        public static class Builder {
            private Long userId;
            private final java.util.List<OrderItem> items = new java.util.ArrayList<>();
            public Builder userId(Long u) { userId = u; return this; }
            public Builder addItem(Product p, int q) { items.add(new OrderItem(p, q)); return this; }
            public Order build() { return new Order(this); }
        }
    }

    // Strategy
    interface PaymentStrategy { boolean process(double amount); }
    static class CreditCardPayment implements PaymentStrategy {
        @Override public boolean process(double amount) { System.out.println("[Card] $" + amount); return true; }
    }
    static class PaymentProcessor {
        private PaymentStrategy strategy;
        public void setStrategy(PaymentStrategy s) { strategy = s; }
        public boolean processPayment(double amount) { return strategy.process(amount); }
    }

    // Observer
    interface OrderListener { void onOrderUpdate(String message); }
    static class NotificationService {
        private final java.util.List<OrderListener> listeners = new java.util.ArrayList<>();
        public void addListener(OrderListener l) { listeners.add(l); }
        public void notify(String msg) { listeners.forEach(l -> l.onOrderUpdate(msg)); }
    }

    // Command
    interface OrderCommand { void execute(); void undo(); }
    static class CancelOrderCommand implements OrderCommand {
        private final Order order;
        CancelOrderCommand(Order o) { order = o; }
        @Override public void execute() { order.setStatus("CANCELLED"); System.out.println("[Command] Cancelled"); }
        @Override public void undo() { order.setStatus("PENDING"); System.out.println("[Command] Restored"); }
    }

    // Facade
    static class OrderFacade {
        public void processOrder(Order order) {
            System.out.println("[Facade] Processing order " + order.getId());
            order.setStatus("CONFIRMED");
            System.out.println("[Facade] Order confirmed");
        }
    }
}
