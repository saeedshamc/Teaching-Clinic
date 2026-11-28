# جلسه ۱۳۷ — Garbage Collection: GC Algorithms & Tuning

## اهداف یادگیری
- `Garbage Collection` را برای automatic memory management بشناسید
- `Minor GC`, `Major GC`, `Full GC` را بفهمید
- `Mark-Sweep`, `Mark-Compact`, `Copying`, `G1`, `ZGC` را بشناسید
- `-Xms`, `-Xmx`, `-XX:+UseG1GC` را برای GC tuning استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۳۶ (JVM Memory Model)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Garbage Collection چیست؟

`GC` — automatic memory management. JVM unreachable objects را شناسایی و حذف می‌کند.

**Reachability:**
- `GC Roots` — objects reachable from stack, static fields, JNI
- `Unreachable` — objects not reachable from GC roots → eligible for GC

### GC Generations

```
Young Generation          Old Generation
├── Eden                  ├── Tenured
├── Survivor 0            └── Metaspace
└── Survivor 1
```

**Minor GC** — Young generation فقط.
**Major GC** — Old generation فقط.
**Full GC** — همهٔ generations.

### GC Algorithms

| Algorithm | Description | Use Case |
|---|---|---|
| **Serial** | Single-threaded | Small heaps |
| **Parallel** | Multi-threaded | Throughput |
| **CMS** | Concurrent Mark-Sweep | Low latency (deprecated) |
| **G1** | Garbage First | Default, balanced |
| **ZGC** | Zero Garbage Collection | Very low latency |
| **Shenandoah** | Concurrent | Very low latency |

### G1 GC — default در Java 9+

```bash
java -XX:+UseG1GC -Xms512m -Xmx2g -jar myapp.jar
```

**G1 characteristics:**
- Region-based heap
- Concurrent marking
- Predictable pause times
- Humongous object handling

### GC Tuning

```bash
# Heap size
java -Xms512m -Xmx2g -jar myapp.jar

# GC algorithm
java -XX:+UseG1GC -jar myapp.jar
java -XX:+UseZGC -jar myapp.jar

# GC logging
java -Xlog:gc* -jar myapp.jar
java -XX:+PrintGCDetails -jar myapp.jar  # Java 8

# Heap dump
java -XX:+HeapDumpOnOutOfMemoryError -jar myapp.jar
```

## کد مهم

`src/GCDemo.java` — GC behavior demo.
`src/MemoryPressure.java` — memory pressure example.
`src/FinalizationDemo.java` — finalization (deprecated).

## خطاهای رایج
- `finalize()` را برای resource cleanup استفاده کردن → deprecated, unreliable.
- `System.gc()` را برای force GC استفاده کردن → performance hit.
- `-Xmx` را بدون `-Xms` استفاده کردن → GC pressure.
- `String` concatenation در loop → `OutOfMemoryError` (before Java 9).

## جمع‌بندی
- GC برای automatic memory management — reachability analysis.
- Generations: Young (Eden, Survivor), Old (Tenured), Metaspace.
- Algorithms: Serial, Parallel, G1, ZGC, Shenandoah.
- Tuning: `-Xms`, `-Xmx`, `-XX:+UseG1GC`, `-Xlog:gc*`.

**جلسهٔ بعد:** ClassLoader.
