// ============================================================
// جلسه ۱۹ — Property Validation
// فایل: 02_property_validation.cs
// ============================================================

class Product
{
    private double _price;
    private int _stock;

    public string Name { get; set; } = "";

    public double Price
    {
        get => _price;
        set
        {
            if (value < 0) throw new ArgumentException("قیمت نمی‌تواند منفی باشد");
            _price = value;
        }
    }

    public int Stock
    {
        get => _stock;
        private set => _stock = value >= 0 ? value : 0;
    }

    public void Restock(int quantity)
    {
        if (quantity > 0) Stock += quantity;
    }

    public bool TrySell(int quantity)
    {
        if (quantity <= 0 || quantity > Stock) return false;
        Stock -= quantity;
        return true;
    }

    public double InventoryValue => Price * Stock;
}

class Person
{
    private int _age;
    private string _email = "";

    public string Name { get; set; } = "";

    public int Age
    {
        get => _age;
        set => _age = Math.Clamp(value, 0, 150);
    }

    public string Email
    {
        get => _email;
        set => _email = value.Contains('@') ? value : throw new ArgumentException("ایمیل نامعتبر");
    }
}

var product = new Product { Name = "لپ‌تاپ", Price = 25_000_000 };
product.Restock(10);
product.TrySell(3);
Console.WriteLine($"{product.Name}: موجودی={product.Stock}, ارزش={product.InventoryValue:N0}");

var person = new Person { Name = "مریم", Age = 25, Email = "maryam@test.com" };
Console.WriteLine($"{person.Name} ({person.Age}) — {person.Email}");
