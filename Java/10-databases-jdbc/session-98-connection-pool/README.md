# جلسه ۹۸ — Connection Pool

## اهداف یادگیری
- چرا Connection Pool لازم است را بفهمید
- `DataSource` interface را بشناسید
- `HikariCP` را به‌عنوان industrial-standard pool بشناسید
- `Connection.borrow` / `release` pattern را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۹۷ (PreparedStatement)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### چرا Connection Pool؟

ساخت `Connection` به دیتابیس گران است (network + authentication + session setup). برای هر query یک connection جدید = کشنده.

```
بدون pool: 100 request → 100 connection → 100× overhead
با pool:   100 request → 10 connection reusable → سریع
```

### DataSource — interface استاندارد

`javax.sql.DataSource` interface برای pool است:

```java
DataSource ds = new HikariDataSource(config);
Connection conn = ds.getConnection();  // borrow
// ... use ...
conn.close();  // return to pool (نه واقعی close)
```

### HikariCP — سریع‌ترین pool

```gradle
// Maven
<dependency>
    <groupId>com.zaxxer</groupId>
    <artifactId>HikariCP</artifactId>
    <version>5.1.0</version>
</dependency>
```

```java
HikariConfig config = new HikariConfig();
config.setJdbcUrl("jdbc:sqlite:test.db");  // یا MySQL/PostgreSQL
config.setMaximumPoolSize(10);
config.setMinimumIdle(2);
config.setConnectionTimeout(30000);

HikariDataSource ds = new HikariDataSource(config);
```

### Pool Demo — benchmark

```java
// بدون pool — ۱۰۰ connection جداگانه
for (int i = 0; i < 100; i++) {
    try (Connection c = DriverManager.getConnection(url)) {
        // use
    }
}

// با pool — ۱۰ connection reusable
DataSource ds = createPool();
for (int i = 0; i < 100; i++) {
    try (Connection c = ds.getConnection()) {
        // use — conn.close() برمی‌گرداند به pool
    }
}
```

### Pool Statistics

```java
HikariDataSource ds = new HikariDataSource(config);
HikariPoolMXBean pool = ds.getHikariPoolMXBean();
System.out.println("Active: " + pool.getActiveConnections());
System.out.println("Idle: " + pool.getIdleConnections());
System.out.println("Total: " + pool.getTotalConnections());
```

## کد مهم

`src/PoolDemo.java` — مقایسهٔ DriverManager vs HikariCP.

## خطاهای رایج
- `pool.close()` را فراموش کردن → resource leak در shutdown.
- `maximumPoolSize` را خیلی کوچک گذاشتن → connection timeout.
- `conn.close()` را فراموش کردن → connection به pool برنمی‌گردد.
- `DriverManager` را در production استفاده کردن → بدون pool.

## جمع‌بندی
- Connection Pool برای reusable connections — HikariCP استاندارد.
- `DataSource` interface، `getConnection`/`close` pattern.
- `maximumPoolSize` و `minimumIdle` برای tuning.

**جلسهٔ بعد:** Transactions — ACID و isolation levels.
