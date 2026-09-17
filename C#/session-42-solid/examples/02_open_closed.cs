// ============================================================
// جلسه ۴۲ — SOLID: Open/Closed Principle
// ============================================================

// ❌ Bad — هر discount جدید نیاز به تغییر دارد
class BadPriceCalculator
{
    public decimal Calculate(string discountType, decimal price) => discountType switch
    {
        "member" => price * 0.9m,
        "season" => price * 0.8m,
        "none" => price,
        _ => price
    };
}

// ✅ Good — extension بدون modification
interface IDiscountStrategy
{
    string Name { get; }
    decimal Apply(decimal price);
}

class NoDiscount : IDiscountStrategy
{
    public string Name => "none";
    public decimal Apply(decimal price) => price;
}

class MemberDiscount : IDiscountStrategy
{
    public string Name => "member";
    public decimal Apply(decimal price) => price * 0.9m;
}

class SeasonDiscount : IDiscountStrategy
{
    public string Name => "season";
    public decimal Apply(decimal price) => price * 0.8m;
}

// تخفیف جدید — بدون تغییر PriceCalculator
class StudentDiscount : IDiscountStrategy
{
    public string Name => "student";
    public decimal Apply(decimal price) => price * 0.85m;
}

class PriceCalculator
{
    public decimal Calculate(IDiscountStrategy strategy, decimal price)
        => strategy.Apply(price);
}

var calc = new PriceCalculator();
decimal price = 1_000_000;

Console.WriteLine("=== ❌ Bad ===");
var bad = new BadPriceCalculator();
Console.WriteLine($"  member: {bad.Calculate("member", price):N0}");

Console.WriteLine("\n=== ✅ Good (OCP) ===");
foreach (IDiscountStrategy s in new IDiscountStrategy[]
    { new NoDiscount(), new MemberDiscount(), new SeasonDiscount(), new StudentDiscount() })
    Console.WriteLine($"  {s.Name}: {calc.Calculate(s, price):N0}");
