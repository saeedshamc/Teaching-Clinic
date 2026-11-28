# جلسه ۱۳۴ — Singleton, Observer & Strategy: Real-world Applications

## اهداف یادگیری
- `Singleton`, `Observer`, `Strategy` را در پروژه‌های واقعی به‌کار ببرید
- Thread-safe Singleton را با Bill Pugh implementation پیاده کنید
- `PropertyChangeListener` برای Observer pattern استفاده کنید
- Strategy pattern را برای algorithm selection استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۳۳ (Factory & Builder)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Singleton — when to use

```java
public class ConfigManager {
    private static class Holder {
        static final ConfigManager INSTANCE = new ConfigManager();
    }

    private ConfigManager() {}

    public static ConfigManager getInstance() {
        return Holder.INSTANCE;   // lazy + thread-safe
    }

    private Map<String, String> config = new HashMap<>();

    public void set(String key, String value) { config.put(key, value); }
    public String get(String key) { return config.get(key); }
}
```

**Use cases:** Database connection, Config manager, Logger, Cache.

### Observer — Java built-in

```java
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class User {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    private String name;

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }

    public void setName(String name) {
        String oldName = this.name;
        this.name = name;
        pcs.firePropertyChange("name", oldName, name);
    }
}

// Usage
User user = new User();
user.addPropertyChangeListener(evt -> {
    System.out.println("Name changed: " + evt.getOldValue() + " → " + evt.getNewValue());
});
user.setName("Ali");
```

### Strategy — algorithm selection

```java
interface SortStrategy {
    void sort(List<Integer> list);
}

class BubbleSort implements SortStrategy {
    @Override public void sort(List<Integer> list) { /* bubble sort */ }
}

class QuickSort implements SortStrategy {
    @Override public void sort(List<Integer> list) { /* quick sort */ }
}

class Sorter {
    private SortStrategy strategy;

    public void setStrategy(SortStrategy strategy) { this.strategy = strategy; }
    public void sort(List<Integer> list) { strategy.sort(list); }
}
```

### Real-world Example — Payment System

```java
interface PaymentGateway {
    boolean processPayment(double amount, String cardNumber);
}

class StripeGateway implements PaymentGateway {
    @Override public boolean processPayment(double amount, String cardNumber) {
        // Stripe API call
        return true;
    }
}

class PayPalGateway implements PaymentGateway {
    @Override public boolean processPayment(double amount, String cardNumber) {
        // PayPal API call
        return true;
    }
}

class PaymentProcessor {
    private PaymentGateway gateway;

    public PaymentProcessor(PaymentGateway gateway) {
        this.gateway = gateway;
    }

    public boolean checkout(double amount, String cardNumber) {
        return gateway.processPayment(amount, cardNumber);
    }
}
```

## کد مهم

`src/ConfigManager.java` — Singleton for config.
`src/UserObserver.java` — Observer with PropertyChangeSupport.
`src/PaymentProcessor.java` — Strategy for payments.
`src/SortStrategy.java` — Strategy for sorting.

## خطاهای رایج
- Singleton را بدون `private constructor` استفاده کردن → multiple instances.
- Observer را بدون `detach` استفاده کردن → memory leak.
- Strategy را بدون `interface` استفاده کردن → not strategy.
- `PropertyChangeSupport` را بدون `firePropertyChange` استفاده کردن → no events.

## جمع‌بندی
- Singleton برای shared resources — thread-safe با Bill Pugh.
- Observer برای event notification — `PropertyChangeSupport`.
- Strategy برای algorithm selection — interchangeable implementations.
- Real-world applications در config, events, payments.

**جلسهٔ بعد:** Patterns Capstone.
