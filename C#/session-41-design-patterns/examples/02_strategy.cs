// ============================================================
// جلسه ۴۱ — Design Patterns
// فایل: 02_strategy.cs
// ============================================================

interface IDiscountStrategy
{
    decimal Apply(decimal price);
    string Name { get; }
}

class NoDiscount : IDiscountStrategy
{
    public string Name => "بدون تخفیف";
    public decimal Apply(decimal price) => price;
}

class MemberDiscount : IDiscountStrategy
{
    public string Name => "عضو باشگاه (۱۰٪)";
    public decimal Apply(decimal price) => price * 0.9m;
}

class SeasonalDiscount : IDiscountStrategy
{
    public string Name => "فروش فصلی (۲۰٪)";
    public decimal Apply(decimal price) => price * 0.8m;
}

class OrderService
{
    private readonly IDiscountStrategy _discount;
    public OrderService(IDiscountStrategy discount) => _discount = discount;

    public decimal CalculateTotal(decimal price)
    {
        var final = _discount.Apply(price);
        Console.WriteLine($"  {_discount.Name}: {price:N0} → {final:N0}");
        return final;
    }
}

decimal basePrice = 1_000_000;

Console.WriteLine("=== Strategy Pattern ===");
new OrderService(new NoDiscount()).CalculateTotal(basePrice);
new OrderService(new MemberDiscount()).CalculateTotal(basePrice);
new OrderService(new SeasonalDiscount()).CalculateTotal(basePrice);

// Runtime strategy switch
IDiscountStrategy strategy = DateTime.Now.Month == 12
    ? new SeasonalDiscount()
    : new NoDiscount();
Console.WriteLine($"\n  Strategy انتخاب‌شده: {strategy.Name}");
