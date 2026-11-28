# پاسخ تمرین‌های جلسه ۱۱۷

## آسان

**۱ و ۲.**
```java
@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public List<User> getAll() { ... }

    @PostMapping
    public User create(@RequestBody User user) { ... }
}
```

## متوسط

**۳ و ۴.**
```java
@GetMapping("/{id}")
public User getById(@PathVariable int id) { ... }

@GetMapping("/search")
public List<User> search(@RequestParam String name) { ... }
```

## سخت

**۵ و ۶.**
```java
@GetMapping("/{id}")
public ResponseEntity<User> getById(@PathVariable int id) {
    return userService.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());
}

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(UserNotFoundException e) {
        return ResponseEntity.notFound().body(Map.of("error", e.getMessage()));
    }
}
```
