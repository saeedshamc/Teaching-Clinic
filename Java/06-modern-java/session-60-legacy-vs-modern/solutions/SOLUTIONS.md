# پاسخ تمرین‌های جلسه ۶۰

## آسان

**۱ و ۲.**
```java
// Vector → ArrayList
Vector<String> vec = new Vector<>();
ArrayList<String> list = new ArrayList<>(vec);

// Hashtable → HashMap
Hashtable<String, Integer> ht = new Hashtable<>();
HashMap<String, Integer> map = new HashMap<>(ht);
```

## متوسط

**۳ و ۴.**
```java
// Date/Calendar → java.time
Date old = new Date();
LocalDateTime new = LocalDateTime.now();

// StringBuffer → StringBuilder
StringBuffer oldSb = new StringBuffer();
StringBuilder newSb = new StringBuilder();
```

## سخت

**۵.**
```java
// Migration checklist:
// - Vector → ArrayList
// - Hashtable → HashMap
// - Enumeration → Iterator
// - Date → LocalDate/LocalDateTime
// - Calendar → java.time
// - StringBuffer → StringBuilder
// - Thread.stop() → interrupt()
// - finalize() → try-with-resources
```

**۶.**
```java
// Legacy: maintain کردن کد قدیمی، legacy APIs هنوز پشتیبانی می‌شوند
// Modern: کد جدید، performance-critical، thread-safety، web services
```
