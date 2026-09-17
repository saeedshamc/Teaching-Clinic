// ============================================================
// تمرین ۲ جلسه ۴۳ — Unit of Work (پاسخ نمونه)
// ============================================================

class Author { public int Id { get; set; } public string Name { get; set; } = ""; }
class Book { public int Id { get; set; } public string Title { get; set; } = ""; public int AuthorId { get; set; } }

interface IUnitOfWork
{
    void AddAuthor(Author a);
    void AddBook(Book b);
    int SaveChanges();
    Author? GetAuthor(int id);
}

class SimpleUnitOfWork : IUnitOfWork
{
    private readonly List<Author> _authors = new();
    private readonly List<Book> _books = new();
    private int _authorId = 1, _bookId = 1, _pending = 0;

    public void AddAuthor(Author a) { a.Id = _authorId++; _authors.Add(a); _pending++; }
    public void AddBook(Book b) { b.Id = _bookId++; _books.Add(b); _pending++; }
    public Author? GetAuthor(int id) => _authors.FirstOrDefault(a => a.Id == id);
    public int SaveChanges() { var n = _pending; _pending = 0; Console.WriteLine($"  Saved {n} changes"); return n; }
}

var uow = new SimpleUnitOfWork();
var author = new Author { Name = "Robert Martin" };
uow.AddAuthor(author);
uow.AddBook(new Book { Title = "Clean Code", AuthorId = author.Id });
uow.SaveChanges();
Console.WriteLine($"Author: {uow.GetAuthor(author.Id)?.Name}");
