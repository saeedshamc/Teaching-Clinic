// ============================================================
// جلسه ۳۹ — ASP.NET MVC
// فایل: 01_mvc_flow.cs — شبیه‌سازی جریان MVC
// ============================================================

record Product(int Id, string Name, decimal Price);

// Model: داده
var products = new List<Product>
{
    new(1, "لپ‌تاپ", 25_000_000),
    new(2, "ماوس", 450_000),
    new(3, "کیبورد", 1_200_000)
};

// Controller: دریافت درخواست و انتخاب Action
string route = "/Product/Index";
Console.WriteLine($"درخواست: {route}");

// Action: Index
if (route == "/Product/Index")
{
    // View: رندر HTML (اینجا Console)
    Console.WriteLine("\n=== View: لیست محصولات ===");
    foreach (var p in products)
        Console.WriteLine($"  [{p.Id}] {p.Name} — {p.Price:N0} تومان");
}

// Action: Details
int detailId = 2;
var selected = products.FirstOrDefault(p => p.Id == detailId);
Console.WriteLine($"\n=== View: جزئیات محصول {detailId} ===");
Console.WriteLine(selected is not null
    ? $"  {selected.Name} — {selected.Price:N0} تومان"
    : "  404 Not Found");
