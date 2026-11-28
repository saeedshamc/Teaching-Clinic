# پروژهٔ جلسه ۱۴۳ — سیستم مدیریت کاربران امن

یک سیستم مدیریت کاربران امن با همهٔ security features بسازید:
- [ ] `SecureUserService` با `register` و `authenticate`
- [ ] `AuthService` با `login`, `register`, `validateToken`
- [ ] `SecureController` با `register`, `login`, `getAllUsers`
- [ ] `PBKDF2` برای password hashing
- [ ] `JWT` برای token generation
- [ ] `Bearer` token authentication

نمونه:
```
POST /api/auth/register {username, email, password}
→ {id, username, email, role}

POST /api/auth/login {username, password}
→ {token, username, role, expiresIn}

GET /api/users
Authorization: Bearer {token}
→ {users: [...]}
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
