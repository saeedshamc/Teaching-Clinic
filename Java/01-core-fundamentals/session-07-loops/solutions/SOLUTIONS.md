# پاسخ تمرین‌های جلسه ۰۷

## آسان

**۱.**
```java
for (int i = 1; i <= 10; i++) System.out.println(i);
```

**۲.**
```java
int n = 5, fact = 1;
while (n > 1) { fact *= n; n--; }
System.out.println(fact);   // 120
```

## متوسط

**۳.**
```java
int n = 48, count = 0;
do {
    n /= 2;
    count++;
} while (n % 2 == 0);
System.out.println("divisions: " + count);   // 4 (48→24→12→6→3)
```

**۴.**
```java
for (int i = 1; i <= 10; i++) {
    for (int j = 1; j <= 10; j++) {
        System.out.print(i * j + "\t");
    }
    System.out.println();
}
```

## سخت

**۵.**
```java
for (int i = 2; i <= 100; i++) {
    boolean prime = true;
    for (int j = 2; j <= Math.sqrt(i); j++) {
        if (i % j == 0) { prime = false; break; }
    }
    if (prime) System.out.println(i);
}
```

**۶.**
```java
for (int i = 1; i <= 30; i++) {
    if (i % 15 == 0) System.out.println("FizzBuzz");
    else if (i % 3 == 0) System.out.println("Fizz");
    else if (i % 5 == 0) System.out.println("Buzz");
    else System.out.println(i);
}
```
