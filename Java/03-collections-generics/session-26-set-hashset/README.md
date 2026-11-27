# جلسه ۲۶ — Set و HashSet

## اهداف یادگیری
- `Set` interface و `HashSet` را به‌کار ببرید
- duplicate نبودن و عدم ترتیب را بفهمید
- `LinkedHashSet` و `TreeSet` را بشناسید
- Set operations (union, intersection, difference) را پیاده کنید

## پیش‌نیازها
- جلسهٔ ۲۵ (List)

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### Set — بدون duplicate

```java
import java.util.Set;
import java.util.HashSet;

Set<String> names = new HashSet<>();
names.add("Ali");
names.add("Sara");
names.add("Ali");          // duplicate — نادیده گرفته می‌شود
System.out.println(names.size());  // 2
System.out.println(names);         // [Ali, Sara] یا [Sara, Ali] — ترتیب تضمین نیست
```

### HashSet در برابر LinkedHashSet در برابر TreeSet

| HashSet | LinkedHashSet | TreeSet |
|---|---|---|
| بدون ترتیب | ترتیب درج | مرتب بر اساس natural ordering |
| O(1) | O(1) | O(log n) |
| برای uniqueness سریع | وقتی ترتیب درج مهم است | وقتی مرتب‌سازی می‌خواهید |

```java
Set<String> hash = new HashSet<>();          // بدون ترتیب
Set<String> linked = new LinkedHashSet<>();  // ترتیب درج
Set<String> tree = new TreeSet<>();          // مرتب (الفبا)
```

### Set operations

```java
Set<Integer> a = new HashSet<>(Set.of(1, 2, 3));
Set<Integer> b = new HashSet<>(Set.of(3, 4, 5));

// union — 
Set<Integer> union = new HashSet<>(a);
union.addAll(b);   // [1, 2, 3, 4, 5]

// intersection — اشتراک
Set<Integer> inter = new HashSet<>(a);
inter.retainAll(b);   // [3]

// difference — تفاضل
a.removeAll(b);   // a = [1, 2] — b حذف شد
```

## کد مهم

`src/SetDemo.java` — HashSet، LinkedHashSet، TreeSet، set operations.

## خطاهای رایج
- `get(i)` روی Set → وجود ندارد — Set index ندارد.
- `add` روی `Set.of` → `UnsupportedOperationException`.
- `null` در TreeSet → `NullPointerException` (بدون comparator).
- duplicate را انتظار داشتن در HashSet → نادیده گرفته می‌شود.

## جمع‌بندی
- Set = unique elements، بدون duplicate.
- HashSet سریع، LinkedHashSet با ترتیب درج، TreeSet مرتب.
- Union/intersection/difference با `addAll`/`retainAll`/`removeAll`.

**جلسهٔ بعد:** Map و HashMap.
