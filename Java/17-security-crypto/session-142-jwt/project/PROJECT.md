# پروژهٔ جلسه ۱۴۲ — سیستم احراز هویت JWT

یک سیستم احراز هویت JWT بسازید:
- `JwtUtil` با `generateToken`, `extractUsername`, `validateToken`
- `JwtToken` با `isExpired` برای token model
- `AuthController` با `login` و `getCurrentUser`
- `JwtAuthenticationFilter` برای request filtering
- `Claims` برای custom data (role, userId)

نمونه:
```
POST /api/auth/login {username: "admin", password: "password123"}
→ {token: "eyJ...", username: "admin", expiresIn: 86400}

GET /api/auth/me
Authorization: Bearer eyJ...
→ {username: "admin", authenticated: true}
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
