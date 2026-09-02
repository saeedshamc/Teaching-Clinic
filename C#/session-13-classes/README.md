# جلسه ۱۳: کلاس و شیء (Classes & Objects) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک تفاوت **کلاس** (قالب/Blueprint) و **شیء** (نمونه/Instance)
- تعریف فیلد (Field) و متد (Method) داخل کلاس
- ساخت شیء با `new` و دسترسی به اعضا
- ساخت چند شیء مستقل از یک کلاس
- کار با آرایه‌ای از اشیاء
- درک مفهوم **Reference Type** در C#

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | جمع‌بندی جلسه ۱۲ (پروژه ماشین‌حساب) و ارتباط با OOP |
| 10–25 | کلاس و شیء | تعریف کلاس، فیلد، متد — مثال `Person` |
| 25–40 | چند شیء | استقلال state هر شیء — مثال `BankAccount` |
| 40–55 | آرایه اشیاء | حلقه روی `Product[]` — جستجو و جمع |
| 55–70 | متدهای پیشرفته | پارامتر، بازگشت مقدار، `this` |
| 70–85 | تمرین کلاسی | کلاس `Student` و `Rectangle` |
| 85–90 | جمع‌بندی | اشتباهات رایج + تکلیف |

---

## ۱. چرا به کلاس نیاز داریم؟

تا اینجا با متغیرهای ساده کار کردیم. فرض کنید می‌خواهیم ۱۰۰ دانشجو را مدیریت کنیم:

```csharp
// ❌ روش بد — متغیرهای جدا و نامرتب
string student1Name = "علی";
int student1Age = 20;
string student2Name = "مریم";
int student2Age = 22;
// ... ۹۸ بار دیگر!
```

```csharp
// ✅ روش درست — داده و رفتار در یک واحد
class Student
{
    public string Name = "";
    public int Age;
    public void Introduce() => Console.WriteLine($"من {Name} هستم، {Age} ساله.");
}
```

**OOP** (برنامه‌نویسی شیءگرا) داده و رفتار مرتبط را در یک **کلاس** جمع می‌کند.

---

## ۲. تعریف کلاس

```csharp
class Car
{
    // فیلدها (Fields) — داده‌های داخلی
    public string Brand = "";
    public int Year;
    public double Speed;

    // متدها (Methods) — رفتار
    public void Accelerate(double amount)
    {
        Speed += amount;
        Console.WriteLine($"{Brand}: سرعت = {Speed} km/h");
    }

    public void Brake()
    {
        Speed = 0;
        Console.WriteLine($"{Brand}: توقف");
    }
}
```

### اجزای کلاس

| جزء | نقش | مثال |
|-----|-----|------|
| Field | ذخیره داده | `public int Age` |
| Method | عملیات/رفتار | `public void SayHello()` |
| Property | دسترسی کنترل‌شده (جلسه بعد) | `public string Name { get; set; }` |

---

## ۳. ساخت شیء (Instantiation)

```csharp
// ساخت شیء با new
Car myCar = new Car();

// مقداردهی فیلدها
myCar.Brand = "پژو";
myCar.Year = 1400;
myCar.Speed = 0;

// فراخوانی متد
myCar.Accelerate(50);  // پژو: سرعت = 50 km/h
myCar.Brake();         // پژو: توقف
```

### Object Initializer (مقداردهی اولیه)

```csharp
Car car2 = new Car
{
    Brand = "سمند",
    Year = 1398,
    Speed = 0
};
```

---

## ۴. چند شیء از یک کلاس

هر `new` یک شیء **مستقل** می‌سازد:

```csharp
Car carA = new Car { Brand = "پژو", Speed = 0 };
Car carB = new Car { Brand = "سمند", Speed = 0 };

carA.Accelerate(80);  // فقط carA تغییر می‌کند
Console.WriteLine(carB.Speed);  // 0 — بدون تغییر
```

**نکته مهم:** `carA` و `carB` به دو مکان **متفاوت** در حافظه اشاره می‌کنند (Reference Type).

---

## ۵. Reference Type در مقابل Value Type

```csharp
int x = 10;
int y = x;    // کپی مقدار
y = 20;
Console.WriteLine(x);  // 10 — x تغییر نکرد

Car a = new Car { Speed = 50 };
Car b = a;    // کپی آدرس (reference)
b.Speed = 100;
Console.WriteLine(a.Speed);  // 100 — هر دو به یک شیء اشاره می‌کنند!
```

---

