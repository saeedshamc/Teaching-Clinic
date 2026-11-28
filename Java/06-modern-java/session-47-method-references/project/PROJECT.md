# پروژهٔ جلسه ۴۷ — سیستم تبدیل داده

یک سیستم تبدیل داده بسازید که:
- `Converter<T,R>` interface با `convert(T)`
- method reference برای تبدیل‌های مختلف: String→Integer, String→Double, Integer→String
- `applyConverters` که چند converter را پشت سر هم اجرا کند
- در `main` روی دادهٔ نمونه تست کنید

نمونه:
```
"42" → Integer: 42
"3.14" → Double: 3.14
42 → String: "42"
```

## صورت پروژه
پاسخ در `solution/DataConverter.java` — از method reference استفاده کنید.
