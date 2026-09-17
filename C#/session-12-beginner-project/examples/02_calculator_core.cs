// ============================================================
// جلسه ۱۲ — فاز ۲: هسته محاسبات (متدها)
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

double Calculate(double a, double b, char op)
{
    return op switch
    {
        '+' => Add(a, b),
        '-' => Subtract(a, b),
        '*' => Multiply(a, b),
        '/' => TryDivide(a, b, out double r)
            ? r
            : throw new DivideByZeroException("تقسیم بر صفر"),
        _ => throw new ArgumentException($"عملگر نامعتبر: {op}")
    };
}

double ReadNumber(string prompt)
{
    while (true)
    {
        Console.Write(prompt);
        if (double.TryParse(Console.ReadLine(), out double n))
            return n;
        Console.WriteLine("  ❌ عدد نامعتبر");
    }
}

// --- Demo یک عملیات ---
Console.WriteLine("=== Demo ماشین‌حساب ===");
double x = ReadNumber("عدد اول: ");
double y = ReadNumber("عدد دوم: ");
Console.Write("عملگر (+,-,*,/): ");
char op = Console.ReadLine()?.Trim()[0] ?? '+';

try
{
    double result = Calculate(x, y, op);
    Console.WriteLine($"\n  {x} {op} {y} = {result}");
}
catch (DivideByZeroException ex)
{
    Console.WriteLine($"  ❌ {ex.Message}");
}
catch (ArgumentException ex)
{
    Console.WriteLine($"  ❌ {ex.Message}");
}

// --- تست سریع همه عملگرها ---
Console.WriteLine("\n=== تست خودکار ===");
(char op, double a, double b)[] tests = {
    ('+', 10, 3), ('-', 10, 3), ('*', 10, 3), ('/', 10, 3)
};
foreach (var (o, a, b) in tests)
{
    try
    {
        Console.WriteLine($"  {a} {o} {b} = {Calculate(a, b, o)}");
    }
    catch (Exception ex)
    {
        Console.WriteLine($"  {a} {o} {b} → {ex.Message}");
    }
}
