# جلسه ۲۵: LINQ پیشرفته (LINQ Advanced) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- **Join** — ترکیب دو منبع داده
- **SelectMany** — flatten کردن مجموعه‌های تو در تو
- **Skip/Take** — صفحه‌بندی (Pagination)
- **Distinct, Union, Intersect, Except** — عملیات مجموعه
- **Aggregate** — تجمیع سفارشی
- **Let, Into** در Query Syntax
- بهینه‌سازی و deferred vs immediate execution

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | LINQ Basics |
| 10–30 | Join | Inner Join داده‌ها |
| 30–45 | Skip/Take | Pagination |
| 45–60 | Set Operations | Distinct, Union, Except |
| 60–75 | SelectMany و Aggregate | پیشرفته |
| 75–90 | جمع‌بندی + تکلیف |

---

## ۱. Join — ترکیب دو منبع

```csharp
var orders = new[]
{
    new { OrderId = 1, CustomerId = 101 },
    new { OrderId = 2, CustomerId = 102 },
    new { OrderId = 3, CustomerId = 101 }
};

var customers = new[]
{
    new { Id = 101, Name = "علی" },
    new { Id = 102, Name = "مریم" }
};

var result = from o in orders
             join c in customers on o.CustomerId equals c.Id
             select new { o.OrderId, c.Name };
```

**Method Syntax:**

```csharp
var result = orders.Join(customers,
    o => o.CustomerId,
    c => c.Id,
    (o, c) => new { o.OrderId, c.Name });
```

---

## ۲. GroupJoin — Left Join

```csharp
var grouped = customers.GroupJoin(orders,
    c => c.Id,
    o => o.CustomerId,
    (c, orderGroup) => new
    {
        c.Name,
        OrderCount = orderGroup.Count()
    });
```

---

## ۳. SelectMany — Flatten

```csharp
class Department
{
    public string Name { get; set; } = "";
    public List<string> Employees { get; set; } = new();
}

var departments = new List<Department>
{
    new() { Name = "فناوری", Employees = new() { "علی", "مریم" } },
    new() { Name = "فروش", Employees = new() { "رضا" } }
};

// همه کارمندان — flat
var allEmployees = departments.SelectMany(d => d.Employees);
// ["علی", "مریم", "رضا"]

// با اطلاعات دپارتمان
var withDept = departments.SelectMany(
    d => d.Employees,
    (d, emp) => new { d.Name, Employee = emp });
```

---

## ۴. Skip و Take — Pagination

```csharp
var all = Enumerable.Range(1, 100);

int pageSize = 10;
int page = 3;  // صفحه ۳

var pageData = all
    .Skip((page - 1) * pageSize)
    .Take(pageSize);
// [21, 22, ..., 30]

// TakeWhile / SkipWhile
var nums = new[] { 1, 2, 3, 4, 5, 1, 2 };
var taken = nums.TakeWhile(n => n < 4);  // [1, 2, 3]
```

---

## ۵. Set Operations

```csharp
var setA = new[] { 1, 2, 3, 4, 5 };
var setB = new[] { 4, 5, 6, 7, 8 };

var union = setA.Union(setB);           // {1,2,3,4,5,6,7,8}
var intersect = setA.Intersect(setB);   // {4, 5}
var except = setA.Except(setB);         // {1, 2, 3}
var distinct = new[] { 1, 2, 2, 3, 3 }.Distinct();  // {1, 2, 3}
```

---

## ۶. Aggregate — تجمیع سفارشی

```csharp
var words = new[] { "سلام", "دنیا", "C#" };

// رشته ترکیبی
var sentence = words.Aggregate((a, b) => a + " " + b);
// "سلام دنیا C#"

// با seed
var product = Enumerable.Range(1, 5).Aggregate(1, (acc, n) => acc * n);
// 120 = 5!

// نتیجه سفارشی
var stats = scores.Aggregate(
    new { Min = int.MaxValue, Max = int.MinValue, Sum = 0 },
    (acc, score) => new
    {
        Min = Math.Min(acc.Min, score),
        Max = Math.Max(acc.Max, score),
        Sum = acc.Sum + score
    });
```

---

## ۷. Zip — ترکیب دو به دو

```csharp
var names = new[] { "علی", "مریم", "رضا" };
var ages = new[] { 25, 30, 22 };

var paired = names.Zip(ages, (name, age) => $"{name}: {age} سال");
// ["علی: 25 سال", "مریم: 30 سال", "رضا: 22 سال"]
```

---

## ۸. Deferred vs Immediate

| Deferred (lazy) | Immediate (eager) |
|-----------------|-------------------|
| `Where`, `Select`, `OrderBy` | `ToList`, `ToArray` |
| `GroupBy`, `Join` | `Count`, `Sum`, `First` |
| `Take`, `Skip` | `ToDictionary` |

```csharp
var query = numbers.Where(n => n > 5);  // هنوز اجرا نشده
var list = query.ToList();               // الان اجرا شد
```

**نکته:** اگر منبع داده تغییر کند بین تعریف query و iterate، نتیجه متفاوت است.

---

## ۹. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_join.cs](./examples/01_join.cs) | Inner Join |
| [02_skip_take.cs](./examples/02_skip_take.cs) | Pagination |
| [03_set_operations.cs](./examples/03_set_operations.cs) | Union, Except |
| [04_selectmany_aggregate.cs](./examples/04_selectmany_aggregate.cs) | پیشرفته |

---

## ۱۰. مثال واقعی — گزارش فروش

```csharp
var orderDetails = orders.Join(products,
    o => o.ProductId,
    p => p.Id,
    (o, p) => new { o.Date, p.Name, o.Quantity, Revenue = o.Quantity * p.Price })
    .GroupBy(x => x.Date.Month)
    .Select(g => new { Month = g.Key, Total = g.Sum(x => x.Revenue) })
    .OrderBy(x => x.Month);
```

---

## ۱۱. اجرای مثال‌ها

```powershell
./run-example.ps1 session-25-linq-advanced/examples/01_join.cs
```

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| Join با `==` در query | باید `equals` | `on x equals y` |
| Multiple enumerate | اجرای دوباره | `.ToList()` یکبار |
| Take قبل از OrderBy | ترتیب اشتباه | اول OrderBy |
| N+1 در Join | کندی | یک query |
| SelectMany فراموش | nested list | SelectMany |

---

## ۱۳. تمرین کلاسی

1. Join سفارش‌ها و مشتریان
2. Pagination — صفحه ۲ از ۵
3. Distinct شهرهای دانشجویان

---

## ۱۴. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۵. خلاصه

```
Join          → ترکیب دو منبع
GroupJoin     → Left Join
SelectMany    → Flatten
Skip/Take     → Pagination
Union/Except  → عملیات مجموعه
Aggregate     → تجمیع سفارشی
Zip           → جفت‌سازی
ToList()      → اجرای فوری
```

---

**جلسه قبل:** [۲۴ — LINQ Basics](../session-24-linq-basics/) | **بعد:** [۲۶ — Extension Methods](../session-26-extension-methods/)
