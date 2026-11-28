# پروژهٔ جلسه ۱۲۳ — سیستم مدیریت کاربران کامل

یک سیستم مدیریت کاربران کامل با Spring Boot بسازید:
- [ ] `User` entity با JPA + validation
- [ ] `UserRepository` با `JpaRepository` + derived queries
- [ ] `UserService` با `@Transactional`
- [ ] `UserController` با `@RestController` + `@Valid`
- [ ] `SecurityConfig` با `SecurityFilterChain`
- [ ] `LoggingAspect` با `@Aspect`
- [ ] `pom.xml` با همهٔ dependencies

نمونه:
```
GET /api/users → [users]
POST /api/users → 201 {user}
GET /api/users/1 → 200 {user}
DELETE /api/users/1 → 204
GET /api/users/search?name=Ali → [matching]
GET /api/users/adults?minAge=18 → [adults]
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
