// ============================================================
// پاسخ نمونه — تمرین‌های خانه (۵–۸)
// ============================================================

double Add(double a, double b) => a + b;
double Subtract(double a, double b) => a - b;
double Multiply(double a, double b) => a * b;

bool TryDivide(double a, double b, out double result)
{
    result = 0;
    if (b == 0) return false;
    result = a / b;
    return true;
}

double Calculate(double x, double y, char op) => op switch
{
    '+' => Add(x, y),
    '-' => Subtract(x, y),
    '*' => Multiply(x, y),
    '/' => TryDivide(x, y, out double r) ? r : double.NaN,
    _   => double.NaN
};

void Swap(ref int a, ref int b) => (a, b) = (b, a);

void MinMax(int[] arr, out int min, out int max)
{
    min = max = arr[0];
    foreach (int v in arr)
    {
        if (v < min) min = v;
        if (v > max) max = v;
    }
}

string FormatDate(int year, int month, int day) =>
    $"{year:D4}/{month:D2}/{day:D2}";

string FormatDate(DateTime dt) =>
    FormatDate(dt.Year, dt.Month, dt.Day);

// تست
Console.WriteLine($"10 + 3 = {Calculate(10, 3, '+')}");
int p = 5, q = 10;
Swap(ref p, ref q);
Console.WriteLine($"بعد Swap: p={p}, q={q}");
MinMax(new[] { 45, 12, 78 }, out int mn, out int mx);
Console.WriteLine($"min={mn}, max={mx}");
Console.WriteLine(FormatDate(1403, 6, 15));
Console.WriteLine(FormatDate(DateTime.Now));
