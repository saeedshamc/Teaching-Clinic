# پروژهٔ جلسه ۱۱۴ — سیستم مدیریت کاربران با Spring Core

یک سیستم مدیریت کاربران با Spring Core بسازید که:
- `AppConfig` با `@Configuration` و `@ComponentScan`
- `UserDao` با `@Repository` برای data access
- `UserService` با `@Service` و `@Autowired` برای business logic
- `User` model
- `Main` با `ApplicationContext` برای demo

نمونه:
```
=== Spring Core Demo ===
Users: [User{id=1,...}, User{id=2,...}]
User 1: User{id=1, name='Ali'}
Created: User{id=3, name='Reza'}
```

## صورت پروژه
پاسخ در `solution/` — AppConfig.java + UserDao.java + UserService.java + Main.java.
