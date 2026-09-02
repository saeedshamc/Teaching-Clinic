// ============================================================
// جلسه 50 — پروژه نهایی
// فایل: 01_domain_model.cs
// ============================================================
record Product(int Id, string Name, decimal Price);
var products = new List<Product>
{
    new(1, "Mouse", 25),
    new(2, "Keyboard", 45)
};
foreach (var p in products) Console.WriteLine(p);
