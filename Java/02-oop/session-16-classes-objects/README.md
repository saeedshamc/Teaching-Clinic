# جلسه ۱۶ — کلاس و آبجکت (Classes & Objects)

## اهداف یادگیری
- تفاوت Class و Object را بفهمید
- فیلدها، متدها و سازنده را تعریف کنید
- `this` و `new` را به‌کار ببرید
- encapsulation پایه را با getter/setter شروع کنید

## پیش‌نیازها
- فاز ۱ کامل (جلسات ۰۱–۱۵)

## مدت زمان پیشنهادی
۷۵ دقیقه

## درس

### Class در برابر Object

- **Class** = نقشه/طرح (blueprint) — تعریف می‌کنید چه فیلدها و متدهایی دارد.
- **Object** = نمونهٔ واقعی ساخته‌شده از کلاس — در حافظه فضا می‌گیرد.

```java
class Dog {                    // class = تعریف
    String name;               // فیلد — دادهٔ هر نمونه
    int age;

    void bark() {              // متد — رفتار
        System.out.println(name + " says woof!");
    }
}

Dog d1 = new Dog();            // object = نمونه
Dog d2 = new Dog();
d1.name = "Rex";
d2.name = "Buddy";
d1.bark();                     // Rex says woof!
```

### new و سازنده

```java
Dog() {                        // سازنده — نامش = نام کلاس، بدون return type
    name = "Unknown";
    age = 0;
}
```
- `new Dog()` → حافظه می‌گیرد و سازنده را صدا می‌زند.
- اگر سازنده تعریف نکنید، Java یک سازندهٔ پیش‌فرض می‌سازد.

### this

```java
class Person {
    String name;
    int age;

    Person(String name, int age) {
        this.name = name;      // this = این نمونهٔ خاص
        this.age = age;
    }

    void introduce() {
        System.out.println("I am " + this.name);   // this.name = فیلد
    }
}
```

### Getter و Setter (encapsulation)

```java
class Person {
    private String name;       // private = فقط داخل کلاس
    private int age;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) {
        if (age >= 0 && age <= 150) this.age = age;   // validation
    }
}
```

### چند نمونه از یک کلاس

```java
Dog[] dogs = new Dog[3];
for (int i = 0; i < dogs.length; i++) {
    dogs[i] = new Dog();
    dogs[i].name = "Dog" + i;
}
```

## کد مهم

`src/ClassDemo.java` — کلاس ساده، سازنده، this، getter/setter، چند نمونه.

## خطاهای رایج
- `new` را فراموش کردن → `NullPointerException`.
- فیلد را `private` بدون getter/setter → بیرون کلاس دسترسی ندارد.
- `this` را در static context استفاده کردن → compile error.
- نام فیلد و پارامتر یکسان بدون `this` → فیلد ست نمی‌شود.

## جمع‌بندی
- Class = تعریف، Object = نمونهٔ ساخته‌شده با `new`.
- سازنده برای مقداردهی اولیه، `this` برای تفکیک فیلد/پارامتر.
- Getter/setter برای encapsulation.

**جلسهٔ بعد:** سازنده‌های پیشرفته و encapsulation.
