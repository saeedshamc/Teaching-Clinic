// ============================================================
// تمرین ۲ جلسه ۴۶ — Pagination (پاسخ نمونه)
// ============================================================

record Book(int Id, string Title, string Genre);

class PagedResult<T> { public List<T> Items { get; init; } = new(); public int Total { get; init; } public int Page { get; init; } }

PagedResult<Book> GetBooks(List<Book> all, int page, int size, string? genre)
{
    var q = string.IsNullOrEmpty(genre) ? all : all.Where(b => b.Genre == genre).ToList();
    return new PagedResult<Book>
    {
        Items = q.Skip((page - 1) * size).Take(size).ToList(),
        Total = q.Count,
        Page = page
    };
}

var books = Enumerable.Range(1, 25).Select(i => new Book(i, $"Book-{i}", i % 2 == 0 ? "Fiction" : "Tech")).ToList();
var result = GetBooks(books, page: 2, size: 5, genre: "Tech");
Console.WriteLine($"Page {result.Page}: {result.Items.Count} items, total={result.Total}");
foreach (var b in result.Items) Console.WriteLine($"  {b.Title} ({b.Genre})");
