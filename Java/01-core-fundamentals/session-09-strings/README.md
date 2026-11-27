# جلسه ۰۹ — String و StringBuilder

## اهداف یادگیری
- `String` immutable را بفهمید و با آن کار کنید
- متدهای رایج String را به‌کار ببرید (length, charAt, substring, equals, indexOf...)
- `StringBuilder`/`StringBuffer` را برای concat در حلقه بشناسید
- `==` در برابر `equals()` را درک کنید

## پیش‌نیازها
- جلسات ۰۴–۰۸

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### String immutable

```java
String s = "Hello";
s.toUpperCase();        // مقدار جدید می‌سازد ولی برنمی‌گرداند!
System.out.println(s);  // Hello — تغییر نکرد چون immutable است
s = s.toUpperCase();    // باید assign کرد
System.out.println(s);  // HELLO
```

### متدهای رایج

```java
String name = "Ali Rezaei";
name.length();                    // 11
name.charAt(0);                   // 'A'
name.indexOf("Rezaei");           // 4
name.substring(4);                // "Rezaei"
name.substring(4, 9);             // "Rezae"
name.contains("Ali");             // true
name.startsWith("Ali");           // true
name.endsWith("ei");              // true
name.equals("ali rezaei");        // false — case sensitive
name.equalsIgnoreCase("ali rezaei"); // true
name.toUpperCase();               // "ALI REZAEI"
name.toLowerCase();               // "ali rezaei"
name.trim();                      // حذف فاصله‌های ابتدا/انتها
name.replace('a', 'A');           // "Ali Rezaei" → "Ali Rezaei" (a→A)
name.split(" ");                  // ["Ali", "Rezaei"]
```

### equals در برابر ==

```java
String a = new String("Hello");
String b = new String("Hello");
System.out.println(a == b);      // false — مقایسهٔ reference!
System.out.println(a.equals(b)); // true  — مقایسهٔ محتوا
```
- `==` روی String فقط اگر literal یکسان باشد (string pool) true می‌شود.
- **همیشه** برای مقایسهٔ محتوا از `.equals()` استفاده کنید.

### StringBuilder (برای concat در حلقه)

```java
StringBuilder sb = new StringBuilder();
for (int i = 0; i < 1000; i++) {
    sb.append(i).append(", ");    // بدون ساخت String جدید در هر تکرار
}
String result = sb.toString();
```
- `String` concat با `+` در حلقه کند است چون هر بار String جدید می‌سازد (immutable).
- `StringBuilder` mutable است و درجا append می‌کند — برای concat زیاد.
- `StringBuffer` thread-safe نسخه است (کُندتر) — در فاز ۳ concurrency می‌بینیم.

### format

```java
String formatted = String.format("Name: %s, Age: %d, Score: %.2f", "Ali", 20, 17.5);
System.out.println(formatted);
// یا printf:
System.out.printf("%s is %d years old%n", "Ali", 20);
```

## کد مهم

`src/StringDemo.java` — immutability، متدها، equals vs ==، StringBuilder در حلقه، format.

## خطاهای رایج
- `s.toUpperCase()` را بدون assign فراموش کردن → مقدار تغییر نمی‌کند.
- `==` برای مقایسهٔ String → false حتی با محتوای یکسان.
- `charAt` روی index نامعتبر → `StringIndexOutOfBoundsException`.
- concat با `+` در حلقهٔ بزرگ → کند؛ از `StringBuilder` استفاده کنید.

## جمع‌بندی
- String immutable → متدها مقدار جدید می‌دهند، باید assign کنید.
- `.equals()` برای محتوا، `==` برای reference.
- `StringBuilder` برای concat در حلقه.

**جلسهٔ بعد:** متدها (Methods) و overloading.
