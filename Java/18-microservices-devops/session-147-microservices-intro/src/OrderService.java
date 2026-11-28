// OrderService.java — Order microservice با Feign client
// نیازمند: spring-cloud-starter-openfeign jar برای کامپایل
// import org.springframework.cloud.openfeign.FeignClient;
// import org.springframework.web.bind.annotation.*;
import java.util.*;

public class OrderService {

    private final Map<Long, Map<String, Object>> orders = new HashMap<>();
    private long nextId = 1;

    public OrderService() {
        orders.put(1L, Map.of("id", 1L, "userId", 1L, "productId", 1L, "quantity", 1));
        nextId = 2;
    }

    // GET /api/orders/{id}
    public Map<String, Object> getOrder(Long id) {
        return orders.getOrDefault(id, Map.of("error", "Order not found"));
    }

    // GET /api/orders/user/{userId}
    public List<Map<String, Object>> getUserOrders(Long userId) {
        return orders.values().stream()
            .filter(o -> ((Long) o.get("userId")).equals(userId))
            .toList();
    }

    // POST /api/orders
    public Map<String, Object> createOrder(Long userId, Long productId, int quantity) {
        Map<String, Object> order = Map.of(
            "id", nextId++,
            "userId", userId,
            "productId", productId,
            "quantity", quantity,
            "status", "PENDING"
        );
        orders.put((Long) order.get("id"), order);
        return order;
    }
}
