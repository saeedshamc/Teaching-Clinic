# پروژهٔ جلسه ۱۸ — سیستم حیوانات خانگی

یک سیستم حیوانات خانگی بسازید که:
- کلاس پایهٔ `Pet` با `name`, `age` و متدهای `eat()`, `sleep()`, `makeSound()`
- `Dog`, `Cat`, `Bird` را extends کنید با override `makeSound()`
- در `main` آرایه‌ای از `Pet` بسازید و متدها را صدا بزنید (polymorphism)
- هر حیوان `toString()` یا `describe()` داشته باشد

نمونه:
```
Rex (Dog, 3): Woof!
Whiskers (Cat, 2): Meow!
Tweety (Bird, 1): Tweet!
```

## صورت پروژه
پاسخ در `solution/PetSystem.java` — از inheritance و polymorphism استفاده کنید.
