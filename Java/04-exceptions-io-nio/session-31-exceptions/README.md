# جلسه ۳۱ — Exception Handling

## اهداف یادگیری
- `try`, `catch`, `finally` را به‌کار ببرید
- checked در برابر unchecked exception را بفهمید
- `throw` و `throws` را بشناسید
- custom exception بسازید

## پیش‌نیازها
- فازهای ۱ و ۲

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### try-catch-finally

```java
try {
    int result = 10 / 0;   // ArithmeticException
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero: " + e.getMessage());
} finally {
    System.out.println("Always runs");
}
```

- `try` → کدی که ممکن است خطا دهد.
- `catch` → مدیریت خطا؛ می‌توانید چند catch برای انواع مختلف.
- `finally` → همیشه اجرا می‌شود (حتی با return یا exception).

### چند catch و ترتیب

```java
try {
    // ...
} catch (FileNotFoundException e) {
    // خاص‌تر اول
} catch (IOException e) {
    // عمومی‌تر بعد
} catch (Exception e) {
    // عمومی‌ترین آخر
}
```
- ترتیب مهم است — خاص‌ترین exception اول.

### throw و throws

```java
// throw — پرتاب exception
if (age < 0) {
    throw new IllegalArgumentException("Age cannot be negative");
}

// throws — اعلام اینکه متد ممکن است exception بدهد
static void readFile() throws IOException {
    // ...
}
```

### Custom exception

```java
class InvalidAgeException extends Exception {   // checked
    InvalidAgeException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends RuntimeException {  // unchecked
    InsufficientFundsException(String message) {
        super(message);
    }
}
```

### Checked vs Unchecked

| Checked | Unchecked (Runtime) |
|---|---|
| باید catch یا throws شود | لازم نیست |
| IOException, SQLException | NullPointerException, ArithmeticException |
| برای خطاهای قابل پیش‌بینی | برای باگ‌ها و خطاهای برنامه‌نویسی |

## کد مهم

`src/ExceptionDemo.java` — try-catch-finally، throw/throws، custom exception، چند catch.

## خطاهای رایج
- `catch` برای checked exception بدون `throws` → compile error.
- ترتیب catch اشتباه (عمومی قبل از خاص) → unreachable code.
- `finally` را برای return استفاده کردن → exception را می‌بلعد.
- custom exception را `extends Exception` برای unchecked → اشتباه؛ باید `RuntimeException`.

## جمع‌بندی
- try-catch-finally برای مدیریت خطا.
- throw برای پرتاب، throws برای اعلام.
- Checked = باید مدیریت شود، unchecked = runtime.

**جلسهٔ بعد:** try-with-resources.
