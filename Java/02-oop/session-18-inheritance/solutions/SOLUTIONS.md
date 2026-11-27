# پاسخ تمرین‌های جلسه ۱۸

## آسان

**۱ و ۲.**
```java
class Vehicle {
    int speed;
    Vehicle(int speed) { this.speed = speed; }
    void move() { System.out.println("Moving at " + speed); }
}
class Car extends Vehicle {
    Car(int speed) { super(speed); }
    void honk() { System.out.println("Beep beep!"); }
}
class Bike extends Vehicle {
    Bike(int speed) { super(speed); }
}
```

## متوسط

**۳.**
```java
class Animal {
    String name;
    void describe() { System.out.println(name + " is an animal"); }
}
class Dog extends Animal {
    @Override
    void describe() {
        super.describe();   // Animal is an animal
        System.out.println(name + " is a dog");
    }
}
```

**۴.**
```java
final class Constants {
    static final int MAX = 100;
}
// class MyConstants extends Constants {} // compile error!
```

## سخت

**۵.**
```java
class Grandparent { void greet() { System.out.println("Grandparent"); } }
class Parent extends Grandparent {
    @Override void greet() { System.out.println("Parent"); super.greet(); }
}
class Child extends Parent {
    @Override void greet() { System.out.println("Child"); super.greet(); }
}
// Child.greet() → Child, Parent, Grandparent
```

**۶.**
```java
class Shape {
    final void draw() { System.out.println("Drawing"); }
}
class Circle extends Shape {
    // @Override void draw() {} // compile error — final method!
}
```
