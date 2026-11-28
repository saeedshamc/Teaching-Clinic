# پاسخ تمرین‌های REST Store Backend

## آسان

**۲. ثبت‌نام:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"ali","email":"ali@mail.com","password":"123456"}'
```

**۳. ورود:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"ali","password":"123456"}'
# → {"token":"eyJ...", ...}

curl http://localhost:8080/api/products \
  -H "Authorization: Bearer eyJ..."
```

## متوسط

**۴. سفارش:**
```bash
curl -X POST http://localhost:8080/api/orders \
  -H "Authorization: Bearer eyJ..." \
  -H "Content-Type: application/json" \
  -d '{"items":[{"productId":1,"quantity":2}]}'
```
موجودی در `OrderService.createOrder` با `product.setStock(stock - qty)` کم می‌شود
و `@Transactional` تضمین می‌کند یا همه یا هیچ.

**۵ و ۶.** `/api/orders/my` فقط سفارش‌های همان کاربر را برمی‌گرداند چون
`myOrders` با `userId` فیلتر می‌کند.

## سخت

**۷.** در `UserService.register` قبل از save:
```java
if (username.equals("admin")) user.setRole(User.Role.ADMIN);
```
سپس `/api/products` POST با ADMIN کار می‌کند (به‌خاطر `@PreAuthorize`).

**۸.**
```bash
curl -X PUT http://localhost:8080/api/orders/1/status \
  -H "Authorization: Bearer <admin-token>" \
  -H "Content-Type: application/json" \
  -d '{"status":"PAID"}'
```

**۹.** در یک `AdminController` جدید:
```java
@GetMapping("/api/admin/stats")
@PreAuthorize("hasRole('ADMIN')")
public Map<String,Object> stats() {
    List<Order> orders = orderService.allOrders();
    BigDecimal revenue = orders.stream()
        .filter(o -> o.getStatus() == Order.Status.PAID || o.getStatus() == Order.Status.DELIVERED)
        .map(Order::getTotalAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
    return Map.of("orders", orders.size(), "revenue", revenue);
}
```
