// ============================================================
// جلسه 2 — متغیرها و انواع داده
// فایل: 03_casting.cs
// ============================================================
// تبدیل صریح و TryParse
double d = 9.8;
int rounded = (int)d;

string input = "42";
if (int.TryParse(input, out int number))
{
    Console.WriteLine($"عدد: {number}");
}
