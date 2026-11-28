# جلسه ۹۹ — Transactions و ACID

## اهداف یادگیری
- `Transaction` و ACID properties را بفهمید
- `setAutoCommit(false)` و `commit`/`rollback` را به‌کار ببرید
- Isolation levels را بشناسید
- Savepoints را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۹۸ (Connection Pool)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### ACID — چهار خاصیت تراکنش

| خاصیت | معنی |
|---|---|
| **A**tomicity | همه یا هیچ — یا کامل یا rollback |
| **C**onsistency | دیتابیس از حالت معتبر به معتبر می‌رود |
| **I**solation | تراکنش‌های همزمان همدیگر را نمی‌بینند |
| **D**urability | بعد از commit، داده برای همیشه می‌ماند |

### Transaction Pattern

```java
try (Connection conn = DriverManager.getConnection(url)) {
    conn.setAutoCommit(false);   // شروع transaction

    try {
        // چند عملیات — همه یا هیچ
        insertAccount(conn, "Ali", 1000);
        insertAccount(conn, "Sara", 500);
        transfer(conn, "Ali", "Sara", 200);

        conn.commit();   // همه موفق → save
    } catch (SQLException e) {
        conn.rollback(); // هر خطا → همه برگردان
        throw e;
    }
}
```

### Isolation Levels

| Level | Lost Update | Dirty Read | Non-repeatable Read | Phantom Read |
|---|---|---|---|---|
| READ_UNCOMMITTED | ✅ | ✅ | ✅ | ✅ |
| READ_COMMITTED | ✅ | ❌ | ✅ | ✅ |
| REPEATABLE_READ | ✅ | ❌ | ❌ | ✅ |
| SERIALIZABLE | ❌ | ❌ | ❌ | ❌ |

```java
conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
```

### Savepoints — nested rollback

```java
conn.setAutoCommit(false);
Savepoint sp1 = conn.setSavepoint("before_transfer");

try {
    insertAccount(conn, "Reza", 300);
    transfer(conn, "Ali", "Reza", 100);
    conn.commit();
} catch (SQLException e) {
    conn.rollback(sp1);   // فقط تا savepoint برگردان
    // یا conn.rollback(); برای rollback کامل
}
```

## کد مهم

`src/TransactionDemo.java` — account transfer با commit/rollback/savepoints.

## خطاهای رایج
- `setAutoCommit(false)` را فراموش کردن → هر statement خودش commit می‌شود.
- `commit` یا `rollback` را فراموش کردن → transaction باز می‌ماند.
- `conn.close()` قبل از `commit`/`rollback` → implicit rollback.
- Isolation level را بدون درک استفاده کردن → performance یا consistency issues.

## جمع‌بندی
- Transactions برای atomic multi-step operations.
- `setAutoCommit(false)` → `commit()` یا `rollback()`.
- Savepoints برای partial rollback.

**جلسهٔ بعد:** JPA Intro — ORM در جاوا.
