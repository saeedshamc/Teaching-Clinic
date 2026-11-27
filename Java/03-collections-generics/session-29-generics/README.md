# جلسه ۲۹ — Generics

## اهداف یادگیری
- Generic class و method را بسازید
- `<T>` type parameter را بفهمید
- bounded type (`extends`, `super`) را بشناسید
- wildcard (`?`) را به‌کار ببرید

## پیش‌نیازها
- جلسات ۲۵–۲۸

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Generic class

```java
class Box<T> {              // T = type parameter — هر نوعی
    private T value;

    void set(T value) { this.value = value; }
    T get() { return value; }
}

Box<String> stringBox = new Box<>();
stringBox.set("Hello");
System.out.println(stringBox.get());   // Hello

Box<Integer> intBox = new Box<>();
intBox.set(42);
```

### Generic method

```java
static <T> T first(List<T> list) {
    return list.isEmpty() ? null : list.get(0);
}

List<String> names = List.of("Ali", "Sara");
String first = first(names);   // T = String

List<Integer> nums = List.of(1, 2, 3);
Integer firstNum = first(nums);  // T = Integer
```

### Bounded type

```java
// T باید Number یا زیرکلاس آن باشد
static <T extends Number> double sum(List<T> list) {
    double total = 0;
    for (T n : list) total += n.doubleValue();
    return total;
}

List<Integer> ints = List.of(1, 2, 3);
List<Double> doubles = List.of(1.5, 2.5);
sum(ints);     // OK — Integer extends Number
sum(doubles);  // OK
// sum(List.of("a")); // خطا — String extends Number نیست
```

### Wildcard

```java
// ? = هر نوع — read-only
static void printList(List<?> list) {
    for (Object obj : list) System.out.println(obj);
}

// ? extends Number = Number یا زیرکلاس — read
static double sumWild(List<? extends Number> list) { ... }

// ? super Integer = Integer یا supertype — write
static void addNumber(List<? super Integer> list, int n) {
    list.add(n);
}
```

## کد مهم

`src/GenericsDemo.java` — generic class، method، bounded، wildcard.

## خطاهای رایج
- raw type (`Box` بدون `<T>`) → unchecked warning.
- `List<int>` → compile error — primitive نمی‌شود، باید `Integer`.
- `? super` برای read → compile error — فقط write.
- generic array (`new T[10]`) → compile error — type erasure.

## جمع‌بندی
- Generic class/method برای type-safe collections.
- `<T extends Number>` برای محدودیت، `<?>` برای flexibility.
- Wildcard برای متدهایی که نوع دقیق را نمی‌دانند.

**جلسهٔ بعد:** Comparators و Iterators.
