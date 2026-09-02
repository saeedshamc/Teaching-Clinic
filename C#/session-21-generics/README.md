# جلسه ۲۱: Generic (عمومی) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک **Generics** — کد قابل استفاده مجدد با نوع دلخواه
- `Generic Class` و `Generic Method`
- محدودیت‌های نوع: `where T : class`, `new()`, `IComparable`
- `List<T>`, `Dictionary<K,V>` — مجموعه‌های Generic
- Generic Repository Pattern
- مزایا نسبت به `object` و boxing

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | مشکل کد تکراری برای انواع مختلف |
| 10–30 | Generic Class | `Box<T>`, `Pair<T1,T2>` |
| 30–50 | Generic Method | `Swap<T>`, `FindMax<T>` |
| 50–65 | Constraints | where T : IComparable |
| 65–80 | Repository | `IRepository<T>` |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. مشکل — کد تکراری

```csharp
class IntBox { public int Value { get; set; } }
class StringBox { public string Value { get; set; } }
class DoubleBox { public double Value { get; set; } }
// برای هر نوع یک کلاس! 😱
```

```csharp
// ❌ object — boxing/unboxing + cast ناامن
class ObjectBox
{
    public object Value { get; set; }
}
var box = new ObjectBox { Value = 42 };
int n = (int)box.Value;  // cast خطرناک
```

---

## ۲. Generic Class

```csharp
class Box<T>
{
    public T Value { get; set; }

    public Box(T value) => Value = value;

    public override string ToString() => $"Box<{typeof(T).Name}>({Value})";
}

var intBox = new Box<int>(42);
var strBox = new Box<string>("سلام");
var bookBox = new Box<Book>(new Book("978-1", "کلین کد", "RM"));
```

**`<T>`** = Type Parameter — هنگام استفاده مشخص می‌شود.

---

## ۳. چند Type Parameter

```csharp
class Pair<TKey, TValue>
{
    public TKey Key { get; set; }
    public TValue Value { get; set; }

    public Pair(TKey key, TValue value)
    {
        Key = key;
        Value = value;
    }
}

var pair = new Pair<string, int>("سن", 25);
```

---

## ۴. Generic Method

```csharp
static void Swap<T>(ref T a, ref T b)
{
    (a, b) = (b, a);
}

static T FindMax<T>(T a, T b) where T : IComparable<T>
{
    return a.CompareTo(b) > 0 ? a : b;
}

int x = 5, y = 10;
Swap(ref x, ref y);  // x=10, y=5

string s1 = "apple", s2 = "banana";
Console.WriteLine(FindMax(s1, s2));  // banana
```

---

## ۵. Type Constraints (where)

```csharp
// T باید کلاس باشد (reference type)
class Cache<T> where T : class
{
    private T? _value;
    public void Set(T value) => _value = value;
}

// T باید constructor بدون پارامتر داشته باشد
class Factory<T> where T : new()
{
    public T Create() => new T();
}

// T باید IComparable باشد
static T Max<T>(T a, T b) where T : IComparable<T>
    => a.CompareTo(b) > 0 ? a : b;

// چند constraint
class Repository<T> where T : class, new()
{
    public T Create() => new T();
}
```

| Constraint | معنی |
|------------|------|
| `where T : struct` | value type |
| `where T : class` | reference type |
| `where T : new()` | constructor بدون پارامتر |
| `where T : BaseClass` | ارث‌بری از BaseClass |
| `where T : IInterface` | پیاده‌سازی interface |

---

## ۶. Generic Collections

```csharp
List<int> numbers = new() { 1, 2, 3 };
List<string> names = new() { "علی", "مریم" };
Dictionary<string, int> ages = new() { ["علی"] = 25 };

// بدون cast — type-safe
foreach (int n in numbers) { }
```

---

## ۷. Generic Repository

```csharp
interface IRepository<T> where T : class
{
    void Add(T entity);
    T? GetById(int id);
    IEnumerable<T> GetAll();
    bool Delete(int id);
}

class InMemoryRepository<T> : IRepository<T> where T : class
{
    private readonly List<T> _items = new();
    private readonly Func<T, int> _idSelector;

    public InMemoryRepository(Func<T, int> idSelector) =>
        _idSelector = idSelector;

    public void Add(T entity) => _items.Add(entity);
    public T? GetById(int id) => _items.FirstOrDefault(e => _idSelector(e) == id);
    public IEnumerable<T> GetAll() => _items;
    public bool Delete(int id)
    {
        var item = GetById(id);
        return item != null && _items.Remove(item);
    }
}
```

---

## ۸. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_generic_class.cs](./examples/01_generic_class.cs) | Box<T>, Pair<T1,T2> |
| [02_generic_methods.cs](./examples/02_generic_methods.cs) | Swap, FindMax |
| [03_constraints.cs](./examples/03_constraints.cs) | where constraints |
| [04_generic_repository.cs](./examples/04_generic_repository.cs) | IRepository<T> |

---

## ۹. مثال واقعی — Result<T>

```csharp
class Result<T>
{
    public bool IsSuccess { get; }
    public T? Value { get; }
    public string? Error { get; }

    private Result(T value) { IsSuccess = true; Value = value; }
    private Result(string error) { IsSuccess = false; Error = error; }

    public static Result<T> Ok(T value) => new(value);
    public static Result<T> Fail(string error) => new(error);
}

Result<int> r1 = Result<int>.Ok(42);
Result<int> r2 = Result<int>.Fail("خطا");
```

---

## ۱۰. اجرای مثال‌ها

```powershell
./run-example.ps1 session-21-generics/examples/01_generic_class.cs
```

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `Box<int>` و `Box<string>` یکی | Generic types متفاوتند | هر T نوع جدا |
| Cast از `object` | runtime error | Generic |
| Constraint فراموش | compile error | `where T : ...` |
| `T` با نوع concrete اشتباه | compile error | constraint مناسب |
| Over-generic | پیچیدگی بی‌مورد | فقط وقتی لازم |

---

## ۱۲. Generic vs Non-Generic

| | ArrayList (قدیم) | List<T> |
|---|-----------------|---------|
| Type safety | ❌ cast | ✅ compile-time |
| Performance | boxing | بدون boxing |
| IntelliSense | ضعیف | قوی |

---

## ۱۳. تمرین کلاسی

1. `Stack<T>` — Push, Pop, Peek
2. `FindMax<T>` با `IComparable<T>`
3. `Repository<Book>` برای پروژه کتابخانه

---

## ۱۴. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۵. خلاصه

```
class Name<T>           → کلاس عمومی
void Method<T>(T param) → متد عمومی
where T : constraint    → محدودیت نوع
List<T>                 → مجموعه type-safe
```

---

**جلسه قبل:** [۲۰ — پروژه OOP](../session-20-oop-project/) | **بعد:** [۲۲ — Delegates](../session-22-delegates/)
