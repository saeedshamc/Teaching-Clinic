// ============================================================
// جلسه ۲۰ — Library Service
// فایل: 03_library_service.cs
// ============================================================

class Book
{
    public string Isbn { get; }
    public string Title { get; set; }
    public string Author { get; set; }
    public bool IsAvailable { get; set; } = true;
    public Book(string isbn, string title, string author)
    { Isbn = isbn; Title = title; Author = author; }
}

class Member { public int Id { get; }; public string Name { get; set; } = "";
    public Member(int id, string name) { Id = id; Name = name; } }

class Loan
{
    public Book Book { get; }
    public Member Member { get; }
    public DateTime BorrowDate { get; }
    public bool IsActive { get; private set; } = true;
    public Loan(Book book, Member member) { Book = book; Member = member; BorrowDate = DateTime.Now; }
    public void Complete() => IsActive = false;
}

interface IBookRepository
{
    void Add(Book book);
    Book? FindByIsbn(string isbn);
    IEnumerable<Book> GetAll();
}

interface IMemberRepository
{
    void Add(Member member);
    Member? FindById(int id);
}

class InMemoryBookRepository : IBookRepository
{
    private readonly Dictionary<string, Book> _data = new();
    public void Add(Book b) => _data[b.Isbn] = b;
    public Book? FindByIsbn(string isbn) => _data.GetValueOrDefault(isbn);
    public IEnumerable<Book> GetAll() => _data.Values;
}

class InMemoryMemberRepository : IMemberRepository
{
    private readonly Dictionary<int, Member> _data = new();
    public void Add(Member m) => _data[m.Id] = m;
    public Member? FindById(int id) => _data.GetValueOrDefault(id);
}

class LibraryService
{
    private readonly IBookRepository _books;
    private readonly IMemberRepository _members;
    private readonly List<Loan> _loans = new();

    public LibraryService(IBookRepository books, IMemberRepository members)
    {
        _books = books;
        _members = members;
    }

    public void AddBook(string isbn, string title, string author) =>
        _books.Add(new Book(isbn, title, author));

    public void RegisterMember(int id, string name) =>
        _members.Add(new Member(id, name));

    public bool BorrowBook(string isbn, int memberId)
    {
        var book = _books.FindByIsbn(isbn);
        var member = _members.FindById(memberId);
        if (book == null || member == null || !book.IsAvailable) return false;
        book.IsAvailable = false;
        _loans.Add(new Loan(book, member));
        return true;
    }

    public bool ReturnBook(string isbn)
    {
        var loan = _loans.LastOrDefault(l => l.Book.Isbn == isbn && l.IsActive);
        if (loan == null) return false;
        loan.Complete();
        loan.Book.IsAvailable = true;
        return true;
    }

    public void PrintAvailableBooks()
    {
        Console.WriteLine("=== کتاب‌های موجود ===");
        foreach (var b in _books.GetAll().Where(b => b.IsAvailable))
            Console.WriteLine($"  [{b.Isbn}] {b.Title}");
    }

    public void PrintActiveLoans()
    {
        Console.WriteLine("=== امانت‌های فعال ===");
        foreach (var l in _loans.Where(l => l.IsActive))
            Console.WriteLine($"  {l.Book.Title} → {l.Member.Name}");
    }
}

var service = new LibraryService(new InMemoryBookRepository(), new InMemoryMemberRepository());
service.AddBook("978-1", "کلین کد", "رابرت مارتین");
service.AddBook("978-2", "C# in Depth", "جان اسکیت");
service.RegisterMember(1, "علی");
service.RegisterMember(2, "مریم");

service.BorrowBook("978-1", 1);
service.PrintAvailableBooks();
service.PrintActiveLoans();
service.ReturnBook("978-1");
Console.WriteLine("\nبعد از برگشت:");
service.PrintAvailableBooks();
