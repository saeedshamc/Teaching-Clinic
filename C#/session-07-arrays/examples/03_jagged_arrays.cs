// ============================================================
// جلسه ۷ — Jagged Arrays
// ============================================================

// --- jagged: هر سطر طول متفاوت ---
int[][] jagged = {
    new[] { 1, 2 },
    new[] { 3, 4, 5, 6 },
    new[] { 7 },
    new[] { 8, 9, 10 }
};

Console.WriteLine($"تعداد سطرها: {jagged.Length}");
Console.WriteLine($"سطر 1 طول: {jagged[1].Length}");
Console.WriteLine($"jagged[1][2] = {jagged[1][2]}"); // 5

Console.WriteLine();

// --- چاپ jagged ---
Console.WriteLine("=== Jagged Array ===");
for (int row = 0; row < jagged.Length; row++)
{
    Console.Write($"  سطر {row}: ");
    for (int col = 0; col < jagged[row].Length; col++)
        Console.Write($"{jagged[row][col]} ");
    Console.WriteLine();
}

Console.WriteLine();

// --- مثال: کلاس‌ها با تعداد دانش‌آموز متفاوت ---
string[][] classes = {
    new[] { "علی", "مریم" },
    new[] { "رضا", "سارا", "امیر", "نازنین" },
    new[] { "حسین" }
};

Console.WriteLine("=== کلاس‌ها ===");
for (int c = 0; c < classes.Length; c++)
{
    Console.Write($"  کلاس {c + 1} ({classes[c].Length} نفر): ");
    foreach (string name in classes[c])
        Console.Write($"{name} ");
    Console.WriteLine();
}

Console.WriteLine();

// --- تفاوت با 2D: jagged انعطاف‌پذیرتر ---
int[][] triangle = new int[4][];
for (int i = 0; i < triangle.Length; i++)
    triangle[i] = new int[i + 1];

int val = 1;
for (int r = 0; r < triangle.Length; r++)
    for (int c = 0; c < triangle[r].Length; c++)
        triangle[r][c] = val++;

Console.WriteLine("=== مثلث اعداد ===");
foreach (int[] row in triangle)
{
    foreach (int n in row)
        Console.Write($"{n,3}");
    Console.WriteLine();
}
