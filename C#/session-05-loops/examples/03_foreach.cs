// ============================================================
// جلسه ۵ — foreach
// ============================================================

// --- مثال ۱: foreach روی آرایه string ---
string[] names = { "علی", "مریم", "رضا", "سارا" };
Console.WriteLine("=== سلام ===");
foreach (string name in names)
    Console.WriteLine($"  سلام {name}!");

Console.WriteLine();

// --- مثال ۲: foreach روی آرایه int ---
int[] scores = { 85, 92, 78, 96, 88 };
int total = 0;
foreach (int score in scores)
    total += score;
double average = (double)total / scores.Length;
Console.WriteLine($"میانگین: {average:F1}");

Console.WriteLine();

// --- مثال ۳: foreach با char[] ---
string text = "سلام";
Console.WriteLine("=== کاراکترها ===");
foreach (char c in text)
    Console.Write($"[{c}] ");

Console.WriteLine("\n");

// --- مثال ۴: foreach vs for — cuándo usar cuál ---
Console.WriteLine("=== با index (for) ===");
for (int i = 0; i < names.Length; i++)
    Console.WriteLine($"  {i}: {names[i]}");

Console.WriteLine("\n=== بدون index (foreach) ===");
foreach (string name in names)
    Console.WriteLine($"  → {name}");

// نکته: foreach برای read-only؛ برای تغییر عنصر از for استفاده کنید
