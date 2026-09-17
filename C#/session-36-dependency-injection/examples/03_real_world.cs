// ============================================================
// جلسه ۳۶ — DI: اپلیکیشن لایه‌ای
// ============================================================

var service = AppComposition.BuildProductService();
service.CreateProduct("لپ‌تاپ", 25_000_000);
service.CreateProduct("ماوس", 350_000);
Console.WriteLine($"ارزش کل انبار: {service.TotalValue():N0} تومان");

var fakeRepo = new FakeProductRepository();
var testService = AppComposition.BuildProductService(fakeRepo);
testService.CreateProduct("Test Item");
Console.WriteLine($"Test repo count: {fakeRepo.Data.Count}");

public interface IProductRepository
{
    IEnumerable<string> GetAll();
    void Add(string name);
}

public class InMemoryProductRepository : IProductRepository
{
    private readonly List<string> _products = new();
    public IEnumerable<string> GetAll() => _products;
    public void Add(string name) => _products.Add(name);
}

public class FakeProductRepository : IProductRepository
{
    public List<string> Data { get; } = new();
    public IEnumerable<string> GetAll() => Data;
    public void Add(string name) => Data.Add(name);
}

public class ProductService
{
    private readonly IProductRepository _repo;
    public ProductService(IProductRepository repo) => _repo = repo;

    public void CreateProduct(string name, decimal price)
    {
        _repo.Add($"{name}:{price}");
    }

    public decimal TotalValue() => _repo.GetAll().Count * 1000;
}

public static class AppComposition
{
    public static ProductService BuildProductService(IProductRepository? repo = null) =>
        new(repo ?? new InMemoryProductRepository());
}
