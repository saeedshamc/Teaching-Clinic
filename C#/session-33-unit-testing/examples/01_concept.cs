// ============================================================
// جلسه ۳۳ — Unit Testing: AAA و Assert (شبیه‌سازی xUnit)
// ============================================================

Console.WriteLine("=== Unit Tests ===");

RunTest("Add_TwoPositive_ReturnsSum", () =>
{
    var calc = new Calculator();
    AssertEqual(5, calc.Add(2, 3));
});

RunTest("Subtract_ReturnsDifference", () =>
{
    var calc = new Calculator();
    AssertEqual(7, calc.Subtract(10, 3));
});

RunTest("Divide_ByZero_Throws", () =>
{
    var calc = new Calculator();
    try { calc.Divide(10, 0); throw new Exception("Expected exception"); }
    catch (DivideByZeroException) { }
});

void RunTest(string name, Action test)
{
    try { test(); Console.WriteLine($"  ✓ {name}"); }
    catch (Exception ex) { Console.WriteLine($"  ✗ {name}: {ex.Message}"); }
}

void AssertEqual<T>(T expected, T actual, string? msg = null)
{
    if (!EqualityComparer<T>.Default.Equals(expected, actual))
        throw new Exception(msg ?? $"Expected {expected}, got {actual}");
}

public class Calculator
{
    public int Add(int a, int b) => a + b;
    public int Subtract(int a, int b) => a - b;
    public double Divide(int a, int b) =>
        b == 0 ? throw new DivideByZeroException() : (double)a / b;
}
