// OrderService.java — پاسخ کامل پروژهٔ جلسه ۱۲۶
// قابل کامپایل با javac — بدون dependency
import java.util.*;

public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }

    public Order createOrder(Long userId, Long productId, int quantity) {
        Product product = productRepository.findById(productId).orElseThrow();
        if (product.getStock() < quantity) {
            throw new IllegalArgumentException("Insufficient stock");
        }
        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        Order order = new Order(userId, productId, quantity, product.getPrice() * quantity);
        return orderRepository.save(order);
    }

    public List<Order> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }
}
