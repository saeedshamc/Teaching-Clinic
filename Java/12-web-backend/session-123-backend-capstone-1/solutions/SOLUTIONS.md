# پاسخ تمرین‌های جلسه ۱۲۳

## آسان

**۱ و ۲.**
```java
@Entity
@Table(name = "users")
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
}

public interface UserRepository extends JpaRepository<User, Long> { }
```

## متوسط

**۳ و ۴.**
```java
@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    public User createUser(User user) { return userRepository.save(user); }
}

@RestController
@RequestMapping("/api/users")
public class UserController {
    @GetMapping
    public List<User> getAll() { return userService.getAllUsers(); }
}
```

## سخت

**۵ و ۶.**
```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/api/admin/**").hasRole("ADMIN")
        .anyRequest().authenticated())
        .httpBasic(Customizer.withDefaults());
    return http.build();
}

@PostMapping
public ResponseEntity<User> create(@Valid @RequestBody User user) { ... }
```
