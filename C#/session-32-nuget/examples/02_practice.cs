// ============================================================
// جلسه ۳۲ — NuGet: شبیه‌سازی استفاده از package
// ============================================================

using MathExtra;

double price = 1_000_000;
double discount = Calculator.PercentOf(price, 15);
Console.WriteLine($"قیمت: {price:N0} — تخفیف 15%: {discount:N0}");
Console.WriteLine($"قیمت نهایی: {price - discount:N0}");
Console.WriteLine($"IsBetween(5,1,10): {Calculator.IsBetween(5, 1, 10)}");

namespace MathExtra;

public static class Calculator
{
    public static double PercentOf(double value, double percent) =>
        value * percent / 100;

    public static bool IsBetween(int value, int min, int max) =>
        value >= min && value <= max;
}
