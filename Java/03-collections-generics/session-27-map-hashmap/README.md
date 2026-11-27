# جلسه ۲۷ — Map و HashMap

## اهداف یادگیری
- `Map` interface و `HashMap` را به‌کار ببرید
- `put`, `get`, `containsKey`, `remove` را بشناسید
- iteration روی Map (entrySet, keySet, values) را یاد بگیرید
- `LinkedHashMap` و `TreeMap` را بشناسید

## پیش‌نیازها
- جلسات ۲۵ و ۲۶

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Map — key-value pairs

```java
import java.util.Map;
import java.util.HashMap;

Map<String, Integer> ages = new HashMap<>();
ages.put("Ali", 20);
ages.put("Sara", 22);
ages.put("Ali", 25);          // key تکراری — value به‌روز می‌شود
System.out.println(ages.get("Ali"));    // 25
System.out.println(ages.size());        // 2
System.out.println(ages.containsKey("Sara"));   // true
System.out.println(ages.containsValue(22));      // true
ages.remove("Ali");
System.out.println(ages);               // {Sara=22}
```

### iteration روی Map

```java
// entrySet — key + value
for (Map.Entry<String, Integer> entry : ages.entrySet()) {
    System.out.println(entry.getKey() + " = " + entry.getValue());
}

// keySet — فقط keyها
for (String key : ages.keySet()) {
    System.out.println(key);
}

// values — فقط valueها
for (Integer val : ages.values()) {
    System.out.println(val);
}
```

### HashMap در برابر LinkedHashMap در برابر TreeMap

| HashMap | LinkedHashMap | TreeMap |
|---|---|---|
| بدون ترتیب | ترتیب درج | مرتب بر اساس key |
| O(1) | O(1) | O(log n) |
| پیش‌فرض | وقتی ترتیب مهم است | وقتی sorted می‌خواهید |

```java
Map<String, Integer> hash = new HashMap<>();
Map<String, Integer> linked = new LinkedHashMap<>();
Map<String, Integer> tree = new TreeMap<>();
```

### getOrDefault و computeIfAbsent

```java
Map<String, Integer> counts = new HashMap<>();
counts.put("java", counts.getOrDefault("java", 0) + 1);   // اگر نبود 0 بعد +1

counts.computeIfAbsent("python", k -> 0);   // اگر نبود بساز
counts.merge("java", 1, Integer::sum);      // java += 1
```

## کد مهم

`src/MapDemo.java` — HashMap، iteration، LinkedHashMap، TreeMap، getOrDefault/merge.

## خطاهای رایج
- `get(key)` روی key ناموجود → `null` برمی‌گرداند نه خطا.
- `null` key در TreeMap → `NullPointerException`.
- `put` با key تکراری → value قبلی overwrite می‌شود.
- iteration روی `entrySet` را فراموش کردن → فقط keySet یا values.

## جمع‌بندی
- Map = key-value pairs، key unique.
- HashMap پیش‌فرض، LinkedHashMap با ترتیب، TreeMap sorted.
- `entrySet` برای iteration کامل، `keySet`/`values` برای 부분.

**جلسهٔ بعد:** Queue و Deque.
