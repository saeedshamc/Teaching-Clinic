// OrderIntegrationTest.java — پاسخ کامل پروژهٔ جلسه ۱۲۸
// نیازمند: spring-boot-starter-test jar برای کامپایل
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {
        orderRepository.deleteAll();
        productRepository.deleteAll();
    }

    @Test
    void testCreateOrder() {
        // Create product first
        Product product = new Product("Laptop", 1200.0, 10);
        restTemplate.postForEntity("/api/products", product, Product.class);

        // Create order
        Order order = new Order(1L, 1L, 2, 2400.0);
        ResponseEntity<Order> response = restTemplate.postForEntity(
            "/api/orders", order, Order.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetUserOrders() {
        // Create product and order
        Product product = new Product("Mouse", 25.0, 50);
        restTemplate.postForEntity("/api/products", product, Product.class);

        Order order = new Order(1L, 1L, 5, 125.0);
        restTemplate.postForEntity("/api/orders", order, Order.class);

        // Get user orders
        ResponseEntity<Order[]> response = restTemplate.getForEntity(
            "/api/orders/user/1", Order[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().length);
    }
}
