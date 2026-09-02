// تمرین ۴–۵

class Result<T>
{
    public bool IsSuccess { get; }
    public T? Value { get; }
    public string? Error { get; }
    private Result(T v) { IsSuccess = true; Value = v; }
    private Result(string e) { IsSuccess = false; Error = e; }
    public static Result<T> Ok(T v) => new(v);
    public static Result<T> Fail(string e) => new(e);
}

class Product { public int Id { get; set; } public string Name { get; set; } = ""; public double Price { get; set; } }

class ProductRepository
{
    private readonly List<Product> _items = new();
    private int _nextId = 1;
    public void Add(Product p) { p.Id = _nextId++; _items.Add(p); }
    public Product? GetById(int id) => _items.FirstOrDefault(p => p.Id == id);
    public IEnumerable<Product> GetAll() => _items;
    public bool Delete(int id) => _items.RemoveAll(p => p.Id == id) > 0;
}

Result<int> divide(int a, int b) =>
    b == 0 ? Result<int>.Fail("تقسیم بر صفر") : Result<int>.Ok(a / b);

var r1 = divide(10, 2);
var r2 = divide(10, 0);
Console.WriteLine(r1.IsSuccess ? $"نتیجه: {r1.Value}" : r1.Error);
Console.WriteLine(r2.IsSuccess ? $"نتیجه: {r2.Value}" : r2.Error);

var repo = new ProductRepository();
repo.Add(new Product { Name = "لپ‌تاپ", Price = 25_000_000 });
repo.Add(new Product { Name = "ماوس", Price = 450_000 });
foreach (var p in repo.GetAll()) Console.WriteLine($"#{p.Id} {p.Name}: {p.Price:N0}");
