// ============================================================
// جلسه ۲۵ — SelectMany و Aggregate
// فایل: 04_selectmany_aggregate.cs
// ============================================================

class Department
{
    public string Name { get; set; } = "";
    public List<string> Employees { get; set; } = new();
}

var departments = new List<Department>
{
    new() { Name = "فناوری", Employees = new() { "علی", "مریم", "رضا" } },
    new() { Name = "فروش", Employees = new() { "سارا", "امیر" } },
    new() { Name = "HR", Employees = new() { "لیلا" } }
};

// SelectMany — flatten
var allEmployees = departments.SelectMany(d => d.Employees);
Console.WriteLine($"همه کارمندان: {string.Join(", ", allEmployees)}");

var withDept = departments.SelectMany(
    d => d.Employees,
    (dept, emp) => new { dept.Name, Employee = emp });

Console.WriteLine("\n=== کارمندان با دپارتمان ===");
foreach (var item in withDept)
    Console.WriteLine($"  {item.Employee} — {item.Name}");

// Zip — جفت‌سازی
var names = new[] { "علی", "مریم", "رضا" };
var scores = new[] { 85, 92, 78 };
var paired = names.Zip(scores, (name, score) => $"{name}: {score}");
Console.WriteLine($"\nZip: {string.Join(" | ", paired)}");

// Aggregate
var words = new[] { "C#", "یک", "زبان", "قدرتمند" };
var sentence = words.Aggregate((a, b) => a + " " + b);
Console.WriteLine($"\nAggregate: {sentence}");

var factorial = Enumerable.Range(1, 6).Aggregate(1, (acc, n) => acc * n);
Console.WriteLine($"6! = {factorial}");

// Aggregate با نتیجه سفارشی
var monthlySales = new[] { 12_000_000, 15_500_000, 11_000_000, 18_000_000, 14_000_000 };
var stats = monthlySales.Aggregate(
    new { Min = double.MaxValue, Max = double.MinValue, Sum = 0.0, Count = 0 },
    (acc, sale) => new
    {
        Min = Math.Min(acc.Min, sale),
        Max = Math.Max(acc.Max, sale),
        Sum = acc.Sum + sale,
        Count = acc.Count + 1
    },
    acc => new
    {
        acc.Min,
        acc.Max,
        Average = acc.Sum / acc.Count,
        acc.Count
    });

Console.WriteLine($"\nفروش ماهانه — کم: {stats.Min:N0}, زیاد: {stats.Max:N0}, میانگین: {stats.Average:N0}");

// Deferred execution demo
var source = new List<int> { 1, 2, 3, 4, 5 };
var query = source.Where(n => n > 2);
Console.WriteLine($"\nقبل از تغییر: {string.Join(", ", query)}");
source.Add(6);
Console.WriteLine($"بعد از Add(6): {string.Join(", ", query)}");  // 6 هم هست!

var materialized = source.Where(n => n > 2).ToList();
source.Add(7);
Console.WriteLine($"بعد از ToList و Add(7): {string.Join(", ", materialized)}");  // 7 نیست
