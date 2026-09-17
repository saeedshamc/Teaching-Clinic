// ============================================================
// جلسه ۱ — ورودی و خروجی Console
// فایل: 03_console_io.cs
// ============================================================

Console.WriteLine("=== فرم کوچک آشنایی ===");

Console.Write("نام: ");
string? name = Console.ReadLine();

Console.Write("رشته مورد علاقه: ");
string? favorite = Console.ReadLine();

Console.WriteLine();
Console.WriteLine($"سلام {name}!");
Console.WriteLine($"رشته مورد علاقه‌ات: {favorite ?? "(وارد نشده)"}");

// ReadLine ممکن است null برگرداند (مثلاً Ctrl+Z در ویندوز)
// علامت ? در string? یعنی nullable — در جلسه ۹ عمیق‌تر بررسی می‌کنیم.
