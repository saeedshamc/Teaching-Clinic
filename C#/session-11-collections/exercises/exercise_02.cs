// ============================================================
// پاسخ نمونه — تمرین‌های خانه (۵–۸)
// ============================================================

// ۵. Word frequency
string sentence = "سلام دنیا سلام C#";
var freq = new Dictionary<string, int>();
foreach (string w in sentence.Split(' '))
    freq[w] = freq.GetValueOrDefault(w) + 1;
foreach (var kv in freq) Console.WriteLine($"{kv.Key}: {kv.Value}");

// ۶. Shopping cart
var cart = new List<string> { "لباس", "کفش", "کلاه" };
var prices = new Dictionary<string, decimal>
{
    ["لباس"] = 250_000, ["کفش"] = 180_000, ["کلاه"] = 90_000
};
decimal total = 0;
foreach (string item in cart)
    if (prices.TryGetValue(item, out decimal p)) total += p;
Console.WriteLine($"جمع: {total:N0}");

// ۷. Remove dupes → sorted
var nums = new List<int> { 1, 2, 2, 3, 3, 3, 4 };
var result = new List<int>(new HashSet<int>(nums));
result.Sort();
Console.WriteLine(string.Join(", ", result));

// ۸. Task scheduler
var scheduler = new Queue<Action>();
scheduler.Enqueue(() => Console.WriteLine("Step 1"));
scheduler.Enqueue(() => Console.WriteLine("Step 2"));
scheduler.Enqueue(() => Console.WriteLine("Step 3"));
while (scheduler.Count > 0) scheduler.Dequeue().Invoke();
