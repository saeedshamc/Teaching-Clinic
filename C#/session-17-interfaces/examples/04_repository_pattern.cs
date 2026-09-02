// ============================================================
// جلسه ۱۷ — Repository Pattern
// فایل: 04_repository_pattern.cs
// ============================================================

record Product(string Name, double Price, int Stock);

interface IProductRepository
{
    void Add(Product product);
    Product? FindByName(string name);
    IEnumerable<Product> GetAll();
    bool UpdateStock(string name, int newStock);
    bool Delete(string name);
}

class InMemoryProductRepository : IProductRepository
{
    private readonly List<Product> _products = new();

    public void Add(Product product) => _products.Add(product);

    public Product? FindByName(string name) =>
        _products.FirstOrDefault(p => p.Name == name);

    public IEnumerable<Product> GetAll() => _products;

    public bool UpdateStock(string name, int newStock)
    {
        var idx = _products.FindIndex(p => p.Name == name);
        if (idx < 0) return false;
        var p = _products[idx];
        _products[idx] = p with { Stock = newStock };
        return true;
    }

    public bool Delete(string name) =>
        _products.RemoveAll(p => p.Name == name) > 0;
}

class ProductService
{
    private readonly IProductRepository _repo;
    public ProductService(IProductRepository repo) => _repo = repo;

    public void RegisterProduct(string name, double price, int stock)
    {
        _repo.Add(new Product(name, price, stock));
        Console.WriteLine($"✅ «{name}» ثبت شد");
    }

    public void ShowInventory()
    {
        Console.WriteLine("\n=== موجودی انبار ===");
        foreach (var p in _repo.GetAll())
            Console.WriteLine($"  {p.Name}: {p.Price:N0} تومان | موجودی: {p.Stock}");
    }
}

IProductRepository repo = new InMemoryProductRepository();
var service = new ProductService(repo);

service.RegisterProduct("لپ‌تاپ", 25_000_000, 5);
service.RegisterProduct("ماوس", 450_000, 20);
service.ShowInventory();

repo.UpdateStock("ماوس", 18);
Console.WriteLine($"\nماوس بعد از فروش: {repo.FindByName("ماوس")?.Stock}");
