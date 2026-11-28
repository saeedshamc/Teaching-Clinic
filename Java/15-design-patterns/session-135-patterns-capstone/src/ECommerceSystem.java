// ECommerceSystem.java — پاسخ کامل capstone: همهٔ patterns ترکیب‌شده
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class ECommerceSystem {

    public static void main(String[] args) {
        // Singleton — DatabaseConnection
        DatabaseConnection db = DatabaseConnection.getInstance();
        db.connect();

        // Factory — Product creation
        ProductFactory productFactory = new ProductFactory();
        Product laptop = productFactory.createProduct("laptop", "Gaming Laptop", 1500.0);
        Product phone = productFactory.createProduct("phone", "iPhone 15", 999.0);

        // Decorator — Discount
        Product discountedLaptop = new DiscountDecorator(laptop, 10);
        System.out.println("Laptop price: $" + discountedLaptop.getPrice());

        // Builder — Order
        Order order = new Order.Builder()
            .userId(1L)
            .addItem(discountedLaptop, 1)
            .addItem(phone, 2)
            .build();

        // Strategy — Payment
        PaymentProcessor processor = new PaymentProcessor();
        processor.setStrategy(new CreditCardPayment());
        processor.processPayment(order.getTotal());

        // Observer — Notifications
        NotificationService notifications = new NotificationService();
        notifications.addListener(msg -> System.out.println("[Email] " + msg));
        notifications.addListener(msg -> System.out.println("[SMS] " + msg));
        notifications.notify("Order " + order.getId() + " confirmed!");

        // Command — Order actions
        OrderCommand cancelCommand = new CancelOrderCommand(order);
        OrderCommand refundCommand = new RefundOrderCommand(order);
        cancelCommand.execute();
        cancelCommand.undo();

        // Facade — OrderProcessing
        OrderFacade facade = new OrderFacade();
        facade.processOrder(order);

        db.disconnect();
    }

    // ---------- Singleton ----------
    static class DatabaseConnection {
        private static class Holder { static final DatabaseConnection INSTANCE = new DatabaseConnection(); }
        private DatabaseConnection() {}
        public static DatabaseConnection getInstance() { return Holder.INSTANCE; }
        public void connect() { System.out.println("[DB] Connected"); }
        public void disconnect() { System.out.println("[DB] Disconnected"); }
    }

    // ---------- Factory ----------
    static class Product {
        protected String type, name;
        protected double price;
        public Product(String type, String name, double price) {
            this.type = type; this.name = name; this.price = price;
        }
        public double getPrice() { return price; }
        public String getName() { return name; }
    }

    static class Laptop extends Product {
        Laptop(String name, double price) { super("laptop", name, price); }
    }
    static class Phone extends Product {
        Phone(String name, double price) { super("phone", name, price); }
    }

    static class ProductFactory {
        public Product createProduct(String type, String name, double price) {
            return switch (type.toLowerCase()) {
                case "laptop" -> new Laptop(name, price);
                case "phone" -> new Phone(name, price);
                default -> throw new IllegalArgumentException("Unknown product: " + type);
            };
        }
    }

    // ---------- Decorator ----------
    static class DiscountDecorator extends Product {
        private final Product product;
        private final double discountPercent;

        DiscountDecorator(Product product, double discountPercent) {
            super(product.type, product.name, product.price);
            this.product = product;
            this.discountPercent = discountPercent;
        }

        @Override
        public double getPrice() {
            return product.getPrice() * (1 - discountPercent / 100);
        }
    }

    // ---------- Builder ----------
    static class OrderItem {
        final Product product;
        final int quantity;
        OrderItem(Product product, int quantity) {
            this.product = product; this.quantity = quantity;
        }
        double getTotal() { return product.getPrice() * quantity; }
    }

    static class Order {
        private Long id;
        private Long userId;
        private final List<OrderItem> items = new ArrayList<>();
        private String status = "PENDING";
        private static long nextId = 1;

        private Order(Builder builder) {
            this.id = nextId++;
            this.userId = builder.userId;
            this.items.addAll(builder.items);
        }

        public Long getId() { return id; }
        public Long getUserId() { return userId; }
        public List<OrderItem> getItems() { return items; }
        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }
        public double getTotal() { return items.stream().mapToDouble(OrderItem::getTotal).sum(); }

        public static class Builder {
            private Long userId;
            private final List<OrderItem> items = new ArrayList<>();

            public Builder userId(Long userId) { this.userId = userId; return this; }
            public Builder addItem(Product product, int quantity) {
                items.add(new OrderItem(product, quantity));
                return this;
            }
            public Order build() { return new Order(this); }
        }
    }

    // ---------- Strategy ----------
    interface PaymentStrategy {
        boolean process(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        @Override public boolean process(double amount) {
            System.out.println("[Payment] Credit card: $" + amount);
            return true;
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        @Override public boolean process(double amount) {
            System.out.println("[Payment] PayPal: $" + amount);
            return true;
        }
    }

    static class PaymentProcessor {
        private PaymentStrategy strategy;
        public void setStrategy(PaymentStrategy strategy) { this.strategy = strategy; }
        public boolean processPayment(double amount) { return strategy.process(amount); }
    }

    // ---------- Observer ----------
    interface OrderListener {
        void onOrderUpdate(String message);
    }

    static class NotificationService {
        private final List<OrderListener> listeners = new ArrayList<>();
        public void addListener(OrderListener listener) { listeners.add(listener); }
        public void notify(String message) {
            listeners.forEach(l -> l.onOrderUpdate(message));
        }
    }

    // ---------- Command ----------
    interface OrderCommand {
        void execute();
        void undo();
    }

    static class CancelOrderCommand implements OrderCommand {
        private final Order order;
        private String previousStatus;

        CancelOrderCommand(Order order) { this.order = order; }

        @Override public void execute() {
            previousStatus = order.getStatus();
            order.setStatus("CANCELLED");
            System.out.println("[Command] Order cancelled");
        }
        @Override public void undo() {
            order.setStatus(previousStatus);
            System.out.println("[Command] Order restored to " + previousStatus);
        }
    }

    static class RefundOrderCommand implements OrderCommand {
        private final Order order;
        RefundOrderCommand(Order order) { this.order = order; }
        @Override public void execute() { System.out.println("[Command] Order refunded"); }
        @Override public void undo() { System.out.println("[Command] Refund undone"); }
    }

    // ---------- Facade ----------
    static class OrderFacade {
        private final ProductFactory productFactory = new ProductFactory();
        private final PaymentProcessor paymentProcessor = new PaymentProcessor();
        private final NotificationService notifications = new NotificationService();

        public void processOrder(Order order) {
            System.out.println("[Facade] Processing order " + order.getId());
            paymentProcessor.setStrategy(new CreditCardPayment());
            paymentProcessor.processPayment(order.getTotal());
            notifications.notify("Order " + order.getId() + " processed");
            order.setStatus("CONFIRMED");
            System.out.println("[Facade] Order " + order.getId() + " confirmed");
        }
    }
}