## ۶. آرایه‌ای از اشیاء

```csharp
class Product
{
    public string Name = "";
    public double Price;
}

Product[] products = new Product[]
{
    new Product { Name = "لپ‌تاپ", Price = 25_000_000 },
    new Product { Name = "ماوس", Price = 500_000 },
    new Product { Name = "کیبورد", Price = 1_200_000 }
};

// حلقه روی اشیاء
double total = 0;
foreach (var p in products)
{
    Console.WriteLine($"{p.Name}: {p.Price:N0} تومان");
    total += p.Price;
}
Console.WriteLine($"جمع: {total:N0}");
```

---

## ۷. متد با پارامتر و بازگشت مقدار

```csharp
class Rectangle
{
    public double Width;
    public double Height;

    public double GetArea() => Width * Height;

    public double GetPerimeter() => 2 * (Width + Height);

    public bool IsSquare() => Width == Height;
}

var rect = new Rectangle { Width = 5, Height = 3 };
Console.WriteLine($"مساحت: {rect.GetArea()}");       // 15
Console.WriteLine($"مربع؟ {rect.IsSquare()}");        // False
```

---

## ۸. کلمه کلیدی `this`

```csharp
class Counter
{
    public int Count;

    public void Increment()
    {
        this.Count++;  // this = همین شیء فعلی
    }
}
```

در اکثر موارد `this` اختیاری است؛ وقتی نام پارامتر با فیلد یکی باشد ضروری می‌شود:

```csharp
public void SetName(string name)
{
    this.Name = name;  // name پارامتر، Name فیلد
}
```

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_class_basics.cs](./examples/01_class_basics.cs) | تعریف کلاس، فیلد، متد |
| [02_multiple_objects.cs](./examples/02_multiple_objects.cs) | چند شیء مستقل |
| [03_object_array.cs](./examples/03_object_array.cs) | آرایه اشیاء و حلقه |
| [04_methods_params.cs](./examples/04_methods_params.cs) | پارامتر، بازگشت، `this` |

---

## ۱۰. مثال واقعی — سیستم کتابخانه ساده

```csharp
class Book
{
    public string Title = "";
    public string Author = "";
    public bool IsAvailable = true;

    public void Borrow()
    {
        if (IsAvailable)
        {
            IsAvailable = false;
            Console.WriteLine($"«{Title}» امانت داده شد.");
        }
        else
            Console.WriteLine("این کتاب در دسترس نیست.");
    }

    public void ReturnBook()
    {
        IsAvailable = true;
        Console.WriteLine($"«{Title}» برگشت داده شد.");
    }
}
```

---

## ۱۱. اجرای مثال‌ها

```powershell
./run-example.ps1 session-13-classes/examples/01_class_basics.cs
```

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `Person p; p.Name = "Ali";` | شیء ساخته نشده — `NullReferenceException` | همیشه `new Person()` |
| فراموش کردن `public` | دسترسی از بیرون ممکن نیست | فیلد/متد عمومی = `public` |
| `Car c1 = c2` فکر کپی عمیق | هر دو به یک شیء اشاره می‌کنند | `new Car { ... }` برای کپی |
| متد بدون `()` | `obj.Method` فراخوانی نمی‌شود | `obj.Method()` |
| فیلد static با instance اشتباه گرفتن | رفتار غیرمنتظره | جلسه بعدی — فعلاً instance |

---

## ۱۳. تمرین کلاسی (۲۰ دقیقه)

1. کلاس `Student` با `Name`, `Grade`, متد `IsPassed()` (نمره ≥ ۱۰)
2. کلاس `BankAccount` با `Balance` و متدهای `Deposit`, `Withdraw`
3. آرایه‌ای از `Student` — میانگین نمرات

---

## ۱۴. تکلیف خانه

**تکلیف:** [question.md](./exercises/question.md)

پاسخ نمونه: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۵. خلاصه

```
class = قالب (Blueprint)
object = نمونه (Instance) ← new ClassName()
field  = داده
method = رفتار
```

| مفهوم | یک خط |
|-------|-------|
| کلاس | طرح ساخت اشیاء |
| شیء | نمونه واقعی در حافظه |
| `new` | ساخت شیء جدید |
| Reference | متغیر آدرس شیء را نگه می‌دارد |

---

**جلسه قبل:** [۱۲ — پروژه مبتدی](../session-12-beginner-project/) | **بعد:** [۱۴ — Properties و Constructors](../session-14-properties-constructors/)
