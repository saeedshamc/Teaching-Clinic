# جلسه ۱۳۹ — JVM Profiling: Performance Analysis & Monitoring

## اهداف یادگیری
- `JVM Profiling` را برای performance analysis بشناسید
- `JFR`, `VisualVM`, `JMC`, `async-profiler` را بفهمید
- `Thread.dump`, `jstack`, `jmap` را برای debugging استفاده کنید
- `-Xlog`, `-XX:+PrintGCDetails` را برای logging استفاده کنید

## پیش‌نیازها
- جلسهٔ ۱۳۸ (ClassLoader)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Profiling Tools

| Tool | Purpose | Usage |
|---|---|---|
| **JFR** | Java Flight Recorder | `jcmd <pid> JFR.start` |
| **VisualVM** | GUI profiler | `jvisualvm` |
| **JMC** | Java Mission Control | `jmc` |
| **async-profiler** | Low-overhead profiler | `./profiler.sh` |
| **jstack** | Thread dump | `jstack <pid>` |
| **jmap** | Heap dump | `jmap -dump:live,format=b,file=heap.hprof <pid>` |

### JFR — Java Flight Recorder

```bash
# Start recording
jcmd <pid> JFR.start duration=60s filename=recording.jfr

# Dump recording
jcmd <pid> JFR.dump filename=recording.jfr

# Analyze in JMC
jmc recording.jfr
```

### Thread Dump — jstack

```bash
# Get thread dump
jstack <pid> > threaddump.txt

# Or use jcmd
jcmd <pid> Thread.print > threaddump.txt
```

### Heap Dump — jmap

```bash
# Heap dump
jmap -dump:live,format=b,file=heap.hprof <pid>

# Analyze in VisualVM or Eclipse MAT
```

### JVM Flags for Profiling

```bash
# GC logging
java -Xlog:gc* -jar myapp.jar

# GC details (Java 8)
java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -jar myapp.jar

# Heap dump on OOM
java -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heap.hprof -jar myapp.jar

# JFR
java -XX:+FlightRecorder -XX:StartFlightRecording=duration=60s,filename=recording.jfr -jar myapp.jar
```

## کد مهم

`src/ProfilingDemo.java` — profiling demo.
`src/ThreadDumpDemo.java` — thread dump example.
`src/MemoryLeakDemo.java` — memory leak example.

## خطاهای رایج
- `jstack` را بدون `pid` استفاده کردن → `Usage: jstack <pid>`.
- `jmap` را بدون `-dump` استفاده کردن → no output.
- `jvisualvm` را بدون JDK استفاده کردن → not found.
- `JFR` را بدون `-XX:+FlightRecorder` استفاده کردن → not available.

## جمع‌بندی فاز ۹ — Testing + Patterns + JVM

| جلسه | موضوع |
|---|---|
| ۱۲۵ | JUnit 5 |
| ۱۲۶ | Mockito |
| ۱۲۷ | TDD |
| ۱۲۸ | Integration Tests |
| ۱۲۹ | Testing Capstone |
| ۱۳۰ | Creational Patterns |
| ۱۳۱ | Structural Patterns |
| ۱۳۲ | Behavioral Patterns |
| ۱۳۳ | Factory & Builder |
| ۱۳۴ | Singleton & Observer & Strategy |
| ۱۳۵ | Patterns Capstone |
| ۱۳۶ | JVM Memory Model |
| ۱۳۷ | Garbage Collection |
| ۱۳۸ | ClassLoader |
| ۱۳۹ | JVM Profiling |

**فاز ۹ کامل شد — ۱۵ جلسه ✅**
