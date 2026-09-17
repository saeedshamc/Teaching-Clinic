# جلسه ۱۸: کلاس‌های انتزاعی (Abstract Classes) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تفاوت **Abstract Class** و **Concrete Class**
- متدهای **abstract** — بدون پیاده‌سازی، اجبار در فرزند
- متدهای **virtual** با پیاده‌سازی پیش‌فرض در abstract class
- **نمونه‌سازی نکردن** از abstract class
- Template Method Pattern
- انتخاب بین Abstract Class و Interface

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | Interface و محدودیت‌هایش |
| 10–30 | Abstract Class | تعریف و abstract members |
| 30–50 | Template Method | الگوی طراحی |
| 50–65 | Abstract vs Interface | تصمیم‌گیری |
| 65–80 | تمرین | سیستم سند و شکل |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. Abstract Class چیست؟

```csharp
abstract class Animal
{
    public string Name { get; set; } = "";

    // abstract — بدون بدنه، فرزند باید پیاده کند
    public abstract void MakeSound();

    // virtual — پیاده‌سازی پیش‌فرض، قابل override
    public virtual void Sleep() =>
        Console.WriteLine($"{Name} می‌خوابد.");

    // concrete — پیاده‌سازی کامل
    public void Eat() =>
        Console.WriteLine($"{Name} غذا می‌خورد.");
}

class Dog : Animal
{
    public override void MakeSound() =>
        Console.WriteLine($"{Name}: ووف!");
}

// Animal a = new Animal();  // ❌ نمی‌توان نمونه ساخت
Animal pet = new Dog { Name = "رکسی" };
pet.MakeSound();
```

---

## ۲. abstract vs virtual vs concrete

| نوع | در Abstract Class | در فرزند |
|-----|-------------------|----------|
| `abstract` | بدون بدنه | **باید** `override` |
| `virtual` | با بدنه پیش‌فرض | **می‌تواند** `override` |
| عادی | پیاده‌سازی کامل | ارث‌بری (یا `new`) |

```csharp
abstract class Shape
{
    public string Color { get; set; } = "";

    public abstract double GetArea();           // اجباری
    public virtual void Draw() =>               // اختیاری
        Console.WriteLine($"رسم شکل {Color}");
}
```

---

## ۳. Template Method Pattern

```csharp
abstract class DataExporter
{
    // Template — الگوریتم ثابت، مراحل متغیر
    public void Export()
    {
        var data = FetchData();
        var formatted = FormatData(data);
        WriteOutput(formatted);
        Console.WriteLine("✅ Export کامل شد");
    }

    protected abstract string FetchData();
    protected abstract string FormatData(string data);
    protected virtual void WriteOutput(string data) =>
        Console.WriteLine(data);
}

class CsvExporter : DataExporter
{
    protected override string FetchData() => "علی,25\nمریم,30";
    protected override string FormatData(string data) => "CSV:\n" + data;
}

class JsonExporter : DataExporter
{
    protected override string FetchData() => "[{\"name\":\"علی\"}]";
    protected override string FormatData(string data) => "JSON:\n" + data;
}
```

**ایده:** اسکلت الگوریتم در والد، جزئیات در فرزند.

---

## ۴. Constructor در Abstract Class

```csharp
abstract class Vehicle
{
    public string Brand { get; }
    protected int EnginePower;

    protected Vehicle(string brand, int power)
    {
        Brand = brand;
        EnginePower = power;
    }

    public abstract void Start();
}

class Car : Vehicle
{
    public Car(string brand, int power) : base(brand, power) { }

    public override void Start() =>
        Console.WriteLine($"{Brand} ({EnginePower}hp) روشن شد");
}
```

---

## ۵. Abstract Class به‌عنوان نوع

```csharp
abstract class Payment
{
    public double Amount { get; set; }
    public abstract bool Process();
    public virtual void PrintReceipt() =>
        Console.WriteLine($"رسید: {Amount:N0} تومان");
}

void ProcessPayments(Payment[] payments)
{
    foreach (var p in payments)
    {
        if (p.Process())
            p.PrintReceipt();
    }
}
```

---

## ۶. Abstract vs Interface — جدول تصمیم

| سناریو | انتخاب |
|--------|--------|
| IS-A با state مشترک | Abstract Class |
| فقط قرارداد رفتاری | Interface |
| چند قابلیت مستقل | Interface |
| Template Method | Abstract Class |
| Mock در تست | Interface |
| وراثت از کلاس موجود | Interface |

```csharp
// ترکیب رایج
abstract class BaseEntity
{
    public int Id { get; set; }
    public DateTime CreatedAt { get; set; } = DateTime.Now;
}

interface IAuditable
{
    void LogChange(string action);
}

class Product : BaseEntity, IAuditable
{
    public string Name { get; set; } = "";
    public void LogChange(string action) =>
        Console.WriteLine($"Product {Id}: {action}");
}
```

---

## ۷. sealed abstract — وجود ندارد!

```csharp
abstract class A { }
sealed class B : A { }  // B دیگر abstract نیست — sealed است
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_abstract_basics.cs](./examples/01_abstract_basics.cs) | abstract members |
| [02_template_method.cs](./examples/02_template_method.cs) | Template Method |
| [03_shape_hierarchy.cs](./examples/03_shape_hierarchy.cs) | Shape abstract |
| [04_game_characters.cs](./examples/04_game_characters.cs) | مثال بازی |

---

## ۹. مثال واقعی — سیستم گزارش

```csharp
abstract class Report
{
    public string Title { get; set; } = "";
    protected List<string> Data { get; } = new();

    public void Generate()
    {
        CollectData();
        var output = BuildReport();
        Save(output);
    }

    protected abstract void CollectData();
    protected abstract string BuildReport();
    protected virtual void Save(string content) =>
        Console.WriteLine(content);
}
```

---

## ۱۰. اجرای مثال‌ها

```powershell
./run-example.ps1 session-18-abstract-classes/examples/01_abstract_basics.cs
```

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `new AbstractClass()` | compile error | از فرزند استفاده کنید |
| abstract بدون override | compile error | `override` در فرزند |
| abstract با بدنه | compile error | `virtual` استفاده کنید |
| همه چیز abstract | over-engineering | فقط متدهای متغیر |
| Interface + Abstract اشتباه | طراحی پیچیده | هر کدام نقش مشخص |

---

## ۱۲. تمرین کلاسی

1. `Document` abstract — `Print()`, `GetPageCount()`
2. `GameCharacter` — `Attack()` abstract, `TakeDamage()` virtual
3. `DataExporter` — Template Method

---

## ۱۳. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۴. خلاصه

```
abstract class     → نمی‌توان new کرد
abstract method    → بدون بدنه، override اجباری
virtual method     → بدنه پیش‌فرض، override اختیاری
Template Method    → الگوریتم در والد، جزئیات در فرزند
```

---

**جلسه قبل:** [۱۷ — Interface](../session-17-interfaces/) | **بعد:** [۱۹ — Encapsulation](../session-19-encapsulation/)
