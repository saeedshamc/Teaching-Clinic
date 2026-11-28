# پاسخ تمرین‌های جلسه ۱۱۹

## آسان

**۱ و ۲.**
```java
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
            .requestMatchers("/api/public/**").permitAll()
            .anyRequest().authenticated())
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
```

## متوسط

**۳ و ۴.**
```java
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) {
        return User.builder()
            .username(username)
            .password(encodedPassword)
            .roles("USER")
            .build();
    }
}

@PreAuthorize("hasRole('ADMIN')")
public void deleteUser(Long id) { ... }
```

## سخت

**۵ و ۶.**
```java
String token = jwtUtil.generateToken(username);
boolean valid = jwtUtil.validateToken(token);

@PreAuthorize("isAuthenticated()")
public List<User> getAllUsers() { ... }
```
