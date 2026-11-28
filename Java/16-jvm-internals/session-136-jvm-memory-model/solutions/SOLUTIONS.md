# پاسخ تمرین‌های جلسه ۱۳۶

## آسان

**۱ و ۲.**
```java
public class MemoryDemo {
    public static void main(String[] args) {
        int stackVar = 42;                    // stack
        String heapString = new String("Hi"); // heap
        int[] heapArray = new int[1000];      // heap
        System.out.println(stackVar + " " + heapString);
    }
}

Runtime runtime = Runtime.getRuntime();
long maxMemory = runtime.maxMemory() / 1024 / 1024;
System.out.println("Max: " + maxMemory + " MB");
```

## متوسط

**۳ و ۴.**
```java
static void infiniteRecursion() {
    infiniteRecursion();   // StackOverflowError
}

// JVM flags
java -Xms512m -Xmx2g -jar myapp.jar
```

## سخت

**۵ و ۶.**
```bash
java -XX:+PrintGCDetails -jar myapp.jar
java -XX:+HeapDumpOnOutOfMemoryError -jar myapp.jar
java -XX:MaxMetaspaceSize=256m -jar myapp.jar
```
