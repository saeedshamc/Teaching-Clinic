# پاسخ تمرین‌های جلسه ۲۱

## آسان

**۱.**
```java
interface Flyable { void fly(); }
class Bird implements Flyable {
    public void fly() { System.out.println("Bird flies"); }
}
class Airplane implements Flyable {
    public void fly() { System.out.println("Airplane flies"); }
}
```

**۲.**
```java
interface Swimmable { void swim(); }
class Fish implements Swimmable {
    public void swim() { System.out.println("Fish swims"); }
}
class Duck implements Swimmable {
    public void swim() { System.out.println("Duck swims"); }
}
```

## متوسط

**۳.**
```java
class Duck implements Flyable, Swimmable {
    public void fly() { System.out.println("Duck flies"); }
    public void swim() { System.out.println("Duck swims"); }
}
```

**۴.**
```java
interface Animal {
    void eat();
    default void sleep() { System.out.println("Sleeping..."); }
}
class Dog implements Animal {
    public void eat() { System.out.println("Dog eats"); }
    @Override public void sleep() { System.out.println("Dog sleeps"); }
}
```

## سخت

**۵.**
```java
interface Utils {
    static int max(int a, int b) { return a > b ? a : b; }
}
// صدا زدن: Utils.max(5, 3) → 5
```

**۶.**
```java
Flyable[] flyers = {new Bird(), new Airplane(), new Duck()};
for (Flyable f : flyers) f.fly();
```
