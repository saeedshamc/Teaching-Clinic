# پروژهٔ جلسه ۱۰۱ — سیستم مدیریت سفارشات با Hibernate

یک سیستم مدیریت سفارشات بسازید که:
- `User` و `Order` entities با `@OneToMany`/`@ManyToOne`
- `HibernateConfig` برای SessionFactory
- `OrderDao` با `save`, `find`, `update`, `delete`, `findByUser`
- `OrderManager` با main برای demo
- HQL queries برای filter orders
- Lazy loading برای orders

نمونه:
```
=== Order Manager (Hibernate) ===
User: Ali with 3 orders
Order: Laptop — 1200.00
Order: Mouse — 25.99
Order: Keyboard — 75.00
Total: 1300.99
```

## صورت پروژه
پاسخ در `solution/` — OrderUser.java + OrderItem.java + OrderDao.java.
