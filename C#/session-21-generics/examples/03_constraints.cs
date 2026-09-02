// ============================================================
// جلسه ۲۱ — Type Constraints
// فایل: 03_constraints.cs
// ============================================================

// where T : class — فقط reference type
class Cache<T> where T : class
{
    private T? _cached;

    public void Set(T value) => _cached = value;
    public T? Get() => _cached;
    public bool HasValue => _cached != null;
}

// where T : new() — باید constructor بدون پارامتر داشته باشد
class Factory<T> where T : new()
{
    public T Create() => new T();
    public T[] CreateMany(int count) => Enumerable.Range(0, count).Select(_ => new T()).ToArray();
}

class Sample { public string Name { get; set; } = "default"; }

// where T : IComparable<T>
static T GetMin<T>(T a, T b) where T : IComparable<T>
    => a.CompareTo(b) < 0 ? a : b;

// where T : class, IComparable<T> — ترکیب constraints
static T GetMaxOf<T>(List<T> items) where T : class, IComparable<T>
{
    if (items.Count == 0) throw new ArgumentException("لیست خالی");
    T max = items[0];
    foreach (var item in items)
        if (item.CompareTo(max) > 0) max = item;
    return max;
}

var cache = new Cache<string>();
cache.Set("داده ذخیره شده");
Console.WriteLine($"Cache: {cache.Get()}");

var factory = new Factory<Sample>();
var samples = factory.CreateMany(3);
Console.WriteLine($"Factory: {samples.Length} نمونه — {samples[0].Name}");

Console.WriteLine($"Min: {GetMin(10, 5)}");
Console.WriteLine($"Min str: {GetMin("zebra", "apple")}");

var names = new List<string> { "علی", "مریم", "رضا", "سارا" };
Console.WriteLine($"Max name: {GetMaxOf(names)}");
