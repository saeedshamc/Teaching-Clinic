// PatternApplications.java — پاسخ کامل پروژهٔ جلسه ۱۳۴
// Singleton + Observer + Strategy real-world examples
public class PatternApplications {

    public static void main(String[] args) {
        // Singleton
        ConfigManager config = ConfigManager.getInstance();
        config.set("app.name", "MyApp");
        System.out.println("Config: " + config.get("app.name"));

        // Observer
        User user = new User("Ali", "ali@mail.com");
        user.addPropertyChangeListener(evt ->
            System.out.println("[Listener] " + evt.getPropertyName() + " changed"));
        user.setName("Sara");

        // Strategy — Payment
        PaymentProcessor processor = new PaymentProcessor();
        processor.setGateway(new CreditCardGateway());
        processor.checkout(100.0, "4111****");
        processor.setGateway(new PayPalGateway());
        processor.checkout(50.0, "user@paypal.com");

        // Strategy — Sort
        Sorter sorter = new Sorter();
        sorter.setStrategy(new BubbleSort());
        java.util.List<Integer> list = new java.util.ArrayList<>(java.util.List.of(5, 2, 8, 1));
        sorter.sort(list);
        System.out.println("Sorted: " + list);
    }

    // ---------- Singleton ----------
    static class ConfigManager {
        private static class Holder { static final ConfigManager INSTANCE = new ConfigManager(); }
        private final java.util.Map<String, String> config = new java.util.HashMap<>();
        private ConfigManager() {}
        public static ConfigManager getInstance() { return Holder.INSTANCE; }
        public void set(String k, String v) { config.put(k, v); }
        public String get(String k) { return config.get(k); }
    }

    // ---------- Observer ----------
    static class User {
        private final java.beans.PropertyChangeSupport pcs = new java.beans.PropertyChangeSupport(this);
        private String name;
        User(String name, String email) { this.name = name; }
        public void addPropertyChangeListener(java.beans.PropertyChangeListener l) {
            pcs.addPropertyChangeListener(l);
        }
        public void setName(String name) {
            String old = this.name;
            this.name = name;
            pcs.firePropertyChange("name", old, name);
        }
    }

    // ---------- Strategy — Payment ----------
    interface PaymentGateway { boolean processPayment(double amount, String details); }
    static class CreditCardGateway implements PaymentGateway {
        @Override public boolean processPayment(double amount, String card) {
            System.out.println("[CreditCard] $" + amount);
            return true;
        }
    }
    static class PayPalGateway implements PaymentGateway {
        @Override public boolean processPayment(double amount, String email) {
            System.out.println("[PayPal] $" + amount);
            return true;
        }
    }
    static class PaymentProcessor {
        private PaymentGateway gateway;
        void setGateway(PaymentGateway g) { gateway = g; }
        boolean checkout(double amount, String details) { return gateway.processPayment(amount, details); }
    }

    // ---------- Strategy — Sort ----------
    interface SortStrategy { void sort(java.util.List<Integer> list); }
    static class BubbleSort implements SortStrategy {
        @Override public void sort(java.util.List<Integer> list) {
            for (int i = 0; i < list.size() - 1; i++)
                for (int j = 0; j < list.size() - i - 1; j++)
                    if (list.get(j) > list.get(j + 1)) java.util.Collections.swap(list, j, j + 1);
        }
    }
    static class Sorter {
        private SortStrategy strategy;
        void setStrategy(SortStrategy s) { strategy = s; }
        void sort(java.util.List<Integer> list) { strategy.sort(list); }
    }
}
