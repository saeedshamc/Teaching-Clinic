// ============================================================
// جلسه ۴۸ — Stack Trace Analysis
// ============================================================

class OrderProcessor
{
    public void Process(int orderId)
    {
        Validate(orderId);
        CalculateTotal(orderId);
    }

    void Validate(int orderId)
    {
        if (orderId <= 0)
            throw new ArgumentException($"Invalid order ID: {orderId}", nameof(orderId));
    }

    void CalculateTotal(int orderId)
    {
        ApplyDiscount(orderId, discount: -10); // bug: negative discount
    }

    void ApplyDiscount(int orderId, decimal discount)
    {
        if (discount < 0)
            throw new InvalidOperationException(
                $"Discount cannot be negative: {discount}");
    }
}

Console.WriteLine("=== Stack Trace ===");
try
{
    new OrderProcessor().Process(0);
}
catch (ArgumentException ex)
{
    Console.WriteLine($"Exception: {ex.GetType().Name}: {ex.Message}");
    Console.WriteLine("Stack trace (read bottom → top):");
    foreach (var line in ex.StackTrace!.Split('\n').Take(5))
        Console.WriteLine($"  {line.Trim()}");
}

Console.WriteLine();

try
{
    new OrderProcessor().Process(1);
}
catch (InvalidOperationException ex)
{
    Console.WriteLine($"Exception: {ex.Message}");
    Console.WriteLine("Call chain: Process → CalculateTotal → ApplyDiscount");
    Console.WriteLine("  Root cause: negative discount passed");
}

Console.WriteLine("\n=== Debug tip ===");
Console.WriteLine("  1. Read stack trace from TOP (where thrown)");
Console.WriteLine("  2. Find YOUR code (not framework)");
Console.WriteLine("  3. Check input values at that frame");
