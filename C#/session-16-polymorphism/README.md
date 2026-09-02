# جلسه ۱۶: چندریختی (Polymorphism) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک **Polymorphism** — یک رابط، رفتارهای مختلف
- **Upcasting** — ارجاع فرزند به متغیر والد
- **Downcasting** — `as` و `is` برای تبدیل امن
- **Pattern Matching** با `is` (C# 7+)
- متدهای virtual در عمل — Late Binding
- طراحی کد انعطاف‌پذیر با Polymorphism

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | وراثت و virtual/override |
| 10–30 | Polymorphism | آرایه Shape[] — رفتار متفاوت |
| 30–50 | Upcasting/Downcasting | as، is، pattern matching |
| 50–65 | مثال واقعی | سیستم نوتیفیکیشن |
| 65–80 | تمرین | پردازش پرداخت‌ها |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. Polymorphism چیست؟

> **چندریختی:** یک متغیر از نوع پایه می‌تواند به اشیاء مختلف از انواع مشتق اشاره کند و هر کدام رفتار خود را اجرا کند.

```csharp
class Animal
{
    public virtual void Speak() => Console.WriteLine("...");
}

class Dog : Animal
{
    public override void Speak() => Console.WriteLine("ووف!");
}

class Cat : Animal
{
    public override void Speak() => Console.WriteLine("میو!");
}

Animal[] zoo = { new Dog(), new Cat(), new Dog() };

foreach (Animal a in zoo)
    a.Speak();  // ووف! میو! ووف! — رفتار واقعی شیء
```

---

## ۲. Early vs Late Binding

| نوع | زمان تصمیم | مثال |
|-----|-----------|------|
| Early (Static) | Compile-time | `int x = 5;` |
| Late (Dynamic) | Runtime | `animal.Speak()` — بسته به نوع واقعی |

```csharp
Animal pet = new Dog();  // compile-time: Animal
pet.Speak();             // runtime: Dog.Speak() → "ووف!"
```

---

## ۳. Upcasting — تبدیل به بالا

```csharp
Dog dog = new Dog();
Animal animal = dog;  // Upcasting — ضمنی و امن

animal.Speak();  // ✅ Polymorphism کار می‌کند
// animal.Bark();  // ❌ Animal متد Bark ندارد
```

**Upcasting** همیشه امن است — فرزند **هست** یک والد.

---

## ۴. Downcasting — تبدیل به پایین

```csharp
Animal animal = new Dog();

// روش ۱: cast صریح — خطر NullReferenceException
Dog dog1 = (Dog)animal;

// روش ۲: as — امن، null اگر ناموفق
Dog? dog2 = animal as Dog;
if (dog2 != null)
    dog2.Bark();

// روش ۳: is — بررسی نوع
if (animal is Dog dog3)
    dog3.Bark();
```

| عملگر | نتیجه ناموفق | توصیه |
|--------|-------------|--------|
| `(Dog)a` | Exception | فقط وقتی مطمئنید |
| `a as Dog` | `null` | بررسی null |
| `a is Dog d` | false | **پیشنهادی** |

---

## ۵. Pattern Matching (C# 7+)

```csharp
void ProcessPayment(Payment payment)
{
    switch (payment)
    {
        case CreditCard cc:
            Console.WriteLine($"کارت: ****{cc.LastFour}");
            break;
        case BankTransfer bt:
            Console.WriteLine($"انتقال به: {bt.AccountNumber}");
            break;
        case Cash:
            Console.WriteLine("نقدی");
            break;
        default:
            Console.WriteLine("نامشخص");
            break;
    }
}
```

**Switch expression (C# 8+):**

```csharp
string fee = payment switch
{
    CreditCard => "۲٪",
    BankTransfer => "ثابت ۵۰۰۰",
    Cash => "۰",
    _ => "؟"
};
```

---

## ۶. virtual در عمل — مثال Shape

```csharp
abstract class Shape  // جلسه ۱۸
{
    public abstract double GetArea();
    public virtual void Draw() => Console.WriteLine("رسم شکل...");
}

void PrintAreas(Shape[] shapes)
{
    foreach (var s in shapes)
    {
        Console.WriteLine($"مساحت: {s.GetArea():F2}");
        s.Draw();
    }
}
```

یک تابع، همه اشکال — **Open/Closed Principle**.

---

## ۷. typeof و GetType()

```csharp
Animal a = new Dog();
Console.WriteLine(a.GetType().Name);  // Dog — نوع runtime

if (a.GetType() == typeof(Dog))
    Console.WriteLine("این یک سگ است");
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_polymorphism_basics.cs](./examples/01_polymorphism_basics.cs) | آرایه Animal[] |
| [02_upcasting_downcasting.cs](./examples/02_upcasting_downcasting.cs) | as، is |
| [03_pattern_matching.cs](./examples/03_pattern_matching.cs) | switch pattern |
| [04_payment_system.cs](./examples/04_payment_system.cs) | مثال واقعی |

---

## ۹. مثال واقعی — سیستم اعلان

```csharp
abstract class Notification
{
    public string Message { get; set; } = "";
    public abstract void Send();
}

class EmailNotification : Notification
{
    public string Email { get; set; } = "";
    public override void Send() =>
        Console.WriteLine($"📧 ایمیل به {Email}: {Message}");
}

class SmsNotification : Notification
{
    public string Phone { get; set; } = "";
    public override void Send() =>
        Console.WriteLine($"📱 SMS به {Phone}: {Message}");
}

void NotifyAll(Notification[] notifications)
{
    foreach (var n in notifications)
        n.Send();  // Polymorphism
}
```

---

## ۱۰. اجرای مثال‌ها

```powershell
./run-example.ps1 session-16-polymorphism/examples/01_polymorphism_basics.cs
```

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `new` به‌جای `override` | Polymorphism نشکسته | virtual + override |
| Cast بدون بررسی | InvalidCastException | `is` / `as` |
| نوع compile-time فرض | رفتار اشتباه | `GetType()` در runtime |
| if-else زنجیره‌ای طولانی | کد شکننده | Polymorphism یا pattern |
| Downcasting زیاد | طراحی ضعیف | Interface (جلسه ۱۷) |

---

## ۱۲. چه زمانی Polymorphism؟

✅ وقتی مجموعه‌ای از انواع مرتبط رفتار مشترک دارند
✅ وقتی می‌خواهید کد باز برای توسعه، بسته برای تغییر باشد
❌ وقتی فقط یک نوع دارید — ساده نگه دارید

---

## ۱۳. تمرین کلاسی

1. `Animal[]` — هر حیوان `Speak()` متفاوت
2. `Shape[]` — جمع مساحت‌ها
3. `is` برای تشخیص `Dog` از `Cat` در حلقه

---

## ۱۴. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۵. خلاصه

```
Polymorphism  = یک رابط، رفتارهای مختلف
Upcasting     = Child → Parent (امن)
Downcasting   = Parent → Child (با is/as)
virtual       = تصمیم در runtime
Pattern Match = switch هوشمند روی نوع
```

---

**جلسه قبل:** [۱۵ — وراثت](../session-15-inheritance/) | **بعد:** [۱۷ — Interface](../session-17-interfaces/)
