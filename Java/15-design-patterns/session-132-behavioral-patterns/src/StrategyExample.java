// StrategyExample.java — Strategy pattern demo
// قابل کامپایل با javac — بدون dependency
public class StrategyExample {

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();

        // Pay with credit card
        cart.setPaymentStrategy(new CreditCardPayment());
        cart.checkout(100.0);

        // Pay with PayPal
        cart.setPaymentStrategy(new PayPalPayment());
        cart.checkout(50.0);

        // Pay with Bitcoin
        cart.setPaymentStrategy(new BitcoinPayment());
        cart.checkout(200.0);
    }

    interface PaymentStrategy {
        void pay(double amount);
    }

    static class CreditCardPayment implements PaymentStrategy {
        @Override public void pay(double amount) {
            System.out.println("Paid $" + amount + " with credit card");
        }
    }

    static class PayPalPayment implements PaymentStrategy {
        @Override public void pay(double amount) {
            System.out.println("Paid $" + amount + " with PayPal");
        }
    }

    static class BitcoinPayment implements PaymentStrategy {
        @Override public void pay(double amount) {
            System.out.println("Paid $" + amount + " with Bitcoin");
        }
    }

    static class ShoppingCart {
        private PaymentStrategy paymentStrategy;

        public void setPaymentStrategy(PaymentStrategy strategy) {
            this.paymentStrategy = strategy;
        }

        public void checkout(double amount) {
            if (paymentStrategy == null) {
                throw new IllegalStateException("No payment strategy set");
            }
            paymentStrategy.pay(amount);
        }
    }
}
