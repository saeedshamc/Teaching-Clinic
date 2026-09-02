// ============================================================
// جلسه ۴۰ — ساخت پروژه وب
// فایل: 02_dto_mapping.cs — Entity به DTO
// ============================================================

class Product
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public decimal Price { get; set; }
    public string InternalSku { get; set; } = ""; // نباید به API برود
    public decimal CostPrice { get; set; }          // محرمانه
}

record ProductListDto(int Id, string Name, decimal Price);
record ProductDetailDto(int Id, string Name, decimal Price, string DisplayPrice);
record CreateProductRequest(string Name, decimal Price);

static class ProductMapper
{
    public static ProductListDto ToListDto(Product p)
        => new(p.Id, p.Name, p.Price);

    public static ProductDetailDto ToDetailDto(Product p)
        => new(p.Id, p.Name, p.Price, $"{p.Price:N0} تومان");

    public static Product FromCreateRequest(CreateProductRequest req, string sku)
        => new() { Name = req.Name, Price = req.Price, InternalSku = sku, CostPrice = req.Price * 0.7m };
}

var products = new List<Product>
{
    new() { Id = 1, Name = "لپ‌تاپ", Price = 25_000_000, InternalSku = "SKU-001", CostPrice = 20_000_000 },
    new() { Id = 2, Name = "ماوس", Price = 450_000, InternalSku = "SKU-002", CostPrice = 300_000 }
};

Console.WriteLine("=== API Response: GET /products (List DTO) ===");
foreach (var dto in products.Select(ProductMapper.ToListDto))
    Console.WriteLine($"  {{ id: {dto.Id}, name: \"{dto.Name}\", price: {dto.Price} }}");

Console.WriteLine("\n=== API Response: GET /products/1 (Detail DTO) ===");
var detail = ProductMapper.ToDetailDto(products[0]);
Console.WriteLine($"  {{ id: {detail.Id}, name: \"{detail.Name}\", displayPrice: \"{detail.DisplayPrice}\" }}");

Console.WriteLine("\n=== POST /products ===");
var created = ProductMapper.FromCreateRequest(new CreateProductRequest("هدفون", 800_000), "SKU-003");
created.Id = 3;
products.Add(created);
Console.WriteLine($"  Created (بدون InternalSku): {ProductMapper.ToListDto(created)}");
