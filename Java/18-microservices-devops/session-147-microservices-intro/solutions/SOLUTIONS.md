# پاسخ تمرین‌های جلسه ۱۴۷

## آسان

**۱ و ۲.**
```java
public class UserService {
    private final Map<Long, Map<String, Object>> users = new HashMap<>();
    public Map<String, Object> getUser(Long id) { return users.get(id); }
    public List<Map<String, Object>> getAllUsers() { return new ArrayList<>(users.values()); }
    public Map<String, Object> createUser(String username, String email) {
        Map<String, Object> user = Map.of("id", nextId++, "username", username, "email", email);
        users.put((Long) user.get("id"), user);
        return user;
    }
}
```

## متوسط

**۳ و ۴.**
```java
public class OrderService {
    public List<Map<String, Object>> getUserOrders(Long userId) {
        return orders.values().stream()
            .filter(o -> ((Long) o.get("userId")).equals(userId))
            .toList();
    }
}

@Bean
public RouteLocator routes(RouteLocatorBuilder builder) {
    return builder.routes()
        .route("user-service", r -> r.path("/api/users/**").uri("http://user-service:8081"))
        .build();
}
```

## سخت

**۵ و ۶.**
```java
@FeignClient(name = "user-service", fallback = UserServiceFallback.class)
public interface UserServiceClient {
    @GetMapping("/api/users/{id}")
    User getUser(@PathVariable Long id);
}

@Component
public class UserServiceFallback implements UserServiceClient {
    @Override public User getUser(Long id) { return new User(id, "Unknown"); }
}
```
