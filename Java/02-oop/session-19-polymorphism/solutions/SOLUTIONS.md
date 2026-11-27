# پاسخ تمرین‌های جلسه ۱۹

## آسان

**۱ و ۲.**
```java
class Shape {
    double area() { return 0; }
}
class Circle extends Shape {
    double r;
    @Override double area() { return Math.PI * r * r; }
}
class Rectangle extends Shape {
    double w, h;
    @Override double area() { return w * h; }
}
Shape[] shapes = {new Circle(), new Rectangle()};
for (Shape s : shapes) System.out.println(s.area());

if (shapes[0] instanceof Circle) {
    Circle c = (Circle) shapes[0];
    c.r = 5;
}
```

## متوسط

**۳ و ۴.**
```java
static void describe(Animal a) {
    a.makeSound();   // dynamic dispatch
    if (a instanceof Dog d) {
        d.bark();    // متد خاص Dog
    } else if (a instanceof Cat c) {
        c.purr();    // متد خاص Cat
    }
}
```

## سخت

**۵.**
```java
Animal a = new Dog("Rex");
if (a instanceof Dog d) {   // d خودکار cast شده
    d.bark();
}
// یا با else-if chain
if (a instanceof Dog d) d.bark();
else if (a instanceof Cat c) c.purr();
else if (a instanceof Bird b) b.tweet();
```

**۶.**
```java
Object[] items = {new Dog("Rex"), new Cat("Luna"), new Bird("Kiwi"), "hello", 42};
for (Object obj : items) {
    if (obj instanceof Dog d) d.bark();
    else if (obj instanceof Cat c) c.purr();
    else if (obj instanceof Bird b) b.tweet();
    else System.out.println("Unknown: " + obj);
}
```
