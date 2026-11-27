# جلسه ۱۹ — چندریختی (Polymorphism)

## اهداف یادگیری
- Polymorphism را بفهمید (یک رابط، چند پیاده‌سازی)
- Upcasting و Downcasting را به‌کار ببرید
- `instanceof` و pattern matching را بشناسید
- Dynamic dispatch (runtime polymorphism) را درک کنید

## پیش‌نیازها
- جلسات ۱۶–۱۸

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### مفهوم

Polymorphism یعنی «یک متد، چند رفتار» — شیء والد می‌تواند به فرزند اشاره کند و متد درست در runtime اجرا می‌شود.

```java
Animal a = new Dog();   // Dog را به Animal ارجاع می‌دهیم (upcasting)
a.makeSound();          // Dog.makeSound() اجرا می‌شود — نه Animal!
```

### Upcasting و Downcasting

```java
Animal a = new Dog();        // upcasting — امن، خودکار
Dog d = (Dog) a;             // downcasting — نیاز به cast، خطرناک
// Cat c = (Cat) a;          // ClassCastException!

if (a instanceof Dog) {      // چک نوع قبل از cast
    Dog d2 = (Dog) a;
    d2.bark();               // متد خاص Dog
}
```

### Dynamic Dispatch

```java
void playSound(Animal a) {   // هر Animal‌ای قبول می‌کند
    a.makeSound();            // در runtime، متد درست صدا زده می‌شود
}

playSound(new Dog());   // Woof!
playSound(new Cat());   // Meow!
playSound(new Bird());  // Tweet!
```

### instanceof pattern matching (Java 16+)

```java
if (a instanceof Dog d) {   // cast خودکار — d در دسترس است
    d.bark();
} else if (a instanceof Cat c) {
    c.purr();
}
```

### Array of parent type

```java
Animal[] pets = {new Dog(), new Cat(), new Bird()};
for (Animal p : pets) {
    p.makeSound();   // هرکدام صدای خودشان
}
```

## کد مهم

`src/PolymorphismDemo.java` — upcasting، downcasting، instanceof، dynamic dispatch، آرایهٔ والد.

## خطاهای رایج
- `(Dog) animal` وقتی animal در واقع Cat است → `ClassCastException`.
- `instanceof` را قبل از cast فراموش کردن → crash.
- متد overload را با override اشتباه گرفتن — overload در compile-time، override در runtime.
- `null instanceof X` → همیشه false (null است نه X).

## جمع‌بندی
- Upcasting امن و خودکار، downcasting با cast و چک `instanceof`.
- Dynamic dispatch = متد فرزند در runtime اجرا می‌شود.
- آرایهٔ نوع والد برای heterogeneous collections.

**جلسهٔ بعد:** کلاس‌های abstract.
