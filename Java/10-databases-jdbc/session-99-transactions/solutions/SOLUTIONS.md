# پاسخ تمرین‌های جلسه ۹۹

## آسان

**۱ و ۲.**
```java
conn.setAutoCommit(false);
try {
    // operations
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
}
```

## متوسط

**۳ و ۴.**
```java
conn.setAutoCommit(false);
try {
    debit(conn, fromId, amount);
    credit(conn, toId, amount);
    conn.commit();
} catch (SQLException e) {
    conn.rollback();
}

conn.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
```

## سخت

**۵ و ۶.**
```java
conn.setAutoCommit(false);
Savepoint sp = conn.setSavepoint("before_ops");
try {
    // ops
    conn.commit();
} catch (SQLException e) {
    conn.rollback(sp);  // partial rollback
    conn.commit();      // commit what succeeded before savepoint
}
```
