// ============================================================
// جلسه ۱۲ — فاز ۴: نسخه پیشرفته (ANS + پاک history)
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

double Calculate(double a, double b, char op) => op switch
{
    '+' => Add(a, b),
    '-' => Subtract(a, b),
    '*' => Multiply(a, b),
    '/' => TryDivide(a, b, out double r) ? r : throw new DivideByZeroException(),
    '%' => a % b,
    '^' => Math.Pow(a, b),
    _   => throw new ArgumentException($"عملگر: {op}")
};

double ReadNumber(string prompt, double? defaultValue = null)
{
    while (true)
    {
        string hint = defaultValue.HasValue ? $" [{defaultValue}]" : "";
        Console.Write($"{prompt}{hint}: ");

        string? input = Console.ReadLine()?.Trim();

        if (string.IsNullOrEmpty(input) && defaultValue.HasValue)
            return defaultValue.Value;

        if (double.TryParse(input, out double n))
            return n;

        Console.WriteLine("  ❌ عدد نامعتبر");
    }
}

void ShowMenu(double lastResult)
{
    Console.WriteLine("\n╔════════════════════════════════╗");
    Console.WriteLine("║   ماشین‌حساب پیشرفته CLI       ║");
    Console.WriteLine($"║   ANS = {lastResult,-23} ║");
    Console.WriteLine("╠════════════════════════════════╣");
    Console.WriteLine("║ 1.+  2.-  3.*  4./  5.%  6.^  ║");
    Console.WriteLine("║ 7.تاریخچه  8.پاک history  0.خروج ║");
    Console.WriteLine("╚════════════════════════════════╝");
}

var history = new List<string>();
double lastResult = 0;
const int MaxHistory = 20;

bool running = true;
while (running)
{
    ShowMenu(lastResult);
    Console.Write("انتخاب: ");
    int.TryParse(Console.ReadLine(), out int choice);

    switch (choice)
    {
        case 0:
            running = false;
            Console.WriteLine("خداحافظ! 👋");
            break;

        case >= 1 and <= 6:
            char op = choice switch
            {
                1 => '+', 2 => '-', 3 => '*', 4 => '/',
                5 => '%', 6 => '^', _ => '?'
            };
            try
            {
                double a = ReadNumber("  عدد اول", lastResult);
                double b = ReadNumber("  عدد دوم");
                double result = Calculate(a, b, op);
                lastResult = result;

                string entry = $"{a} {op} {b} = {result}";
                Console.WriteLine($"  ✅ = {result}");

                history.Add($"[{DateTime.Now:HH:mm:ss}] {entry}");
                if (history.Count > MaxHistory)
                    history.RemoveAt(0);
            }
            catch (DivideByZeroException)
            {
                Console.WriteLine("  ❌ تقسیم بر صفر!");
            }
            catch (ArgumentException ex)
            {
                Console.WriteLine($"  ❌ {ex.Message}");
            }
            break;

        case 7:
            if (history.Count == 0)
                Console.WriteLine("  تاریخچه خالی");
            else
                for (int i = 0; i < history.Count; i++)
                    Console.WriteLine($"  {i + 1}. {history[i]}");
            break;

        case 8:
            history.Clear();
            Console.WriteLine("  🗑️ تاریخچه پاک شد");
            break;

        default:
            Console.WriteLine("  ❌ گزینه نامعتبر");
            break;
    }
}
