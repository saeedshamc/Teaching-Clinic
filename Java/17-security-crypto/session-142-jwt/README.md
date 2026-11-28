# جلسه ۱۴۲ — JWT: JSON Web Tokens

## اهداف یادگیری
- `JWT` را برای stateless authentication بشناسید
- `Header`, `Payload`, `Signature` را بفهمید
- `Jwts`, `Claims`, `Expiration` را به‌کار ببرید
- `Bearer Token` authentication را پیاده کنید

## پیش‌نیازها
- جلسهٔ ۱۴۱ (Encryption)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JWT Structure

```
Header.Payload.Signature
```

```json
// Header
{"alg":"HS256","typ":"JWT"}

// Payload
{"sub":"user123","name":"Ali","iat":1234567890,"exp":1234567890}

// Signature
HMACSHA256(base64(header) + "." + base64(payload), secret)
```

### JWT Creation — JJWT

```java
// نیازمند: jjwt-api, jjwt-impl, jjwt-jackson jars
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private final String secret = "my-secret-key-for-jwt-signing-32-chars-minimum";
    private final long expirationMs = 86400000;   // 24 hours

    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
            .signWith(getSigningKey())
            .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .getBody()
            .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(getSigningKey()).build()
                .parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }
}
```

### JWT Claims — custom data

```java
public String generateTokenWithClaims(String username, String role) {
    return Jwts.builder()
        .setSubject(username)
        .claim("role", role)
        .claim("userId", 123)
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + expirationMs))
        .signWith(getSigningKey())
        .compact();
}

public String extractRole(String token) {
    return Jwts.parser()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody()
        .get("role", String.class);
}
```

### Bearer Token Authentication

```java
// Client sends token in Authorization header
GET /api/users
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...

// Server validates token
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain chain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            if (jwtUtil.validateToken(token)) {
                String username = jwtUtil.extractUsername(token);
                // Set authentication in SecurityContext
            }
        }
        chain.doFilter(request, response);
    }
}
```

## کد مهم

`src/JwtUtil.java` — JWT utilities.
`src/JwtToken.java` — token model.
`src/JwtAuthenticationFilter.java` — authentication filter.
`src/AuthController.java` — login endpoint.

## خطاهای رایج
- `secret` را بدون minimum length استفاده کردن → `WeakKeyException`.
- `expiration` را بدون `setExpiration` استفاده کردن → token never expires.
- `validateToken` را بدون `try-catch` استفاده کردن → `JwtException`.
- `Bearer` را بدون space استفاده کردن → `Invalid token`.

## جمع‌بندی
- JWT برای stateless authentication — Header.Payload.Signature.
- `Jwts.builder()` برای token creation.
- `Claims` برای custom data — role, userId.
- `Bearer` token در `Authorization` header.

**جلسهٔ بعد:** Security Capstone.
