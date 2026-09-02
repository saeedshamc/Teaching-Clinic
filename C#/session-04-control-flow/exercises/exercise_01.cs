// ============================================================
// پاسخ نمونه — تمرین‌های کلاسی (۱–۴)
// ============================================================

// ۱. زوج/فرد
Console.Write("عدد: ");
int.TryParse(Console.ReadLine(), out int n);
Console.WriteLine(n % 2 == 0 ? "زوج" : "فرد");

// ۲. بزرگترین دو عدد
Console.Write("a: "); int.TryParse(Console.ReadLine(), out int a);
Console.Write("b: "); int.TryParse(Console.ReadLine(), out int b);
int bigger = a > b ? a : b;
Console.WriteLine($"بزرگتر: {bigger}");

// ۳. فصل سال
Console.Write("شماره ماه (1-12): ");
int.TryParse(Console.ReadLine(), out int month);
string season = month switch
{
    1 or 2 or 3       => "بهار",
    4 or 5 or 6       => "تابستان",
    7 or 8 or 9       => "پاییز",
    10 or 11 or 12    => "زمستان",
    _                 => "ماه نامعتبر"
};
Console.WriteLine($"فصل: {season}");

// ۴. ماشین‌حساب mini
Console.Write("x: "); double.TryParse(Console.ReadLine(), out double x);
Console.Write("y: "); double.TryParse(Console.ReadLine(), out double y);
Console.Write("op (+,-,*,/): ");
string? op = Console.ReadLine();

double result = op switch
{
    "+" => x + y,
    "-" => x - y,
    "*" => x * y,
    "/" => y == 0 ? double.NaN : x / y,
    _   => double.NaN
};

if (double.IsNaN(result))
    Console.WriteLine("خطا: عملگر نامعتبر یا تقسیم بر صفر");
else
    Console.WriteLine($"= {result}");
