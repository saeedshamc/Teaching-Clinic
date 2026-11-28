// JdbcBasics.java — JDBC CRUD کامل
// پیش‌نیاز: SQLite JDBC driver در classpath (sqlite-jdbc-x.x.x.jar)
// اجرا: java -cp .;sqlite-jdbc.jar JdbcBasics
//
// این کد با javac کامپایل می‌شود چون java.sql بخشی از JDK است.
// برای اجرا به driver دیتابیس نیاز است.
import java.sql.*;

public class JdbcBasics {

    // ---------- اتصال ----------
    // برای تست: SQLite فایلی — نیازی به سرور نیست
    private static final String URL = "jdbc:sqlite:test.db";
    // برای MySQL: "jdbc:mysql://localhost:3306/mydb?useSSL=false"
    // برای PostgreSQL: "jdbc:postgresql://localhost:5432/mydb"

    public static void main(String[] args) {
        // ---------- Driver registration (JDBC 4+: خودکار) ----------
        // Class.forName("org.sqlite.JDBC"); // فقط برای JDBC قدیمی لازم است

        // ---------- CREATE ----------
        createTable();

        // ---------- INSERT ----------
        insertUser("Ali", "ali@mail.com", 25);
        insertUser("Sara", "sara@mail.com", 30);

        // ---------- SELECT ----------
        selectAllUsers();
        selectUserById(1);

        // ---------- UPDATE ----------
        updateUserAge(1, 26);

        // ---------- DELETE ----------
        deleteUser(2);

        // ---------- نتیجهٔ نهایی ----------
        System.out.println("\n--- Final users ---");
        selectAllUsers();
    }

    // ---------- CREATE TABLE ----------
    static void createTable() {
        String sql = """
            CREATE TABLE IF NOT EXISTS users (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL,
                email TEXT,
                age INTEGER
            )
            """;
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'users' created/exists.");
        } catch (SQLException e) {
            System.out.println("createTable error: " + e.getMessage());
        }
    }

    // ---------- INSERT ----------
    static void insertUser(String name, String email, int age) {
        String sql = "INSERT INTO users(name, email, age) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setInt(3, age);
            int rows = ps.executeUpdate();
            System.out.println("Inserted " + rows + " user: " + name);
        } catch (SQLException e) {
            System.out.println("insertUser error: " + e.getMessage());
        }
    }

    // ---------- SELECT ALL ----------
    static void selectAllUsers() {
        String sql = "SELECT id, name, email, age FROM users ORDER BY id";
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- All users ---");
            while (rs.next()) {
                System.out.printf("id=%d name=%s email=%s age=%d%n",
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("email"),
                    rs.getInt("age"));
            }
        } catch (SQLException e) {
            System.out.println("selectAllUsers error: " + e.getMessage());
        }
    }

    // ---------- SELECT BY ID ----------
    static void selectUserById(int id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    System.out.printf("Found: id=%d name=%s%n",
                        rs.getInt("id"), rs.getString("name"));
                } else {
                    System.out.println("User id=" + id + " not found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("selectUserById error: " + e.getMessage());
        }
    }

    // ---------- UPDATE ----------
    static void updateUserAge(int id, int newAge) {
        String sql = "UPDATE users SET age = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, newAge);
            ps.setInt(2, id);
            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " user(s).");
        } catch (SQLException e) {
            System.out.println("updateUserAge error: " + e.getMessage());
        }
    }

    // ---------- DELETE ----------
    static void deleteUser(int id) {
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            System.out.println("Deleted " + rows + " user(s).");
        } catch (SQLException e) {
            System.out.println("deleteUser error: " + e.getMessage());
        }
    }
}
