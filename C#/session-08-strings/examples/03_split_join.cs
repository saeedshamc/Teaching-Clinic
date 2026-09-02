// ============================================================
// جلسه 8 — کار با رشته‌ها
// فایل: 03_split_join.cs
// ============================================================
string csv = "Ali,Sara,Reza";
string[] parts = csv.Split(',');
Console.WriteLine(string.Join(" | ", parts));
