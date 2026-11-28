# پاسخ تمرین‌های جلسه ۴۶

## آسان

**۱ و ۲.**
```java
Runnable r = () -> System.out.println("Hello");

interface Calculator { int calculate(int a, int b); }
Calculator add = (a, b) -> a + b;
```

## متوسط

**۳.**
```java
StringProcessor upper = input -> {
    String result = input.toUpperCase();
    return result + "!";
};
```

**۴.**
```java
static void process(String input, StringProcessor p) {
    System.out.println(p.process(input));
}
process("test", s -> s.length() * 2);
```

## سخت

**۵.**
```java
Predicate<Integer> isEven = n -> n % 2 == 0;
Predicate<Integer> isPositive = n -> n > 0;
```

**۶.**
```java
Runnable r = () -> {
    try {
        Thread.sleep(1000);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
};
```
