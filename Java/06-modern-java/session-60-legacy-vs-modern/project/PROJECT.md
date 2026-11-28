# پروژهٔ جلسه ۶۰ — مایگریشن کامل Legacy به Modern

یک کد legacy کامل را به modern Java مایگریت کنید:
- `Vector` → `ArrayList`
- `Hashtable` → `HashMap`
- `Enumeration` → `Iterator`
- `Date` → `LocalDateTime`
- `StringBuffer` → `StringBuilder`
- `Thread.stop()` → `interrupt()` (در کامنت)

نمونه:
```
Before (Legacy):
  Vector[Ali, Sara, Reza]
  Hashtable{Ali=20, Sara=25}
  Date: Sat Nov 28...
  StringBuffer: Hello World

After (Modern):
  ArrayList[Ali, Sara, Reza]
  HashMap{Ali=20, Sara=25}
  LocalDateTime: 2026-11-28T...
  StringBuilder: Hello World
```

## صورت پروژه
پاسخ در `solution/FullMigration.java` — هر دو نسخه را نشان دهید.
