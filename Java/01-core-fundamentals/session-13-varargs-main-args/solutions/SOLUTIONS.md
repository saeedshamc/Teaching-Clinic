# پاسخ تمرین‌های جلسه ۱۳

## آسان

**۱.**
```java
public static void main(String[] args) {
    System.out.println("count=" + args.length);
    for (String a : args) System.out.println(a);
}
```

**۲.**
```java
static void print(String... words) {
    for (String w : words) System.out.print(w + " ");
    System.out.println();
}
```

## متوسط

**۳.**
```java
static int sum(int... nums) {
    int s = 0;
    for (int n : nums) s += n;
    return s;
}
```

**۴.**
```java
static void log(String level, String... msgs) {
    System.out.print("[" + level + "] ");
    for (String m : msgs) System.out.print(m + " ");
    System.out.println();
}
```

## سخت

**۵.**
```java
public static void main(String[] args) {
    if (args.length < 2) {
        System.out.println("Usage: java App <file> <words...>");
        return;
    }
    String file = args[0];
    StringBuilder sb = new StringBuilder();
    for (int i = 1; i < args.length; i++) sb.append(args[i]).append(" ");
    System.out.println("File: " + file + ", Words: " + sb);
}
```

**۶.**
```java
static void print(int... nums) { for (int n : nums) System.out.print(n + " "); }
static void print(String... words) { for (String w : words) System.out.print(w + " "); }
// print(1,2,3) → int version; print("a","b") → String version
// print() → ambiguous! باید cast کنید: print((int[]) null) یا print((String[]) null)
```
