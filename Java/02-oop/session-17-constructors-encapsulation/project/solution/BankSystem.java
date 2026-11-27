// BankSystem.java — پاسخ پروژهٔ جلسه ۱۷
public class BankSystem {

    static class BankAccount {
        private final int accountNumber;
        private final String ownerName;
        private double balance;

        BankAccount(int accountNumber, String ownerName, double initialBalance) {
            this.accountNumber = accountNumber;
            this.ownerName = ownerName;
            this.balance = initialBalance;
        }

        public int getAccountNumber() { return accountNumber; }
        public String getOwnerName() { return ownerName; }
        public double getBalance() { return balance; }

        public void deposit(double amount) {
            if (amount <= 0) throw new IllegalArgumentException("Amount must be positive");
            balance += amount;
        }

        public boolean withdraw(double amount) {
            if (amount <= 0 || amount > balance) return false;
            balance -= amount;
            return true;
        }

        public boolean transfer(BankAccount target, double amount) {
            if (!withdraw(amount)) return false;
            target.deposit(amount);
            return true;
        }
    }

    public static void main(String[] args) {
        BankAccount acc1 = new BankAccount(1001, "Ali", 1000);
        BankAccount acc2 = new BankAccount(1002, "Sara", 500);

        System.out.printf("Account %d (%s): %.2f%n", acc1.getAccountNumber(), acc1.getOwnerName(), acc1.getBalance());
        System.out.printf("Account %d (%s): %.2f%n", acc2.getAccountNumber(), acc2.getOwnerName(), acc2.getBalance());

        acc1.transfer(acc2, 200);

        System.out.printf("After transfer: %d=%.2f, %d=%.2f%n",
                acc1.getAccountNumber(), acc1.getBalance(),
                acc2.getAccountNumber(), acc2.getBalance());
    }
}
