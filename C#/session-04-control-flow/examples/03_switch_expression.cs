// ============================================================
// جلسه ۴ — Switch Expression (C# 8+)
// ============================================================

// --- مثال ۱: mapping عملگر به نام فارسی ---
Console.Write("عملگر: ");
string? op = Console.ReadLine();

string opName = op switch
{
    "+" => "جمع",
    "-" => "تفریق",
    "*" => "ضرب",
    "/" => "تقسیم",
    _   => "نامعتبر"
};
Console.WriteLine($"عملگر: {opName}");
Console.WriteLine();

// --- مثال ۲: محاسبه با switch expression ---
double x = 20, y = 4;
char operatorChar = '*';

double result = operatorChar switch
{
    '+' => x + y,
    '-' => x - y,
    '*' => x * y,
    '/' => y == 0 ? throw new DivideByZeroException("تقسیم بر صفر!") : x / y,
    _   => double.NaN
};
Console.WriteLine($"{x} {operatorChar} {y} = {result}");
Console.WriteLine();

// --- مثال ۳: relational pattern — نمره ---
int[] scores = { 95, 72, 48, 88, 55 };

foreach (int s in scores)
{
    string label = s switch
    {
        >= 90 => "A",
        >= 75 => "B",
        >= 50 => "C",
        _     => "F"
    };
    Console.WriteLine($"نمره {s} → {label}");
}

Console.WriteLine();

// --- مثال ۴: pattern matching با when ---
object value = -7;

string desc = value switch
{
    int n when n < 0  => $"عدد منفی: {n}",
    int n when n == 0 => "صفر",
    int n             => $"عدد مثبت: {n}",
    string s          => $"رشته با طول {s.Length}",
    null              => "null",
    _                 => "نوع دیگر"
};
Console.WriteLine(desc);
