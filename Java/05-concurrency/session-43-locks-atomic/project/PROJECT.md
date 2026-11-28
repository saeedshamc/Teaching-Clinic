# پروژهٔ جلسه ۴۳ — Thread-Safe Cache

یک cache ساده بسازید که:
- `AtomicReference<HashMap>` یا `ConcurrentHashMap` برای ذخیره‌سازی
- `get(key)` و `put(key, value)` با atomic operations
- `computeIfAbsent` برای محاسبهٔ lazy
- در `main` چند thread همزمان put/get کنید

نمونه:
```
Cache size: 100
Threads: 5
Concurrent operations: 1000
```

## صورت پروژه
پاسخ در `solution/ThreadSafeCache.java` — از `AtomicReference` یا `ConcurrentHashMap` استفاده کنید.
