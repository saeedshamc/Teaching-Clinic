// ============================================================
// جلسه ۲۴ — Aggregation و جستجو
// فایل: 03_aggregation.cs
// ============================================================

var numbers = Enumerable.Range(1, 20);

Console.WriteLine("=== Aggregation ===");
Console.WriteLine($"Sum: {numbers.Sum()}");
Console.WriteLine($"Average: {numbers.Average():F2}");
Console.WriteLine($"Min: {numbers.Min()}, Max: {numbers.Max()}");
Console.WriteLine($"Count: {numbers.Count()}");

var scores = new[] { 85, 92, 78, 95, 88, 72, 90, 65, 98, 83 };
Console.WriteLine($"\nنمرات — میانگین: {scores.Average():F1}, بالاترین: {scores.Max()}");

class Sale
{
    public string Product { get; set; } = "";
    public double Amount { get; set; }
    public string Region { get; set; } = "";
}

var sales = new List<Sale>
{
    new() { Product = "لپ‌تاپ", Amount = 25_000_000, Region = "تهران" },
    new() { Product = "ماوس", Amount = 450_000, Region = "اصفهان" },
    new() { Product = "لپ‌تاپ", Amount = 24_000_000, Region = "تهران" },
    new() { Product = "کیبورد", Amount = 1_200_000, Region = "شیراز" },
    new() { Product = "مانیتور", Amount = 8_500_000, Region = "تهران" }
};

Console.WriteLine($"\nکل فروش: {sales.Sum(s => s.Amount):N0}");
Console.WriteLine($"میانگین: {sales.Average(s => s.Amount):N0}");
Console.WriteLine($"بیشترین: {sales.Max(s => s.Amount):N0}");

// First, Any, All
Console.WriteLine("\n=== جستجو ===");
var firstBig = sales.First(s => s.Amount > 10_000_000);
Console.WriteLine($"اولین بزرگ: {firstBig.Product} — {firstBig.Amount:N0}");

var hasTehran = sales.Any(s => s.Region == "تهران");
Console.WriteLine($"فروش تهران؟ {hasTehran}");

var allPositive = sales.All(s => s.Amount > 0);
Console.WriteLine($"همه مثبت؟ {allPositive}");

var notFound = sales.FirstOrDefault(s => s.Amount > 100_000_000);
Console.WriteLine($"بالای 100M: {(notFound == null ? "ندارد" : notFound.Product)}");

// Count با شرط
int tehranCount = sales.Count(s => s.Region == "تهران");
Console.WriteLine($"فروش تهران: {tehranCount} مورد");
