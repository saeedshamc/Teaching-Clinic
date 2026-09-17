// ============================================================
// جلسه ۴۳ — Generic Repository
// ============================================================

class Product { public int Id { get; set; } public string Name { get; set; } = ""; public decimal Price { get; set; } }
class Category { public int Id { get; set; } public string Name { get; set; } = ""; }

interface IRepository<T> where T : class
{
    T? GetById(int id);
    IReadOnlyList<T> GetAll();
    void Add(T entity);
    void Delete(int id);
}

class InMemoryRepository<T> : IRepository<T> where T : class
{
    private readonly List<T> _store = new();
    private readonly Func<T, int> _getId;
    private readonly Action<T, int> _setId;
    private int _nextId = 1;

    public InMemoryRepository(Func<T, int> getId, Action<T, int> setId)
    {
        _getId = getId; _setId = setId;
    }

    public T? GetById(int id) => _store.FirstOrDefault(e => _getId(e) == id);
    public IReadOnlyList<T> GetAll() => _store.AsReadOnly();

    public void Add(T entity)
    {
        _setId(entity, _nextId++);
        _store.Add(entity);
    }

    public void Delete(int id) => _store.RemoveAll(e => _getId(e) == id);
}

var productRepo = new InMemoryRepository<Product>(p => p.Id, (p, id) => p.Id = id);
var categoryRepo = new InMemoryRepository<Category>(c => c.Id, (c, id) => c.Id = id);

productRepo.Add(new Product { Name = "لپ‌تاپ", Price = 25_000_000 });
productRepo.Add(new Product { Name = "ماوس", Price = 450_000 });
categoryRepo.Add(new Category { Name = "الکترونیک" });

Console.WriteLine("=== Products ===");
foreach (var p in productRepo.GetAll())
    Console.WriteLine($"  [{p.Id}] {p.Name} — {p.Price:N0}");

Console.WriteLine("\n=== Categories ===");
foreach (var c in categoryRepo.GetAll())
    Console.WriteLine($"  [{c.Id}] {c.Name}");
