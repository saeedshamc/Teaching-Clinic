// ============================================================
// تمرین جلسه 10 — پاسخ نمونه
// ============================================================
bool TryReadInt(string? input, out int value)
{
    return int.TryParse(input, out value);
}
Console.Write("عدد: ");
if (!TryReadInt(Console.ReadLine(), out int n)) Console.WriteLine("خطا");
else Console.WriteLine(n);
