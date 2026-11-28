# پروژهٔ جلسه ۱۳۷ — تحلیل Garbage Collection

یک پروژهٔ تحلیل GC بسازید:
- `GCDemo` با `System.gc()` برای force GC
- `MemoryPressure` با `List<byte[]>` برای memory pressure
- `FinalizationDemo` با `finalize()` (deprecated)
- `GCLogger` با `-Xlog:gc*` برای GC logging
- `HeapDump` با `-XX:+HeapDumpOnOutOfMemoryError`

نمونه:
```
Used memory after GC: 45 MB
Iteration 0 — Used: 12 MB
Iteration 50 — Used: 256 MB
Memory pressure detected — clearing list
```

## صورت پروژه
پاسخ در `solution/` — می‌توانید `src/` را گسترش دهید.
