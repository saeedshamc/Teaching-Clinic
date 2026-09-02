// ============================================================
// پاسخ نمونه — تمرین‌های کلاسی (۱–۴)
// ============================================================

int[] scores = { 85, 92, 78, 96, 88 };

// ۱. میانگین
int sum = 0;
foreach (int s in scores) sum += s;
Console.WriteLine($"میانگین: {(double)sum / scores.Length:F1}");

// ۲. min/max
int min = scores[0], max = scores[0];
foreach (int s in scores)
{
    if (s < min) min = s;
    if (s > max) max = s;
}
Console.WriteLine($"min={min}, max={max}");

// ۳. ماتریس 3×3
int[,] m = { { 1,2,3 }, { 4,5,6 }, { 7,8,9 } };
for (int r = 0; r < 3; r++)
{
    for (int c = 0; c < 3; c++)
        Console.Write($"{m[r,c]} ");
    Console.WriteLine();
}

// ۴. معکوس
int[] orig = { 1, 2, 3, 4, 5 };
int[] rev = new int[orig.Length];
for (int i = 0; i < orig.Length; i++)
    rev[i] = orig[orig.Length - 1 - i];
Console.WriteLine("معکوس: " + string.Join(", ", rev));
