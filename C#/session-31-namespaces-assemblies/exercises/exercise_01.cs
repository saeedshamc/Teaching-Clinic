// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۱
// ============================================================

namespace Exercise.Models;
public record Book(string Title, int Year);

namespace Exercise.Helpers;
public static class StringHelper
{
    public static string Reverse(string s)
    {
        char[] c = s.ToCharArray();
        Array.Reverse(c);
        return new string(c);
    }
}

namespace Exercise.Services;
using Exercise.Models;
using Exercise.Helpers;

public class BookService
{
    private readonly List<Book> _books = new();
    public void Add(Book b) => _books.Add(b);
    public string ListReversedTitles() =>
        string.Join(", ", _books.Select(b => StringHelper.Reverse(b.Title)));
}

using Exercise.Models;
using Exercise.Services;

var svc = new BookService();
svc.Add(new Book("CSharp", 2024));
svc.Add(new Book("DotNet", 2025));
Console.WriteLine(svc.ListReversedTitles());
