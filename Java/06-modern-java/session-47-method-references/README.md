# جلسه ۴۷ — Method References

## اهداف یادگیری
- `::` syntax را برای method reference بفهمید
- ۴ نوع method reference را بشناسید (static, instance, constructor, array)
- method reference در برابر lambda را تشخیص دهید
- `this::` و `super::` را به‌کار ببرید

## پیش‌نیازها
- جلسهٔ ۴۶ (lambda)

## مدت زمان پیشنهادی
۶۰ دقیقه

## درس

### ۴ نوع method reference

```java
// ۱. Static method — ClassName::staticMethod
String::valueOf        // s -> String.valueOf(s)
Math::max               // (a,b) -> Math.max(a,b)

// ۲. Instance method — object::instanceMethod
String name = "hello";
name::toUpperCase      // () -> name.toUpperCase()
System.out::println    // s -> System.out.println(s)

// ۳. Instance method of arbitrary object — ClassName::instanceMethod
String::length         // s -> s.length()
String::isEmpty        // s -> s.isEmpty()

// ۴. Constructor — ClassName::new
ArrayList::new         // () -> new ArrayList<>()
HashMap::new           // () -> new HashMap<>()
```

### مثال با Stream (preview)

```java
List<String> names = List.of("Ali", "Sara", "Reza");

// با lambda
names.forEach(name -> System.out.println(name));

// با method reference — تمیزتر
names.forEach(System.out::println);

// با constructor reference
List<String> upper = names.stream()
    .map(String::toUpperCase)   // method reference
    .toList();
```

### این مثال در برابر lambda

```java
// lambda
Function<String, Integer> f1 = s -> s.length();

// method reference — معادل
Function<String, Integer> f2 = String::length;

// lambda برای static
Function<Integer, String> f3 = n -> String.valueOf(n);

// method reference
Function<Integer, String> f4 = String::valueOf;
```

## کد مهم

`src/MethodRefDemo.java` — ۴ نوع method reference، lambda vs method ref.

## خطاهای رایج
- method reference برای interface با signature متفاوت → compile error.
- `this::method` در static context → compile error.
- constructor reference برای interface → باید constructor واقعی باشد.
- method reference که lambda را readable‌تر نمی‌کند → lambda بهتر است.

## جمع‌بندی
- `ClassName::staticMethod` برای static.
- `object::instanceMethod` برای instance.
- `ClassName::instanceMethod` برای arbitrary instance.
- `ClassName::new` برای constructor.

**جلسهٔ بعد:** Streams Basics.
