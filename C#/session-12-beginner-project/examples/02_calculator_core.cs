// ============================================================
// جلسه 12 — پروژه مبتدی: ماشین‌حساب CLI
// فایل: 02_calculator_core.cs
// ============================================================
double Calc(double a, double b, string op) => op switch
{
    "+" => a + b,
    "-" => a - b,
    "*" => a * b,
    "/" => b == 0 ? throw new DivideByZeroException() : a / b,
    _ => throw new ArgumentException("op")
};
Console.WriteLine(Calc(10, 2, "/"));
