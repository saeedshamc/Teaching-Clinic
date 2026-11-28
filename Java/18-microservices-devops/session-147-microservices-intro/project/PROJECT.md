# پروژهٔ جلسه ۱۴۷ — Microservices System با Spring Cloud

یک microservices system بسازید:
- `UserService` با `getUser`, `getAllUsers`, `createUser`
- `ProductService` با `getProduct`, `getAllProducts`, `createProduct`
- `OrderService` با `getOrder`, `getUserOrders`, `createOrder`
- `GatewayConfig` با `RouteLocator` برای API Gateway
- `@FeignClient` برای service-to-service communication
- `Circuit Breaker` با `fallback` برای resilience

نمونه:
```
GET /api/users/1 → {id: 1, username: "admin"}
GET /api/products/1 → {id: 1, name: "Laptop", price: 1500.0}
GET /api/orders/user/1 → [{id: 1, userId: 1, productId: 1}]
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
