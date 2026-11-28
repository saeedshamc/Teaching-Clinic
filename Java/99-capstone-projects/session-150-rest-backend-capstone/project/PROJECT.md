# پروژهٔ جلسه ۱۵۰ — سیستم فروشگاه کامل با Spring Boot

یک سیستم فروشگاه کامل با Spring Boot بسازید:
- [ ] `Product` entity با JPA + validation
- [ ] `User` entity با roles
- [ ] `Order` entity با transaction
- [ ] `ProductRepository`, `UserRepository`, `OrderRepository`
- [ ] `ProductService`, `UserService`, `OrderService`
- [ ] `ProductController`, `UserController`, `OrderController`
- [ ] `JwtUtil` برای authentication
- [ ] `WebSocketConfig` برای notifications
- [ ] `SecurityConfig` برای authorization
- [ ] `LoggingAspect` برای AOP logging
- [ ] `pom.xml` با همهٔ dependencies

نمونه:
```
GET /api/products → [products]
GET /api/products/category/electronics → [electronics]
POST /api/orders → create order
GET /api/orders/user/1 → user's orders
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
