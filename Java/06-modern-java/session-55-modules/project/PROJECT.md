# پروژهٔ جلسه ۵۵ — ماژول چندبخشی

یک پروژهٔ چندماژولی بسازید:
- `module-info.java` برای هر module
- `com.example.core` — core utilities
- `com.example.app` — main application که `requires com.example.core`
- `exports` برای API packages
- `javac --module-path` و `java --module-path` برای compile و run

نمونه:
```
com.example.core → exports com.example.core.api
com.example.app → requires com.example.core, exports com.example.app.main
```

## صورت پروژه
پاسخ در `solution/` — ساختار module + کد نمونه.
