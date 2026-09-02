// ============================================================
// جلسه ۳۱ — Namespaces: ساختار لایه‌ای واقعی
// ============================================================

using Shop.Infrastructure;
using Shop.Application;
using Shop.Domain.Interfaces;

IProductRepository repo = new InMemoryProductRepository();
var service = new ProductService(repo);
service.Create("لپ‌تاپ", 25_000_000);
service.Create("ماوس", 350_000);
Console.WriteLine($"ارزش کل انبار: {service.TotalValue():N0} تومان");

namespace Shop.Domain.Entities;

public record Product(int Id, string Name, decimal Price);

namespace Shop.Domain.Interfaces;

using Shop.Domain.Entities;

public interface IProductRepository
{
    IEnumerable<Product> GetAll();
    void Add(Product product);
}

namespace Shop.Infrastructure;

using Shop.Domain.Entities;
using Shop.Domain.Interfaces;

public class InMemoryProductRepository : IProductRepository
{
    private readonly List<Product> _products = new();
    public IEnumerable<Product> GetAll() => _products;
    public void Add(Product p) => _products.Add(p);
}

namespace Shop.Application;

using Shop.Domain.Entities;
using Shop.Domain.Interfaces;

public class ProductService
{
    private readonly IProductRepository _repo;
    public ProductService(IProductRepository repo) => _repo = repo;

    public void Create(string name, decimal price)
    {
        var all = _repo.GetAll().ToList();
        int id = all.Count == 0 ? 1 : all.Max(p => p.Id) + 1;
        _repo.Add(new Product(id, name, price));
    }

    public decimal TotalValue() => _repo.GetAll().Sum(p => p.Price);
}
