// ============================================================
// جلسه 4 — ساختارهای شرطی
// فایل: 02_switch.cs
// ============================================================
Console.Write("روز (1-7): ");
int.TryParse(Console.ReadLine(), out int day);
switch (day)
{
    case 6:
    case 7:
        Console.WriteLine("آخر هفته");
        break;
    default:
        Console.WriteLine("روز کاری");
        break;
}
