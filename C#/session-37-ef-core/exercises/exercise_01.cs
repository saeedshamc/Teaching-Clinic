// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۷
// ============================================================

public class Book
{
    public int Id { get; set; }
    public string Title { get; set; } = "";
    public string Author { get; set; } = "";
    public decimal Price { get; set; }
}

public class BookContext
{
    public List<Book> Books { get; } = new();
    private int _id = 1;

    public void Add(Book b) { b.Id = _id++; Books.Add(b); }
}

var ctx = new BookContext();
ctx.Add(new Book { Title = "C# in Depth", Author = "Jon Skeet", Price = 680_000 });
ctx.Add(new Book { Title = "Clean Code", Author = "Martin", Price = 450_000 });
ctx.Add(new Book { Title = "Free Guide", Author = "Community", Price = 0 });

var expensive = ctx.Books.Where(b => b.Price > 500_000).OrderByDescending(b => b.Price);
Console.WriteLine("Books > 500k:");
foreach (var b in expensive)
    Console.WriteLine($"  {b.Title} — {b.Price:N0}");
