// ============================================================
// تمرین جلسه 2 — پاسخ نمونه
// ============================================================
Console.Write("عدد اول: ");
int.TryParse(Console.ReadLine(), out int a);
Console.Write("عدد دوم: ");
int.TryParse(Console.ReadLine(), out int b);
Console.WriteLine($"جمع: {a + b}");
Console.WriteLine($"میانگین: {(a + b) / 2.0}");
