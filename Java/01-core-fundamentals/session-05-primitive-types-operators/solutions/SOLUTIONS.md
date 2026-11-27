# پاسخ تمرین‌های جلسه ۰۵

## آسان

**۱.**
```java
int x = 7, y = 2;
System.out.println(x / y);          // 3 — تقسیم صحیح int
double d = x / (double) y;
System.out.println(d);              // 3.5 — cast به double قبل از تقسیم
```
تفاوت: int/int → int (باقیمانده حذف)، double → اعشار.

**۲.** `true && false || true` → `(true && false) || true` → `false || true` → `true`. تقدم `&&` بالاتر از `||` است.

## متوسط

**۳.**
```java
int n = 15;
if (n % 2 == 0) System.out.println("even");
else System.out.println("odd");   // odd
```

**۴.**
```java
int v = 1;
v = v << 3;   // 1 * 2^3 = 8
System.out.println(v);            // 8
```

## سخت

**۵.**
```java
int n = 10;
boolean isEven = (n & 1) == 0;   // bit آخر 0 → زوج
System.out.println(isEven);       // true
```

**۶.** `1 + 2 * 3 == 7` → `1 + 6 == 7` → `7 == 7` → true. `(1 + 2) * 3 == 9` → `3 * 3 == 9` → true. پرانتز تقدم را عوض می‌کند.
