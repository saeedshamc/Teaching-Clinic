# پاسخ تمرین‌های جلسه ۳۱

## آسان

**۱.**
```java
try {
    int r = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
}
```

**۲.**
```java
try {
    System.out.println("Try");
} finally {
    System.out.println("Finally");   // همیشه اجرا می‌شود
}
```

## متوسط

**۳.**
```java
class InvalidPasswordException extends Exception {
    InvalidPasswordException(String msg) { super(msg); }
}
static void validate(String password) throws InvalidPasswordException {
    if (password.length() < 8) throw new InvalidPasswordException("Too short");
}
```

**۴.**
```java
static void risky() throws IOException { ... }
// در main:
try { risky(); } catch (IOException e) { System.out.println(e.getMessage()); }
```

## سخت

**۵.**
```java
try {
    // کد
} catch (FileNotFoundException e) { ... }   // خاص اول
catch (IOException e) { ... }                // عمومی بعد
catch (Exception e) { ... }                  // عمومی‌ترین آخر
```

**۶.**
```java
try {
    // کد
} finally {
    // cleanup
    // اگر exception بود، دوباره پرتاب می‌شود
}
```
