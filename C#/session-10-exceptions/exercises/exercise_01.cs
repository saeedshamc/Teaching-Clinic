// ============================================================
// پاسخ نمونه — تمرین‌های کلاسی (۱–۴)
// ============================================================

// ۱. Parse safe
Console.Write("عدد: ");
try
{
    int n = int.Parse(Console.ReadLine()!);
    Console.WriteLine($"✅ {n}");
}
catch (FormatException)
{
    Console.WriteLine("❌ ورودی عدد نیست!");
}

// ۲. Divide
try
{
    Console.Write("a: "); int a = int.Parse(Console.ReadLine() ?? "10");
    Console.Write("b: "); int b = int.Parse(Console.ReadLine() ?? "0");
    Console.WriteLine($"a/b = {a / b}");
}
catch (DivideByZeroException)
{
    Console.WriteLine("❌ تقسیم بر صفر!");
}

// ۳. finally
try
{
    Console.WriteLine("عملیات...");
    throw new Exception("test");
}
catch (Exception ex)
{
    Console.WriteLine($"catch: {ex.Message}");
}
finally
{
    Console.WriteLine("--- پایان ---");
}

// ۴. ValidateAge
void ValidateAge(int age)
{
    if (age < 0 || age > 150)
        throw new ArgumentOutOfRangeException(nameof(age), "سن نامعتبر");
}

try { ValidateAge(-1); }
catch (ArgumentOutOfRangeException ex) { Console.WriteLine(ex.Message); }
