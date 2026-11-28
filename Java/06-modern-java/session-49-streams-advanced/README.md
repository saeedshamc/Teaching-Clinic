# جلسه ۴۹ — Streams Advanced

## اهداف یادگیری
- `Collectors` را برای جمع‌آوری پیشرفته به‌کار ببرید
- `groupingBy`, `partitioningBy`, `joining` را بشناسید
- `reduce` و `collect` را برای aggregation بفهمید
- `flatMap` را برای flatten کردن stream بشناسید

## پیش‌نیازها
- جلسهٔ ۴۸ (Streams Basics)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Collectors — جمع‌آوری پیشرفته

```java
import java.util.stream.Collectors;

// joining — الحاق رشته‌ها
String joined = names.stream()
    .collect(Collectors.joining(", "));   // "Ali, Sara, Reza"

// groupingBy — گروه‌بندی
Map<Integer, List<Person>> byAge = people.stream()
    .collect(Collectors.groupingBy(Person::getAge));

// groupingBy با downstream collector
Map<String, Long> countByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.counting()
    ));

// partitioningBy — تقسیم به دو گروه
Map<Boolean, List<Person>> adults = people.stream()
    .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
```

### reduce — کاهش به یک مقدار

```java
// sum
int sum = numbers.stream()
    .reduce(0, Integer::sum);

// max
Optional<Integer> max = numbers.stream()
    .reduce(Integer::max);

// custom reduce
String concat = names.stream()
    .reduce("", (a, b) -> a + " " + b);
```

### flatMap — flatten کردن

```java
List<List<Integer>> nested = List.of(
    List.of(1, 2),
    List.of(3, 4),
    List.of(5, 6)
);

// flatMap — flatten
List<Integer> flat = nested.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
// [1, 2, 3, 4, 5, 6]

// مثال: لیست string به کلمات
List<String> sentences = List.of("Hello world", "Java is fun");
List<String> words = sentences.stream()
    .flatMap(s -> Arrays.stream(s.split(" ")))
    .collect(Collectors.toList());
// [Hello, world, Java, is, fun]
```

### Collectors.toMap

```java
Map<String, Integer> nameToAge = people.stream()
    .collect(Collectors.toMap(
        Person::getName,
        Person::getAge
    ));

// با merge function برای duplicate keys
Map<String, Integer> nameToAge = people.stream()
    .collect(Collectors.toMap(
        Person::getName,
        Person::getAge,
        (a, b) -> a + b   // اگر duplicate → جمع
    ));
```

## کد مهم

`src/StreamAdvancedDemo.java` — Collectors، groupingBy، reduce، flatMap، toMap.

## خطاهای رایج
- `groupingBy` بدون downstream → `List` برمی‌گرداند نه مقدار تکی.
- `toMap` با duplicate key → `IllegalStateException` — merge function لازم است.
- `flatMap` را با `map` اشتباه گرفتن → map تک‌سطحی، flatMap چندسطحی.
- `reduce` بدون identity → `Optional` برمی‌گرداند.

## جمع‌بندی
- Collectors برای جمع‌آوری پیشرفته.
- `groupingBy`/`partitioningBy` برای دسته‌بندی.
- `reduce` برای aggregation، `flatMap` برای flatten.

**جلسهٔ بعد:** Optional.
