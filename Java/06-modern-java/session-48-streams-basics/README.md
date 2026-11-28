# جلسه ۴۸ — Streams Basics

## اهداف یادگیری
- `Stream` را برای پردازش collectionها به‌کار ببرید
- `filter`, `map`, `collect` را بشناسید
- intermediate در برابر terminal operations را تشخیص دهید
- `stream()` در برابر `parallelStream()` را بفهمید

## پیش‌نیازها
- جلسات ۴۶ و ۴۷ (lambda, method ref)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Stream — pipeline پردازش

```java
import java.util.List;
import java.util.stream.Collectors;

List<String> names = List.of("Ali", "Sara", "Reza", "Mina", "Ahmed");

// filter — انتخاب بر اساس شرط
List<String> filtered = names.stream()
    .filter(name -> name.startsWith("A"))
    .collect(Collectors.toList());
// [Ali, Ahmed]

// map — transform
List<String> upper = names.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());
// [ALI, SARA, REZA, MINA, AHMED]

// forEach — terminal operation
names.stream()
    .filter(name -> name.length() > 3)
    .forEach(System.out::println);
```

### Intermediate vs Terminal

| Intermediate | Terminal |
|---|---|
| `filter`, `map`, `sorted`, `distinct` | `collect`, `forEach`, `count`, `findFirst` |
| lazy — اجرا نمی‌شود تا terminal | eager — اجرا می‌شود |
| برمی‌گرداند Stream | برمی‌گرداند نتیجه |
| می‌توان زنجیره کرد | پایان زنجیره |

```java
// بدون terminal — هیچ اتفاقی نمی‌افتد!
names.stream()
    .filter(name -> name.startsWith("A"))
    .map(String::toUpperCase);
// ❌ هیچ printing یا collect — lazy evaluation
```

### عملیات رایج

```java
// count
long count = names.stream().count();

// findFirst / findAny
Optional<String> first = names.stream().findFirst();

// anyMatch / allMatch / noneMatch
boolean hasA = names.stream().anyMatch(s -> s.startsWith("A"));
boolean allLong = names.stream().allMatch(s -> s.length() > 2);

// min / max با comparator
Optional<String> longest = names.stream()
    .max(Comparator.comparingInt(String::length));

// sorted
List<String> sorted = names.stream()
    .sorted()
    .collect(Collectors.toList());

// distinct
List<String> unique = names.stream()
    .distinct()
    .collect(Collectors.toList());

// limit / skip
List<String> top3 = names.stream()
    .limit(3)
    .collect(Collectors.toList());
```

## کد مهم

`src/StreamDemo.java` — filter/map/collect، intermediate/terminal، عملیات رایج.

## خطاهای رایج
- فراموش کردن terminal operation → stream اجرا نمی‌شود.
- `stream()` را دوبار استفاده کردن → `IllegalStateException` — stream یک‌بار مصرف است.
- `collect(Collectors.toList())` را فراموش کردن → stream برنمی‌گردد.
- side effect در `map`/`filter` → از `forEach` استفاده کنید.

## جمع‌بندی
- Stream = pipeline برای پردازش collection.
- Intermediate = lazy، terminal = eager.
- `filter` برای انتخاب، `map` برای transform، `collect` برای جمع‌آوری.

**جلسهٔ بعد:** Streams Advanced.
