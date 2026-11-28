# پروژهٔ جلسه ۱۲۰ — سیستم با Validation و AOP

یک سیستم با validation و AOP بسازید که:
- `ValidatedUser` با `@NotNull`, `@Size`, `@Email`, `@Min`, `@Max`
- `UserController` با `@Valid` برای validation
- `LoggingAspect` با `@Aspect` برای logging
- `PerformanceAspect` با `@Around` برای measure execution time
- `ExceptionAspect` با `@AfterThrowing` برای error logging
- `CustomValidator` برای custom validation

نمونه:
```
POST /api/users {"name":"A","email":"invalid","age":10}
→ 400 Bad Request {errors: ["Name must be 2-50 chars", "Invalid email", "Age must be 18+"]}

[AROUND] createUser took 15ms
[EXCEPTION] deleteUser: User not found
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
