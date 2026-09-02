// ============================================================
// جلسه ۳۰ — پروژه پیشرفته: گزارش‌های LINQ
// ============================================================

var students = new List<Student>
{
    new(1, "علی", "SE", 18.5),
    new(2, "مریم", "AI", 19.0),
    new(3, "رضا", "SE", 15.0),
    new(4, "سارا", "IT", 17.5),
    new(5, "نیما", "AI", 16.8)
};

Console.WriteLine("=== ممتاز ===");
foreach (var s in students.Where(s => s.Gpa >= 17).OrderByDescending(s => s.Gpa))
    Console.WriteLine($"  {s.FullName}: {s.Gpa}");

Console.WriteLine("\n=== گروه رشته ===");
foreach (var group in students.GroupBy(s => s.Major))
{
    double avg = group.Average(s => s.Gpa);
    Console.WriteLine($"  {group.Key}: {group.Count()} نفر — میانگین {avg:F1}");
}

Console.WriteLine("\n=== Top 2 ===");
students.OrderByDescending(s => s.Gpa).Take(2)
    .ToList().ForEach(s => Console.WriteLine($"  {s.FullName}: {s.Gpa}"));

string term = "رض";
var found = students.Where(s => s.FullName.Contains(term, StringComparison.OrdinalIgnoreCase));
Console.WriteLine($"\nجستجوی '{term}': {string.Join(", ", found.Select(s => s.FullName))}");

public record Student(int Id, string FullName, string Major, double Gpa);
