# جلسه ۲۴: LINQ مقدماتی (LINQ Basics) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک **LINQ** — Language Integrated Query
- **Query Syntax** در مقابل **Method Syntax**
- عملیات پایه: `Where`, `Select`, `OrderBy`, `GroupBy`
- `First`, `Single`, `Any`, `All`, `Count`
- `Sum`, `Average`, `Min`, `Max`
- کار با `IEnumerable<T>` و deferred execution

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | Collections و Lambda |
| 10–30 | Where و Select | فیلتر و تبدیل |
| 30–50 | OrderBy و GroupBy | مرتب‌سازی و گروه‌بندی |
| 50–65 | Aggregation | Sum, Avg, Count |
| 65–80 | تمرین | تحلیل داده دانشجو |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. LINQ چیست؟

> **LINQ** (Language Integrated Query) — زبان پرس‌وجوی یکپارچه برای کار با داده.

```csharp
var numbers = new[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

// Method Syntax
var evens = numbers.Where(n => n % 2 == 0);

// Query Syntax
var evens2 = from n in numbers
             where n % 2 == 0
             select n;
```

هر دو **یکسان** هستند — Method Syntax رایج‌تر است.

---

## ۲. Where — فیلتر

```csharp
var products = new[]
{
    new { Name = "لپ‌تاپ", Price = 25_000_000 },
    new { Name = "ماوس", Price = 450_000 },
    new { Name = "کتاب", Price = 150_000 }
};

var expensive = products.Where(p => p.Price > 1_000_000);
var cheap = products.Where(p => p.Price < 500_000);
```

---

## ۳. Select — تبدیل (Projection)

```csharp
var names = products.Select(p => p.Name);
var prices = products.Select(p => p.Price);

// تبدیل به نوع جدید
var summaries = products.Select(p => new
{
    p.Name,
    Category = p.Price > 1_000_000 ? "گران" : "ارزان"
});
```

---

## ۴. OrderBy / OrderByDescending

```csharp
var sorted = products.OrderBy(p => p.Price);
var sortedDesc = products.OrderByDescending(p => p.Price);

// چند سطح
var students = new[]
{
    new { Name = "علی", Grade = 18, Age = 20 },
    new { Name = "مریم", Grade = 18, Age = 22 },
    new { Name = "رضا", Grade = 15, Age = 20 }
};

var ranked = students
    .OrderByDescending(s => s.Grade)
    .ThenBy(s => s.Age);
```

---

## ۵. GroupBy

```csharp
var grouped = products.GroupBy(p =>
    p.Price > 1_000_000 ? "گران" : "ارزان");

foreach (var group in grouped)
{
    Console.WriteLine($"[{group.Key}]: {group.Count()} محصول");
    foreach (var item in group)
        Console.WriteLine($"  {item.Name}");
}
```

---

## ۶. First, Single, Any, All

```csharp
var first = numbers.First();           // 1
var firstEven = numbers.First(n => n % 2 == 0);  // 2
var last = numbers.Last();             // 10
var any = numbers.Any(n => n > 100);   // false
var all = numbers.All(n => n > 0);     // true

// FirstOrDefault — null/0 اگر نباشد
var notFound = numbers.FirstOrDefault(n => n > 100);  // 0
```

| متد | رفتار |
|-----|--------|
| `First()` | اولین — exception اگر خالی |
| `FirstOrDefault()` | اولین — default اگر خالی |
| `Single()` | تنها یکی — exception اگر 0 یا >1 |
| `Any()` | حداقل یکی؟ |
| `All()` | همه؟ |

---

## ۷. Aggregation

```csharp
var sum = numbers.Sum();
var avg = numbers.Average();
var min = numbers.Min();
var max = numbers.Max();
var count = numbers.Count();

// روی property
var totalPrice = products.Sum(p => p.Price);
var avgPrice = products.Average(p => p.Price);
```

---

## ۸. Query Syntax کامل

```csharp
var result = from p in products
             where p.Price > 500_000
             orderby p.Price descending
             select new { p.Name, p.Price };

var grouped = from s in students
              group s by s.Grade into g
              select new { Grade = g.Key, Count = g.Count() };
```

---

## ۹. Deferred Execution

```csharp
var query = numbers.Where(n => n % 2 == 0);  // هنوز اجرا نشده!
// ...
var list = query.ToList();  // الان اجرا می‌شود
```

LINQ تا زمان **iterate** یا **ToList/ToArray** اجرا نمی‌شود.

---

## ۱۰. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_where_select.cs](./examples/01_where_select.cs) | فیلتر و تبدیل |
| [02_orderby_groupby.cs](./examples/02_orderby_groupby.cs) | مرتب و گروه |
| [03_aggregation.cs](./examples/03_aggregation.cs) | Sum, Avg, Count |
| [04_query_syntax.cs](./examples/04_query_syntax.cs) | Query Syntax |

---

## ۱۱. مثال واقعی — تحلیل فروش

```csharp
var sales = new[]
{
    new { Product = "لپ‌تاپ", Amount = 25_000_000, Region = "تهران" },
    new { Product = "ماوس", Amount = 450_000, Region = "اصفهان" },
    new { Product = "لپ‌تاپ", Amount = 24_000_000, Region = "تهران" }
};

var tehranTotal = sales
    .Where(s => s.Region == "تهران")
    .Sum(s => s.Amount);

var topProducts = sales
    .GroupBy(s => s.Product)
    .Select(g => new { Product = g.Key, Total = g.Sum(s => s.Amount) })
    .OrderByDescending(x => x.Total);
```

---

## ۱۲. اجرای مثال‌ها

```powershell
./run-example.ps1 session-24-linq-basics/examples/01_where_select.cs
```

---

## ۱۳. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `First()` روی خالی | Exception | `FirstOrDefault()` |
| Query دوبار iterate | اجرای دوباره | `.ToList()` |
| `Select` بدون استفاده | بی‌فایده | نتیجه را ذخیره کنید |
| LINQ روی null | NullRef | `?.` یا بررسی null |
| `Count()` vs `.Count` | property vs method | List.Count property |

---

## ۱۴. تمرین کلاسی

1. فیلتر محصولات گران‌تر از 1M
2. میانگین نمرات دانشجوها
3. گروه‌بندی بر اساس شهر

---

## ۱۵. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۶. خلاصه

```
Where      → فیلتر
Select     → تبدیل
OrderBy    → مرتب‌سازی
GroupBy    → گروه‌بندی
Sum/Avg    → تجمیع
First/Any  → جستجو
from...select → Query Syntax
```

---

**جلسه قبل:** [۲۳ — Events](../session-23-events/) | **بعد:** [۲۵ — LINQ Advanced](../session-25-linq-advanced/)
