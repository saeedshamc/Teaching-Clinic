package com.store.controller;

import com.store.entity.Order;
import com.store.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> create(Authentication auth,
            @RequestBody Map<String, List<Map<String, Object>>> body) {
        List<Map<String, Object>> items = body.get("items");
        if (items == null || items.isEmpty())
            return ResponseEntity.badRequest().body(Map.of("error", "items الزامی است"));
        Order order = orderService.createOrder(auth.getName(), items);
        return ResponseEntity.status(201).body(Map.of(
            "orderId", order.getId(),
            "total", order.getTotalAmount(),
            "status", order.getStatus().name()));
    }

    @GetMapping("/my")
    public ResponseEntity<?> myOrders(Authentication auth) {
        return ResponseEntity.ok(orderService.myOrders(auth.getName()));
    }

    @GetMapping
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Order>> all() {
        return ResponseEntity.ok(orderService.allOrders());
    }

    @PutMapping("/{id}/status")
    @org.springframework.security.access.prepost.PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateStatus(@PathVariable Long id,
            @RequestBody Map<String, String> body) {
        Order.Status status = Order.Status.valueOf(body.get("status"));
        return ResponseEntity.ok(orderService.updateStatus(id, status));
    }
}
