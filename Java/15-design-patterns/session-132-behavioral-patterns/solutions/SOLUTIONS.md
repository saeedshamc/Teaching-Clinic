# پاسخ تمرین‌های جلسه ۱۳۲

## آسان

**۱ و ۲.**
```java
class Subject {
    private List<Observer> observers = new ArrayList<>();
    public void attach(Observer o) { observers.add(o); }
    public void notify(String msg) { observers.forEach(o -> o.update(msg)); }
}

interface PaymentStrategy { void pay(double amount); }
class CreditCardPayment implements PaymentStrategy {
    @Override public void pay(double amount) { /* ... */ }
}
```

## متوسط

**۳ و ۴.**
```java
interface Command { void execute(); void undo(); }
class LightOnCommand implements Command {
    @Override public void execute() { light.on(); }
    @Override public void undo() { light.off(); }
}

abstract class Handler {
    protected Handler next;
    public Handler setNext(Handler n) { next = n; return this; }
    public abstract void handle(Request req);
}
```

## سخت

**۵ و ۶.**
```java
// Iterator
class CustomCollection implements Iterable<Item> {
    public Iterator<Item> iterator() { return new CustomIterator(); }
}

// Mediator
class ChatMediator {
    private List<User> users = new ArrayList<>();
    public void sendMessage(User sender, String msg) {
        users.forEach(u -> { if (u != sender) u.receive(msg); });
    }
}
```
