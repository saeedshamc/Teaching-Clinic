// ============================================================
// تمرین ۱ جلسه ۴۷ — Cache Service (پاسخ نمونه)
// ============================================================

class Cache
{
    private readonly Dictionary<string, object> _data = new();
    public T GetOrCreate<T>(string key, Func<T> factory)
    {
        if (_data.TryGetValue(key, out var val)) { Console.WriteLine($"  HIT: {key}"); return (T)val; }
        Console.WriteLine($"  MISS: {key}");
        var created = factory();
        _data[key] = created!;
        return created;
    }
}

var cache = new Cache();
cache.GetOrCreate("users", () => new List<string> { "Ali", "Mariam" });
cache.GetOrCreate("users", () => new List<string> { "Should not run" });
