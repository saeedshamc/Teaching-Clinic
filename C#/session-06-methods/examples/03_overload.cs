// ============================================================
// جلسه 6 — متدها
// فایل: 03_overload.cs
// ============================================================
int Max(int a, int b) => a > b ? a : b;
double Max(double a, double b) => a > b ? a : b;
Console.WriteLine(Max(3, 8));
Console.WriteLine(Max(3.5, 2.1));
