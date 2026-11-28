# پروژهٔ جلسه ۳۳ — لاگ‌نویس

یک سیستم لاگ بسازید که:
- `FileWriter` با `append=true` برای append
- `BufferedWriter` برای کارایی
- `LogLevel` enum (INFO, WARN, ERROR)
- `log(level, message)` که با timestamp می‌نویسد
- `readLog()` که آخرین ۱۰ خط را می‌خواند
- در `main` چند لاگ بنویسید و بخوانید

نمونه:
```
[2024-01-15 10:30:00] INFO: Application started
[2024-01-15 10:30:05] WARN: Low memory
[2024-01-15 10:30:10] ERROR: Connection failed
```

## صورت پروژه
پاسخ در `solution/Logger.java` — از `java.time.LocalDateTime` برای timestamp و try-with-resources استفاده کنید.
