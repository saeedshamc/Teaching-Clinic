// ============================================================
// جلسه ۲۴ — OrderBy و GroupBy
// فایل: 02_orderby_groupby.cs
// ============================================================

class Student
{
    public string Name { get; set; } = "";
    public string City { get; set; } = "";
    public double Grade { get; set; }
    public int Age { get; set; }
}

var students = new List<Student>
{
    new() { Name = "علی", City = "تهران", Grade = 18.5, Age = 20 },
    new() { Name = "مریم", City = "اصفهان", Grade = 19.0, Age = 22 },
    new() { Name = "رضا", City = "تهران", Grade = 15.5, Age = 21 },
    new() { Name = "سارا", City = "شیراز", Grade = 17.0, Age = 20 },
    new() { Name = "امیر", City = "تهران", Grade = 19.0, Age = 19 },
    new() { Name = "لیلا", City = "اصفهان", Grade = 16.0, Age = 23 }
};

// OrderBy
Console.WriteLine("=== مرتب بر اساس نمره (نزولی) ===");
foreach (var s in students.OrderByDescending(s => s.Grade))
    Console.WriteLine($"  {s.Name}: {s.Grade}");

// ThenBy — مرتب‌سازی چندسطحی
Console.WriteLine("\n=== نمره نزولی، سن صعودی ===");
foreach (var s in students.OrderByDescending(s => s.Grade).ThenBy(s => s.Age))
    Console.WriteLine($"  {s.Name}: نمره={s.Grade}, سن={s.Age}");

// GroupBy
Console.WriteLine("\n=== گروه‌بندی بر اساس شهر ===");
var byCity = students.GroupBy(s => s.City);
foreach (var group in byCity)
{
    Console.WriteLine($"  [{group.Key}] — {group.Count()} نفر");
    foreach (var s in group)
        Console.WriteLine($"    {s.Name}: {s.Grade}");
}

// GroupBy با aggregation
Console.WriteLine("\n=== میانگین نمره هر شهر ===");
var cityAvg = students
    .GroupBy(s => s.City)
    .Select(g => new { City = g.Key, Avg = g.Average(s => s.Grade), Count = g.Count() });

foreach (var c in cityAvg.OrderByDescending(c => c.Avg))
    Console.WriteLine($"  {c.City}: میانگین={c.Avg:F1}, تعداد={c.Count}");

// GroupBy با شرط
Console.WriteLine("\n=== قبول / مردود ===");
var passFail = students.GroupBy(s => s.Grade >= 17 ? "عالی" : s.Grade >= 10 ? "قبول" : "مردود");
foreach (var g in passFail)
    Console.WriteLine($"  {g.Key}: {string.Join(", ", g.Select(s => s.Name))}");
