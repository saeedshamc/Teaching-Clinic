# پاسخ تمرین‌های جلسه ۱۳۹

## آسان

**۱ و ۲.**
```java
long start = System.currentTimeMillis();
int result = computeIntensive(1000000);
long duration = System.currentTimeMillis() - start;

Thread thread = new Thread(() -> { /* work */ });
thread.start();
```

## متوسط

**۳ و ۴.**
```bash
jcmd <pid> JFR.start duration=60s filename=recording.jfr
jmap -dump:live,format=b,file=heap.hprof <pid>
```

## سخت

**۵ و ۶.**
```bash
jvisualvm <pid>
jmc
./profiler.sh -d 60 -f profile.html <pid>
```
