# پاسخ تمرین‌های جلسه ۱۴۲

## آسان

**۱ و ۲.**
```java
public String generateToken(String username) {
    return Jwts.builder()
        .setSubject(username)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 86400000))
        .signWith(getSigningKey())
        .compact();
}

public boolean isExpired() {
    return System.currentTimeMillis() > expiration;
}
```

## متوسط

**۳ و ۴.**
```java
public Map<String, Object> login(String username, String password) {
    if (users.containsKey(username) && users.get(username).equals(password)) {
        String token = jwtUtil.generateToken(username);
        return Map.of("token", token, "username", username);
    }
    return Map.of("error", "Invalid credentials");
}

String token = authHeader.substring(7);   // remove "Bearer "
```

## سخت

**۵ و ۶.**
```java
.claim("role", "ADMIN")
.claim("userId", 123)

class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(...) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                // set authentication
            }
        }
        chain.doFilter(request, response);
    }
}
```
