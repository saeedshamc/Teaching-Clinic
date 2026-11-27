# جلسه ۱۴ — Math، Random و کلاس‌های رایج

## اهداف یادگیری
- `Math` utility class را به‌کار ببرید (abs, max, min, pow, sqrt, random...)
- `Random` و `ThreadLocalRandom` را برای عدد تصادفی بشناسید
- `BigInteger`/`BigDecimal` را برای دقت بالا بشناسید
- `Date`/`Calendar` را به‌صورت مقدماتی ببینید (جزئیات در فاز ۴ legacy)

## پیش‌نیازها
- جلسات ۰۴–۱۳

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Math

```java
Math.abs(-5);          // 5
Math.max(3, 7);        // 7
Math.min(3, 7);        // 3
Math.pow(2, 10);       // 1024.0
Math.sqrt(16);         // 4.0
Math.ceil(3.2);        // 4.0 — سقف
Math.floor(3.8);       // 3.0 — کف
Math.round(3.5);       // 4 — nearest
Math.random();         // [0.0, 1.0) — double تصادفی
```

### Random و ThreadLocalRandom

```java
import java.util.Random;

Random rand = new Random();
rand.nextInt();          // هر int
rand.nextInt(100);       // 0..99
rand.nextDouble();       // [0,1)
rand.nextBoolean();      // true/false

// Java 8+ — بدون seed، thread-safe‌تر برای concurrent
import java.util.concurrent.ThreadLocalRandom;
int n = ThreadLocalRandom.current().nextInt(1, 100);   // 1..99
```

### BigInteger و BigDecimal — دقت بالا

```java
import java.math.BigInteger;
import java.math.BigDecimal;

// BigInteger — عدد صحیح بدون محدودیت
BigInteger big = new BigInteger("123456789012345678901234567890");
BigInteger sum = big.add(new BigInteger("1"));
BigInteger fact = BigInteger.ONE;
for (int i = 1; i <= 30; i++) fact = fact.multiply(BigInteger.valueOf(i));
System.out.println(fact);   // 30! — بزرگ‌تر از long

// BigDecimal — اعشار دقیق (برای پول)
BigDecimal price = new BigDecimal("19.99");
BigDecimal tax = price.multiply(new BigDecimal("0.09"));
BigDecimal total = price.add(tax);
System.out.println(total);   // 21.7891
```

### Date و Calendar (مقدماتی)

```java
import java.util.Date;
import java.util.Calendar;

Date now = new Date();                    // زمان فعلی
System.out.println(now);

Calendar cal = Calendar.getInstance();    // singleton
cal.set(2024, Calendar.JANUARY, 15);      // ماه 0-بیس!
cal.add(Calendar.DAY_OF_MONTH, 7);        // +7 روز
Date future = cal.getTime();
```
- `Calendar.MONTH` صفر-بیس است (JANUARY=0) — تلهٔ رایج.
- در Java 8+ `java.time` بهتر است (LocalDate, LocalDateTime) — در فاز ۴ می‌بینیم.

## کد مهم

`src/CommonClassesDemo.java` — Math، Random/ThreadLocalRandom، BigInteger/BigDecimal، Date/Calendar.

## خطاهای رایج
- `Math.random()` همیشه `[0,1)` است نه شامل ۱.
- `new BigDecimal(0.1)` → `0.1000000000000000055511151231257827` — همیشه از `String` استفاده کنید: `new BigDecimal("0.1")`.
- `Calendar.MONTH` صفر-بیس → JANUARY=0 نه 1.
- `int` برای فاکتوریل ۲۰+ → overflow؛ از `BigInteger` استفاده کنید.

## جمع‌بندی
- `Math` برای محاسبات پایه، `Random`/`ThreadLocalRandom` برای تصادفی.
- `BigInteger`/`BigDecimal` برای دقت نامحدود.
- `Date`/`Calendar` قدیمی — در فاز ۴ جایگزین مدرن `java.time` را می‌بینیم.

**جلسهٔ بعد:** مینی‌پروژهٔ جمع‌بندی فاز ۱.
