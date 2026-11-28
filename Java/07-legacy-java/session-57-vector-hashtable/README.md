# جلسه ۵۷ — Vector و Hashtable (Legacy Collections)

## اهداف یادگیری
- `Vector` و `Hashtable` را بشناسید
- synchronized بودن آن‌ها را درک کنید
- Enumeration را برای legacy iteration بفهمید
- چرا منسوخ شده‌اند و جایگزین مدرنشان را یاد بگیرید

## پیش‌نیازها
- جلسهٔ ۵۶ (Legacy Overview)

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Vector — synchronized ArrayList

```java
import java.util.Vector;

Vector<String> vec = new Vector<>();
vec.add("Ali");
vec.add("Sara");

// همه متدها synchronized — thread-safe ولی کندتر
System.out.println(vec.get(0));
System.out.println(vec.size());

// Enumeration — legacy iteration
Enumeration<String> e = vec.elements();
while (e.hasMoreElements()) {
    System.out.println(e.nextElement());
}
```

### Hashtable — synchronized HashMap

```java
import java.util.Hashtable;

Hashtable<String, Integer> table = new Hashtable<>();
table.put("Ali", 20);
table.put("Sara", 25);

System.out.println(table.get("Ali"));   // 20

// Enumeration برای keys و values
Enumeration<String> keys = table.keys();
while (keys.hasMoreElements()) {
    String key = keys.nextElement();
    System.out.println(key + " = " + table.get(key));
}
```

### چرا منسوخ شده‌اند؟

| Vector/Hashtable | ArrayList/HashMap |
|---|---|
| synchronized — کندتر | non-synchronized — سریع‌تر |
| Enumeration — قدیمی | Iterator — مدرن |
| Legacy Java 1.0 | Java 1.2+ Collections Framework |
| برای most cases overkill | برای most cases کافی |

### Enumeration vs Iterator

```java
// Enumeration — forward, no remove
Enumeration<String> e = vec.elements();
while (e.hasMoreElements()) {
    String s = e.nextElement();
    // e.remove() وجود ندارد!
}

// Iterator — forward + backward (ListIterator) + remove
Iterator<String> it = vec.iterator();
while (it.hasNext()) {
    String s = it.next();
    it.remove();   // می‌توانید حذف کنید
}
```

## کد مهم

`src/VectorHashtableDemo.java` — Vector، Hashtable، Enumeration، مقایسه با ArrayList/HashMap.

## خطاهای رایج
- `Vector`/`Hashtable` را برای کد جدید استفاده کردن → کندتر.
- `Enumeration` را برای کد جدید استفاده کردن → `Iterator` بهتر است.
- `Hashtable` null key/value قبول نمی‌کند → `NullPointerException`.
- synchronized بودن را برای performance-critical code فراموش کردن.

## جمع‌بندی
- Vector = synchronized ArrayList، Hashtable = synchronized HashMap.
- Enumeration = legacy iteration، Iterator = modern.
- برای کد جدید از ArrayList/HashMap استفاده کنید.

**جلسهٔ بعد:** Date و Calendar.
