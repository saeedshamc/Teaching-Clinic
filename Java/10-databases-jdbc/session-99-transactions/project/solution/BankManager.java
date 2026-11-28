// BankManager.java — پاسخ کامل پروژهٔ جلسه ۹۹
// نیازمند SQLite JDBC driver در runtime
import java.sql.SQLException;
import java.util.Scanner;

public class BankManager {

    private static final BankDao dao = new BankDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        dao.createTable();
        dao.insertAccount(1, "Ali", 1000);
        dao.insertAccount(2, "Sara", 500);
        dao.insertAccount(3, "Reza", 300);

        System.out.println("=== Bank System ===");
        while (true) {
            System.out.println("""
                1. Transfer
                2. Deposit
                3. Withdraw
                4. List Accounts
                5. Add Account
                0. Exit""");
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1 -> transfer();
                case 2 -> deposit();
                case 3 -> withdraw();
                case 4 -> listAccounts();
                case 5 -> addAccount();
                case 0 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private static void transfer() throws SQLException {
        int from = readInt("From ID: ");
        int to = readInt("To ID: ");
        double amount = readDouble("Amount: ");
        boolean ok = dao.transfer(from, to, amount);
        System.out.println(ok ? "Transfer successful!" : "Transfer failed — insufficient funds.");
    }

    private static void deposit() throws SQLException {
        int id = readInt("Account ID: ");
        double amount = readDouble("Amount: ");
        dao.deposit(id, amount);
        System.out.println("Deposited.");
    }

    private static void withdraw() throws SQLException {
        int id = readInt("Account ID: ");
        double amount = readDouble("Amount: ");
        boolean ok = dao.withdraw(id, amount);
        System.out.println(ok ? "Withdrawn." : "Insufficient funds.");
    }

    private static void listAccounts() throws SQLException {
        dao.printAllAccounts();
    }

    private static void addAccount() throws SQLException {
        int id = readInt("ID: ");
        String name = readString("Name: ");
        double balance = readDouble("Balance: ");
        dao.insertAccount(id, name, balance);
        System.out.println("Account added.");
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Integer.parseInt(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a number."); }
        }
    }

    private static double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            try { return Double.parseDouble(scanner.nextLine().trim()); }
            catch (NumberFormatException e) { System.out.println("Enter a number."); }
        }
    }
}
