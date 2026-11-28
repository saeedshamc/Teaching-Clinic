# پاسخ تمرین‌های جلسه ۵۰

## آسان

**۱ و ۲.**
```java
Optional<String> opt = Optional.of("Hello");
Optional<String> empty = Optional.empty();
Optional<String> nullable = Optional.ofNullable(null);

System.out.println(opt.isPresent());     // true
System.out.println(empty.isPresent());   // false
```

## متوسط

**۳.**
```java
Optional<String> upper = name.map(String::toUpperCase);
Optional<String> flat = name.flatMap(n -> Optional.of("Hello " + n));
```

**۴.**
```java
String v1 = opt.orElse("default");
String v2 = opt.orElseGet(() -> expensive());
```

## سخت

**۵.**
```java
String v = opt.orElseThrow(() -> new IllegalStateException("Missing"));
```

**۶.**
```java
Optional<String> first = names.stream()
    .filter(s -> s.startsWith("A"))
    .findFirst();
```
