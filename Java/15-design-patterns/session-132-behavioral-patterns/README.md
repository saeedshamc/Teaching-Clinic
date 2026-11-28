# جلسه ۱۳۲ — Behavioral Patterns: Observer, Strategy, Command

## اهداف یادگیری
- Behavioral patterns را برای object interaction بفهمید
- `Observer`, `Strategy`, `Command`, `Chain of Responsibility` را پیاده کنید
- `Iterator`, `Mediator`, `Template Method` را بشناسید
- Loose coupling را با patterns بهبود دهید

## پیش‌نیازها
- جلسهٔ ۱۳۱ (Structural Patterns)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Behavioral Patterns — چرا؟

Behavioral patterns object interaction را organize می‌کنند — flexible communication.

### Observer — publish-subscribe

```java
interface Observer {
    void update(String message);
}

class Subject {
    private final List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer) { observers.add(observer); }
    public void detach(Observer observer) { observers.remove(observer); }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

// Usage
Subject subject = new Subject();
subject.attach(new ConsoleObserver());
subject.attach(new FileObserver());
subject.notifyObservers("New event!");
```

### Strategy — interchangeable algorithms

```java
interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    @Override public void pay(double amount) {
        System.out.println("Paid " + amount + " with credit card");
    }
}

class PayPalPayment implements PaymentStrategy {
    @Override public void pay(double amount) {
        System.out.println("Paid " + amount + " with PayPal");
    }
}

class ShoppingCart {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.paymentStrategy = strategy;
    }

    public void checkout(double amount) {
        paymentStrategy.pay(amount);
    }
}
```

### Command — encapsulate request

```java
interface Command {
    void execute();
    void undo();
}

class LightOnCommand implements Command {
    private final Light light;
    public LightOnCommand(Light light) { this.light = light; }
    @Override public void execute() { light.on(); }
    @Override public void undo() { light.off(); }
}

class RemoteControl {
    private Command command;
    public void setCommand(Command command) { this.command = command; }
    public void pressButton() { command.execute(); }
    public void pressUndo() { command.undo(); }
}
```

### Chain of Responsibility — request chain

```java
abstract class Handler {
    protected Handler next;

    public Handler setNext(Handler next) { this.next = next; return this; }

    public abstract void handle(Request request);
}

class AuthHandler extends Handler {
    @Override public void handle(Request request) {
        if (!request.isAuthenticated()) {
            System.out.println("Auth failed");
            return;
        }
        if (next != null) next.handle(request);
    }
}

class ValidationHandler extends Handler {
    @Override public void handle(Request request) {
        if (!request.isValid()) {
            System.out.println("Validation failed");
            return;
        }
        if (next != null) next.handle(request);
    }
}
```

## کد مهم

`src/ObserverExample.java` — Observer pattern.
`src/StrategyExample.java` — Strategy pattern.
`src/CommandExample.java` — Command pattern.
`src/ChainExample.java` — Chain of Responsibility.

## خطاهای رایج
- Observer را بدون `attach`/`detach` استفاده کردن → memory leak.
- Strategy را بدون `interface` استفاده کردن → not strategy.
- Command را بدون `undo` استفاده کردن → not reversible.
- Chain را بدون `next` reference استفاده کردن → broken chain.

## جمع‌بندی
- Observer برای publish-subscribe.
- Strategy برای interchangeable algorithms.
- Command برای encapsulate requests.
- Chain of Responsibility برای request processing chain.

**جلسهٔ بعد:** Factory & Builder — deep dive.
