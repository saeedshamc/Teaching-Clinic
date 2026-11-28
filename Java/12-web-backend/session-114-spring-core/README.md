# جلسه ۱۱۴ — Spring Core: Dependency Injection

## اهداف یادگیری
- `Spring Framework` را به‌عنوان DI container بشناسید
- `@Component`, `@Service`, `@Repository`, `@Controller` را بفهمید
- `@Autowired` برای dependency injection را به‌کار ببرید
- `ApplicationContext` و bean lifecycle را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۱۱۳ (Jakarta EE)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Spring Core چیست؟

`Spring` — lightweight DI framework. Objects (beans) را manage می‌کند و dependencies را inject می‌کند:

```java
@Component
public class UserService {
    private final UserDao userDao;

    @Autowired
    public UserService(UserDao userDao) {
        this.userDao = userDao;   // dependency injected
    }
}
```

### Stereotype Annotations

| Annotation | Usage |
|---|---|
| `@Component` | Generic bean |
| `@Service` | Business logic layer |
| `@Repository` | Data access layer |
| `@Controller` | Web layer |
| `@Configuration` | Configuration class |
| `@Bean` | Bean definition |

### ApplicationContext — Spring container

```java
// XML-based (old)
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
UserService service = context.getBean(UserService.class);

// Annotation-based (modern)
AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
context.register(AppConfig.class);
context.refresh();
UserService service = context.getBean(UserService.class);
```

### @Configuration + @Bean

```java
@Configuration
public class AppConfig {

    @Bean
    public UserDao userDao() {
        return new UserDao();
    }

    @Bean
    public UserService userService(UserDao userDao) {
        return new UserService(userDao);
    }
}
```

### Component Scanning

```java
@Configuration
@ComponentScan("com.example")   // scan for @Component, @Service, etc.
public class AppConfig {
    // beans auto-discovered
}
```

### Constructor Injection — Best Practice

```java
@Service
public class UserService {
    private final UserDao userDao;

    // Constructor injection — recommended
    public UserService(UserDao userDao) {
        this.userDao = userDao;
    }
}
```

## کد مهم

`src/AppConfig.java` — configuration.
`src/UserDao.java` — repository.
`src/UserService.java` — service with DI.
`src/Main.java` — entry point.

## خطاهای رایج
- `@Autowired` را بدون constructor injection استفاده کردن → field injection less testable.
- Circular dependencies → `BeanCurrentlyInCreationException`.
- `@ComponentScan` را بدون base package استفاده کردن → scans everything.
- `context.close()` را فراموش کردن → resource leak.

## جمع‌بندی
- Spring Core برای DI — beans را manage می‌کند.
- `@Component`/`@Service`/`@Repository` برای stereotypes.
- `@Autowired` یا constructor injection برای dependencies.
- `ApplicationContext` برای bean lifecycle.

**جلسهٔ بعد:** Spring MVC — web layer.
