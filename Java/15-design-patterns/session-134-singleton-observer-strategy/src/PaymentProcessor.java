// PaymentProcessor.java — Strategy pattern برای payments
// قابل کامپایل با javac — بدون dependency
public class PaymentProcessor {

    public static void main(String[] args) {
        CheckoutProcessor processor = new CheckoutProcessor();

        // Credit card
        processor.setGateway(new CreditCardGateway());
        processor.checkout(100.0, "4111111111111111");

        // PayPal
        processor.setGateway(new PayPalGateway());
        processor.checkout(50.0, "user@paypal.com");

        // Bank transfer
        processor.setGateway(new BankTransferGateway());
        processor.checkout(200.0, "IBAN123");
    }

    interface PaymentGateway {
        boolean processPayment(double amount, String details);
    }

    static class CreditCardGateway implements PaymentGateway {
        @Override public boolean processPayment(double amount, String cardNumber) {
            System.out.println("[CreditCard] Processing $" + amount + " with card " +
                cardNumber.substring(0, 4) + "****");
            return true;
        }
    }

    static class PayPalGateway implements PaymentGateway {
        @Override public boolean processPayment(double amount, String email) {
            System.out.println("[PayPal] Processing $" + amount + " for " + email);
            return true;
        }
    }

    static class BankTransferGateway implements PaymentGateway {
        @Override public boolean processPayment(double amount, String iban) {
            System.out.println("[BankTransfer] Processing $" + amount + " to IBAN " + iban);
            return true;
        }
    }

    static class CheckoutProcessor {
        private PaymentGateway gateway;

        public void setGateway(PaymentGateway gateway) {
            this.gateway = gateway;
        }

        public boolean checkout(double amount, String details) {
            if (gateway == null) {
                throw new IllegalStateException("No payment gateway set");
            }
            return gateway.processPayment(amount, details);
        }
    }
}
