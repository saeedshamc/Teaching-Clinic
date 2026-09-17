// ============================================================
// جلسه ۲ — ورودی امن با TryParse
// ============================================================

Console.Write("عدد اول: ");
string? s1 = Console.ReadLine();

Console.Write("عدد دوم: ");
string? s2 = Console.ReadLine();

if (!int.TryParse(s1, out int a) || !int.TryParse(s2, out int b))
{
    Console.WriteLine("❌ لطفاً فقط عدد صحیح وارد کنید.");
    return;
}

Console.WriteLine($"جمع: {a + b}");
Console.WriteLine($"میانگین: {(a + b) / 2.0}");
