// ============================================================
// جلسه 12 — پروژه مبتدی: ماشین‌حساب CLI
// فایل: 03_full_calculator.cs
// ============================================================
while (true)
{
    Console.Write("a: "); if (!double.TryParse(Console.ReadLine(), out double a)) continue;
    Console.Write("b: "); if (!double.TryParse(Console.ReadLine(), out double b)) continue;
    Console.Write("op: "); string? op = Console.ReadLine();
    try
    {
        double r = op switch
        {
            "+" => a + b,
            "-" => a - b,
            "*" => a * b,
            "/" => a / b,
            _ => throw new Exception("نامعتبر")
        };
        Console.WriteLine(r);
    }
    catch (Exception ex) { Console.WriteLine(ex.Message); }
    Console.WriteLine("---");
}
