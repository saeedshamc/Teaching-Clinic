# پاسخ تمرین‌های جلسه ۵۱

## آسان

**۱ و ۲.**
```java
record Point(int x, int y) {}
Point p = new Point(3, 4);
System.out.println(p.x());   // 3 — نه getX()!

record Person(String name, int age) {
    Person {
        if (age < 0) throw new IllegalArgumentException("Invalid age");
    }
}
```

## متوسط

**۳.**
```java
record Circle(double radius) {
    double area() { return Math.PI * radius * radius; }
    double diameter() { return radius * 2; }
}
```

**۴.**
```java
record Address(String street, String city) {}
record Employee(String name, Address address) {}
```

## سخت

**۵.**
```java
List<Person> people = List.of(
    new Person("Ali", 20),
    new Person("Sara", 25)
);
people.stream().map(Person::name).forEach(System.out::println);
```

**۶.**
```java
record Person(String name, int age) implements Serializable {}
```
