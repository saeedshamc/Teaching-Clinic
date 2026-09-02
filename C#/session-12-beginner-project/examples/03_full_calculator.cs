// ============================================================
// جلسه ۱۲ — فاز ۳: ماشین‌حساب کامل
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
    _   => throw new ArgumentException($"عملگر نامعتبر: {op}")
};

double ReadNumber(string prompt)
{
    while (true)
    {
        Console.Write(prompt);
        if (double.TryParse(Console.ReadLine(), out double n)) return n;
        Console.WriteLine("  ❌ عدد نامعتبر");
    }
}

void ShowMenu()
{
    Console.WriteLine("\n╔══════════════════════════════╗");
    Console.WriteLine("║     ماشین‌حساب CLI           ║");
    Console.WriteLine("╠══════════════════════════════╣");
    Console.WriteLine("║  1. جمع (+)   2. تفریق (-)   ║");
    Console.WriteLine("║  3. ضرب (*)   4. تقسیم (/)   ║");
    Console.WriteLine("║  5. تاریخچه  0. خروج         ║");
    Console.WriteLine("╚══════════════════════════════╝");
}

void ShowHistory(List<string> history)
{
    if (history.Count == 0) { Console.WriteLine("  تاریخچه خالی"); return; }
    for (int i = 0; i < history.Count; i++)
        Console.WriteLine($"  {i + 1}. {history[i]}");
}

var history = new List<string>();
bool running = true;

while (running)
{
    ShowMenu();
    Console.Write("انتخاب: ");
    int.TryParse(Console.ReadLine(), out int choice);

    switch (choice)
    {
        case 0:
            running = false;
            Console.WriteLine("خداحافظ! 👋");
            break;

        case 1: case 2: case 3: case 4:
            char op = choice switch { 1 => '+', 2 => '-', 3 => '*', 4 => '/', _ => '?' };
            try
            {
                double a = ReadNumber("  عدد اول: ");
                double b = ReadNumber("  عدد دوم: ");
                double result = Calculate(a, b, op);
                string entry = $"{a} {op} {b} = {result}";
                Console.WriteLine($"  = {result}");
                history.Add($"[{DateTime.Now:HH:mm:ss}] {entry}");
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

        case 5:
            ShowHistory(history);
            break;

        default:
            Console.WriteLine("  ❌ گزینه نامعتبر");
            break;
    }
}
