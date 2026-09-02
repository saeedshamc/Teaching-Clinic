// ============================================================
// جلسه ۳۷ — EF Core: CRUD کامل
// ============================================================

var db = new ProductDbContext();

db.Add(new Product { Name = "لپ‌تاپ", Price = 25_000_000, Stock = 10 });
db.Add(new Product { Name = "ماوس", Price = 350_000, Stock = 50 });
db.SaveChanges();

Console.WriteLine("=== All Products ===");
foreach (var p in db.Products)
    Console.WriteLine($"  [{p.Id}] {p.Name} — {p.Price:N0} — Stock: {p.Stock}");

var laptop = db.Products.First(p => p.Name == "لپ‌تاپ");
laptop.Price = 23_000_000;
laptop.Stock = 8;
db.Update(laptop);
db.SaveChanges();
db.Delete(2);
db.SaveChanges();

Console.WriteLine("\n=== After Update/Delete ===");
foreach (var p in db.Products.OrderBy(x => x.Price))
    Console.WriteLine($"  {p.Name}: {p.Price:N0}");

Console.WriteLine($"\nExpensive items: {db.Products.Count(p => p.Price > 1_000_000)}");

public class Product
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public decimal Price { get; set; }
    public int Stock { get; set; }
}

public class ProductDbContext
{
    private readonly List<Product> _products = new();
    private int _nextId = 1;

    public IQueryable<Product> Products => _products.AsQueryable();

    public void Add(Product product)
    {
        product.Id = _nextId++;
        _products.Add(product);
    }

    public void Update(Product product)
    {
        var idx = _products.FindIndex(p => p.Id == product.Id);
        if (idx >= 0) _products[idx] = product;
    }

    public bool Delete(int id)
    {
        var p = _products.FirstOrDefault(x => x.Id == id);
        if (p is null) return false;
        _products.Remove(p);
        return true;
    }

    public void SaveChanges() => Console.WriteLine("  SaveChanges → DB updated");
}
