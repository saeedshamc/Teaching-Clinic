// ============================================================
// جلسه ۱۰ — finally
// ============================================================

// --- finally همیشه اجرا می‌شود ---
int AttemptOperation(bool fail)
{
    Console.WriteLine("  → شروع عملیات");
    try
    {
        if (fail)
            throw new InvalidOperationException("عملیات ناموفق!");
        Console.WriteLine("  → موفق");
        return 1;
    }
    catch (InvalidOperationException ex)
    {
        Console.WriteLine($"  → catch: {ex.Message}");
        return -1;
    }
    finally
    {
        Console.WriteLine("  → finally: cleanup");
    }
}

Console.WriteLine("=== موفق ===");
AttemptOperation(fail: false);
Console.WriteLine("\n=== ناموفق ===");
AttemptOperation(fail: true);

Console.WriteLine();

// --- finally حتی با return ---
string GetValue(bool throwError)
{
    try
    {
        if (throwError) throw new Exception("خطا");
        return "موفق";
    }
    finally
    {
        Console.WriteLine("  finally قبل از return");
    }
}

Console.WriteLine($"نتیجه: {GetValue(false)}");

Console.WriteLine();

// --- شبیه‌سازی resource cleanup ---
class FakeResource : IDisposable
{
    public string Name { get; }
    public FakeResource(string name) { Name = name; Console.WriteLine($"  Open: {name}"); }
    public void Dispose() => Console.WriteLine($"  Close: {Name}");
}

void UseResource(bool error)
{
    FakeResource? resource = null;
    try
    {
        resource = new FakeResource("database");
        if (error) throw new Exception("Query failed");
        Console.WriteLine("  Query OK");
    }
    catch (Exception ex)
    {
        Console.WriteLine($"  Error: {ex.Message}");
    }
    finally
    {
        resource?.Dispose();
    }
}

Console.WriteLine("=== Resource OK ===");
UseResource(false);
Console.WriteLine("\n=== Resource Error ===");
UseResource(true);

Console.WriteLine();

// --- using — modern alternative ---
Console.WriteLine("=== using statement ===");
using (var r = new FakeResource("file"))
{
    Console.WriteLine("  Processing...");
}
