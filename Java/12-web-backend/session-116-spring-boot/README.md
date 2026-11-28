# جلسه ۱۱۶ — Spring Boot: Auto-Configuration

## اهداف یادگیری
- `Spring Boot` را برای rapid development بشناسید
- `@SpringBootApplication`, `@RestController`, `@Service` را بفهمید
- `application.properties` و `application.yml` را به‌کار ببرید
- `spring-boot-starter` dependencies را بشناسید

## پیش‌نیازها
- جلسهٔ ۱۱۵ (Spring MVC)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Spring Boot چیست؟

`Spring Boot` — opinionated framework برای rapid Spring development. Auto-configuration، embedded server، starters.

### @SpringBootApplication

```java
@SpringBootApplication   // = @Configuration + @EnableAutoConfiguration + @ComponentScan
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}
```

### Spring Boot Starters

```xml
<dependencies>
    <!-- Web -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <!-- Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>

    <!-- Security -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-security</artifactId>
    </dependency>

    <!-- Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### application.properties / application.yml

```properties
# application.properties
server.port=8080
spring.application.name=my-app
spring.datasource.url=jdbc:sqlite:mydb.db
spring.datasource.driver-class-name=org.sqlite.JDBC
spring.jpa.hibernate.ddl-auto=update
logging.level.root=INFO
```

```yaml
# application.yml
server:
  port: 8080
spring:
  application:
    name: my-app
  datasource:
    url: jdbc:sqlite:mydb.db
    driver-class-name: org.sqlite.JDBC
  jpa:
    hibernate:
      ddl-auto: update
logging:
  level:
    root: INFO
```

### REST Controller — بدون config

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping
    public List<User> getAll() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.createUser(user);
    }
}
```

### Profiles — environment-specific config

```properties
# application-dev.properties
server.port=8081
logging.level.root=DEBUG

# application-prod.properties
server.port=8080
logging.level.root=WARN
```

```bash
java -jar myapp.jar --spring.profiles.active=dev
```

## کد مهم

`src/MyAppApplication.java` — Spring Boot entry point.
`src/UserController.java` — REST controller.
`src/UserService.java` — service.
`src/application.properties` — config.

## خطاهای رایج
- `@SpringBootApplication` را بدون `main` method استفاده کردن → app نمی‌شود.
- `spring-boot-starter` را بدون version specification استفاده کردن → dependency management.
- `application.properties` را در wrong location قرار دادن → config not found.
- `server.port` را بدون restart تغییر دادن → port already in use.

## جمع‌بندی
- Spring Boot برای rapid development — auto-configuration.
- `@SpringBootApplication` برای entry point.
- Starters برای dependencies، `application.properties` برای config.
- Profiles برای environment-specific settings.

**جلسهٔ بعد:** REST Controllers — API design.
