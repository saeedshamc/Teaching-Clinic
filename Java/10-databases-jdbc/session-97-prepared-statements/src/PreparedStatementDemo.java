// PreparedStatementDemo.java — مقایسهٔ Statement vs PreparedStatement
// نیازمند SQLite JDBC driver در runtime برای اجرا
// با javac کامپایل می‌شود — java.sql بخشی از JDK است
import java.sql.*;

public class PreparedStatementDemo {

    private static final String URL = "jdbc:sqlite:ps_demo.db";

    public static void main(String[] args) {
        setup();
        demoSqlInjection();
        demoPreparedStatement();
        demoBatch();
        demoGeneratedKeys();
    }

    // ---------- Setup ----------
    static void setup() {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS users");
            stmt.execute("""
                CREATE TABLE users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    email TEXT
                )
                """);
            System.out.println("Table ready.");
        } catch (SQLException e) {
            System.out.println("setup error: " + e.getMessage());
        }
    }

    // ---------- SQL Injection Demo ----------
    static void demoSqlInjection() {
        System.out.println("\n--- SQL Injection demo ---");
        // ورودی مخرب — تلاش برای حذف جدول
        String maliciousName = "Ali' OR '1'='1";

        // با Statement — خطرناک
        String dangerousSql = "SELECT * FROM users WHERE name = '" + maliciousName + "'";
        System.out.println("Dangerous SQL: " + dangerousSql);

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(dangerousSql)) {
            int count = 0;
            while (rs.next()) count++;
            System.out.println("Rows returned: " + count + " (OR '1'='1 همیشه true است!)");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // با PreparedStatement — امن
        String safeSql = "SELECT * FROM users WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(safeSql)) {
            ps.setString(1, maliciousName);
            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;
                while (rs.next()) count++;
                System.out.println("Safe query rows: " + count + " (مقدار به‌عنوان literal تفسیر شد)");
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- PreparedStatement — همهٔ انواع ----------
    static void demoPreparedStatement() {
        System.out.println("\n--- PreparedStatement types ---");
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "Sara");
            ps.setString(2, "sara@mail.com");
            System.out.println("Inserted: " + ps.executeUpdate() + " row(s)");

            ps.setString(1, "Reza");
            ps.setNull(2, Types.VARCHAR);   // email = NULL
            System.out.println("Inserted with NULL: " + ps.executeUpdate() + " row(s)");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- Batch Update ----------
    static void demoBatch() {
        System.out.println("\n--- Batch update ---");
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String[][] data = {
                {"User1", "u1@mail.com"},
                {"User2", "u2@mail.com"},
                {"User3", "u3@mail.com"},
                {"User4", "u4@mail.com"},
                {"User5", "u5@mail.com"}
            };
            for (String[] row : data) {
                ps.setString(1, row[0]);
                ps.setString(2, row[1]);
                ps.addBatch();
            }
            int[] results = ps.executeBatch();
            System.out.println("Batch inserted " + results.length + " rows.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // ---------- Generated Keys ----------
    static void demoGeneratedKeys() {
        System.out.println("\n--- Generated keys ---");
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, "KeyUser");
            ps.setString(2, "key@mail.com");
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    System.out.println("New user id = " + keys.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

        // نمایش همه
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT id, name FROM users ORDER BY id")) {
            System.out.println("\nAll users:");
            while (rs.next()) {
                System.out.println("  " + rs.getInt("id") + " — " + rs.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
