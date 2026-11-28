# پروژهٔ جلسه ۹۷ — سیستم ثبت‌نام امن با PreparedStatement

یک سیستم ثبت‌نام بسازید که:
- `User` model با name, email, password
- `UserDao` با `PreparedStatement` برای همهٔ queries
- `BatchImporter` برای insert گروهی با `executeBatch`
- `RegistrationManager` با main برای demo
- SQL Injection test — تلاش برای inject و نشان دادن امنیت

نمونه:
```
=== Registration System ===
[Add User] → INSERT با PreparedStatement
[Batch Import] → executeBatch برای ۱۰۰ کاربر
[SQL Injection Test] → ورودی مخرب → امن
```

## صورت پروژه
پاسخ در `solution/` — RegistrationUser.java + SecureUserDao.java + BatchImporter.java.
