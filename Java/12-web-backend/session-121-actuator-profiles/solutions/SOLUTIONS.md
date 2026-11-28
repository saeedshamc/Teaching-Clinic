# پاسخ تمرین‌های جلسه ۱۲۱

## آسان

**۱ و ۲.**
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```
```properties
# application-dev.properties
server.port=8081
logging.level.root=DEBUG
```

## متوسط

**۳ و ۴.**
```java
@Component
public class CustomHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        return Health.up().withDetail("db", "UP").build();
    }
}

@Bean
@Profile("dev")
public DataSource devDataSource() { ... }
```

## سخت

**۵ و ۶.**
```java
@Component
public class AppInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app", Map.of("name", "MyApp"));
    }
}
```
```properties
management.endpoints.web.exposure.include=health,info,metrics
```
