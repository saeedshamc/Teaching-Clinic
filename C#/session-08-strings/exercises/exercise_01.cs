// ============================================================
// تمرین جلسه 8 — پاسخ نمونه
// ============================================================
Console.Write("کلمه: ");
string? w = Console.ReadLine() ?? "";
int count = 0;
foreach (char c in w.ToLower())
    if ("aeiou".Contains(c)) count++;
Console.WriteLine(count);
