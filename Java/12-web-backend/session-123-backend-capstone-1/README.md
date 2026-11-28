# جلسه ۱۲۳ — Backend Capstone 1: سیستم مدیریت کاربران کامل

## اهداف یادگیری
- همهٔ مباحث Spring Boot را ترکیب کنید
- REST API + Spring Data JPA + Security + Validation + AOP را integrate کنید
- لایه‌بندی صحیح (Controller → Service → Repository) را رعایت کنید

## پیش‌نیازها
- جلسات ۱۱۱–۱۲۲

## مدت زمان پیشنهادی
۹۰ دقیقه

## درس

### پروژه: User Management System

یک سیستم مدیریت کاربران کامل با Spring Boot بسازید:

**Layers:**
```
Controller (REST API) → Service (business logic) → Repository (data access) → Database
```

**Features:**
- CRUD operations با REST endpoints
- JPA entities با validation
- Spring Security برای authentication/authorization
- AOP برای logging و performance monitoring
- Actuator برای health checks

### Architecture

```
UserController (@RestController)
    ↓
UserService (@Service)
    ↓
UserRepository (JpaRepository)
    ↓
User (@Entity)
```

### pom.xml — Dependencies

```xml
<dependencies>
    <dependency>spring-boot-starter-web</dependency>
    <dependency>spring-boot-starter-data-jpa</dependency>
    <dependency>spring-boot-starter-security</dependency>
    <dependency>spring-boot-starter-validation</dependency>
    <dependency>spring-boot-starter-actuator</dependency>
    <dependency>com.h2database:h2</dependency>
</dependencies>
```

## کد مهم

`src/User.java` — JPA entity.
`src/UserRepository.java` — Spring Data repository.
`src/UserService.java` — service layer.
`src/UserController.java` — REST controller.
`src/SecurityConfig.java` — security config.
`src/LoggingAspect.java` — AOP logging.

## جمع‌بندی
- لایه‌بندی: Controller → Service → Repository → Entity.
- Spring Boot برای rapid development.
- Security برای authentication/authorization.
- AOP برای cross-cutting concerns.

**جلسهٔ بعد:** Backend Capstone 2 — پروژهٔ نهایی.
