// ============================================================
// پاسخ نمونه — تمرین‌های خانه (۵–۸)
// ============================================================

int IndexOf(int[] arr, int target)
{
    for (int i = 0; i < arr.Length; i++)
        if (arr[i] == target) return i;
    return -1;
}

int[] data = { 10, 25, 30, 25, 40 };
Console.WriteLine($"IndexOf(25)={IndexOf(data, 25)}, IndexOf(99)={IndexOf(data, 99)}");

// ۶. جمع سطر/ستون
int[,] matrix = { { 1,2,3 }, { 4,5,6 }, { 7,8,9 } };
for (int r = 0; r < 3; r++)
{
    int rowSum = 0;
    for (int c = 0; c < 3; c++) rowSum += matrix[r, c];
    Console.WriteLine($"سطر {r}: {rowSum}");
}
for (int c = 0; c < 3; c++)
{
    int colSum = 0;
    for (int r = 0; r < 3; r++) colSum += matrix[r, c];
    Console.WriteLine($"ستون {c}: {colSum}");
}

// ۷. Fibonacci array
int[] fib = new int[15];
fib[0] = 0; fib[1] = 1;
for (int i = 2; i < 15; i++)
    fib[i] = fib[i - 1] + fib[i - 2];
Console.WriteLine("Fib: " + string.Join(", ", fib));

// ۸. Range slice
int[] nums = { 10, 20, 30, 40, 50, 60 };
int[] middle = nums[2..5];
Console.WriteLine("وسط: " + string.Join(", ", middle)); // 30, 40, 50
