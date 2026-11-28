# پروژهٔ جلسه ۱۴۰ — سیستم ذخیرهٔ امن رمز عبور

یک سیستم ذخیرهٔ امن رمز عبور بسازید:
- `SecurePasswordStorage` با `salt:hash` format
- `HashDemo` با `SHA-256` برای comparison
- `PBKDF2Demo` با `PBEKeySpec` برای password hashing
- `BcryptDemo` با `spring-security-crypto` (optional)
- `PasswordValidator` برای password strength

نمونه:
```
Password: mySecretPassword
Stored hash: c2FsdA==:aGFzaA==
Password matches: true
Wrong password matches: false
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
