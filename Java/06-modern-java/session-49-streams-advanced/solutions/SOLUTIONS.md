# پاسخ تمرین‌های جلسه ۴۹

## آسان

**۱ و ۲.**
```java
String names = people.stream()
    .map(Person::getName)
    .collect(Collectors.joining(", "));

Map<String, List<Person>> byDept = people.stream()
    .collect(Collectors.groupingBy(Person::getDepartment));
```

## متوسط

**۳.**
```java
Map<Boolean, List<Person>> adults = people.stream()
    .collect(Collectors.partitioningBy(p -> p.getAge() >= 18));
```

**۴.**
```java
Map<String, Integer> nameToAge = people.stream()
    .collect(Collectors.toMap(Person::getName, Person::getAge,
        (a, b) -> a + b));
```

## سخت

**۵.**
```java
List<Integer> flat = nested.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList());
```

**۶.**
```java
var stats = people.stream()
    .mapToInt(Person::getAge)
    .summaryStatistics();
System.out.println("avg=" + stats.getAverage() + " max=" + stats.getMax());
```
