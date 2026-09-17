// تمرین ۴–۵

class Sale { public string Product { get; set; } = ""; public double Amount { get; set; } }
class Student { public string Name { get; set; } = ""; public double Grade { get; set; } }

var sales = new List<Sale>
{
    new() { Product = "لپ‌تاپ", Amount = 25_000_000 },
    new() { Product = "ماوس", Amount = 450_000 },
    new() { Product = "لپ‌تاپ", Amount = 24_000_000 },
    new() { Product = "کیبورد", Amount = 1_200_000 }
};

var report = sales.GroupBy(s => s.Product).Select(g => new { Product = g.Key, Total = g.Sum(s => s.Amount) });
foreach (var r in report.OrderByDescending(r => r.Total))
    Console.WriteLine($"{r.Product}: {r.Total:N0}");

var students = new List<Student>
{
    new() { Name = "علی", Grade = 18 }, new() { Name = "مریم", Grade = 19 },
    new() { Name = "رضا", Grade = 15 }, new() { Name = "سارا", Grade = 17 }
};

var top3 = students.OrderByDescending(s => s.Grade).Take(3);
Console.WriteLine("\n=== Top 3 ===");
foreach (var s in top3) Console.WriteLine($"  {s.Name}: {s.Grade}");
