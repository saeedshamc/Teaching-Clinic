# پاسخ تمرین‌های جلسه ۱۳۴

## آسان

**۱ و ۲.**
```java
public class ConfigManager {
    private static class Holder {
        static final ConfigManager INSTANCE = new ConfigManager();
    }
    private ConfigManager() {}
    public static ConfigManager getInstance() { return Holder.INSTANCE; }
}

public class User {
    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);
    public void addPropertyChangeListener(PropertyChangeListener l) {
        pcs.addPropertyChangeListener(l);
    }
    public void setName(String name) {
        pcs.firePropertyChange("name", oldName, name);
    }
}
```

## متوسط

**۳ و ۴.**
```java
interface PaymentGateway { boolean processPayment(double amount, String details); }
class CreditCardGateway implements PaymentGateway {
    @Override public boolean processPayment(double amount, String card) { return true; }
}

interface SortStrategy { void sort(List<Integer> list); }
class BubbleSort implements SortStrategy {
    @Override public void sort(List<Integer> list) { /* bubble sort */ }
}
```

## سخت

**۵ و ۶.**
```java
public class Logger {
    private static class Holder { static final Logger INSTANCE = new Logger(); }
    private Logger() {}
    public static Logger getInstance() { return Holder.INSTANCE; }
    private List<Consumer<String>> listeners = new ArrayList<>();
    public void addListener(Consumer<String> listener) { listeners.add(listener); }
    public void log(String message) { listeners.forEach(l -> l.accept(message)); }
}

interface NotificationService { void send(String message); }
class EmailNotification implements NotificationService {
    @Override public void send(String message) { /* email */ }
}
```
