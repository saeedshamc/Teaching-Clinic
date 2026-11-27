# جلسه ۱۸ — ارث‌بری (Inheritance)

## اهداف یادگیری
- `extends` برای ارث‌بری را بفهمید
- `super` برای دسترسی به والد را به‌کار ببرید
- `super()` در سازنده و method overriding را یاد بگیرید
- `final` روی کلاس/متد/فیلد را بشناسید

## پیش‌نیازها
- جلسات ۱۶ و ۱۷

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### extends — ارث‌بری

```java
class Animal {
    String name;
    void eat() { System.out.println(name + " is eating"); }
    void sleep() { System.out.println(name + " is sleeping"); }
}

class Dog extends Animal {     // Dog همهٔ فیلدها/متدهای Animal را دارد
    void bark() { System.out.println(name + " barks!"); }
}

Dog d = new Dog();
d.name = "Rex";
d.eat();    // از Animal — به ارث رسیده
d.bark();   // متد خود Dog
```

### super — دسترسی به والد

```java
class Dog extends Animal {
    int age;

    Dog(String name, int age) {
        super(name);        // صدا زدن سازندهٔ والد — باید اولین statement باشد
        this.age = age;
    }

    @Override
    void eat() {
        super.eat();        // صدا زدن متد والد
        System.out.println("Dog finished eating");
    }
}
```

### Method Overriding

- متد فرزند **هم‌نام و هم‌امضا** با والد → override می‌شود.
- `@Override` annotation برای اطمینان (compile-time check).
- Override فقط می‌تواند **access را بازتر** کند نه محدودتر (public → public، نه private).

```java
class Animal {
    void makeSound() { System.out.println("..."); }
}
class Dog extends Animal {
    @Override
    void makeSound() { System.out.println("Woof!"); }  // override
}
class Cat extends Animal {
    @Override
    void makeSound() { System.out.println("Meow!"); }
}
```

### final

```java
final class Constants {        // کلاس final → ارث‌بری ممنوع
    static final int MAX = 100;
}

class Shape {
    final void draw() { ... }  // متد final → override ممنوع
}
class Point {
    final int x = 5;           // فیلد final → فقط یک‌بار مقدار می‌گیرد
}
```

## کد مهم

`src/InheritanceDemo.java` — extends، super، override، final، چند سطح ارث‌بری.

## خطاهای رایج
- `super()` باید اولین statement در سازنده باشد → compile error.
- override با access محدودتر (public → private) → compile error.
- `@Override` روی متدی که override نیست → compile error (خوب است!).
- ارث‌بری چندگانه در Java **ممنوع** است — فقط یک والد مستقیم.

## جمع‌بندی
- `extends` برای ارث‌بری، `super` برای والد، `@Override` برای override.
- `final` برای جلوگیری از تغییر/ارث‌بری.
- Java single inheritance — برای چند قابلیت interface (جلسهٔ ۲۱).

**جلسهٔ بعد:** چندریختی (Polymorphism).
