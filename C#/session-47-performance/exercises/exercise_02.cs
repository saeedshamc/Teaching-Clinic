// ============================================================
// تمرین ۲ جلسه ۴۷ — Benchmark List vs HashSet (پاسخ نمونه)
// ============================================================

var items = Enumerable.Range(1, 10_000).ToList();
var set = items.ToHashSet();
int target = 9_999;

var sw1 = System.Diagnostics.Stopwatch.StartNew();
for (int i = 0; i < 1000; i++) _ = items.Contains(target);
sw1.Stop();

var sw2 = System.Diagnostics.Stopwatch.StartNew();
for (int i = 0; i < 1000; i++) _ = set.Contains(target);
sw2.Stop();

Console.WriteLine($"List.Contains:    {sw1.ElapsedMilliseconds}ms");
Console.WriteLine($"HashSet.Contains: {sw2.ElapsedMilliseconds}ms");
