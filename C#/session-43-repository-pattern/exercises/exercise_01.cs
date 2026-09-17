// ============================================================
// تمرین ۱ جلسه ۴۳ — Book Repository (پاسخ نمونه)
// ============================================================

class Book { public int Id { get; set; } public string Title { get; set; } = ""; public string Author { get; set; } = ""; }

interface IBookRepository
{
    IReadOnlyList<Book> SearchByTitle(string title);
    void Add(Book book);
}

class InMemoryBookRepository : IBookRepository
{
    private readonly List<Book> _store = new();
    private int _id = 1;
    public IReadOnlyList<Book> SearchByTitle(string title)
        => _store.Where(b => b.Title.Contains(title, StringComparison.OrdinalIgnoreCase)).ToList();
    public void Add(Book book) { book.Id = _id++; _store.Add(book); }
}

class BookService
{
    private readonly IBookRepository _repo;
    public BookService(IBookRepository repo) => _repo = repo;
    public void AddBook(string title, string author) => _repo.Add(new Book { Title = title, Author = author });
    public void Search(string q)
    {
        foreach (var b in _repo.SearchByTitle(q))
            Console.WriteLine($"  [{b.Id}] {b.Title} — {b.Author}");
    }
}

var svc = new BookService(new InMemoryBookRepository());
svc.AddBook("Clean Code", "Martin");
svc.AddBook("Clean Architecture", "Martin");
Console.WriteLine("Search 'clean':");
svc.Search("clean");
