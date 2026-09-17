// ============================================================
// تمرین ۱ جلسه ۳۹ — Controller و Action (پاسخ نمونه)
// ============================================================

record Book(int Id, string Title, string Author);

enum ResultType { View, NotFound }
record ActionResult(ResultType Type, object? Data = null);

class BookController
{
    private readonly List<Book> _books = new()
    {
        new(1, "C# in Depth", "Jon Skeet"),
        new(2, "Clean Code", "Robert Martin"),
        new(3, "Design Patterns", "GoF")
    };

    public ActionResult Index()
        => new(ResultType.View, _books);

    public ActionResult Details(int id)
    {
        var book = _books.FirstOrDefault(b => b.Id == id);
        return book is null
            ? new ActionResult(ResultType.NotFound)
            : new ActionResult(ResultType.View, book);
    }

    public ActionResult Search(string keyword)
    {
        var results = _books
            .Where(b => b.Title.Contains(keyword, StringComparison.OrdinalIgnoreCase)
                     || b.Author.Contains(keyword, StringComparison.OrdinalIgnoreCase))
            .ToList();
        return new ActionResult(ResultType.View, results);
    }
}

var ctrl = new BookController();

Console.WriteLine("=== Index ===");
foreach (var b in (List<Book>)ctrl.Index().Data!)
    Console.WriteLine($"  [{b.Id}] {b.Title} — {b.Author}");

Console.WriteLine("\n=== Details(2) ===");
var d = ctrl.Details(2);
Console.WriteLine(d.Type == ResultType.View ? $"  {d.Data}" : "  404");

Console.WriteLine("\n=== Search('code') ===");
foreach (var b in (List<Book>)ctrl.Search("code").Data!)
    Console.WriteLine($"  {b.Title}");
