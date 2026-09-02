// ============================================================
// جلسه ۷ — آرایه یک‌بعدی
// ============================================================

// --- تعریف و مقداردهی ---
int[] scores = { 85, 92, 78, 96, 88 };
string[] cities = new string[3] { "تهران", "اصفهان", "شیراز" };

Console.WriteLine($"تعداد: {scores.Length}");
Console.WriteLine($"اولین: {scores[0]}, آخرین: {scores[scores.Length - 1]}");

Console.WriteLine();

// --- تغییر عنصر ---
scores[2] = 80;
Console.WriteLine("بعد از تغییر:");
foreach (int s in scores)
    Console.Write($"{s} ");
Console.WriteLine("\n");

// --- for با index ---
Console.WriteLine("=== با index ===");
for (int i = 0; i < scores.Length; i++)
    Console.WriteLine($"  [{i}] = {scores[i]}");

Console.WriteLine();

// --- محاسبات ---
int sum = 0;
foreach (int s in scores) sum += s;
double avg = (double)sum / scores.Length;
Console.WriteLine($"جمع={sum}, میانگین={avg:F1}");

// --- Array methods ---
int[] nums = { 3, 1, 4, 1, 5, 9 };
Array.Sort(nums);
Console.Write("مرتب: ");
Array.ForEach(nums, n => Console.Write($"{n} "));

int idx = Array.IndexOf(nums, 5);
Console.WriteLine($"\nIndexOf(5) = {idx}");
