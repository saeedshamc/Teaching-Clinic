// ============================================================
// جلسه ۱۰ — الگوهای Exception Handling
// ============================================================

// --- الگوی ۱: Try pattern (بدون exception) ---
bool TryDivide(int a, int b, out double result)
{
    result = 0;
    if (b == 0) return false;
    result = (double)a / b;
    return true;
}

if (TryDivide(10, 3, out double q))
    Console.WriteLine($"10/3 = {q:F2}");
if (!TryDivide(10, 0, out _))
    Console.WriteLine("❌ تقسیم بر صفر");

Console.WriteLine();

// --- الگوی ۲: Guard + throw ---
void Transfer(decimal fromBalance, decimal amount)
{
    if (amount <= 0)
        throw new ArgumentException("مبلغ نامعتبر");
    if (amount > fromBalance)
        throw new InvalidOperationException("موجودی کافی نیست");
    Console.WriteLine($"✅ انتقال {amount:N0}");
}

// --- الگوی ۳: catch-log-rethrow ---
void LogAndRethrow(Action action)
{
    try { action(); }
    catch (Exception ex)
    {
        Console.WriteLine($"[LOG] {DateTime.Now:HH:mm:ss} — {ex.GetType().Name}: {ex.Message}");
        throw;
    }
}

Console.WriteLine("=== Log and rethrow ===");
try
{
    LogAndRethrow(() => int.Parse("xyz"));
}
catch (FormatException)
{
    Console.WriteLine("Handled at top level");
}

Console.WriteLine();

// --- الگوی ۴: Aggregate handling ---
string[] values = { "10", "20", "abc", "30" };
int success = 0, failed = 0;

foreach (string v in values)
{
    try
    {
        int n = int.Parse(v);
        Console.WriteLine($"  OK: {n}");
        success++;
    }
    catch (FormatException)
    {
        Console.WriteLine($"  SKIP: '{v}'");
        failed++;
    }
}
Console.WriteLine($"موفق: {success}, ناموفق: {failed}");

Console.WriteLine();

// --- الگوی ۵: when filter ---
try
{
    throw new ArgumentException("bad arg");
}
catch (ArgumentException ex) when (ex.Message.Contains("bad"))
{
    Console.WriteLine("Filtered catch: bad arg");
}
catch (ArgumentException)
{
    Console.WriteLine("General ArgumentException");
}

Console.WriteLine();

// --- Fail fast vs catch all ---
void ProcessUserInput(string? input)
{
    // Fail fast — validate first
    if (string.IsNullOrWhiteSpace(input))
        throw new ArgumentException("ورودی خالی");

    // business logic — only expected exceptions
    int value = int.Parse(input);
    Console.WriteLine($"Processed: {value}");
}

try { ProcessUserInput(""); }
catch (ArgumentException ex) { Console.WriteLine($"Validation: {ex.Message}"); }

try { ProcessUserInput("42"); }
catch (Exception ex) { Console.WriteLine(ex.Message); }
