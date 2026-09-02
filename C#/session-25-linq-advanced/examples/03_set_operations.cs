// ============================================================
// جلسه ۲۵ — Set Operations
// فایل: 03_set_operations.cs
// ============================================================

var courseA = new[] { "علی", "مریم", "رضا", "سارا", "امیر" };
var courseB = new[] { "مریم", "سارا", "لیلا", "کاوه", "امیر" };

Console.WriteLine("=== عملیات مجموعه ===");
Console.WriteLine($"A: {string.Join(", ", courseA)}");
Console.WriteLine($"B: {string.Join(", ", courseB)}");

// Union — اجتماع (بدون تکرار)
var union = courseA.Union(courseB);
Console.WriteLine($"\nUnion (همه): {string.Join(", ", union)}");

// Intersect — اشتراک
var intersect = courseA.Intersect(courseB);
Console.WriteLine($"Intersect (مشترک): {string.Join(", ", intersect)}");

// Except — تفاضل A - B
var onlyA = courseA.Except(courseB);
Console.WriteLine($"Except A-B (فقط A): {string.Join(", ", onlyA)}");

var onlyB = courseB.Except(courseA);
Console.WriteLine($"Except B-A (فقط B): {string.Join(", ", onlyB)}");

// Distinct
var withDupes = new[] { 1, 2, 2, 3, 3, 3, 4, 5, 5 };
Console.WriteLine($"\nDistinct: {string.Join(", ", withDupes.Distinct())}");

// DistinctBy (.NET 6+)
class Student
{
    public string Name { get; set; } = "";
    public string City { get; set; } = "";
}

var students = new List<Student>
{
    new() { Name = "علی", City = "تهران" },
    new() { Name = "مریم", City = "تهران" },
    new() { Name = "رضا", City = "اصفهان" },
    new() { Name = "سارا", City = "تهران" }
};

var uniqueCities = students.Select(s => s.City).Distinct();
Console.WriteLine($"\nشهرهای یکتا: {string.Join(", ", uniqueCities)}");

var onePerCity = students.DistinctBy(s => s.City);
Console.WriteLine("یک نفر از هر شهر:");
foreach (var s in onePerCity)
    Console.WriteLine($"  {s.Name} — {s.City}");

// Concat — الحاق (با تکرار)
var concat = courseA.Concat(new[] { "علی" });
Console.WriteLine($"\nConcat (با تکرار): {concat.Count()} عنصر");
