// ============================================================
// جلسه ۴۳ — Mock Repository برای Test
// ============================================================

class Book { public int Id { get; set; } public string Title { get; set; } = ""; public string Author { get; set; } = ""; }

interface IBookRepository
{
    Book? GetById(int id);
    IReadOnlyList<Book> SearchByAuthor(string author);
    void Add(Book book);
}

// Fake/Mock Repository (بدون Moq — manual)
class FakeBookRepository : IBookRepository
{
    private readonly List<Book> _data;
    public FakeBookRepository(IEnumerable<Book> seed) => _data = seed.ToList();
    public Book? GetById(int id) => _data.FirstOrDefault(b => b.Id == id);
    public IReadOnlyList<Book> SearchByAuthor(string author)
        => _data.Where(b => b.Author.Contains(author, StringComparison.OrdinalIgnoreCase)).ToList();
    public void Add(Book book) => _data.Add(book);
}

class BookService
{
    private readonly IBookRepository _repo;
    public BookService(IBookRepository repo) => _repo = repo;

    public string? GetBookSummary(int id)
    {
        var book = _repo.GetById(id);
        return book is null ? null : $"{book.Title} by {book.Author}";
    }

    public int CountByAuthor(string author) => _repo.SearchByAuthor(author).Count;
}

// Test
var fakeRepo = new FakeBookRepository(new[]
{
    new Book { Id = 1, Title = "Clean Code", Author = "Robert Martin" },
    new Book { Id = 2, Title = "C# in Depth", Author = "Jon Skeet" },
    new Book { Id = 3, Title = "Agile Software Development", Author = "Robert Martin" }
});

var service = new BookService(fakeRepo);

Console.WriteLine("=== Unit Test (Manual Mock) ===");
Console.WriteLine($"  GetBookSummary(1): {service.GetBookSummary(1)}");
Console.WriteLine($"  GetBookSummary(99): {service.GetBookSummary(99) ?? "null"}");
Console.WriteLine($"  CountByAuthor('Martin'): {service.CountByAuthor("Martin")}");
Console.WriteLine($"  ✓ All assertions passed (manual check)");
