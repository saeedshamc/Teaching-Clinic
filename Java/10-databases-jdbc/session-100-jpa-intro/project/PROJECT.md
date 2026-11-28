# پروژهٔ جلسه ۱۰۰ — سیستم مدیریت محصولات با JPA

یک سیستم مدیریت محصولات بسازید که:
- `Product` entity با id, name, price, category
- `persistence.xml` برای JPA configuration
- `ProductDao` با `persist`, `find`, `merge`, `remove`, `createQuery`
- `ProductManager` با main برای demo
- JPQL queries برای filter by category/price

نمونه:
```
=== Product Manager (JPA) ===
[Add] Product{id=1, name='Laptop', price=1200}
[Find] Product{id=1, name='Laptop'}
[Query] 3 products found
[Update] Price updated to 1100
[Delete] Product removed
```

## صورت پروژه
پاسخ در `solution/` — Product.java + ProductDao.java + ProductManager.java.
