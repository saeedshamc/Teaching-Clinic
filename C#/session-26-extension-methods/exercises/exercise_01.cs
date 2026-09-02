// ============================================================
// پاسخ نمونه — تمرین جلسه ۲۶
// ============================================================

public static class StringExtensions
{
    public static bool IsPalindrome(this string text)
    {
        var cleaned = new string(text
            .Where(char.IsLetterOrDigit)
            .Select(char.ToLower)
            .ToArray());
        return cleaned == new string(cleaned.Reverse().ToArray());
    }

    public static string ToPersianDigits(this string input)
    {
        return input
            .Replace('0', '۰').Replace('1', '۱').Replace('2', '۲')
            .Replace('3', '۳').Replace('4', '۴').Replace('5', '۵')
            .Replace('6', '۶').Replace('7', '۷').Replace('8', '۸')
            .Replace('9', '۹');
    }
}

public static class EnumerableExtensions
{
    public static double AverageSafe(this IEnumerable<int> source)
    {
        var list = source.ToList();
        return list.Count == 0 ? 0 : list.Average();
    }

    public static IEnumerable<T> Paginate<T>(this IEnumerable<T> source, int page, int pageSize)
    {
        return source.Skip((page - 1) * pageSize).Take(pageSize);
    }
}

Console.WriteLine("radar".IsPalindrome());           // True
Console.WriteLine("123".ToPersianDigits());          // ۱۲۳
Console.WriteLine(new[] { 10, 20, 30 }.AverageSafe()); // 20

var items = Enumerable.Range(1, 20).Paginate(2, 5);
Console.WriteLine(string.Join(", ", items)); // 6, 7, 8, 9, 10
