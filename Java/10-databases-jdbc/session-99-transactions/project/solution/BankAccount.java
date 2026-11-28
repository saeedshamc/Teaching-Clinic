// BankAccount.java — model برای account
// قابل کامپایل با javac — بدون dependency خارجی
public class BankAccount {
    private int id;
    private String name;
    private double balance;

    public BankAccount() {}
    public BankAccount(int id, String name, double balance) {
        this.id = id; this.name = name; this.balance = balance;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; }

    @Override
    public String toString() {
        return String.format("%s: %.2f", name, balance);
    }
}
