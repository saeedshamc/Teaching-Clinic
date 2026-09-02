// تمرین ۱–۳

class Product { public string Name { get; set; } = ""; public double Price { get; set; } }
class Student { public string Name { get; set; } = ""; public string City { get; set; } = ""; public double Grade { get; set; } }

var products = new List<Product>
{
    new() { Name = "لپ‌تاپ", Price = 25_000_000 },
    new() { Name = "ماوس", Price = 450_000 },
    new() { Name = "مانیتور", Price = 8_500_000 }
};

var expensive = products.Where(p => p.Price > 1_000_000).Select(p => new { p.Name, p.Price });
foreach (var p in expensive) Console.WriteLine($"{p.Name}: {p.Price:N0}");

var students = new List<Student>
{
    new() { Name = "علی", City = "تهران", Grade = 18 },
    new() { Name = "مریم", City = "اصفهان", Grade = 19 },
    new() { Name = "رضا", City = "تهران", Grade = 15 }
};

Console.WriteLine($"میانگین: {students.Average(s => s.Grade):F1}");
Console.WriteLine($"بالاترین: {students.Max(s => s.Grade)}, پایین‌ترین: {students.Min(s => s.Grade)}");

var byCity = students.GroupBy(s => s.City).Select(g => new { City = g.Key, Count = g.Count(), Avg = g.Average(s => s.Grade) });
foreach (var c in byCity) Console.WriteLine($"{c.City}: {c.Count} نفر, میانگین={c.Avg:F1}");
