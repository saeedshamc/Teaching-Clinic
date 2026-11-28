// RegistrationManager.java — پاسخ کامل پروژهٔ جلسه ۹۷
// نیازمند SQLite JDBC driver در runtime
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegistrationManager {

    private static final SecureUserDao dao = new SecureUserDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {
        dao.createTable();
        System.out.println("=== Registration System (Secure) ===");

        while (true) {
            System.out.println("""
                1. Register User
                2. Login (find by email)
                3. List All Users
                4. Batch Import 100 Users
                5. SQL Injection Test
                0. Exit""");
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> listUsers();
                case 4 -> batchImport();
                case 5 -> sqlInjectionTest();
                case 0 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Invalid.");
            }
        }
    }

    private static void register() throws SQLException {
        String name = readString("Name: ");
        String email = readString("Email: ");
        String password = readString("Password: ");
        try {
            int id = dao.insert(name, email, password);
            System.out.println("Registered! id=" + id);
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void login() throws SQLException {
        String email = readString("Email: ");
        RegistrationUser user = dao.findByEmail(email);
        if (user != null) {
            System.out.println("Welcome, " + user.getName() + "!");
        } else {
            System.out.println("User not found.");
        }
    }

    private static void listUsers() throws SQLException {
        dao.findAll().forEach(u -> System.out.println(u.getName() + " — " + u.getEmail()));
    }

    private static void batchImport() throws SQLException {
        List<RegistrationUser> users = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            users.add(new RegistrationUser(0, "BatchUser" + i, "batch" + i + "@mail.com", "pass" + i));
        }
        int[] results = dao.batchInsert(users);
        System.out.println("Batch inserted " + results.length + " users.");
    }

    private static void sqlInjectionTest() throws SQLException {
        // تلاش برای SQL Injection — باید امن باشد
        String malicious = "' OR '1'='1";
        RegistrationUser user = dao.findByEmail(malicious);
        System.out.println(user != null ? "INJECTION SUCCESS!" : "Injection blocked — safe!");
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
}
