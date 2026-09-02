// ============================================================
// جلسه ۲۲ — فیلتر و Strategy Pattern
// فایل: 04_filter_strategy.cs
// ============================================================

class Product
{
    public string Name { get; set; } = "";
    public double Price { get; set; }
    public string Category { get; set; } = "";
}

// Generic Filter با Predicate
static List<T> Filter<T>(IEnumerable<T> items, Predicate<T> predicate)
{
    var result = new List<T>();
    foreach (var item in items)
        if (predicate(item)) result.Add(item);
    return result;
}

// Sort با Func comparer
static void SortBy<T>(List<T> items, Func<T, T, int> compare)
{
    items.Sort((a, b) => compare(a, b));
}

// Validator با Func rules
class Validator
{
    private readonly List<Func<string, string?>> _rules = new();

    public void AddRule(Func<string, string?> rule) => _rules.Add(rule);
    public (bool IsValid, string? Error) Validate(string input)
    {
        foreach (var rule in _rules)
        {
            var error = rule(input);
            if (error != null) return (false, error);
        }
        return (true, null);
    }
}

var products = new List<Product>
{
    new() { Name = "لپ‌تاپ", Price = 25_000_000, Category = "الکترونیک" },
    new() { Name = "ماوس", Price = 450_000, Category = "الکترونیک" },
    new() { Name = "کتاب", Price = 150_000, Category = "فرهنگی" },
    new() { Name = "مانیتور", Price = 8_500_000, Category = "الکترونیک" }
};

// فیلتر با Predicate
var expensive = Filter(products, p => p.Price > 1_000_000);
Console.WriteLine("=== گران (بالای 1M) ===");
foreach (var p in expensive) Console.WriteLine($"  {p.Name}: {p.Price:N0}");

var electronics = Filter(products, p => p.Category == "الکترونیک");
Console.WriteLine($"\nالکترونیک: {electronics.Count} محصول");

// مرتب‌سازی با Func
SortBy(products, (a, b) => a.Price.CompareTo(b.Price));
Console.WriteLine("\n=== مرتب بر اساس قیمت ===");
foreach (var p in products) Console.WriteLine($"  {p.Name}: {p.Price:N0}");

// Validator
var passwordValidator = new Validator();
passwordValidator.AddRule(s => s.Length < 8 ? "حداقل ۸ کاراکتر" : null);
passwordValidator.AddRule(s => !s.Any(char.IsDigit) ? "حداقل یک عدد" : null);
passwordValidator.AddRule(s => !s.Any(char.IsUpper) ? "حداقل یک حرف بزرگ" : null);

foreach (var pwd in new[] { "abc", "abcdefgh", "Abcdef1" })
{
    var (valid, error) = passwordValidator.Validate(pwd);
    Console.WriteLine($"'{pwd}': {(valid ? "✅" : $"❌ {error}")}");
}
