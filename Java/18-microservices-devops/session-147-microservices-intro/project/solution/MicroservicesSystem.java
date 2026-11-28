// MicroservicesSystem.java — پاسخ کامل پروژهٔ جلسه ۱۴۷
// Microservices system demo
import java.util.*;

public class MicroservicesSystem {

    public static void main(String[] args) {
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();

        // User service
        System.out.println("=== User Service ===");
        System.out.println("User 1: " + userService.getUser(1L));
        System.out.println("All users: " + userService.getAllUsers().size());

        // Product service
        System.out.println("\n=== Product Service ===");
        System.out.println("Product 1: " + productService.getProduct(1L));
        System.out.println("All products: " + productService.getAllProducts().size());

        // Order service
        System.out.println("\n=== Order Service ===");
        System.out.println("Order 1: " + orderService.getOrder(1L));
        System.out.println("User 1 orders: " + orderService.getUserOrders(1L).size());

        // API Gateway routing
        System.out.println("\n=== API Gateway ===");
        System.out.println("/api/users/** → user-service:8081");
        System.out.println("/api/products/** → product-service:8082");
        System.out.println("/api/orders/** → order-service:8083");
    }

    static class UserService {
        private final Map<Long, Map<String, Object>> users = new HashMap<>();
        private long nextId = 1;
        UserService() {
            users.put(1L, Map.of("id", 1L, "username", "admin", "email", "admin@mail.com"));
            nextId = 2;
        }
        Map<String, Object> getUser(Long id) { return users.getOrDefault(id, Map.of("error", "Not found")); }
        List<Map<String, Object>> getAllUsers() { return new ArrayList<>(users.values()); }
    }

    static class ProductService {
        private final Map<Long, Map<String, Object>> products = new HashMap<>();
        private long nextId = 1;
        ProductService() {
            products.put(1L, Map.of("id", 1L, "name", "Laptop", "price", 1500.0));
            nextId = 2;
        }
        Map<String, Object> getProduct(Long id) { return products.getOrDefault(id, Map.of("error", "Not found")); }
        List<Map<String, Object>> getAllProducts() { return new ArrayList<>(products.values()); }
    }

    static class OrderService {
        private final Map<Long, Map<String, Object>> orders = new HashMap<>();
        private long nextId = 1;
        OrderService() {
            orders.put(1L, Map.of("id", 1L, "userId", 1L, "productId", 1L, "quantity", 1));
            nextId = 2;
        }
        Map<String, Object> getOrder(Long id) { return orders.getOrDefault(id, Map.of("error", "Not found")); }
        List<Map<String, Object>> getUserOrders(Long userId) {
            return orders.values().stream()
                .filter(o -> ((Long) o.get("userId")).equals(userId))
                .toList();
        }
    }
}
