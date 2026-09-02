// ============================================================
// جلسه ۴۷ — Benchmark Concept
// ============================================================

string StringConcat(List<string> items)
{
    string result = "";
    foreach (var s in items) result += s;
    return result;
}

string StringJoin(List<string> items) => string.Join("", items);

string StringBuilderJoin(List<string> items)
{
    var sb = new System.Text.StringBuilder();
    foreach (var s in items) sb.Append(s);
    return sb.ToString();
}

void Benchmark(string name, Func<string> action, int iterations = 1000)
{
    // warmup
    action();
    var sw = System.Diagnostics.Stopwatch.StartNew();
    for (int i = 0; i < iterations; i++) action();
    sw.Stop();
    Console.WriteLine($"  {name,-20} {sw.ElapsedMilliseconds,5}ms ({iterations} iterations)");
}

var items = Enumerable.Range(1, 100).Select(i => $"item{i}").ToList();

Console.WriteLine("=== Benchmark: String Building (100 items × 1000 iter) ===");
Benchmark("Concat (+)", () => StringConcat(items));
Benchmark("string.Join", () => StringJoin(items));
Benchmark("StringBuilder", () => StringBuilderJoin(items));

Console.WriteLine("\n=== Performance Rule ===");
Console.WriteLine("  1. Measure (don't guess)");
Console.WriteLine("  2. Fix the biggest bottleneck");
Console.WriteLine("  3. Measure again to verify");
