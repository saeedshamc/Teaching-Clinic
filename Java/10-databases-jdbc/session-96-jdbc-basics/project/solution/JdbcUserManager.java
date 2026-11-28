// JdbcUserManager.java — پاسخ کامل پروژهٔ جلسه ۹۶
// نیازمند SQLite JDBC driver در runtime
import java.util.List;
import java.util.Scanner;

public class JdbcUserManager {

    private static final UserDao dao = new UserDao();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        dao.createTable();
        System.out.println("=== User Manager ===");

        while (true) {
            printMenu();
            int choice = readInt("Choice: ");
            switch (choice) {
                case 1 -> addUser();
                case 2 -> listUsers();
                case 3 -> findUser();
                case 4 -> updateUser();
                case 5 -> deleteUser();
                case 0 -> { System.out.println("Bye!"); return; }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
            1. Add User
            2. List Users
            3. Find by ID
            4. Update User
            5. Delete User
            0. Exit""");
    }

    private static void addUser() {
        String name = readString("Name: ");
        String email = readString("Email: ");
        int age = readInt("Age: ");
        int id = dao.insert(new User(0, name, email, age));
        System.out.println("Added user with id=" + id);
    }

    private static void listUsers() {
        List<User> users = dao.findAll();
        if (users.isEmpty()) {
            System.out.println("No users.");
            return;
        }
        users.forEach(System.out::println);
    }

    private static void findUser() {
        int id = readInt("User ID: ");
        User user = dao.findById(id);
        System.out.println(user != null ? user : "User not found.");
    }

    private static void updateUser() {
        int id = readInt("User ID: ");
        User existing = dao.findById(id);
        if (existing == null) { System.out.println("User not found."); return; }
        String name = readString("New name (empty=keep): ");
        String email = readString("New email (empty=keep): ");
        String ageStr = readString("New age (empty=keep): ");
        if (!name.isEmpty()) existing.setName(name);
        if (!email.isEmpty()) existing.setEmail(email);
        if (!ageStr.isEmpty()) existing.setAge(Integer.parseInt(ageStr));
        dao.update(existing);
        System.out.println("Updated.");
    }

    private static void deleteUser() {
        int id = readInt("User ID: ");
        int rows = dao.delete(id);
        System.out.println("Deleted " + rows + " user(s).");
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
