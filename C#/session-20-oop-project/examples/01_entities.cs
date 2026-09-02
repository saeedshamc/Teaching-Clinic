// ============================================================
// جلسه ۲۰ — Entity Classes
// فایل: 01_entities.cs
// ============================================================

class Book
{
    public string Isbn { get; }
    public string Title { get; set; }
    public string Author { get; set; }
    public int Year { get; set; }
    public bool IsAvailable { get; private set; } = true;

    public Book(string isbn, string title, string author, int year)
    {
        Isbn = isbn;
        Title = title;
        Author = author;
        Year = year;
    }

    public void MarkBorrowed() => IsAvailable = false;
    public void MarkReturned() => IsAvailable = true;

    public override string ToString() =>
        $"[{Isbn}] «{Title}» — {Author} ({Year}) | {(IsAvailable ? "موجود" : "امانت")}";
}

class Member
{
    public int Id { get; }
    public string Name { get; set; }
    public string Phone { get; set; }
    public DateTime JoinDate { get; }

    public Member(int id, string name, string phone)
    {
        Id = id;
        Name = name;
        Phone = phone;
        JoinDate = DateTime.Now;
    }

    public override string ToString() => $"#{Id} {Name} — {Phone}";
}

class Loan
{
    public Book Book { get; }
    public Member Member { get; }
    public DateTime BorrowDate { get; }
    public DateTime? ReturnDate { get; private set; }
    public bool IsActive => ReturnDate == null;

    public Loan(Book book, Member member, DateTime borrowDate)
    {
        Book = book;
        Member = member;
        BorrowDate = borrowDate;
    }

    public void Complete() => ReturnDate = DateTime.Now;

    public int DaysBorrowed =>
        ((ReturnDate ?? DateTime.Now) - BorrowDate).Days;
}

// نمایش
var book = new Book("978-1", "کلین کد", "رابرت مارتین", 2008);
var member = new Member(1, "علی احمدی", "09121234567");
var loan = new Loan(book, member, DateTime.Now);

book.MarkBorrowed();
Console.WriteLine(book);
Console.WriteLine(member);
Console.WriteLine($"امانت: {loan.Book.Title} → {loan.Member.Name} ({loan.DaysBorrowed} روز)");
