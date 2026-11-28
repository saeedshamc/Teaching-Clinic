// ObserverExample.java — Observer pattern demo
// قابل کامپایل با javac — بدون dependency
public class ObserverExample {

    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();
        agency.attach(new ConsoleObserver());
        agency.attach(new EmailObserver());
        agency.attach(new SMSObserver());

        agency.publish("Breaking news: Java 21 released!");
        agency.publish("Weather alert: Storm incoming");
    }

    interface Observer {
        void update(String news);
    }

    static class NewsAgency {
        private final java.util.List<Observer> observers = new java.util.ArrayList<>();

        public void attach(Observer observer) { observers.add(observer); }
        public void detach(Observer observer) { observers.remove(observer); }

        public void publish(String news) {
            System.out.println("\n=== Publishing: " + news + " ===");
            for (Observer observer : observers) {
                observer.update(news);
            }
        }
    }

    static class ConsoleObserver implements Observer {
        @Override public void update(String news) {
            System.out.println("[Console] " + news);
        }
    }

    static class EmailObserver implements Observer {
        @Override public void update(String news) {
            System.out.println("[Email] Sending: " + news);
        }
    }

    static class SMSObserver implements Observer {
        @Override public void update(String news) {
            System.out.println("[SMS] Alert: " + news);
        }
    }
}
