// ============================================================
// جلسه 50 — پروژه نهایی
// فایل: 02_service_layer.cs
// ============================================================
class ProductService
{
    private readonly List<(int Id, string Name)> _items = new();
    public void Add(int id, string name) => _items.Add((id, name));
    public IEnumerable<string> GetAll() => _items.Select(x => x.Name);
}
var svc = new ProductService(); svc.Add(1, "Laptop");
foreach (var n in svc.GetAll()) Console.WriteLine(n);
