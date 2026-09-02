// تمرین ۱–۳

static int Calculate(int a, int b, Func<int, int, int> op) => op(a, b);

static List<T> Filter<T>(IEnumerable<T> items, Predicate<T> pred)
{
    var result = new List<T>();
    foreach (var item in items) if (pred(item)) result.Add(item);
    return result;
}

Action<string> logger = msg => Console.WriteLine($"[LOG] {msg}");
logger += msg => Console.WriteLine($"  ⏰ {DateTime.Now:HH:mm:ss} — {msg}");

Console.WriteLine($"10+5 = {Calculate(10, 5, (a, b) => a + b)}");
Console.WriteLine($"10*5 = {Calculate(10, 5, (a, b) => a * b)}");

var nums = new List<int> { 1, 2, 3, 4, 5, 6, 7, 8 };
Console.WriteLine($"زوج: {string.Join(", ", Filter(nums, n => n % 2 == 0))}");

logger("سیستم آماده است");
