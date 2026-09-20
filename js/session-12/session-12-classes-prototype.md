# جلسه 12: کلاس‌ها و پروتوتایپ (2 ساعت)

## 🎯 اهداف یادگیری
- یک `class` با constructor و متد بسازید
- `extends` و `super` را در حد کاربردی به کار ببرید
- بگویید prototype زیر کلاس چه می‌کند
- بدون OOP سنگین، بدانید React class component قدیمی از همین مدل می‌آمد

---

## 1. class کاربردی

```javascript
class User {
    constructor(name) {
        this.name = name;
    }

    greet() {
        return `سلام ${this.name}`;
    }
}

const u = new User("علی");
u.greet();
```

`class` شکر روی prototype است، نه مدل جداگانه.

---

## 2. پروتوتایپ در یک نگاه

```javascript
User.prototype.greet === u.greet; // true
u.hasOwnProperty("name");         // true
u.hasOwnProperty("greet");        // false — روی prototype است
```

جستجوی متد: خود شیء → prototype → بالاتر تا `null`.

برای روزمره همان class کافی است؛ زنجیره را برای خواندن خطا و `this` لازم دارید.

---

## 3. ارث‌بری سبک

```javascript
class Admin extends User {
    constructor(name, role) {
        super(name);
        this.role = role;
    }

    greet() {
        return `${super.greet()} — ${this.role}`;
    }
}
```

`super()` قبل از استفاده از `this` در constructor فرزند اجباری است.

---

## 4. فیلد و متد استاتیک

```javascript
class Counter {
    static fromZero() {
        return new Counter();
    }
}
```

`static` روی خود کلاس است، نه روی instance.

---

## 5. تمرین‌ها

### تمرین 1
کلاس `Product` با `name` و `price` و متد `label()`.

### تمرین 2
`DiscountedProduct extends Product` با درصد تخفیف.

### تمرین 3 (چالش)
بدون class، همان `User` را با `function` + `prototype` بنویسید تا فرق را ببینید.

---

## 6. نکات مهم

1. ✅ برای داده + رفتار مشخص از class استفاده کنید
2. ✅ `this` در متد معمولی به instance است؛ arrow به‌عنوان متد prototype مناسب نیست
3. ❌ درخت ارث‌بری عمیق نسازید — ترکیب (object ساده + توابع) اغلب کافی است

---

## 7. خلاصه جلسه

- class، constructor، متد
- prototype پشت صحنه
- extends / super در حد نیاز
- OOP سنگین هدف این دوره نیست

**آماده برای جلسه بعد: مدیریت خطا! 🚀**
