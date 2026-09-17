// ============================================================
// تمرین ۱ جلسه ۴۱ — Factory + Strategy (پاسخ نمونه)
// ============================================================

interface IShippingStrategy { decimal Calculate(decimal orderTotal); }

class StandardShipping : IShippingStrategy
{
    public decimal Calculate(decimal orderTotal)
        => orderTotal >= 500_000 ? 0 : 30_000;
}

class ExpressShipping : IShippingStrategy
{
    public decimal Calculate(decimal orderTotal) => 50_000;
}

static class ShippingFactory
{
    public static IShippingStrategy Create(string type) => type switch
    {
        "standard" => new StandardShipping(),
        "express" => new ExpressShipping(),
        _ => throw new ArgumentException("نوع نامعتبر")
    };
}

class CheckoutService
{
    private readonly IShippingStrategy _shipping;
    public CheckoutService(IShippingStrategy shipping) => _shipping = shipping;

    public decimal Total(decimal itemsTotal)
    {
        var ship = _shipping.Calculate(itemsTotal);
        Console.WriteLine($"  اقلام: {itemsTotal:N0} + ارسال: {ship:N0} = {(itemsTotal + ship):N0}");
        return itemsTotal + ship;
    }
}

new CheckoutService(ShippingFactory.Create("standard")).Total(600_000);
new CheckoutService(ShippingFactory.Create("standard")).Total(200_000);
new CheckoutService(ShippingFactory.Create("express")).Total(200_000);
