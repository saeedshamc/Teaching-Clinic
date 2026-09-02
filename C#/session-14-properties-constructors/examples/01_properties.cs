// ============================================================
// جلسه ۱۴ — Properties: Auto-property و Validation
// فایل: 01_properties.cs
// ============================================================

class Product
{
    private double _price;

    // Auto-property ساده
    public string Name { get; set; } = "";

    // Property با validation در setter
    public double Price
    {
        get => _price;
        set
        {
            if (value < 0)
                throw new ArgumentException("قیمت نمی‌تواند منفی باشد");
            _price = value;
        }
    }

    // Expression-bodied — محاسبه‌شده، فقط خواندنی
    public string DisplayPrice => $"{Price:N0} تومان";
    public bool IsExpensive => Price > 1_000_000;

    // private set — فقط داخل کلاس قابل تغییر
    public int Stock { get; private set; }

    public void Restock(int qty)
    {
        if (qty > 0) Stock += qty;
    }
}

var laptop = new Product
{
    Name = "لپ‌تاپ",
    Price = 25_000_000
};
laptop.Restock(10);

Console.WriteLine($"{laptop.Name}: {laptop.DisplayPrice}");
Console.WriteLine($"گران؟ {laptop.IsExpensive} | موجودی: {laptop.Stock}");

// laptop.Stock = 100;  // ❌ compile error — private set
