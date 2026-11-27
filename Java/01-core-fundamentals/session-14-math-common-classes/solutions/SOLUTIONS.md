# پاسخ تمرین‌های جلسه ۱۴

## آسان

**۱.**
```java
System.out.println(Math.max(3, 7));   // 7
System.out.println(Math.min(3, 7));   // 3
System.out.println(Math.abs(-5));     // 5
```

**۲.**
```java
Random rand = new Random();
for (int i = 0; i < 10; i++) {
    int n = rand.nextInt(100);   // 0..99
    System.out.println(n);
}
```

## متوسط

**۳.**
```java
BigInteger fact = BigInteger.ONE;
for (int i = 1; i <= 50; i++) {
    fact = fact.multiply(BigInteger.valueOf(i));
}
System.out.println(fact);   // 30414093201713378043612608166064768844377641568960512000000000000
```

**۴.**
```java
BigDecimal price = new BigDecimal("19.99");
BigDecimal tax = price.multiply(new BigDecimal("0.09"));
BigDecimal total = price.add(tax);
System.out.println(total);   // 21.7891
```

## سخت

**۵.**
```java
Random rand = new Random();
int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, sum = 0;
for (int i = 0; i < 100; i++) {
    int n = rand.nextInt(100) + 1;   // 1..100
    max = Math.max(max, n);
    min = Math.min(min, n);
    sum += n;
}
System.out.println("max=" + max + " min=" + min + " avg=" + (sum / 100.0));
```

**۶.**
```java
Calendar cal = Calendar.getInstance();
cal.add(Calendar.DAY_OF_MONTH, 30);
Date future = cal.getTime();
System.out.println(future);
```
