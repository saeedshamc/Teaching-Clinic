// تمرین ۴–۵

var orders = new[] { new { ProductId = 1, Qty = 2 }, new { ProductId = 2, Qty = 1 }, new { ProductId = 1, Qty = 3 } };
var products = new[] { new { Id = 1, Name = "لپ‌تاپ", Price = 25_000_000 }, new { Id = 2, Name = "ماوس", Price = 450_000 } };

var revenue = orders.Join(products, o => o.ProductId, p => p.Id,
    (o, p) => new { p.Name, Revenue = o.Qty * p.Price });
foreach (var r in revenue) Console.WriteLine($"{r.Name}: {r.Revenue:N0}");

var monthly = new[] { 12_000_000.0, 15_500_000, 11_000_000, 18_000_000 };
var stats = monthly.Aggregate(
    new { Min = double.MaxValue, Max = 0.0, Sum = 0.0 },
    (acc, v) => new { Min = Math.Min(acc.Min, v), Max = Math.Max(acc.Max, v), Sum = acc.Sum + v });
Console.WriteLine($"\nکم: {stats.Min:N0}, زیاد: {stats.Max:N0}, میانگین: {stats.Sum / monthly.Length:N0}");
