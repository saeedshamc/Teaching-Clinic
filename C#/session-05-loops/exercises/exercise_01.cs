// ============================================================
// پاسخ نمونه — تمرین‌های کلاسی (۱–۴)
// ============================================================

// ۱. اعداد ۱ تا ۲۰
for (int i = 1; i <= 20; i++)
    Console.Write($"{i} ");
Console.WriteLine("\n");

// ۲. جدول ضرب
Console.Write("N: ");
int.TryParse(Console.ReadLine(), out int n);
for (int i = 1; i <= 10; i++)
    Console.WriteLine($"{n} × {i} = {n * i}");

// ۳. جمع زوج‌ها
int evenSum = 0;
for (int i = 1; i <= 100; i++)
{
    if (i % 2 != 0) continue;
    evenSum += i;
}
Console.WriteLine($"جمع زوج‌ها: {evenSum}"); // 2550

// ۴. min/max
int[] arr = { 45, 12, 78, 34, 91, 23 };
int min = arr[0], max = arr[0];
foreach (int val in arr)
{
    if (val < min) min = val;
    if (val > max) max = val;
}
Console.WriteLine($"min={min}, max={max}");
