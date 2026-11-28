// BankDao.java — DAO برای banking با transactions
// نیازمند SQLite JDBC driver در runtime
import java.sql.*;

public class BankDao {

    private static final String URL = "jdbc:sqlite:bank.db";

    public void createTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS accounts (
                    id INTEGER PRIMARY KEY,
                    name TEXT NOT NULL,
                    balance REAL NOT NULL
                )
                """);
        }
    }

    public void insertAccount(int id, String name, double balance) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(
                 "INSERT OR REPLACE INTO accounts VALUES (?, ?, ?)")) {
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setDouble(3, balance);
            ps.executeUpdate();
        }
    }

    // ---------- Transfer با transaction ----------
    public boolean transfer(int fromId, int toId, double amount) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try {
                // چک موجودی
                double fromBalance = getBalance(conn, fromId);
                if (fromBalance < amount) {
                    conn.rollback();
                    return false;
                }

                debit(conn, fromId, amount);
                credit(conn, toId, amount);
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    // ---------- Deposit ----------
    public void deposit(int id, double amount) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try {
                credit(conn, id, amount);
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    // ---------- Withdraw ----------
    public boolean withdraw(int id, double amount) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL)) {
            conn.setAutoCommit(false);
            try {
                double balance = getBalance(conn, id);
                if (balance < amount) {
                    conn.rollback();
                    return false;
                }
                debit(conn, id, amount);
                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    // ---------- Helpers ----------
    private double getBalance(Connection conn, int id) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "SELECT balance FROM accounts WHERE id = ?")) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getDouble("balance") : 0;
            }
        }
    }

    private void debit(Connection conn, int id, double amount) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE accounts SET balance = balance - ? WHERE id = ?")) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    private void credit(Connection conn, int id, double amount) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE accounts SET balance = balance + ? WHERE id = ?")) {
            ps.setDouble(1, amount);
            ps.setInt(2, id);
            ps.executeUpdate();
        }
    }

    public void printAllAccounts() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM accounts ORDER BY id")) {
            while (rs.next()) {
                System.out.printf("%d: %s — %.2f%n",
                    rs.getInt("id"), rs.getString("name"), rs.getDouble("balance"));
            }
        }
    }
}
