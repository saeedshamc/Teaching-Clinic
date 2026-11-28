# جلسه ۱۳۶ — JVM Memory Model: Heap, Stack, Metaspace

## اهداف یادگیری
- `JVM Memory Model` را برای memory areas بشناسید
- `Heap`, `Stack`, `Metaspace`, `PC Register`, `Native Method Stack` را بفهمید
- `StackOverflowError`, `OutOfMemoryError` را در نظر بگیرید
- `-Xms`, `-Xmx`, `-XX:MaxMetaspaceSize` را برای tuning استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۳۵ (Patterns Capstone)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### JVM Memory Areas

```
┌─────────────────────────────────────┐
│           JVM Memory                │
├─────────────────────────────────────┤
│  Heap          → Objects            │
│  Stack         → Method calls       │
│  Metaspace     → Class metadata     │
│  PC Register   → Instruction pointer│
│  Native Stack  → JNI calls          │
└─────────────────────────────────────┘
```

### Heap — object storage

```java
// Objects live in Heap
String name = new String("Ali");   // String object in Heap
int[] numbers = new int[100];      // Array in Heap
```

**Heap structure:**
- **Young Generation** — new objects (Eden, Survivor)
- **Old Generation** — long-lived objects
- **Metaspace** — class metadata (replaces PermGen in Java 8+)

### Stack — method calls

```java
void method1() {
    int x = 10;              // local variable on Stack
    method2();               // method call on Stack
}

void method2() {
    int y = 20;
}
```

**Stack overflow:**
```java
void infiniteRecursion() {
    infiniteRecursion();   // StackOverflowError
}
```

### Metaspace — class metadata

```java
// Class definitions stored in Metaspace
public class MyClass { }
```

**Metaspace tuning:**
```bash
java -XX:MaxMetaspaceSize=256m -jar myapp.jar
```

### JVM Flags

```bash
# Heap size
java -Xms512m -Xmx2g -jar myapp.jar

# Metaspace size
java -XX:MaxMetaspaceSize=256m -jar myapp.jar

# Print heap info
java -XX:+PrintGCDetails -jar myapp.jar

# Heap dump on OOM
java -XX:+HeapDumpOnOutOfMemoryError -jar myapp.jar
```

## کد مهم

`src/MemoryDemo.java` — heap vs stack demo.
`src/StackOverflowDemo.java` — stack overflow example.
`src/HeapDemo.java` — heap usage demo.

## خطاهای رایج
- `new` را بدون limit استفاده کردن → `OutOfMemoryError`.
- Recursion را بدون base case استفاده کردن → `StackOverflowError`.
- `-Xmx` را بدون `-Xms` استفاده کردن → GC pressure.
- `String` concatenation در loop → `OutOfMemoryError` (before Java 9).

## جمع‌بندی
- Heap برای objects — Young/Old generation.
- Stack برای method calls — `StackOverflowError` on overflow.
- Metaspace برای class metadata — `-XX:MaxMetaspaceSize`.
- JVM flags برای tuning — `-Xms`, `-Xmx`, `-XX:+PrintGCDetails`.

**جلسهٔ بعد:** Garbage Collection.
