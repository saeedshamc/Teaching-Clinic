# پاسخ تمرین‌های جلسه ۱۰۲

## آسان

**۱ و ۲.**
```java
stmt.execute("CREATE TABLE IF NOT EXISTS products (...)");

PreparedStatement ps = conn.prepareStatement(
    "INSERT INTO products(sku, name, price, stock) VALUES (?, ?, ?, ?)");
ps.setString(1, sku); ps.setString(2, name);
ps.setDouble(3, price); ps.setInt(4, stock);
ps.executeUpdate();
```

## متوسط

**۳ و ۴.**
```java
conn.setAutoCommit(false);
try {
    int stock = getStock(conn, productId);
    if (stock < quantity) { conn.rollback(); return false; }
    updateStock(conn, productId, stock - quantity);
    insertOrder(conn, productId, quantity, totalPrice);
    conn.commit();
    return true;
} catch (SQLException e) {
    conn.rollback();
    throw e;
}
```

## سخت

**۵ و ۶.**
```java
// Restock
ps.setInt(1, quantity);
ps.setInt(2, productId);
ps.executeUpdate();  // stock = stock + ?

// Cancel order
conn.setAutoCommit(false);
updateStock(conn, productId, stock + quantity);  // restore
updateOrderStatus(conn, orderId, "CANCELLED");
conn.commit();
```
