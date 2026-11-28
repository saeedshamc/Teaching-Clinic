// TransactionDemo.java — account transfer با transactions
// نیازمند SQLite JDBC driver در runtime برای اجرا
// با javac کامپایل می‌شود — java.sql بخشی از JDK است
import java.sql.*;

public class TransactionDemo {

    private static final String URL = "jdbc:sqlite:tx_demo.db";

    public static void main(String[] args) {
        setup();
        demoSuccessfulTransfer();
        demoFailedTransfer();
        demoSavepoint();
        demoIsolationLevels();
    }

    // ---------- Setup ----------
    static void setup() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS accounts");
            stmt.execute("""
                CREATE TABLE accounts (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    balance REAL NOT NULL
                )
                """);
            stmt.execute("INSERT INTO accounts VALUES (1, 'Ali', 1000)");
            stmt.execute("INSERT INTO accounts VALUES (2, 'Sara', 500)");
            stmt.execute("INSERT INTO accounts VALUES (3, 'Reza', 300)");
            System.out.println("Accounts created.");
        } catch (SQLException e) {
            System.out.println("setup error: " + e.getMessage());
        }
    }

    // ---------- Successful Transfer ----------
    static void demoSuccessfulTransfer() {
        System.out.println("\n--- Successful transfer: Ali → Sara 200 ---");
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try {
                debit(conn, 1, 200);   // Ali -200
                credit(conn, 2, 200);  // Sara +200
                conn.commit();
                System.out.println("Transfer committed.");
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transfer failed, rolled back: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        printBalances();
    }

    // ---------- Failed Transfer (insufficient funds) ----------
    static void demoFailedTransfer() {
        System.out.println("\n--- Failed transfer: Reza → Ali 9999 (insufficient) ---");
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try {
                // چک موجودی
                double balance = getBalance(conn, 3);  // Reza
                if (balance < 9999) {
                    throw new SQLException("Insufficient funds: " + balance);
                }
                debit(conn, 3, 9999);
                credit(conn, 1, 9999);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                System.out.println("Transfer rolled back: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        printBalances();
    }

    // ---------- Savepoint Demo ----------
    static void demoSavepoint() {
        System.out.println("\n--- Savepoint demo ---");
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            Savepoint sp = conn.setSavepoint("before_reza_ops");

            try {
                // عملیات موفق
                credit(conn, 3, 50);   // Reza +50

                // عملیات ناموفق
                debit(conn, 2, 99999);  // Sara -99999 → fail

                conn.commit();
            } catch (SQLException e) {
                // فقط تا savepoint برگردان — Reza +50 حفظ می‌شود
                conn.rollback(sp);
                System.out.println("Rolled back to savepoint: " + e.getMessage());

                // حالا commit برای تغییرات تا savepoint
                conn.commit();
            }
        } catch (SQLException e) {
            System.out.println("Connection error: " + e.getMessage());
        }
        printBalances();
    }

    // ---------- Isolation Levels ----------
    static void demoIsolationLevels() {
        System.out.println("\n--- Isolation levels ---");
        int[] levels = {
            Connection.TRANSACTION_READ_UNCOMMITTED,
            Connection.TRANSACTION_READ_COMMITTED,
            Connection.TRANSACTION_REPEATABLE_READ,
            Connection.TRANSACTION_SERIALIZABLE
        };
        String[] names = {"READ_UNCOMMITTED", "READ_COMMITTED", "REPEATABLE_READ", "SERIALIZABLE"};

        try (Connection conn = DriverManager.getConnection(URL)) {
            for (int i = 0; i < levels.length; i++) {
                conn.setTransactionIsolation(levels[i]);
                System.out.println(names[i] + " = " + conn.getTransactionIsolation());
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- Helpers ----------
    static void debit(Connection conn, int id, double amount) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE accounts SET balance = balance - ? WHERE id = ?")) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    static void credit(Connection conn, int id, double amount) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE id = ?")) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    static double getBalance(Connection conn, int id) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT balance FROM accounts WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble("balance") : 0;
            }
        }
    }

    static void printBalances() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name, balance FROM accounts ORDER BY id")) {
            System.out.println("\nCurrent balances:");
            while (rs.next()) {
                System.out.printf("  %s: %.2f%n", rs.getString("name"), rs.getDouble("balance"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
