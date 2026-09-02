// ============================================================
// جلسه 10 — مدیریت خطا
// فایل: 01_try_catch.cs
// ============================================================
try
{
    Console.Write("a: "); int a = int.Parse(Console.ReadLine()!);
    Console.Write("b: "); int b = int.Parse(Console.ReadLine()!);
    Console.WriteLine(a / b);
}
catch (DivideByZeroException)
{
    Console.WriteLine("تقسیم بر صفر!");
}
catch (FormatException)
{
    Console.WriteLine("ورودی عددی نیست.");
}
