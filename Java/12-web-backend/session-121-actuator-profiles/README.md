# جلسه ۱۲۱ — Actuator & Profiles

## اهداف یادگیری
- `Spring Boot Actuator` را برای monitoring و management بشناسید
- `/actuator/health`, `/actuator/info`, `/actuator/metrics` endpoints را بفهمید
- `@Profile` برای environment-specific beans استفاده کنید
- `application-{profile}.properties` برای config profiles استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۲۰ (Validation & AOP)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Spring Boot Actuator

`Actuator` — production-ready features: health checks, metrics, info.

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

### Actuator Endpoints

```properties
# application.properties
management.endpoints.web.exposure.include=health,info,metrics,env
management.endpoint.health.show-details=always
management.endpoint.health.probes.enabled=true
```

| Endpoint | Description |
|---|---|
| `/actuator/health` | Health check |
| `/actuator/info` | App info |
| `/actuator/metrics` | JVM metrics |
| `/actuator/env` | Environment variables |
| `/actuator/beans` | All beans |
| `/actuator/mappings` | All mappings |

### Health Checks

```java
@Component
public class CustomHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        // check database, external services
        boolean dbUp = checkDatabase();
        boolean apiUp = checkExternalApi();

        if (dbUp && apiUp) {
            return Health.up().withDetail("db", "UP").withDetail("api", "UP").build();
        }
        return Health.down().withDetail("db", dbUp ? "UP" : "DOWN").build();
    }

    private boolean checkDatabase() { return true; }
    private boolean checkExternalApi() { return true; }
}
```

### Info Contributor

```java
@Component
public class AppInfoContributor implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("app", Map.of(
            "name", "MyApp",
            "version", "1.0.0",
            "environment", System.getProperty("spring.profiles.active", "default")
        ));
    }
}
```

### @Profile — environment-specific beans

```java
@Configuration
public class DatabaseConfig {

    @Bean
    @Profile("dev")
    public DataSource devDataSource() {
        // H2 in-memory for dev
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .build();
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        // MySQL for production
        return new DriverManagerDataSource(
            "jdbc:mysql://localhost:3306/mydb", "user", "pass");
    }
}
```

### application-{profile}.properties

```properties
# application-dev.properties
server.port=8081
spring.datasource.url=jdbc:h2:mem:devdb
logging.level.root=DEBUG

# application-prod.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
logging.level.root=WARN
```

```bash
# Run with profile
java -jar myapp.jar --spring.profiles.active=dev
java -jar myapp.jar --spring.profiles.active=prod
```

## کد مهم

`src/CustomHealthIndicator.java` — custom health check.
`src/AppInfoContributor.java` — app info.
`src/DatabaseConfig.java` — profile-specific beans.
`src/application-dev.properties` — dev config.
`src/application-prod.properties` — prod config.

## خطاهای رایج
- `management.endpoints.web.exposure.include` را بدون `*` استفاده کردن → endpoints not exposed.
- `@Profile` را بدون `spring.profiles.active` استفاده کردن → bean not loaded.
- `HealthIndicator` را بدون `health()` method استفاده کردن → `AbstractMethodError`.
- `application-{profile}.properties` را در wrong location قرار دادن → config not found.

## جمع‌بندی
- Actuator برای monitoring — health, metrics, info endpoints.
- `@Profile` برای environment-specific beans.
- `application-{profile}.properties` برای profile-specific config.
- Custom `HealthIndicator` برای custom health checks.

**جلسهٔ بعد:** Backend WebSocket.
