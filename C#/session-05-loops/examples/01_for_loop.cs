// ============================================================
// جلسه ۵ — حلقه for و nested loop
// ============================================================

// --- مثال ۱: شمارش ۱ تا ۵ ---
Console.WriteLine("=== شمارش ===");
for (int i = 1; i <= 5; i++)
    Console.WriteLine($"  {i}");

Console.WriteLine();

// --- مثال ۲: حلقه نزولی ---
Console.WriteLine("=== شمارش معکوس ===");
for (int i = 5; i >= 1; i--)
    Console.Write($"{i} ");
Console.WriteLine("\n");

// --- مثال ۳: جدول ضرب ---
Console.WriteLine("=== جدول ضرب 1-5 ===");
for (int i = 1; i <= 5; i++)
{
    for (int j = 1; j <= 5; j++)
        Console.Write($"{i * j,4}");
    Console.WriteLine();
}

Console.WriteLine();

// --- مثال ۴: جمع ۱ تا ۱۰۰ ---
int sum = 0;
for (int i = 1; i <= 100; i++)
    sum += i;
Console.WriteLine($"جمع 1..100 = {sum}"); // 5050

// --- مثال ۵: فاکتوریل ---
int n = 5;
int factorial = 1;
for (int i = 1; i <= n; i++)
    factorial *= i;
Console.WriteLine($"{n}! = {factorial}");
