# پاسخ تمرین‌های جلسه ۱۱۶

## آسان

**۱ و ۲.**
```java
@SpringBootApplication
public class MyAppApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyAppApplication.class, args);
    }
}
```
```properties
server.port=8080
spring.application.name=my-app
```

## متوسط

**۳ و ۴.**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public List<User> getAll() { ... }
}
```

## سخت

**۵ و ۶.**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
```properties
# application-dev.properties
server.port=8081
```
