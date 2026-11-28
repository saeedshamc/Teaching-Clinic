# جلسه ۱۰۲ — JDBC Capstone: سیستم مدیریت انبار

## اهداف یادگیری
- همهٔ مباحث JDBC را ترکیب کنید
- `Connection`, `PreparedStatement`, `ResultSet` را با هم استفاده کنید
- Transactions برای multi-step operations را پیاده کنید
- `try-with-resources` برای resource management را رعایت کنید

## پیش‌نیازها
- جلسات ۹۶–۱۰۱

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Warehouse Management System

یک سیستم مدیریت انبار بسازید که:

1. **Products** — name, sku, price, stock, category
2. **Orders** — orderId, productId, quantity, totalPrice, status
3. **Operations:**
   - Add/List/Update/Delete products
   - Create order (decrement stock در transaction)
   - List orders
   - Restock product

### Schema

```sql
CREATE TABLE products (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    sku TEXT UNIQUE NOT NULL,
    name TEXT NOT NULL,
    price REAL NOT NULL,
    stock INTEGER DEFAULT 0,
    category TEXT
);

CREATE TABLE orders (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    product_id INTEGER NOT NULL,
    quantity INTEGER NOT NULL,
    total_price REAL NOT NULL,
    status TEXT DEFAULT 'PENDING',
    created_at TEXT DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (product_id) REFERENCES products(id)
);
```

### Transaction برای Order

```java
conn.setAutoCommit(false);
try {
    // ۱. چک موجودی
    int stock = getStock(conn, productId);
    if (stock < quantity) {
        conn.rollback();
        return false;
    }
    // ۲. کاهش موجودی
    updateStock(conn, productId, stock - quantity);
    // ۳. ثبت سفارش
    insertOrder(conn, productId, quantity, totalPrice);
    conn.commit();
    return true;
} catch (SQLException e) {
    conn.rollback();
    throw e;
}
```

## کد مهم

`src/WarehouseSystem.java` — سیستم کامل با همهٔ operations.

## جمع‌بندی فاز ۷ (بخش Databases)
- JDBC Basics, PreparedStatement, Connection Pool, Transactions, JPA, Hibernate ✅
- **بعدی: Networking — Sockets, HTTP, WebSocket**
