// ============================================================
// جلسه ۷ — آرایه دو‌بعدی (ماتریس)
// ============================================================

// --- تعریف ماتریس 3×3 ---
int[,] matrix = {
    { 1,  2,  3 },
    { 4,  5,  6 },
    { 7,  8,  9 }
};

Console.WriteLine($"ابعاد: {matrix.GetLength(0)}×{matrix.GetLength(1)}");
Console.WriteLine($"matrix[1,2] = {matrix[1, 2]}"); // 6

Console.WriteLine();

// --- چاپ ماتریس ---
Console.WriteLine("=== ماتریس ===");
for (int row = 0; row < matrix.GetLength(0); row++)
{
    for (int col = 0; col < matrix.GetLength(1); col++)
        Console.Write($"{matrix[row, col],4}");
    Console.WriteLine();
}

Console.WriteLine();

// --- جمع هر سطر ---
Console.WriteLine("=== جمع سطرها ===");
for (int row = 0; row < matrix.GetLength(0); row++)
{
    int rowSum = 0;
    for (int col = 0; col < matrix.GetLength(1); col++)
        rowSum += matrix[row, col];
    Console.WriteLine($"  سطر {row}: {rowSum}");
}

Console.WriteLine();

// --- ماتریس مثال: نمرات ۳ دانشجو × ۴ درس ---
double[,] grades = {
    { 18.5, 16.0, 19.0, 17.5 },
    { 15.0, 14.5, 16.5, 18.0 },
    { 20.0, 19.5, 18.0, 19.25 }
};

string[] students = { "علی", "مریم", "رضا" };
for (int s = 0; s < grades.GetLength(0); s++)
{
    double sum = 0;
    for (int l = 0; l < grades.GetLength(1); l++)
        sum += grades[s, l];
    Console.WriteLine($"  {students[s]}: میانگین {sum / grades.GetLength(1):F2}");
}
