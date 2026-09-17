# جلسه ۱۴: Properties و Constructors (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تفاوت **Field** و **Property** — چرا Property بهتر است
- Auto-Property و Property با `get`/`set` سفارشی
- **Constructor** — مقداردهی اولیه اجباری
- Constructor Overloading و `this()`
- **readonly** و **init** — فیلدهای فقط‌خواندنی
- Object Initializer و مقداردهی مدرن

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | جلسه ۱۳ — کلاس و شیء |
| 10–30 | Properties | Auto-property، validation در setter |
| 30–50 | Constructors | پیش‌فرض، پارامتردار، زنجیره‌ای |
| 50–65 | readonly و init | فیلدهای ثابت و init-only |
| 65–80 | تمرین | کلاس `Product` و `Employee` |
| 80–90 | جمع‌بندی | اشتباهات + تکلیف |

---

## ۱. چرا Property؟

```csharp
// ❌ فیلد عمومی — بدون کنترل
class BadProduct
{
    public double Price;  // هرکسی می‌تواند -100 بگذارد!
}

// ✅ Property — اعتبارسنجی
class GoodProduct
{
    private double _price;

    public double Price
    {
        get => _price;
        set
        {
            if (value < 0)
                throw new ArgumentException("قیمت نمی‌تواند منفی باشد");
            _price = value;
        }
    }
}
```

**Encapsulation** از همین‌جا شروع می‌شود — داده پشت واسط کنترل‌شده.

---

## ۲. Auto-Property

```csharp
class Person
{
    // کامپایلر خودش backing field می‌سازد
    public string Name { get; set; } = "";
    public int Age { get; set; }

    // فقط خواندنی از بیرون
    public string Id { get; } = Guid.NewGuid().ToString();
}
```

| نوع | سینتکس | دسترسی |
|-----|--------|--------|
| خواندن/نوشتن | `{ get; set; }` | هر دو |
| فقط خواندن | `{ get; }` | فقط get |
| خصوصی set | `{ get; private set; }` | set فقط داخل کلاس |

---

## ۳. Property با منطق سفارشی

```csharp
class BankAccount
{
    private double _balance;

    public double Balance
    {
        get => _balance;
        private set => _balance = value;  // فقط داخل کلاس تغییر
    }

    public void Deposit(double amount)
    {
        if (amount > 0) Balance += amount;
    }

    public string Status => Balance >= 1_000_000 ? "طلایی" : "عادی";
}
```

**Expression-bodied property:**

```csharp
public string FullName => $"{FirstName} {LastName}";
public bool IsAdult => Age >= 18;
```

---

## ۴. Constructor — سازنده

```csharp
class Car
{
    public string Brand { get; }
    public int Year { get; }

    // Constructor — هنگام new اجرا می‌شود
    public Car(string brand, int year)
    {
        Brand = brand;
        Year = year;
    }
}

var car = new Car("پژو", 1400);
// car.Brand = "سمند";  // ❌ خطا — فقط get
```

### Constructor پیش‌فرض

اگر هیچ constructor تعریف نکنید، C# یک constructor خالی (`()`) می‌سازد.
اگر **حداقل یک** constructor تعریف کنید، پیش‌فرض **حذف** می‌شود.

```csharp
class Box
{
    public int Size { get; set; }

    public Box() { }                    // پیش‌فرض — باید خودتان بنویسید
    public Box(int size) => Size = size;
}
```

---

## ۵. Constructor Overloading

```csharp
class Rectangle
{
    public double Width { get; set; }
    public double Height { get; set; }

    public Rectangle() { }  // مربع 1x1 پیش‌فرض
    public Rectangle(double side) : this(side, side) { }
    public Rectangle(double width, double height)
    {
        Width = width;
        Height = height;
    }
}
```

---

## ۶. زنجیره Constructor با `this()` و `base()`

```csharp
class Employee
{
    public string Name { get; set; } = "";
    public string Department { get; set; } = "";
    public double Salary { get; set; }

    public Employee(string name) : this(name, "عمومی", 0) { }

    public Employee(string name, string department, double salary)
    {
        Name = name;
        Department = department;
        Salary = salary;
    }
}
```

`this(...)` — فراخوانی constructor دیگر **همین کلاس**
`base(...)` — فراخوانی constructor **کلاس پایه** (جلسه ۱۵)

---

## ۷. readonly و const

```csharp
class Circle
{
    public const double Pi = 3.14159;       // compile-time ثابت
    public readonly double CreatedAt;        // runtime — فقط در constructor/set

    public Circle()
    {
        CreatedAt = DateTime.Now.Ticks;
    }
}
```

| | const | readonly |
|---|-------|----------|
| مقدار | compile-time | runtime |
| تغییر | هرگز | فقط constructor |
| نوع | فقط primitive/string | هر نوع |

---

## ۸. init (C# 9+)

```csharp
class Order
{
    public int Id { get; init; }           // فقط هنگام ساخت
    public string Customer { get; init; } = "";
}

var order = new Order { Id = 1, Customer = "علی" };
// order.Id = 2;  // ❌ خطا
```

---

## ۹. Object Initializer

```csharp
var emp = new Employee("علی", "فناوری", 15_000_000)
{
    // اگر property قابل set باشد
};
```

---

## ۱۰. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_properties.cs](./examples/01_properties.cs) | Auto-property و validation |
| [02_constructors.cs](./examples/02_constructors.cs) | Constructor و overloading |
| [03_readonly_init.cs](./examples/03_readonly_init.cs) | readonly، const، init |
| [04_expression_bodies.cs](./examples/04_expression_bodies.cs) | Expression-bodied members |

---

## ۱۱. مثال واقعی — محصول فروشگاه

```csharp
class Product
{
    public string Name { get; set; } = "";
    public double Price { get; set; }
    public int Stock { get; private set; }

    public Product(string name, double price, int stock)
    {
        Name = name;
        Price = price > 0 ? price : throw new ArgumentException("قیمت نامعتبر");
        Stock = stock >= 0 ? stock : 0;
    }

    public void Restock(int quantity) => Stock += quantity;
    public bool TrySell(int quantity)
    {
        if (quantity <= Stock) { Stock -= quantity; return true; }
        return false;
    }

    public string DisplayPrice => $"{Price:N0} تومان";
}
```

---

## ۱۲. اجرای مثال‌ها

```powershell
./run-example.ps1 session-14-properties-constructors/examples/01_properties.cs
```

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| فیلد public به‌جای property | بدون validation | Property با private backing field |
| فراموش کردن `()` در constructor chain | compile error | `this(a, b)` |
| set در property فقط‌خواندن | compile error | `{ get; }` یا `init` |
| مقداردهی readonly خارج constructor | compile error | فقط در constructor |
| Constructor پیش‌فرض بعد از حذف | `new Class()` خطا | `public Class() { }` اضافه کنید |

---

## ۱۴. تمرین کلاسی

1. کلاس `Temperature` — property `Celsius` با validation (-273 تا ...)
2. کلاس `Book` — constructor اجباری برای Title و Author
3. کلاس `TimeSpan24` — ساعت و دقیقه با property `TotalMinutes`

---

## ۱۵. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۶. خلاصه

```
Field     → داده خام (معمولاً private)
Property  → get/set کنترل‌شده
Constructor → مقداردهی اولیه اجباری
readonly  → مقدار ثابت بعد از ساخت
init      → set فقط در object initializer
```

---

**جلسه قبل:** [۱۳ — کلاس](../session-13-classes/) | **بعد:** [۱۵ — وراثت](../session-15-inheritance/)
