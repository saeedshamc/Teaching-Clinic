// ============================================================
// جلسه ۴۷ — In-Memory Caching
// ============================================================

class SimpleCache
{
    private readonly Dictionary<string, (object Value, DateTime Expiry)> _store = new();

    public bool TryGet<T>(string key, out T? value)
    {
        value = default;
        if (!_store.TryGetValue(key, out var entry)) return false;
        if (DateTime.UtcNow > entry.Expiry)
        {
            _store.Remove(key);
            Console.WriteLine($"  Cache EXPIRED: {key}");
            return false;
        }
        value = (T)entry.Value;
        return true;
    }

    public void Set(string key, object value, TimeSpan ttl)
        => _store[key] = (value, DateTime.UtcNow.Add(ttl));

    public void Remove(string key) => _store.Remove(key);
}

class CategoryService
{
    private readonly SimpleCache _cache;
    private readonly List<string> _db = new() { "Electronics", "Books", "Clothing" };
    private int _dbHits = 0;

    public CategoryService(SimpleCache cache) => _cache = cache;

    public List<string> GetCategories()
    {
        if (_cache.TryGet<List<string>>("categories", out var cached))
        {
            Console.WriteLine("  Cache HIT ✓");
            return cached!;
        }

        Console.WriteLine("  Cache MISS → DB query");
        _dbHits++;
        var data = _db.ToList();
        _cache.Set("categories", data, TimeSpan.FromSeconds(30));
        return data;
    }

    public void AddCategory(string name)
    {
        _db.Add(name);
        _cache.Remove("categories");
        Console.WriteLine($"  Added '{name}', cache invalidated");
    }

    public int DbHits => _dbHits;
}

var cache = new SimpleCache();
var svc = new CategoryService(cache);

Console.WriteLine("=== Caching ===");
svc.GetCategories();
svc.GetCategories();
svc.GetCategories();
Console.WriteLine($"  DB hits so far: {svc.DbHits}");

svc.AddCategory("Sports");
svc.GetCategories();
Console.WriteLine($"  DB hits total: {svc.DbHits}");
