// ============================================================
// جلسه ۴۶ — Pagination و Filtering
// ============================================================

record Item(int Id, string Name, string Category);

class PaginatedResponse<T>
{
    public List<T> Items { get; init; } = new();
    public int Page { get; init; }
    public int PageSize { get; init; }
    public int TotalCount { get; init; }
    public int TotalPages => (int)Math.Ceiling(TotalCount / (double)PageSize);
}

class ProductApi
{
    private readonly List<Item> _items = Enumerable.Range(1, 47)
        .Select(i => new Item(i, $"Product-{i}", i % 3 == 0 ? "Electronics" : "Books"))
        .ToList();

    public PaginatedResponse<Item> Get(
        int page = 1, int pageSize = 10,
        string? category = null, string? sort = "name")
    {
        var query = _items.AsEnumerable();
        if (!string.IsNullOrEmpty(category))
            query = query.Where(i => i.Category.Equals(category, StringComparison.OrdinalIgnoreCase));

        query = sort == "id"
            ? query.OrderBy(i => i.Id)
            : query.OrderBy(i => i.Name);

        var total = query.Count();
        var items = query.Skip((page - 1) * pageSize).Take(pageSize).ToList();

        return new PaginatedResponse<Item>
        {
            Items = items, Page = page, PageSize = pageSize, TotalCount = total
        };
    }
}

var api = new ProductApi();

Console.WriteLine("GET /api/products?page=1&pageSize=5");
var p1 = api.Get(page: 1, pageSize: 5);
Console.WriteLine($"  items={p1.Items.Count}, total={p1.TotalCount}, pages={p1.TotalPages}");
foreach (var i in p1.Items) Console.WriteLine($"    [{i.Id}] {i.Name}");

Console.WriteLine("\nGET /api/products?category=Electronics&page=1&pageSize=3");
var p2 = api.Get(page: 1, pageSize: 3, category: "Electronics");
Console.WriteLine($"  items={p2.Items.Count}, total={p2.TotalCount}");
