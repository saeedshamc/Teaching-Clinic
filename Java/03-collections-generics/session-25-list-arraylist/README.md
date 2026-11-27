# جلسه ۲۵ — List و ArrayList

## اهداف یادگیری
- `List` interface و `ArrayList` را به‌کار ببرید
- متدهای رایج List را بشناسید (add, get, set, remove, size...)
- `LinkedList` در برابر `ArrayList` را بفهمید
- `List.of` و `Arrays.asList` را بشناسید

## پیش‌نیازها
- جلسات ۱۶–۲۴ (OOP)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### List interface

```java
import java.util.List;
import java.util.ArrayList;

List<String> names = new ArrayList<>();   // interface + پیاده‌سازی
names.add("Ali");
names.add("Sara");
names.add(1, "Reza");          // insert در index 1
System.out.println(names.get(0)); // Ali
System.out.println(names.size()); // 3
names.set(0, "Mina");           // جایگزینی
names.remove("Sara");           // حذف با مقدار
names.remove(0);                // حذف با index
names.contains("Ali");          // true
names.indexOf("Mina");          // 0
names.isEmpty();                // false
names.clear();                  // خالی کردن
```

### ArrayList در برابر LinkedList

| ArrayList | LinkedList |
|---|---|
| دسترسی با index سریع (O(1)) | دسترسی با index کند (O(n)) |
| add/remove آخر سریع | add/remove وسط سریع (O(1)) |
| حافظهٔ کمتر | overhead بیشتر |
| پیش‌فرض برای بیشتر موارد | برای queue/deque زیاد استفاده می‌شود |

### List.of و Arrays.asList

```java
List<String> immutable = List.of("a", "b", "c");   // immutable — نمی‌توان add/remove
List<String> fixed = Arrays.asList("x", "y");       // fixed-size — می‌توان set ولی نه add/remove
List<String> mutable = new ArrayList<>(List.of("1", "2"));  // mutable کپی
```

### Iteration روی List

```java
for (String s : names) { ... }                    // for-each
for (int i = 0; i < names.size(); i++) { ... }    // index loop
Iterator<String> it = names.iterator();           // iterator (فاز بعد)
while (it.hasNext()) { String s = it.next(); }
```

## کد مهم

`src/ListDemo.java` — List interface، ArrayList، LinkedList، List.of، iteration.

## خطاهای رایج
- `UnsupportedOperationException` روی `List.of` → immutable است.
- `ConcurrentModificationException` → در for-each حذف نکنید؛ از Iterator یا index loop استفاده کنید.
- `get(i)` خارج از محدوده → `IndexOutOfBoundsException`.
- `new ArrayList<>()` بدون import → compile error.

## جمع‌بندی
- `List` interface برای ordered collection با duplicate.
- `ArrayList` پیش‌فرض، `LinkedList` برای insert/delete وسط.
- `List.of` برای immutable، `new ArrayList` برای mutable.

**جلسهٔ بعد:** Set و HashSet.
