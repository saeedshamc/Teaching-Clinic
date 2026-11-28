package com.store.service;

import com.store.entity.*;
import com.store.repository.*;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final UserService userService;
    private final SimpMessagingTemplate messaging;

    public OrderService(OrderRepository orderRepository,
                        ProductRepository productRepository,
                        UserService userService,
                        SimpMessagingTemplate messaging) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.userService = userService;
        this.messaging = messaging;
    }

    @Transactional
    public Order createOrder(String username, List<Map<String, Object>> items) {
        User user = userService.findByUsername(username);
        Order order = new Order();
        order.setUser(user);

        for (Map<String, Object> item : items) {
            Long productId = ((Number) item.get("productId")).longValue();
            int qty = ((Number) item.get("quantity")).intValue();

            Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("محصول پیدا نشد: " + productId));
            if (product.getStock() < qty)
                throw new IllegalArgumentException("موجودی کافی نیست برای: " + product.getName());

            product.setStock(product.getStock() - qty);   // کسر موجودی
            OrderItem oi = new OrderItem();
            oi.setProduct(product);
            oi.setQuantity(qty);
            order.addItem(oi);
        }

        Order saved = orderRepository.save(order);
        // اطلاع‌رسانی real-time
        messaging.convertAndSend("/topic/orders",
            Map.of("orderId", saved.getId(), "total", saved.getTotalAmount(),
                   "status", saved.getStatus().name()));
        return saved;
    }

    public List<Order> myOrders(String username) {
        return orderRepository.findByUserIdOrderByCreatedAtDesc(
            userService.findByUsername(username).getId());
    }

    public List<Order> allOrders() { return orderRepository.findAll(); }

    @Transactional
    public Order updateStatus(Long orderId, Order.Status status) {
        Order order = orderRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("سفارش پیدا نشد"));
        order.setStatus(status);
        return orderRepository.save(order);
    }
}
