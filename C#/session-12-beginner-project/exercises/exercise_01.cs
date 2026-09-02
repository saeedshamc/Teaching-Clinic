// ============================================================
// پاسخ نمونه — MVP ماشین‌حساب (فاز ۱–۳)
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
    _   => throw new ArgumentException("عملگر نامعتبر")
};

double ReadNumber(string prompt)
{
    while (true)
    {
        Console.Write(prompt);
        if (double.TryParse(Console.ReadLine(), out double n)) return n;
        Console.WriteLine("❌ نامعتبر");
    }
}

var history = new List<string>();
bool run = true;

while (run)
{
    Console.WriteLine("\n1.+ 2.- 3.* 4./ 5.history 0.exit");
    Console.Write("> ");
    int.TryParse(Console.ReadLine(), out int c);

    switch (c)
    {
        case 0: run = false; break;
        case 5:
            foreach (string h in history) Console.WriteLine($"  {h}");
            break;
        case >= 1 and <= 4:
            char op = c switch { 1 => '+', 2 => '-', 3 => '*', 4 => '/', _ => '?' };
            try
            {
                double a = ReadNumber("a: ");
                double b = ReadNumber("b: ");
                double r = Calculate(a, b, op);
                string e = $"{a}{op}{b}={r}";
                Console.WriteLine($"= {r}");
                history.Add(e);
            }
            catch (DivideByZeroException) { Console.WriteLine("÷0"); }
            break;
        default: Console.WriteLine("?"); break;
    }
}
