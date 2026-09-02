// ============================================================
// پاسخ نمونه — نسخه پیشرفته (ANS + % + ^ + limit)
// ============================================================

double Calculate(double a, double b, char op) => op switch
{
    '+' => a + b,
    '-' => a - b,
    '*' => a * b,
    '/' => b == 0 ? throw new DivideByZeroException() : a / b,
    '%' => a % b,
    '^' => Math.Pow(a, b),
    _   => throw new ArgumentException("?")
};

double ReadNumber(string p, double? def = null)
{
    while (true)
    {
        Console.Write($"{p}{(def.HasValue ? $"[{def}]" : "")}: ");
        string? i = Console.ReadLine()?.Trim();
        if (string.IsNullOrEmpty(i) && def.HasValue) return def.Value;
        if (double.TryParse(i, out double n)) return n;
        Console.WriteLine("❌");
    }
}

var history = new List<string>();
double ans = 0;
const int Max = 20;

while (true)
{
    Console.WriteLine($"\nANS={ans} | 1-6 ops 7=hist 8=clear 0=exit");
    Console.Write("> ");
    int.TryParse(Console.ReadLine(), out int c);
    if (c == 0) break;
    if (c == 8) { history.Clear(); continue; }
    if (c == 7) { history.ForEach(h => Console.WriteLine($"  {h}")); continue; }
    if (c is < 1 or > 6) continue;

    char op = c switch { 1 => '+', 2 => '-', 3 => '*', 4 => '/', 5 => '%', 6 => '^', _ => '?' };
    try
    {
        double a = ReadNumber("a", ans);
        double b = ReadNumber("b");
        ans = Calculate(a, b, op);
        Console.WriteLine($"= {ans}");
        history.Add($"{a}{op}{b}={ans}");
        if (history.Count > Max) history.RemoveAt(0);
    }
    catch (Exception ex) { Console.WriteLine($"❌ {ex.Message}"); }
}
