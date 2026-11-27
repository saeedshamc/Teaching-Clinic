# پاسخ تمرین‌های جلسه ۱۲

## آسان

**۱.**
```java
double d = 3.99;
int i = (int) d;
System.out.println(i);   // 3 — truncate نه round
```

**۲.**
```java
int n = Integer.parseInt("42");        // 42
double d = Double.parseDouble("3.14"); // 3.14
```

## متوسط

**۳.**
```java
int x = 42;
Integer boxed = x;      // boxing
int unboxed = boxed;    // unboxing
Integer nullBoxed = null;
// int n = nullBoxed;   // NullPointerException
```

**۴.**
```java
int n = 42;
String s1 = Integer.toString(n);   // "42"
String s2 = String.valueOf(n);     // "42"
String s3 = n + "";                 // concat — "42"
```

## سخت

**۵.**
```java
byte b = (byte) 300;    // 300 mod 256 = 44
long big = 9_000_000_000L;
int i = (int) big;      // overflow — مقدار wrap می‌شود
System.out.println(b + " " + i);
```

**۶.**
```java
static Integer parseSafe(String s) {
    try {
        return Integer.parseInt(s);
    } catch (NumberFormatException e) {
        return null;   // یا throw
    }
}
```
