# جلسه ۱۵: وراثت (Inheritance) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک **وراثت** — کلاس فرزند از والد
- استفاده از `base` برای دسترسی به اعضای والد
- **Override** متدها با `virtual` و `override`
- محدودیت **sealed** — جلوگیری از وراثت بیشتر
- **protected** — دسترسی در سلسله‌مراتب
- طراحی سلسله‌مراتب معنادار (IS-A relationship)

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | Properties و Constructors |
| 10–30 | مبانی وراثت | `: BaseClass` و `base` |
| 30–50 | virtual/override | متدهای قابل بازنویسی |
| 50–65 | protected و sealed | سطوح دسترسی |
| 65–80 | تمرین | `Animal` → `Dog`, `Cat` |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. وراثت چیست؟

```csharp
// کلاس پایه (Parent / Base / Super)
class Animal
{
    public string Name { get; set; } = "";
    public int Age { get; set; }

    public void Eat() => Console.WriteLine($"{Name} غذا می‌خورد.");
}

// کلاس مشتق (Child / Derived / Sub)
class Dog : Animal
{
    public string Breed { get; set; } = "";

    public void Bark() => Console.WriteLine($"{Name}: ووف ووف!");
}

var dog = new Dog { Name = "رکسی", Age = 3, Breed = "هاسکی" };
dog.Eat();   // از Animal — وراثت
dog.Bark();  // مخصوص Dog
```

**IS-A:** سگ **یک** حیوان است → وراثت منطقی است.

---

## ۲. سینتکس وراثت

```csharp
class Derived : Base
{
    // اعضای public و protected از Base در دسترس
}
```

| کلمه | معنی |
|------|------|
| `:` | «از ... ارث می‌برد» |
| `base` | اشاره به کلاس والد |
| `this` | اشاره به همین کلاس |

---

## ۳. Constructor و `base()`

```csharp
class Vehicle
{
    public string Brand { get; }
    public int Year { get; }

    public Vehicle(string brand, int year)
    {
        Brand = brand;
        Year = year;
    }
}

class Car : Vehicle
{
    public int Doors { get; }

    public Car(string brand, int year, int doors) : base(brand, year)
    {
        Doors = doors;
    }
}

var car = new Car("پژو", 1400, 4);
```

**قانون:** constructor فرزند **باید** constructor والد را فراخوانی کند (صریح یا ضمنی).

---

## ۴. virtual و override

```csharp
class Shape
{
    public virtual double GetArea() => 0;
    public virtual string Describe() => "شکل هندسی";
}

class Circle : Shape
{
    public double Radius { get; set; }

    public override double GetArea() => Math.PI * Radius * Radius;

    public override string Describe() =>
        $"دایره با شعاع {Radius}";
}

class Rectangle : Shape
{
    public double Width { get; set; }
    public double Height { get; set; }

    public override double GetArea() => Width * Height;
}
```

| کلمه | نقش |
|------|-----|
| `virtual` | در والد — «قابل بازنویسی» |
| `override` | در فرزند — بازنویسی واقعی |
| `new` | پنهان‌سازی (نه override) — **اجتناب کنید** |

---

## ۵. protected

```csharp
class BankAccount
{
    protected double Balance;  // فرزندها دسترسی دارند، بیرون نه

    public BankAccount(double initial) => Balance = initial;
}

class SavingsAccount : BankAccount
{
    public double InterestRate { get; set; }

    public SavingsAccount(double initial) : base(initial) { }

    public void AddInterest()
    {
        Balance += Balance * InterestRate;  // ✅ protected
    }
}
```

---

## ۶. sealed — بستن وراثت

```csharp
class FinalClass { }

sealed class CannotInherit : FinalClass { }
// class Child : CannotInherit { }  // ❌ compile error

class Parent
{
    public virtual void Method() { }
}

class Child : Parent
{
    public sealed override void Method() { }  // دیگر override نشود
}
```

---

## ۷. سلسله‌مراتب چندسطحی

```csharp
class Employee
{
    public string Name { get; set; } = "";
    public virtual double CalculateSalary() => 0;
}

class FullTimeEmployee : Employee
{
    public double MonthlySalary { get; set; }
    public override double CalculateSalary() => MonthlySalary;
}

class Manager : FullTimeEmployee
{
    public double Bonus { get; set; }
    public override double CalculateSalary() => MonthlySalary + Bonus;
}
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_inheritance_basics.cs](./examples/01_inheritance_basics.cs) | وراثت ساده |
| [02_base_constructors.cs](./examples/02_base_constructors.cs) | base و constructor |
| [03_virtual_override.cs](./examples/03_virtual_override.cs) | virtual/override |
| [04_hierarchy_realworld.cs](./examples/04_hierarchy_realworld.cs) | مثال واقعی |

---

## ۹. مثال واقعی — سیستم پرداخت

```csharp
abstract class PaymentMethod  // جلسه ۱۸ — فعلاً به‌عنوان کلاس پایه
{
    public string Owner { get; set; } = "";
    public virtual bool ProcessPayment(double amount) => amount > 0;
}

class CreditCard : PaymentMethod
{
    public string CardNumber { get; set; } = "";
    public override bool ProcessPayment(double amount)
    {
        Console.WriteLine($"پرداخت {amount:N0} با کارت ****{CardNumber[^4..]}");
        return base.ProcessPayment(amount);
    }
}
```

---

## ۱۰. اجرای مثال‌ها

```powershell
./run-example.ps1 session-15-inheritance/examples/01_inheritance_basics.cs
```

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| HAS-A به‌جای IS-A | طراحی بد | «سگ یک حیوان است» نه «سگ یک دم دارد» |
| `new` به‌جای `override` | Polymorphism کار نمی‌کند | `virtual` + `override` |
| فراموش کردن `base()` | compile error | `: base(args)` |
| وراثت عمیق بیش از حد | کد شکننده | ترجیح composition |
| override بدون virtual | compile error | `virtual` در والد |

---

## ۱۲. وراثت در مقابل Composition

```
وراثت (IS-A):     Dog : Animal
ترکیب (HAS-A):    Car has Engine
```

**قانون:** اگر رابطه IS-A واضح نیست، از composition استفاده کنید.

---

## ۱۳. تمرین کلاسی

1. `Vehicle` → `Car`, `Motorcycle` با `StartEngine()`
2. `Shape` → `Circle`, `Rectangle` با `GetArea()`
3. `Employee` → `Manager` با `Bonus`

---

## ۱۴. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۵. خلاصه

```
class Child : Parent     → وراثت
base.Member              → دسترسی به والد
base(args)               → constructor والد
virtual / override       → بازنویسی متد
protected                → دسترسی در سلسله‌مراتب
sealed                   → بستن وراثت
```

---

**جلسه قبل:** [۱۴ — Properties](../session-14-properties-constructors/) | **بعد:** [۱۶ — چندریختی](../session-16-polymorphism/)
