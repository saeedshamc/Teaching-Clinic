// ============================================================
// جلسه ۲۶ — Extension Methods: مفهوم پایه
// ============================================================

string greeting = "سلام دنیا";
Console.WriteLine(greeting.AddExclamation());  // سلام دنیا!
Console.WriteLine($"تعداد کلمات: {greeting.WordCount()}"); // 2
Console.WriteLine(StringExtensions.AddExclamation("تست")); // تست!

// کلاس static حامل متدهای توسعه — بعد از top-level statements
public static class StringExtensions
{
    public static string AddExclamation(this string text) => text + "!";

    public static int WordCount(this string text)
    {
        if (string.IsNullOrWhiteSpace(text)) return 0;
        return text.Split(' ', StringSplitOptions.RemoveEmptyEntries).Length;
    }
}
