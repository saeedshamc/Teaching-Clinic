// ============================================================
// تمرین ۱ جلسه ۴۶ — Book CRUD (پاسخ نمونه)
// ============================================================

record Book(int Id, string Title, string Author);
record CreateBook(string Title, string Author);

class BookApi
{
    private readonly List<Book> _books = new();
    private int _id = 1;

    public (int code, object? body) Create(CreateBook req)
    {
        var b = new Book(_id++, req.Title, req.Author);
        _books.Add(b);
        return (201, b);
    }

    public (int code, object? body) Get(int id)
        => _books.FirstOrDefault(b => b.Id == id) is { } b ? (200, b) : (404, new { error = "Not Found" });

    public (int code, object? body) Delete(int id)
        => _books.RemoveAll(b => b.Id == id) > 0 ? (204, null) : (404, new { error = "Not Found" });
}

var api = new BookApi();
var (c1, b1) = api.Create(new CreateBook("Clean Code", "Martin"));
Console.WriteLine($"POST → {c1}: {b1}");
Console.WriteLine($"GET  → {api.Get(1).code}");
Console.WriteLine($"DELETE→ {api.Delete(1).code}");
