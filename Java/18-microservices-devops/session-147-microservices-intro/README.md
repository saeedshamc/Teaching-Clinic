# جلسه ۱۴۷ — Microservices Intro: Service Decomposition

## اهداف یادگیری
- `Microservices` را برای distributed architecture بشناسید
- `Service decomposition` را برای independent services بفهمید
- `API Gateway`, `Service Discovery`, `Load Balancer` را به‌کار ببرید
- `Spring Cloud` را برای microservices infrastructure استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۴۶ (Message Queues)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Microservices چیست؟

`Microservices` — small, independent services که با هم کار می‌کنند. هر service یک business capability.

**Monolith vs Microservices:**

| Monolith | Microservices |
|---|---|
| Single deployable | Multiple deployables |
| Shared database | Database per service |
| Tight coupling | Loose coupling |
| Single team | Multiple teams |
| Easy to start | Complex infrastructure |

### Service Decomposition

```
E-Commerce System:
├── User Service (auth, profile)
├── Product Service (catalog, inventory)
├── Order Service (cart, checkout)
├── Payment Service (transactions)
├── Notification Service (email, SMS)
└── Search Service (product search)
```

### Spring Cloud — microservices infrastructure

```xml
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-netflix-eureka-client</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>
```

### Eureka — Service Discovery

```java
// User Service — registers with Eureka
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}
```

```java
// Order Service — discovers User Service
@FeignClient(name = "user-service")
public interface UserServiceClient {
    @GetMapping("/api/users/{id}")
    User getUser(@PathVariable Long id);
}
```

### API Gateway — single entry point

```java
// Zuul Gateway
@Configuration
public class GatewayConfig {
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
            .route("user-service", r -> r.path("/api/users/**")
                .uri("http://user-service"))
            .route("product-service", r -> r.path("/api/products/**")
                .uri("http://product-service"))
            .build();
    }
}
```

### Circuit Breaker — resilience

```java
@FeignClient(name = "user-service", fallback = UserServiceFallback.class)
public interface UserServiceClient {
    @GetMapping("/api/users/{id}")
    User getUser(@PathVariable Long id);
}

@Component
public class UserServiceFallback implements UserServiceClient {
    @Override
    public User getUser(Long id) {
        return new User(id, "Unknown", "unknown@mail.com");
    }
}
```

## کد مهم

`src/UserService.java` — user microservice.
`src/ProductService.java` — product microservice.
`src/OrderService.java` — order microservice with Feign.
`src/GatewayConfig.java` — API gateway config.
`src/Application.java` — service entry points.

## خطاهای رایج
- `spring-cloud` را بدون `spring-cloud-starter` dependency استفاده کردن → not found.
- `@FeignClient` را بدون `name` استفاده کردن → service not discovered.
- `fallback` را بدون `implements` استفاده کردن → `ClassCastException`.
- `Gateway` را بدون `RouteLocator` استفاده کردن → no routing.

## جمع‌بندی
- Microservices برای distributed architecture.
- Service decomposition — independent services.
- Spring Cloud — Eureka, Feign, Gateway, Circuit Breaker.
- API Gateway برای single entry point.

**جلسهٔ بعد:** Desktop App Capstone.
