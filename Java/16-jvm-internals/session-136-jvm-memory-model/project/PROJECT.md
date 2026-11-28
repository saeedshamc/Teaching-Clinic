# پروژهٔ جلسه ۱۳۶ — تحلیل JVM Memory

یک پروژهٔ تحلیل JVM memory بسازید:
- `MemoryDemo` با stack/heap demo
- `HeapDemo` با `Runtime.getRuntime()` برای memory info
- `StackOverflowDemo` برای `StackOverflowError`
- `MemoryAnalyzer` با `-Xms`, `-Xmx` flags
- `GCLogger` با `-XX:+PrintGCDetails`

نمونه:
```
Stack variable: 42
Heap string: Hello Heap
Max memory: 2048 MB
Total memory: 512 MB
Used memory: 128 MB
StackOverflowError caught!
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
