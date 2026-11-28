# پاسخ تمرین‌های جلسه ۱۴۳

## آسان

**۱ و ۲.**
```java
public User register(String username, String email, String password) {
    String hashedPassword = hashPassword(password);
    User user = new User(nextId++, username, email, hashedPassword);
    users.put(user.getId(), user);
    return user;
}

public class User {
    private Long id;
    private String username;
    private String email;
    private String password;
}
```

## متوسط

**۳ و ۴.**
```java
public Map<String, Object> login(String username, String password) {
    User user = userService.authenticate(username, password);
    String token = generateToken(user);
    return Map.of("token", token, "username", user.getUsername());
}

public Map<String, Object> getAllUsers(String authHeader) {
    // validate token, return users
}
```

## سخت

**۵–۷.**
```java
// PBKDF2
PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 10000, 256);
SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
byte[] hash = factory.generateSecret(spec).getEncoded();

// JWT
String token = Jwts.builder()
    .setSubject(username)
    .setExpiration(new Date(System.currentTimeMillis() + 86400000))
    .signWith(getSigningKey())
    .compact();

// Bearer
String token = authHeader.substring(7);
```
