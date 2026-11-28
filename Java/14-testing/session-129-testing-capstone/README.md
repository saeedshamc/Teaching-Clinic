# جلسه ۱۲۹ — Testing Capstone: پروژهٔ تست کامل

## اهداف یادگیری
- همهٔ مباحث testing را ترکیب کنید
- Unit tests + Integration tests + Mockito را با هم استفاده کنید
- `@SpringBootTest`, `@WebMvcTest`, `@DataJpaTest` را integrate کنید
- Test coverage و quality metrics را در نظر بگیرید

## پیش‌نیازها
- جلسات ۱۲۵–۱۲۸

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: Complete Testing Suite

یک سیستم مدیریت کاربران کامل با testing بسازید:

**Testing Pyramid:**
```
        /\
       /  \     Integration Tests (few)
      /____\
     /      \   Unit Tests (many)
    /________\
```

**Layers:**
- `UserService` — business logic → unit tests with Mockito
- `UserController` — REST API → MVC slice tests
- `UserRepository` — data access → JPA slice tests
- `UserIntegrationTest` — full stack → integration tests

### Dependencies

```xml
<dependencies>
    <dependency>spring-boot-starter-test</dependency>
    <dependency>spring-boot-starter-web</dependency>
    <dependency>spring-boot-starter-data-jpa</dependency>
    <dependency>junit-jupiter</dependency>
    <dependency>mockito-core</dependency>
</dependencies>
```

## کد مهم

`src/UserServiceTest.java` — unit tests with Mockito.
`src/UserControllerTest.java` — MVC slice tests.
`src/UserRepositoryTest.java` — JPA slice tests.
`src/UserIntegrationTest.java` — full integration tests.

## جمع‌بندی فاز ۹ (بخش Testing)
- JUnit 5, Mockito, TDD, Integration Tests, Capstone ✅
- **بعدی: Design Patterns**
