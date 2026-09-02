// ============================================================
// پاسخ نمونه — تمرین‌های کلاسی (۱–۴)
// ============================================================

bool IsEven(int n) => n % 2 == 0;
int Max(int a, int b) => a > b ? a : b;

void PrintMultiplicationTable(int n)
{
    for (int i = 1; i <= 10; i++)
        Console.WriteLine($"{n} × {i} = {n * i}");
}

int Sum(params int[] numbers)
{
    int total = 0;
    foreach (int n in numbers) total += n;
    return total;
}

Console.WriteLine($"IsEven(4)={IsEven(4)}, IsEven(7)={IsEven(7)}");
Console.WriteLine($"Max(12,8)={Max(12, 8)}");
PrintMultiplicationTable(7);
Console.WriteLine($"Sum(1,2,3,4,5)={Sum(1, 2, 3, 4, 5)}");
