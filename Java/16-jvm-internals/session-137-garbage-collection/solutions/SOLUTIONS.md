# پاسخ تمرین‌های جلسه ۱۳۷

## آسان

**۱ و ۲.**
```java
System.gc();   // force GC (not recommended)

List<byte[]> list = new ArrayList<>();
for (int i = 0; i < 100; i++) {
    list.add(new byte[1024 * 1024]);   // 1MB each
}
list.clear();   // make unreachable
```

## متوسط

**۳ و ۴.**
```bash
java -Xms512m -Xmx2g -jar myapp.jar
java -XX:+UseG1GC -jar myapp.jar
java -XX:+UseZGC -jar myapp.jar
```

## سخت

**۵ و ۶.**
```bash
java -Xlog:gc* -jar myapp.jar
java -XX:+HeapDumpOnOutOfMemoryError -jar myapp.jar
java -XX:MaxMetaspaceSize=256m -jar myapp.jar
```
