# پاسخ تمرین‌های جلسه ۱۴۰

## آسان

**۱ و ۲.**
```java
MessageDigest digest = MessageDigest.getInstance("SHA-256");
byte[] hash = digest.digest(password.getBytes());

PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 10000, 256);
SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
byte[] hash = factory.generateSecret(spec).getEncoded();
```

## متوسط

**۳ و ۴.**
```java
String storedHash = Base64.getEncoder().encodeToString(salt) + ":" +
                    Base64.getEncoder().encodeToString(hash);

static boolean verifyPassword(String password, String storedHash) {
    String[] parts = storedHash.split(":");
    byte[] salt = Base64.getDecoder().decode(parts[0]);
    byte[] expectedHash = Base64.getDecoder().decode(parts[1]);
    byte[] actualHash = hashPassword(password, salt);
    return Arrays.equals(expectedHash, actualHash);
}
```

## سخت

**۵ و ۶.**
```java
BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
String encoded = encoder.encode(password);
boolean matches = encoder.matches(password, encoded);

// Argon2 — نیازمند argon2 library
Argon2 argon2 = Argon2Factory.create(Argon2Types.ARGON2id);
String hash = argon2.hash(10, 65536, 1, password.toCharArray());
```
