// ============================================================
// جلسه 14 — Property و Constructor
// فایل: 02_constructors.cs
// ============================================================
class Product
{
    public string Name { get; }
    public Product(string name) => Name = name;
}
var p = new Product("Phone");
Console.WriteLine(p.Name);
