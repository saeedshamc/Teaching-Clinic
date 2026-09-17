// ============================================================
// جلسه ۲۰ — Repository Pattern
// فایل: 02_repositories.cs
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

class Member
{
    public int Id { get; }
    public string Name { get; set; }
    public Member(int id, string name) { Id = id; Name = name; }
}

interface IBookRepository
{
    void Add(Book book);
    Book? FindByIsbn(string isbn);
    IEnumerable<Book> GetAll();
    IEnumerable<Book> SearchByTitle(string keyword);
    bool Remove(string isbn);
}

interface IMemberRepository
{
    void Add(Member member);
    Member? FindById(int id);
    IEnumerable<Member> GetAll();
}

class InMemoryBookRepository : IBookRepository
{
    private readonly Dictionary<string, Book> _books = new();

    public void Add(Book book) => _books[book.Isbn] = book;
    public Book? FindByIsbn(string isbn) => _books.GetValueOrDefault(isbn);
    public IEnumerable<Book> GetAll() => _books.Values;
    public IEnumerable<Book> SearchByTitle(string keyword) =>
        _books.Values.Where(b => b.Title.Contains(keyword, StringComparison.OrdinalIgnoreCase));
    public bool Remove(string isbn) => _books.Remove(isbn);
}

class InMemoryMemberRepository : IMemberRepository
{
    private readonly Dictionary<int, Member> _members = new();
    public void Add(Member member) => _members[member.Id] = member;
    public Member? FindById(int id) => _members.GetValueOrDefault(id);
    public IEnumerable<Member> GetAll() => _members.Values;
}

// استفاده
IBookRepository bookRepo = new InMemoryBookRepository();
bookRepo.Add(new Book("978-1", "کلین کد", "رابرت مارتین"));
bookRepo.Add(new Book("978-2", "طراحی الگوریتم", "کورمن"));

Console.WriteLine("=== همه کتاب‌ها ===");
foreach (var b in bookRepo.GetAll())
    Console.WriteLine($"  {b.Title} — {b.Author}");

Console.WriteLine("\n=== جستجو: کلین ===");
foreach (var b in bookRepo.SearchByTitle("کلین"))
    Console.WriteLine($"  {b.Title}");

IMemberRepository memberRepo = new InMemoryMemberRepository();
memberRepo.Add(new Member(1, "علی"));
Console.WriteLine($"\nعضو: {memberRepo.FindById(1)?.Name}");
