# پروژهٔ جلسه ۹۸ — سیستم مدیریت استخر اتصال

یک سیستم مدیریت استخر اتصال بسازید که:
- `ConnectionPoolManager` با HikariCP
- `DatabaseConfig` برای configuration
- `PoolMonitor` برای statistics
- `ConnectionTester` برای test connections
- `shutdown hook` برای cleanup

نمونه:
```
=== Connection Pool Manager ===
Pool created: max=10, min=2
Active connections: 5
Idle connections: 5
Total connections: 10
```

## صورت پروژه
پاسخ در `solution/` — ConnectionPoolManager.java + PoolMonitor.java.
