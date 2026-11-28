// SecureUserDao.java — پاسخ پروژهٔ جلسه ۹۷
// نیازمند SQLite JDBC driver در runtime
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SecureUserDao {

    private static final String URL = "jdbc:sqlite:secure_users.db";

    public void createTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute("""
                CREATE TABLE IF NOT EXISTS users (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    name TEXT NOT NULL,
                    email TEXT UNIQUE,
                    password TEXT NOT NULL
                )
                """);
        }
    }

    // ---------- INSERT با generated key ----------
    public int insert(String name, String email, String password) throws SQLException {
        String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);
            ps.executeUpdate();
            try (ResultSet keys = ps.getGeneratedKeys()) {
                return keys.next() ? keys.getInt(1) : -1;
            }
        }
    }

    // ---------- SELECT با parameterized query ----------
    public RegistrationUser findByEmail(String email) throws SQLException {
        String sql = "SELECT * FROM users WHERE email = ?";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new RegistrationUser(rs.getInt("id"), rs.getString("name"),
                        rs.getString("email"), rs.getString("password"));
                }
            }
        }
        return null;
    }

    public List<RegistrationUser> findAll() throws SQLException {
        List<RegistrationUser> users = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM users ORDER BY id")) {
            while (rs.next()) {
                users.add(new RegistrationUser(rs.getInt("id"), rs.getString("name"),
                    rs.getString("email"), rs.getString("password")));
            }
        }
        return users;
    }

    // ---------- Batch insert ----------
    public int[] batchInsert(List<RegistrationUser> users) throws SQLException {
        String sql = "INSERT INTO users(name, email, password) VALUES (?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            for (RegistrationUser u : users) {
                ps.setString(1, u.getName());
                ps.setString(2, u.getEmail());
                ps.setString(3, u.getPassword());
                ps.addBatch();
            }
            return ps.executeBatch();
        }
    }
}
