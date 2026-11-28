# پروژهٔ جلسه ۱۱۸ — سیستم مدیریت کاربران با Spring Data JPA

یک سیستم مدیریت کاربران با Spring Data JPA بسازید که:
- `User` entity با `@Entity`, `@Table`, `@Id`, `@GeneratedValue`
- `UserRepository` با `JpaRepository` و derived queries
- `UserService` برای business logic
- `UserController` با `@RestController` برای REST API
- `@Query` برای custom queries
- `Optional<User>` برای single result

نمونه:
```
GET /api/users → [users]
GET /api/users/adults?minAge=18 → [adults]
GET /api/users/search?name=Ali → [matching]
POST /api/users → create user
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
