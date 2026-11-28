# جلسه ۱۱۹ — Spring Security: Authentication & Authorization

## اهداف یادگیری
- `Spring Security` را برای authentication/authorization بشناسید
- `SecurityFilterChain`, `HttpSecurity` را بفهمید
- `UserDetailsService`, `PasswordEncoder` را به‌کار ببرید
- `@PreAuthorize` برای method-level security استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۱۸ (Spring Data JPA)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Spring Security چیست؟

`Spring Security` — authentication (کی هستی؟) و authorization (چه می‌توانی بکنی؟).

### Security Configuration

```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/public/**").permitAll()
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )
            .httpBasic(Customizer.withDefaults())   // or .formLogin()
            .csrf(csrf -> csrf.disable());          // for REST APIs
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

### UserDetailsService — load users

```java
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return User.builder()
            .username(user.getUsername())
            .password(user.getPassword())
            .roles(user.getRole())
            .build();
    }
}
```

### @PreAuthorize — method-level security

```java
@Service
public class UserService {

    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(Long id) {
        // only ADMIN can call this
    }

    @PreAuthorize("#id == authentication.principal.id")
    public User getUserById(Long id) {
        // user can only get their own profile
    }
}
```

### JWT — stateless authentication

```java
// برای REST APIs — بدون session
@Component
public class JwtUtil {
    private final String secret = "my-secret-key";

    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + 86400000)) // 24h
            .signWith(SignatureAlgorithm.HS512, secret)
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
            .setSigningKey(secret)
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }
}
```

## کد مهم

`src/SecurityConfig.java` — security configuration.
`src/UserDetailsServiceImpl.java` — user loading.
`src/JwtUtil.java` — JWT utilities.
`src/User.java` — entity with roles.

## خطاهای رایج
- `csrf` را بدون دلیل disable کردن → security vulnerability.
- `passwordEncoder` را بدون `BCrypt` استفاده کردن → weak hashing.
- `@PreAuthorize` را بدون `@EnableMethodSecurity` استفاده کردن → not active.
- `UserDetails` را بدون `roles` استفاده کردن → authorization fails.

## جمع‌بندی
- Spring Security برای authentication/authorization.
- `SecurityFilterChain` برای HTTP security rules.
- `UserDetailsService` برای user loading.
- `@PreAuthorize` برای method-level security.
- JWT برای stateless REST APIs.

**جلسهٔ بعد:** Validation & AOP.
