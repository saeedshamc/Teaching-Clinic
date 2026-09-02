// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۳
// ============================================================

public class Calculator
{
    public int Multiply(int a, int b) => a * b;
    public static bool IsPrime(int n)
    {
        if (n < 2) return false;
        for (int i = 2; i <= Math.Sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }
}

var primeCases = new (int N, bool Expected)[] { (2, true), (3, true), (4, false), (9, false), (11, true) };

Console.WriteLine("IsPrime tests:");
foreach (var (n, expected) in primeCases)
{
    bool actual = Calculator.IsPrime(n);
    Console.WriteLine($"  {(actual == expected ? "✓" : "✗")} IsPrime({n}) = {actual}");
}

var calc = new Calculator();
Console.WriteLine($"Multiply(6,7) = {calc.Multiply(6, 7)} — {(calc.Multiply(6, 7) == 42 ? "✓" : "✗")}");
