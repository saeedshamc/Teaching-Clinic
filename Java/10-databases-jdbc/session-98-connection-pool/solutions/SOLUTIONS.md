# پاسخ تمرین‌های جلسه ۹۸

## آسان

**۱ و ۲.**
```java
DataSource ds = new HikariDataSource(config);
Connection conn = ds.getConnection();  // borrow
try {
    // use connection
} finally {
    conn.close();  // return to pool
}
```

## متوسط

**۳ و ۴.**
```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl(url);
config.setMaximumPoolSize(10);
config.setMinimumIdle(2);

HikariPoolMXBean pool = ds.getHikariPoolMXBean();
System.out.println("Active: " + pool.getActiveConnections());
```

## سخت

**۵ و ۶.**
```java
// benchmark
long start = System.nanoTime();
runQueries(ds, 100);
long elapsed = (System.nanoTime() - start) / 1_000_000;

// shutdown
Runtime.getRuntime().addShutdownHook(new Thread(() -> ds.close()));
```
