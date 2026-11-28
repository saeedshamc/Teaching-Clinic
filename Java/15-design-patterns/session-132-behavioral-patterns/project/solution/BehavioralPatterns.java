// BehavioralPatterns.java — پاسخ کامل پروژهٔ جلسه ۱۳۲
// Observer + Strategy + Command + Chain examples
public class BehavioralPatterns {

    public static void main(String[] args) {
        // Observer
        NewsAgency agency = new NewsAgency();
        agency.attach(new ConsoleObserver());
        agency.attach(new EmailObserver());
        agency.publish("Breaking news!");

        // Strategy
        ShoppingCart cart = new ShoppingCart();
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(100.0);

        // Command
        Light light = new Light();
        RemoteControl remote = new RemoteControl();
        remote.setCommand(new LightOnCommand(light));
        remote.pressButton();
        remote.undo();

        // Chain
        Handler chain = new AuthHandler().setNext(new ValidationHandler()).setNext(new ResponseHandler());
        chain.handle(new Request(true, true));
    }

    // ---------- Observer ----------
    interface Observer { void update(String news); }
    static class NewsAgency {
        private final java.util.List<Observer> observers = new java.util.ArrayList<>();
        void attach(Observer o) { observers.add(o); }
        void publish(String news) { observers.forEach(o -> o.update(news)); }
    }
    static class ConsoleObserver implements Observer {
        @Override public void update(String news) { System.out.println("[Console] " + news); }
    }
    static class EmailObserver implements Observer {
        @Override public void update(String news) { System.out.println("[Email] " + news); }
    }

    // ---------- Strategy ----------
    interface PaymentStrategy { void pay(double amount); }
    static class CreditCardPayment implements PaymentStrategy {
        @Override public void pay(double amount) { System.out.println("Paid $" + amount + " with card"); }
    }
    static class ShoppingCart {
        private PaymentStrategy strategy;
        void setPaymentStrategy(PaymentStrategy s) { strategy = s; }
        void checkout(double amount) { strategy.pay(amount); }
    }

    // ---------- Command ----------
    interface Command { void execute(); void undo(); }
    static class Light {
        void on() { System.out.println("Light ON"); }
        void off() { System.out.println("Light OFF"); }
    }
    static class LightOnCommand implements Command {
        private final Light light;
        LightOnCommand(Light l) { light = l; }
        @Override public void execute() { light.on(); }
        @Override public void undo() { light.off(); }
    }
    static class RemoteControl {
        private Command command, last;
        void setCommand(Command c) { command = c; }
        void pressButton() { command.execute(); last = command; }
        void undo() { if (last != null) last.undo(); }
    }

    // ---------- Chain ----------
    static class Request {
        final boolean auth, valid;
        Request(boolean a, boolean v) { auth = a; valid = v; }
    }
    abstract static class Handler {
        protected Handler next;
        Handler setNext(Handler n) { next = n; return this; }
        abstract void handle(Request req);
    }
    static class AuthHandler extends Handler {
        @Override void handle(Request req) {
            System.out.println("[Auth] " + (req.auth ? "OK" : "FAIL"));
            if (req.auth && next != null) next.handle(req);
        }
    }
    static class ValidationHandler extends Handler {
        @Override void handle(Request req) {
            System.out.println("[Validation] " + (req.valid ? "OK" : "FAIL"));
            if (req.valid && next != null) next.handle(req);
        }
    }
    static class ResponseHandler extends Handler {
        @Override void handle(Request req) { System.out.println("[Response] Sent"); }
    }
}
