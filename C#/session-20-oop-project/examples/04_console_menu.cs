// ============================================================
// جلسه ۲۰ — منوی کنسول کامل
// فایل: 04_console_menu.cs
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
    public bool IsActive { get; private set; } = true;
    public Loan(Book b, Member m) { Book = b; Member = m; }
    public void Complete() => IsActive = false;
}

class LibraryApp
{
    private readonly Dictionary<string, Book> _books = new();
    private readonly Dictionary<int, Member> _members = new();
    private readonly List<Loan> _loans = new();
    private int _nextMemberId = 1;

    public void Run()
    {
        SeedData();
        while (true)
        {
            Console.WriteLine("\n=== کتابخانه ===");
            Console.WriteLine("1. افزودن کتاب  2. جستجو  3. ثبت عضو");
            Console.WriteLine("4. امانت  5. برگشت  6. گزارش  0. خروج");
            Console.Write("انتخاب: ");
            switch (Console.ReadLine())
            {
                case "1": AddBook(); break;
                case "2": SearchBooks(); break;
                case "3": RegisterMember(); break;
                case "4": BorrowBook(); break;
                case "5": ReturnBook(); break;
                case "6": PrintReport(); break;
                case "0": return;
            }
        }
    }

    void SeedData()
    {
        _books["978-1"] = new Book("978-1", "کلین کد", "رابرت مارتین");
        _books["978-2"] = new Book("978-2", "C# in Depth", "جان اسکیت");
    }

    void AddBook()
    {
        Console.Write("ISBN: "); string? isbn = Console.ReadLine();
        Console.Write("عنوان: "); string? title = Console.ReadLine();
        Console.Write("نویسنده: "); string? author = Console.ReadLine();
        if (!string.IsNullOrEmpty(isbn) && !string.IsNullOrEmpty(title))
        {
            _books[isbn] = new Book(isbn, title, author ?? "");
            Console.WriteLine("✅ ثبت شد");
        }
    }

    void SearchBooks()
    {
        Console.Write("کلمه کلیدی: "); string? kw = Console.ReadLine();
        var results = _books.Values.Where(b =>
            b.Title.Contains(kw ?? "", StringComparison.OrdinalIgnoreCase));
        foreach (var b in results)
            Console.WriteLine($"  [{b.Isbn}] {b.Title} — {(b.IsAvailable ? "موجود" : "امانت")}");
    }

    void RegisterMember()
    {
        Console.Write("نام: "); string? name = Console.ReadLine();
        if (!string.IsNullOrEmpty(name))
        {
            var m = new Member(_nextMemberId++, name);
            _members[m.Id] = m;
            Console.WriteLine($"✅ عضو #{m.Id} ثبت شد");
        }
    }

    void BorrowBook()
    {
        Console.Write("ISBN: "); string? isbn = Console.ReadLine();
        Console.Write("شناسه عضو: "); int.TryParse(Console.ReadLine(), out int mid);
        if (_books.TryGetValue(isbn ?? "", out var book) && _members.TryGetValue(mid, out var member)
            && book.IsAvailable)
        {
            book.IsAvailable = false;
            _loans.Add(new Loan(book, member));
            Console.WriteLine($"✅ «{book.Title}» به {member.Name} امانت داده شد");
        }
        else Console.WriteLine("❌ عملیات ناموفق");
    }

    void ReturnBook()
    {
        Console.Write("ISBN: "); string? isbn = Console.ReadLine();
        var loan = _loans.LastOrDefault(l => l.Book.Isbn == isbn && l.IsActive);
        if (loan != null) { loan.Complete(); loan.Book.IsAvailable = true; Console.WriteLine("✅ برگشت"); }
        else Console.WriteLine("❌ امانت فعالی یافت نشد");
    }

    void PrintReport()
    {
        Console.WriteLine($"کتاب‌ها: {_books.Count} | اعضا: {_members.Count} | امانت فعال: {_loans.Count(l => l.IsActive)}");
        foreach (var b in _books.Values.Where(b => b.IsAvailable))
            Console.WriteLine($"  📗 {b.Title}");
    }
}

new LibraryApp().Run();
