// ============================================================
// جلسه ۲۴ — Where و Select
// فایل: 01_where_select.cs
// ============================================================

class Product
{
    public string Name { get; set; } = "";
    public double Price { get; set; }
    public string Category { get; set; } = "";
    public int Stock { get; set; }
}

var products = new List<Product>
{
    new() { Name = "لپ‌تاپ", Price = 25_000_000, Category = "الکترونیک", Stock = 5 },
    new() { Name = "ماوس", Price = 450_000, Category = "الکترونیک", Stock = 20 },
    new() { Name = "کتاب کلین کد", Price = 350_000, Category = "کتاب", Stock = 15 },
    new() { Name = "مانیتور", Price = 8_500_000, Category = "الکترونیک", Stock = 3 },
    new() { Name = "کیبورد", Price = 1_200_000, Category = "الکترونیک", Stock = 10 }
};

// Where — فیلتر
var expensive = products.Where(p => p.Price > 1_000_000);
Console.WriteLine("=== گران (بالای 1M) ===");
foreach (var p in expensive)
    Console.WriteLine($"  {p.Name}: {p.Price:N0}");

var lowStock = products.Where(p => p.Stock < 5);
Console.WriteLine($"\nموجودی کم: {lowStock.Count()} محصول");

var electronics = products.Where(p => p.Category == "الکترونیک" && p.Price < 2_000_000);
Console.WriteLine($"الکترونیک ارزان: {electronics.Count()}");

// Select — تبدیل (Projection)
var names = products.Select(p => p.Name);
Console.WriteLine($"\nنام‌ها: {string.Join(", ", names)}");

var summaries = products.Select(p => new
{
    p.Name,
    PriceTag = $"{p.Price:N0} تومان",
    Status = p.Stock > 0 ? "موجود" : "ناموجود"
});

Console.WriteLine("\n=== خلاصه ===");
foreach (var s in summaries)
    Console.WriteLine($"  {s.Name} — {s.PriceTag} — {s.Status}");

// ترکیب Where + Select
var affordableElectronics = products
    .Where(p => p.Category == "الکترونیک")
    .Where(p => p.Price < 2_000_000)
    .Select(p => p.Name);

Console.WriteLine($"\nالکترونیک زیر 2M: {string.Join(", ", affordableElectronics)}");
