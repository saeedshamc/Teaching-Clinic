// OrderServiceTest.java — پاسخ کامل پروژهٔ جلسه ۱۲۶
// نیازمند: junit-jupiter + mockito-core jars برای کامپایل
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock OrderRepository orderRepository;
    @Mock ProductRepository productRepository;
    @InjectMocks OrderService orderService;

    @Test
    void testCreateOrder() {
        Product product = new Product(1L, "Laptop", 1200.0, 10);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(orderRepository.save(any(Order.class))).thenAnswer(inv -> inv.getArgument(0));

        Order order = orderService.createOrder(1L, 1L, 2);

        assertNotNull(order);
        assertEquals(2, order.getQuantity());
        assertEquals(8, product.getStock());   // stock reduced
        verify(productRepository).save(product);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void testCreateOrderInsufficientStock() {
        Product product = new Product(1L, "Laptop", 1200.0, 1);
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        assertThrows(IllegalArgumentException.class,
            () -> orderService.createOrder(1L, 1L, 5));
    }

    @Test
    void testGetUserOrders() {
        List<Order> orders = List.of(new Order(1L, 1L, 2, 2400.0));
        when(orderRepository.findByUserId(1L)).thenReturn(orders);

        List<Order> result = orderService.getUserOrders(1L);

        assertEquals(1, result.size());
        verify(orderRepository).findByUserId(1L);
    }
}
