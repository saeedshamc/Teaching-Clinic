// ============================================================
// جلسه ۱۰ — try / catch
// ============================================================

// --- مثال ۱: FormatException ---
Console.Write("عدد وارد کنید: ");
try
{
    int n = int.Parse(Console.ReadLine()!);
    Console.WriteLine($"✅ مربع {n} = {n * n}");
}
catch (FormatException)
{
    Console.WriteLine("❌ ورودی عدد صحیح نیست!");
}

Console.WriteLine();

// --- مثال ۲: DivideByZeroException ---
try
{
    Console.Write("a: ");
    int a = int.Parse(Console.ReadLine() ?? "10");
    Console.Write("b: ");
    int b = int.Parse(Console.ReadLine() ?? "0");
    Console.WriteLine($"a/b = {a / b}");
}
catch (DivideByZeroException)
{
    Console.WriteLine("❌ تقسیم بر صفر!");
}
catch (FormatException ex)
{
    Console.WriteLine($"❌ فرمت: {ex.Message}");
}

Console.WriteLine();

// --- مثال ۳: چند catch ---
string[] inputs = { "42", "abc", "10" };
foreach (string input in inputs)
{
    try
    {
        int val = int.Parse(input);
        Console.WriteLine($"'{input}' → {val}");
    }
    catch (FormatException)
    {
        Console.WriteLine($"'{input}' → نامعتبر");
    }
}

Console.WriteLine();

// --- مثال ۴: catch Exception عمومی (آخر) ---
try
{
    int[] arr = { 1, 2, 3 };
    Console.WriteLine(arr[10]);  // IndexOutOfRangeException
}
catch (IndexOutOfRangeException ex)
{
    Console.WriteLine($"Index خطا: {ex.Message}");
}
catch (Exception ex)
{
    Console.WriteLine($"عمومی: {ex.GetType().Name}");
}

Console.WriteLine();

// --- TryParse — بدون exception ---
Console.Write("عدد (TryParse): ");
if (int.TryParse(Console.ReadLine(), out int parsed))
    Console.WriteLine($"✅ {parsed}");
else
    Console.WriteLine("❌ نامعتبر");
