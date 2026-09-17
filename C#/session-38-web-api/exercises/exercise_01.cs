// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۸ (In-Memory API Logic)
// ============================================================

public record ProductDto(int Id, string Name, decimal Price);
public record CreateProductDto(string Name, decimal Price);

public class ProductService
{
    private readonly List<ProductDto> _products = new();
    private int _nextId = 1;

    public IReadOnlyList<ProductDto> GetAll() => _products.AsReadOnly();

    public ProductDto? GetById(int id) => _products.FirstOrDefault(p => p.Id == id);

    public ProductDto Create(CreateProductDto dto)
    {
        var product = new ProductDto(_nextId++, dto.Name, dto.Price);
        _products.Add(product);
        return product;
    }

    public bool Delete(int id) => _products.RemoveAll(p => p.Id == id) > 0;
}

var svc = new ProductService();
Console.WriteLine($"POST → 201: {svc.Create(new CreateProductDto("لپ‌تاپ", 25_000_000))}");
Console.WriteLine($"POST → 201: {svc.Create(new CreateProductDto("ماوس", 350_000))}");
Console.WriteLine($"GET  → 200: {svc.GetAll().Count} products");
Console.WriteLine($"GET  → 200: {svc.GetById(1)}");
Console.WriteLine($"GET  → 404: {svc.GetById(99) is null}");
Console.WriteLine($"DELETE → 204: {svc.Delete(1)}");
Console.WriteLine($"DELETE → 404: {!svc.Delete(99)}");

Console.WriteLine("\nدر Web API واقعی این logic در Controller/Minimal API + HTTP status codes wrap می‌شود.");
