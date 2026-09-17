// ============================================================
// جلسه ۴۱ — Design Patterns
// فایل: 03_observer.cs
// ============================================================

class ProductAddedEventArgs
{
    public int ProductId { get; init; }
    public string Name { get; init; } = "";
}

class Inventory
{
    public event Action<ProductAddedEventArgs>? ProductAdded;

    public void AddProduct(int id, string name)
    {
        Console.WriteLine($"  📦 محصول اضافه شد: {name}");
        ProductAdded?.Invoke(new ProductAddedEventArgs { ProductId = id, Name = name });
    }
}

// Observers
void LogObserver(ProductAddedEventArgs e)
    => Console.WriteLine($"    [Log] Product #{e.ProductId}: {e.Name}");

void EmailObserver(ProductAddedEventArgs e)
    => Console.WriteLine($"    [Email] اطلاع‌رسانی: {e.Name} موجود شد");

void AnalyticsObserver(ProductAddedEventArgs e)
    => Console.WriteLine($"    [Analytics] event: product_added, id={e.ProductId}");

Console.WriteLine("=== Observer (Events) ===");
var inventory = new Inventory();
inventory.ProductAdded += LogObserver;
inventory.ProductAdded += EmailObserver;
inventory.ProductAdded += AnalyticsObserver;

inventory.AddProduct(1, "لپ‌تاپ");
inventory.AddProduct(2, "ماوس");

Console.WriteLine("\n=== Unsubscribe ===");
inventory.ProductAdded -= EmailObserver;
inventory.AddProduct(3, "کیبورد");
