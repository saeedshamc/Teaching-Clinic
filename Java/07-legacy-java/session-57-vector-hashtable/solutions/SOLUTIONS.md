# پاسخ تمرین‌های جلسه ۵۷

## آسان

**۱ و ۲.**
```java
Vector<String> vec = new Vector<>();
vec.add("Ali");
System.out.println(vec.get(0));
System.out.println(vec.size());

Hashtable<String, Integer> table = new Hashtable<>();
table.put("Ali", 20);
System.out.println(table.get("Ali"));
```

## متوسط

**۳ و ۴.**
```java
Enumeration<String> e = vec.elements();
while (e.hasMoreElements()) {
    System.out.println(e.nextElement());
}

Enumeration<String> keys = table.keys();
while (keys.hasMoreElements()) {
    String key = keys.nextElement();
    System.out.println(key + " = " + table.get(key));
}
```

## سخت

**۵.**
```java
// Vector synchronized — برای single-thread کندتر
// ArrayList non-synchronized — برای most cases سریع‌تر
// برای thread-safe: Collections.synchronizedList(new ArrayList<>())
```

**۶.**
```java
// Hashtable null key/value قبول نمی‌کند
// table.put(null, 1) → NullPointerException
// table.put("key", null) → NullPointerException
// HashMap null را قبول می‌کند
```
