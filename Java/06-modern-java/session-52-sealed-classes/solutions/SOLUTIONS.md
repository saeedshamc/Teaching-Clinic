# پاسخ تمرین‌های جلسه ۵۲

## آسان

**۱ و ۲.**
```java
sealed class Shape permits Circle, Square {}
final class Circle extends Shape {}
final class Square extends Shape {}

sealed interface Result permits Success, Failure {}
record Success(String data) implements Result {}
record Failure(String error) implements Result {}
```

## متوسط

**۳.**
```java
sealed class Vehicle permits Car, Motorcycle {}
final class Car extends Vehicle {}
non-sealed class Motorcycle extends Vehicle {}
class SportBike extends Motorcycle {}   // مجاز
```

**۴.**
```java
String describe(Shape s) {
    return switch (s) {
        case Circle c -> "Circle";
        case Square sq -> "Square";
    };
}
```

## سخت

**۵.**
```java
sealed class Animal permits Mammal, Bird {}
sealed class Mammal extends Animal permits Dog, Cat {}
final class Dog extends Mammal {}
final class Cat extends Mammal {}
final class Bird extends Animal {}
```

**۶.**
```java
// بدون default — compiler همه cases را می‌داند
String process(Result r) {
    return switch (r) {
        case Success s -> "OK";
        case Failure f -> "Error";
    };
}
```
