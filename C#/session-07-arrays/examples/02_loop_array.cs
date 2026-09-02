// ============================================================
// جلسه 7 — آرایه‌ها
// فایل: 02_loop_array.cs
// ============================================================
int[] scores = { 90, 75, 88 };
int sum = 0;
foreach (var s in scores) sum += s;
Console.WriteLine($"میانگین: {sum / (double)scores.Length}");
