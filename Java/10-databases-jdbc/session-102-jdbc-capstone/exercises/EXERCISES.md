# تمرین‌های جلسه ۱۰۲ — JDBC Capstone

## آسان
1. `createTables` با `CREATE TABLE IF NOT EXISTS` بسازید.
2. `addProduct` با `PreparedStatement` بسازید.

## متوسط
3. `createOrder` با transaction (check stock → update stock → insert order) بسازید.
4. `listProducts` و `listOrders` با `ResultSet` بسازید.

## سخت
5. `restock` operation برای update stock بسازید.
6. `cancelOrder` با rollback برای restore stock بسازید.
