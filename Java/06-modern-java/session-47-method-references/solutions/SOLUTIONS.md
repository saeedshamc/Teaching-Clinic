# پاسخ تمرین‌های جلسه ۴۷

## آسان

**۱ و ۲.**
```java
Function<String, Integer> f = String::length;
System.out.println(f.apply("hello"));   // 5

List<String> names = List.of("a", "b");
names.forEach(System.out::println);
```

## متوسط

**۳.**
```java
Supplier<ArrayList<String>> supplier = ArrayList::new;
ArrayList<String> list = supplier.get();
```

**۴.**
```java
people.sort(Comparator.comparingInt(Person::getAge));
```

## سخت

**۵.**
```java
class Parent { void greet() { System.out.println("Parent"); } }
class Child extends Parent {
    void greet() { System.out.println("Child"); }
    void test() {
        Runnable r1 = this::greet;    // Child.greet
        Runnable r2 = super::greet;  // Parent.greet
        r1.run(); r2.run();
    }
}
```

**۶.**
```java
// Person(String name, int age)
BiFunction<String, Integer, Person> factory = Person::new;
Person p = factory.apply("Ali", 20);
```
