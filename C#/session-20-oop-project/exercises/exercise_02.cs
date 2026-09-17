// تمرین ۴–۵: محدودیت امانت و جریمه

class Book { public string Title { get; set; } = ""; public bool IsAvailable { get; set; } = true; }
class Member { public int Id { get; }; public string Name { get; set; } = "";
    public Member(int id, string name) { Id = id; Name = name; } }
class Loan
{
    public Book Book { get; }
    public Member Member { get; }
    public DateTime BorrowDate { get; }
    public bool IsActive { get; private set; } = true;
    public Loan(Book b, Member m, int daysAgo = 0)
    { Book = b; Member = m; BorrowDate = DateTime.Now.AddDays(-daysAgo); }
    public int DaysBorrowed => (DateTime.Now - BorrowDate).Days;
    public void Complete() => IsActive = false;
}

class FineCalculator
{
    public const double DailyFine = 5_000;
    public double Calculate(Loan loan, int maxDays) =>
        loan.DaysBorrowed > maxDays ? (loan.DaysBorrowed - maxDays) * DailyFine : 0;
}

class LibraryWithLimits
{
    private const int MaxBooksPerMember = 3;
    private readonly List<Loan> _loans = new();

    public bool CanBorrow(Member member)
    {
        int active = _loans.Count(l => l.Member.Id == member.Id && l.IsActive);
        return active < MaxBooksPerMember;
    }

    public bool Borrow(Book book, Member member)
    {
        if (!book.IsAvailable || !CanBorrow(member)) return false;
        book.IsAvailable = false;
        _loans.Add(new Loan(book, member));
        return true;
    }
}

var lib = new LibraryWithLimits();
var member = new Member(1, "علی");
var books = Enumerable.Range(1, 4).Select(i => new Book { Title = $"کتاب {i}" }).ToList();

foreach (var b in books.Take(3)) lib.Borrow(b, member);
Console.WriteLine($"امانت 4ام: {lib.Borrow(books[3], member)}");  // false

var fineCalc = new FineCalculator();
var overdueLoan = new Loan(new Book { Title = "کلین کد" }, member, daysAgo: 14);
Console.WriteLine($"جریمه: {fineCalc.Calculate(overdueLoan, 7):N0} تومان");
