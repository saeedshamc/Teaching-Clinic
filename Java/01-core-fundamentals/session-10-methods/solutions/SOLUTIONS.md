# پاسخ تمرین‌های جلسه ۱۰

## آسان

**۱.**
```java
static int max(int a, int b) { return a > b ? a : b; }
```

**۲.**
```java
static void greet(String name) {
    System.out.println("Hello, " + name);
}
```

## متوسط

**۳.**
```java
static int multiply(int a, int b) { return a * b; }
static double multiply(double a, double b) { return a * b; }
static String multiply(String s, int n) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < n; i++) sb.append(s);
    return sb.toString();
}
```

**۴.**
```java
class Counter {
    int count = 0;          // instance — هر نمونه جدا
    static int total = 0;   // static — مشترک بین همه
    void increment() { count++; total++; }
    void print() { System.out.println("count=" + count + ", total=" + total); }
}
```

## سخت

**۵.**
```java
static class Stats {
    int max, min;
    double avg;
}
static Stats analyze(int[] arr) {
    Stats s = new Stats();
    s.max = s.min = arr[0];
    int sum = 0;
    for (int n : arr) {
        s.max = Math.max(s.max, n);
        s.min = Math.min(s.min, n);
        sum += n;
    }
    s.avg = (double) sum / arr.length;
    return s;
}
```

**۶.**
```java
static int factorial(int n) {
    if (n <= 1) return 1;
    return n * factorial(n - 1);
}
static int fib(int n) {
    if (n <= 1) return n;
    return fib(n - 1) + fib(n - 2);
}
```
