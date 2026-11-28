# پاسخ تمرین‌های جلسه ۱۳۵

## آسان

**۱ و ۲.**
```java
public class DatabaseConnection {
    private static class Holder { static final DatabaseConnection INSTANCE = new DatabaseConnection(); }
    private DatabaseConnection() {}
    public static DatabaseConnection getInstance() { return Holder.INSTANCE; }
}

public class ProductFactory {
    public Product createProduct(String type, String name, double price) {
        return switch (type) {
            case "laptop" -> new Laptop(name, price);
            case "phone" -> new Phone(name, price);
            default -> throw new IllegalArgumentException();
        };
    }
}
```

## متوسط

**۳ و ۴.**
```java
public static class Builder {
    private Long userId;
    private final List<OrderItem> items = new ArrayList<>();
    public Builder userId(Long userId) { this.userId = userId; return this; }
    public Builder addItem(Product p, int qty) { items.add(new OrderItem(p, qty)); return this; }
    public Order build() { return new Order(this); }
}

class DiscountDecorator extends Product {
    private final Product product;
    private final double discountPercent;
    @Override public double getPrice() { return product.getPrice() * (1 - discountPercent / 100); }
}
```

## سخت

**۵–۸.**
```java
// Observer
interface OrderListener { void onOrderUpdate(String message); }
class NotificationService {
    private List<OrderListener> listeners = new ArrayList<>();
    public void notify(String msg) { listeners.forEach(l -> l.onOrderUpdate(msg)); }
}

// Strategy
interface PaymentStrategy { boolean process(double amount); }
class CreditCardPayment implements PaymentStrategy {
    @Override public boolean process(double amount) { return true; }
}

// Command
interface OrderCommand { void execute(); void undo(); }
class CancelOrderCommand implements OrderCommand {
    @Override public void execute() { order.setStatus("CANCELLED"); }
    @Override public void undo() { order.setStatus("PENDING"); }
}

// Facade
class OrderFacade {
    public void processOrder(Order order) {
        // complex logic simplified
    }
}
```
