// تمرین ۱–۳: گسترش Repository و Service

class Book
{
    public string Isbn { get; }
    public string Title { get; set; }
    public string Author { get; set; }
    public bool IsAvailable { get; set; } = true;
    public Book(string isbn, string title, string author)
    { Isbn = isbn; Title = title; Author = author; }
}

class Loan
{
    public Book Book { get; }
    public DateTime BorrowDate { get; }
    public bool IsActive { get; private set; } = true;
    public Loan(Book b) { Book = b; BorrowDate = DateTime.Now.AddDays(-10); }
    public void Complete() => IsActive = false;
    public int DaysBorrowed => (DateTime.Now - BorrowDate).Days;
}

class BookRepository
{
    private readonly List<Book> _books = new();
    public void Add(Book b) => _books.Add(b);
    public IEnumerable<Book> SearchByAuthor(string author) =>
        _books.Where(b => b.Author.Contains(author, StringComparison.OrdinalIgnoreCase));
    public IEnumerable<Loan> GetOverdueLoans(List<Loan> loans, int maxDays) =>
        loans.Where(l => l.IsActive && l.DaysBorrowed > maxDays);
}

var repo = new BookRepository();
repo.Add(new Book("1", "کلین کد", "رابرت مارتین"));
repo.Add(new Book("2", "Agile", "رابرت مارتین"));

foreach (var b in repo.SearchByAuthor("martin"))
    Console.WriteLine($"  {b.Title}");

var loans = new List<Loan> { new(repo.SearchByAuthor("martin").First()) };
foreach (var l in repo.GetOverdueLoans(loans, 7))
    Console.WriteLine($"تأخیر: {l.Book.Title} — {l.DaysBorrowed} روز");
