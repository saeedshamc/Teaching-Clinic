// ProductService.java — Product microservice
// نیازمند: spring-boot-starter-web jar برای کامپایل
// import org.springframework.web.bind.annotation.*;
import java.util.*;

public class ProductService {

    private final Map<Long, Map<String, Object>> products = new HashMap<>();
    private long nextId = 1;

    public ProductService() {
        products.put(1L, Map.of("id", 1L, "name", "Laptop", "price", 1500.0));
        products.put(2L, Map.of("id", 2L, "name", "Phone", "price", 999.0));
        nextId = 3;
    }

    // GET /api/products/{id}
    public Map<String, Object> getProduct(Long id) {
        return products.getOrDefault(id, Map.of("error", "Product not found"));
    }

    // GET /api/products
    public List<Map<String, Object>> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    // POST /api/products
    public Map<String, Object> createProduct(String name, double price) {
        Map<String, Object> product = Map.of(
            "id", nextId++,
            "name", name,
            "price", price
        );
        products.put((Long) product.get("id"), product);
        return product;
    }
}
