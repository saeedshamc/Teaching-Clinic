# پروژهٔ جلسه ۱۳۹ — تحلیل JVM Performance

یک پروژهٔ تحلیل JVM performance بسازید:
- `ProfilingDemo` با `System.currentTimeMillis()` برای timing
- `ThreadDumpDemo` با multiple threads برای `jstack`
- `MemoryLeakDemo` با `List<byte[]>` برای memory leak
- `GCLogger` با `-Xlog:gc*` برای GC logging
- `JFRRecorder` با `jcmd JFR.start` برای recording

نمونه:
```
Result: 333333833333500000
Duration: 12 ms
Used memory: 128 MB
Threads created — use jstack to dump
Memory leak detected!
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
