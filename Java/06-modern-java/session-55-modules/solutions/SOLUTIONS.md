# پاسخ تمرین‌های جلسه ۵۵

## آسان

**۱ و ۲.**
```java
// module-info.java
module com.example.test {
    requires java.base;
}

// compile:
javac --module-path out -d out $(find src -name "*.java")
// run:
java --module-path out -m com.example.test/com.example.test.Main
```

## متوسط

**۳ و ۴.**
```java
module com.example.test {
    requires java.base;
    requires java.sql;
    exports com.example.test.api;
}
```

## سخت

**۵.**
```java
// module com.example.lib { exports com.example.lib.api; }
// module com.example.app {
//     requires com.example.lib;
// }
```

**۶.**
```java
// opens com.example.test.internal to com.example.framework;
```
