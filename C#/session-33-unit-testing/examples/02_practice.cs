// ============================================================
// جلسه ۳۳ — Unit Testing: Theory (parameterized)
// ============================================================

var isEvenCases = new (int Input, bool Expected)[]
{
    (0, true), (1, false), (2, true), (99, false), (-4, true)
};

var maxCases = new (int A, int B, int Expected)[]
{
    (5, 3, 5), (3, 5, 5), (7, 7, 7), (-1, -5, -1)
};

Console.WriteLine("=== Theory: IsEven ===");
foreach (var (input, expected) in isEvenCases)
{
    bool actual = MathUtils.IsEven(input);
    Console.WriteLine($"  {(actual == expected ? "✓" : "✗")} IsEven({input}) = {actual}");
}

Console.WriteLine("\n=== Theory: Max ===");
foreach (var (a, b, expected) in maxCases)
{
    int actual = MathUtils.Max(a, b);
    Console.WriteLine($"  {(actual == expected ? "✓" : "✗")} Max({a},{b}) = {actual}");
}

public static class MathUtils
{
    public static bool IsEven(int n) => n % 2 == 0;
    public static int Max(int a, int b) => a > b ? a : b;
}
