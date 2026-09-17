// ============================================================
// جلسه ۱۳ — آرایه‌ای از اشیاء
// فایل: 03_object_array.cs
// ============================================================

class Product
{
    public string Name = "";
    public double Price;
    public int Stock;
}

Product[] shop = new Product[]
{
    new Product { Name = "لپ‌تاپ", Price = 25_000_000, Stock = 5 },
    new Product { Name = "ماوس", Price = 450_000, Stock = 20 },
    new Product { Name = "کیبورد", Price = 1_100_000, Stock = 12 },
    new Product { Name = "مانیتور", Price = 8_500_000, Stock = 3 }
};

Console.WriteLine("=== فهرست محصولات ===");
foreach (var item in shop)
    Console.WriteLine($"{item.Name,-10} | {item.Price,12:N0} | موجودی: {item.Stock}");

// جستجو
string search = "ماوس";
var found = shop.FirstOrDefault(p => p.Name == search);
if (found != null)
    Console.WriteLine($"\nیافت شد: {found.Name} — {found.Price:N0} تومان");
else
    Console.WriteLine($"\n«{search}» پیدا نشد.");

// جمع ارزش کل موجودی انبار
double inventoryValue = 0;
foreach (var p in shop)
    inventoryValue += p.Price * p.Stock;
Console.WriteLine($"\nارزش کل انبار: {inventoryValue:N0} تومان");
