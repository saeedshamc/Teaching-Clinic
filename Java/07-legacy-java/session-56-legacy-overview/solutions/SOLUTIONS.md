# پاسخ تمرین‌های جلسه ۵۶

## آسان

**۱ و ۲.**
```java
Vector<String> vec = new Vector<>();   // synchronized — کندتر
ArrayList<String> list = new ArrayList<>();   // سریع‌تر — بهتر برای most cases

Hashtable<String, Integer> ht = new Hashtable<>();   // synchronized — کندتر
HashMap<String, Integer> map = new HashMap<>();     // سریع‌تر — بهتر
```

## متوسط

**۳.**
```java
Vector<String> vec = new Vector<>();
vec.add("a"); vec.add("b");

// Enumeration (legacy)
Enumeration<String> e = vec.elements();
while (e.hasMoreElements()) System.out.println(e.nextElement());

// Iterator (modern)
Iterator<String> it = vec.iterator();
while (it.hasNext()) System.out.println(it.next());
```

**۴.**
```java
Date old = new Date();           // legacy — deprecated
LocalDateTime new = LocalDateTime.now();   // modern — java.time
```

## سخت

**۵.**
```java
StringBuffer sb = new StringBuffer();   // synchronized — کندتر
StringBuilder sb2 = new StringBuilder();   // سریع‌تر — بهتر
```

**۶.**
```java
// در IDE: Alt+Enter روی deprecated method → "Replace with modern alternative"
// یا: javac -Xlint:deprecation برای دیدن warningها
```
