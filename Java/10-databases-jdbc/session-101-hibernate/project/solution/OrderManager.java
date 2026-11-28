// OrderManager.java — پاسخ کامل پروژهٔ جلسه ۱۰۱
// نیازمند: hibernate-core + h2 database jars برای اجرا
import java.math.BigDecimal;

public class OrderManager {

    public static void main(String[] args) {
        OrderDao dao = new OrderDao();

        try {
            System.out.println("=== Order Manager (Hibernate) ===");

            // ---------- Create user with orders ----------
            OrderUser user = new OrderUser("Ali", "ali@mail.com");
            user.addOrder(new OrderItem("Laptop", new BigDecimal("1200")));
            user.addOrder(new OrderItem("Mouse", new BigDecimal("25.99")));
            user.addOrder(new OrderItem("Keyboard", new BigDecimal("75")));

            dao.saveUserWithOrders(user);
            System.out.println("User saved with " + user.getOrders().size() + " orders");

            // ---------- Query ----------
            Long userId = user.getId();
            OrderUser found = dao.findUserById(userId);
            System.out.println("Found user: " + found.getName());

            // Lazy loading — باید در session باز باشد
            System.out.println("Orders:");
            found.getOrders().forEach(o -> System.out.println("  " + o));

            // ---------- HQL query ----------
            var orders = dao.findOrdersByUser(userId);
            System.out.println("Orders by user: " + orders.size());

            var expensive = dao.findExpensiveOrders(new BigDecimal("100"));
            System.out.println("Expensive orders (>100): " + expensive.size());

            // ---------- Cleanup ----------
            dao.deleteUser(userId);
            System.out.println("User deleted.");

        } finally {
            dao.close();
        }
    }
}
