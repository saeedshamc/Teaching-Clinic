# تمرین‌های جلسه ۹۸ — Connection Pool

## آسان
1. `DataSource` interface را بشناسید.
2. `getConnection()` و `close()` pattern را بفهمید.

## متوسط
3. `HikariConfig` با `maximumPoolSize` و `minimumIdle` تنظیم کنید.
4. `HikariPoolMXBean` برای pool statistics استفاده کنید.

## سخت
5. Benchmark بدون pool vs با pool انجام دهید.
6. `pool.close()` را در shutdown hook استفاده کنید.
