# جلسه ۱۱۷ — REST Controllers: API Design

## اهداف یادگیری
- `@RestController` را برای JSON APIs بشناسید
- `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping` را بفهمید
- `@RequestBody`, `@PathVariable`, `@RequestParam` را به‌کار ببرید
- `ResponseEntity` برای HTTP status codes استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۱۶ (Spring Boot)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### @RestController vs @Controller

```java
@Controller      // returns view name → HTML
@RestController  // returns object → JSON (has @ResponseBody)
```

### CRUD Endpoints

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    @GetMapping                    // GET /api/users
    public List<User> getAll() { ... }

    @GetMapping("/{id}")           // GET /api/users/1
    public User getById(@PathVariable int id) { ... }

    @PostMapping                   // POST /api/users
    public User create(@RequestBody User user) { ... }

    @PutMapping("/{id}")           // PUT /api/users/1
    public User update(@PathVariable int id, @RequestBody User user) { ... }

    @DeleteMapping("/{id}")        // DELETE /api/users/1
    public void delete(@PathVariable int id) { ... }
}
```

### Request Parameters

```java
// Path variable
@GetMapping("/users/{id}")
public User getUser(@PathVariable int id) { ... }

// Query parameter
@GetMapping("/users/search")
public List<User> search(@RequestParam String name) { ... }
// → /users/search?name=Ali

// Request body
@PostMapping("/users")
public User create(@RequestBody User user) { ... }
// JSON body → User object
```

### ResponseEntity — HTTP status + body

```java
@GetMapping("/users/{id}")
public ResponseEntity<User> getUser(@PathVariable int id) {
    return userService.findById(id)
        .map(user -> ResponseEntity.ok(user))           // 200 + body
        .orElse(ResponseEntity.notFound().build());      // 404
}

@PostMapping("/users")
public ResponseEntity<User> create(@RequestBody User user) {
    User created = userService.create(user);
    return ResponseEntity.status(HttpStatus.CREATED).body(created);  // 201
}

@DeleteMapping("/users/{id}")
public ResponseEntity<Void> delete(@PathVariable int id) {
    userService.delete(id);
    return ResponseEntity.noContent().build();   // 204
}
```

### Error Handling

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(UserNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body(Map.of("error", e.getMessage()));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBadRequest(IllegalArgumentException e) {
        return ResponseEntity.badRequest()
            .body(Map.of("error", e.getMessage()));
    }
}
```

## کد مهم

`src/UserApiController.java` — REST CRUD.
`src/GlobalExceptionHandler.java` — error handling.
`src/User.java` — model.

## خطاهای رایج
- `@RequestBody` را بدون JSON content-type استفاده کردن → `HttpMessageNotReadableException`.
- `@PathVariable` را بدون `{id}` در mapping استفاده کردن → `MissingPathVariableException`.
- `ResponseEntity` را بدون status استفاده کردن → default 200.
- `@ExceptionHandler` را بدون `@RestControllerAdvice` استفاده کردن → not global.

## جمع‌بندی
- `@RestController` برای JSON APIs — auto-serialize objects.
- `@RequestBody` برای JSON input، `@PathVariable`/`@RequestParam` برای params.
- `ResponseEntity` برای HTTP status + body.
- `@RestControllerAdvice` برای global error handling.

**جلسهٔ بعد:** Spring Data JPA — database integration.
