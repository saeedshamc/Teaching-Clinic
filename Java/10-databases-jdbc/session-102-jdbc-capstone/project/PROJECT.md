# پروژهٔ جلسه ۱۰۲ — سیستم مدیریت انبار کامل

یک سیستم مدیریت انبار کامل بسازید که:
- [ ] `products` و `orders` tables
- [ ] `addProduct`, `listProducts`, `updateProduct`, `deleteProduct`
- [ ] `createOrder` با transaction (check stock → update → insert)
- [ ] `listOrders`, `cancelOrder` با restore stock
- [ ] `restock` برای update stock
- [ ] `try-with-resources` برای همهٔ operations
- [ ] `PreparedStatement` برای parameterized queries

نمونه:
```
=== Warehouse System ===
[Add] SKU001 — Laptop — 1200.50 — stock:10
[Order] 2× Laptop → SUCCESS (stock:8)
[Order] 100× Laptop → FAILED (insufficient)
[Restock] Laptop +20 → stock:28
[Cancel] Order#1 → stock restored
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
