# پروژهٔ جلسه ۱۱۹ — سیستم احراز هویت با Spring Security

یک سیستم احراز هویت با Spring Security بسازید که:
- `SecurityConfig` با `SecurityFilterChain` برای HTTP security
- `UserDetailsService` برای user loading
- `PasswordEncoder` با `BCryptPasswordEncoder`
- `@PreAuthorize` برای method-level security
- `JwtUtil` برای JWT token generation
- `SecureUserController` با protected endpoints

نمونه:
```
GET /api/public/health → 200 (no auth)
GET /api/users → 401 (needs auth)
GET /api/admin/users → 403 (needs ADMIN role)
POST /api/auth/login → 200 {token}
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
