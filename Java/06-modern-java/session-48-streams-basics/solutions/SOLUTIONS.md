# پاسخ تمرین‌های جلسه ۴۸

## آسان

**۱ و ۲.**
```java
List<String> long = names.stream()
    .filter(s -> s.length() > 3)
    .collect(Collectors.toList());

List<Integer> squared = nums.stream()
    .map(n -> n * n)
    .collect(Collectors.toList());
```

## متوسط

**۳.**
```java
long count = names.stream().count();
Optional<String> first = names.stream().findFirst();
```

**۴.**
```java
List<String> sorted = names.stream().sorted().collect(Collectors.toList());
List<String> unique = names.stream().distinct().collect(Collectors.toList());
```

## سخت

**۵.**
```java
boolean any = names.stream().anyMatch(s -> s.startsWith("A"));
boolean all = names.stream().allMatch(s -> s.length() > 2);
boolean none = names.stream().noneMatch(s -> s.isEmpty());
```

**۶.**
```java
List<String> page1 = names.stream().limit(3).collect(Collectors.toList());
List<String> page2 = names.stream().skip(3).limit(3).collect(Collectors.toList());
```
