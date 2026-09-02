# جلسه ۱۱: مجموعه‌ها (Collections) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تفاوت Array و Collection — fixed vs dynamic
- `List<T>` — لیست dynamic-size
- `Dictionary<TKey, TValue>` — key-value lookup
- `HashSet<T>` — مجموعه بدون تکرار
- `Queue<T>` — FIFO — صف
- متدهای پرکاربرد: Add, Remove, Contains, Count
- iteration با foreach

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | Array vs Collection |
| 10–30 | List<T> |
| 30–50 | Dictionary |
| 50–65 | HashSet |
| 65–75 | Queue |
| 75–90 | تمرین + تکلیف |

---

## ۱. Array vs Collection

| | Array | List<T> |
|---|-------|---------|
| اندازه | Fixed | Dynamic |
| Add/Remove | ❌ | ✅ |
| Index | ✅ | ✅ |
| Performance | سریع‌تر | کمی overhead |

**قانون:** اگر اندازه ثابت → array؛ اگر متغیر → List

---

## ۲. List<T>

```csharp
var names = new List<string> { "علی", "مریم" };
names.Add("رضا");
names.Remove("مریم");
names.Insert(0, "سارا");

Console.WriteLine(names.Count);
Console.WriteLine(names[0]);
Console.WriteLine(names.Contains("علی"));
```

### ۲.۱. Iteration

```csharp
foreach (string name in names)
    Console.WriteLine(name);

for (int i = 0; i < names.Count; i++)
    Console.WriteLine(names[i]);
```

### ۲.۲. متدهای مفید

```csharp
names.Sort();
names.Reverse();
names.Clear();
int idx = names.IndexOf("علی");
```

---

## ۳. Dictionary<TKey, TValue>

```csharp
var ages = new Dictionary<string, int>
{
    ["علی"] = 25,
    ["مریم"] = 30
};

ages["رضا"] = 28;
Console.WriteLine(ages["علی"]);

if (ages.TryGetValue("سارا", out int age))
    Console.WriteLine(age);
else
    Console.WriteLine("پیدا نشد");
```

### ۳.۱. Iteration

```csharp
foreach (var kvp in ages)
    Console.WriteLine($"{kvp.Key}: {kvp.Value}");

foreach (string key in ages.Keys)
    Console.WriteLine(key);
```

**کاربرد:** lookup سریع O(1) — cache, config, word count

---

## ۴. HashSet<T>

```csharp
var unique = new HashSet<int> { 1, 2, 3, 3, 2 };
// { 1, 2, 3 } — تکرار حذف

unique.Add(4);
unique.Add(2);  // false — already exists
Console.WriteLine(unique.Contains(3));
```

**ویژگی:** unique elements — بدون ترتیب insert

**کاربرد:** حذف duplicate، membership test

---

## ۵. Queue<T> — FIFO

```csharp
var queue = new Queue<string>();
queue.Enqueue("اول");
queue.Enqueue("دوم");
queue.Enqueue("سوم");

while (queue.Count > 0)
{
    string item = queue.Dequeue();
    Console.WriteLine(item);
}
// اول، دوم، سوم
```

**FIFO:** First In, First Out — صف چاپ، task queue

---

## ۶. Stack<T> (مرجع)

```csharp
var stack = new Stack<int>();
stack.Push(1);
stack.Push(2);
int top = stack.Pop();  // 2 — LIFO
```

**جلسه بعدی:** کاربرد بیشتر در الگوریتم

---

## ۷. مثال واقعی — فهرست خرید

```csharp
var cart = new List<string>();
cart.Add("لباس");
cart.Add("کفش");
cart.Add("کلاه");

var prices = new Dictionary<string, decimal>
{
    ["لباس"] = 250_000,
    ["کفش"] = 180_000,
    ["کلاه"] = 90_000
};

decimal total = 0;
foreach (string item in cart)
{
    if (prices.TryGetValue(item, out decimal price))
    {
        Console.WriteLine($"{item}: {price:N0}");
        total += price;
    }
}
Console.WriteLine($"جمع: {total:N0}");
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_list.cs](./examples/01_list.cs) | List<T> |
| [02_dictionary.cs](./examples/02_dictionary.cs) | Dictionary |
| [03_hashset.cs](./examples/03_hashset.cs) | HashSet |
| [04_queue.cs](./examples/04_queue.cs) | Queue |

---

## ۹. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `dict["key"]` بدون check | KeyNotFoundException |
| modify List در foreach | InvalidOperationException |
| HashSet برای ordered data | ترتیب تضمین نیست |
| List vs Array confusion | `.Length` vs `.Count` |
| duplicate key در Dictionary | ArgumentException |

---

## ۱۰. بهترین شیوه‌ها

1. **`TryGetValue`** به‌جای `dict[key]`
2. **Initial capacity** برای List بزرگ — `new List<int>(1000)`
3. **HashSet** برای unique check سریع
4. **`var`** وقتی type واضح است
5. **LINQ** جلسه ۲۴ — query روی collections

---

## ۱۱. تمرین کلاسی

1. List — add/remove/print
2. Dictionary — phonebook
3. HashSet — unique words
4. Queue — simulate print queue

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۲. خلاصه

- ✅ List — dynamic array
- ✅ Dictionary — key-value
- ✅ HashSet — unique set
- ✅ Queue — FIFO

**جلسه قبل:** [۱۰](../session-10-exceptions/) | **بعد:** [۱۲](../session-12-beginner-project/)
