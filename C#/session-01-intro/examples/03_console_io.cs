// ============================================================
// جلسه ۱ — ورودی و خروجی Console
// فایل: 03_console_io.cs
// ============================================================

Console.Write("نام خود را وارد کنید: ");
string? name = Console.ReadLine(); // خواندن یک خط از کاربر

Console.Write("سن خود را وارد کنید: ");
string? ageText = Console.ReadLine();

Console.WriteLine();
Console.WriteLine($"سلام {name}! خوش آمدید.");
Console.WriteLine($"سن وارد شده: {ageText}");

// نکته: ReadLine ممکن است null برگرداند (مثلاً Ctrl+Z)
// در جلسات بعد با validation و تبدیل نوع دقیق‌تر کار می‌کنیم.
