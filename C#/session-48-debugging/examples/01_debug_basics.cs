// ============================================================
// جلسه ۴۸ — Debug Basics
// ============================================================

// Simulate debugging with trace output
class Debug
{
    public static void WriteLine(string msg) => Console.WriteLine($"  [DEBUG] {msg}");
    public static void Assert(bool condition, string msg)
    {
        if (!condition) Console.WriteLine($"  [ASSERT FAILED] {msg}");
    }
}

int FindMax(int[] numbers)
{
    Debug.WriteLine($"FindMax called with {numbers.Length} items");
    Debug.Assert(numbers.Length > 0, "Array must not be empty");

    int max = numbers[0];
    for (int i = 1; i < numbers.Length; i++)
    {
        Debug.WriteLine($"  i={i}, numbers[i]={numbers[i]}, current max={max}");
        if (numbers[i] > max)
            max = numbers[i];
    }
    Debug.WriteLine($"Result: max={max}");
    return max;
}

Console.WriteLine("=== Debug Trace (simulate F10/F11) ===");
var result = FindMax(new[] { 3, 7, 2, 9, 1, 5 });
Console.WriteLine($"Max = {result}");

Console.WriteLine("\n=== Breakpoint tip ===");
Console.WriteLine("  Set breakpoint on: if (numbers[i] > max)");
Console.WriteLine("  Conditional: numbers[i] > 100");
