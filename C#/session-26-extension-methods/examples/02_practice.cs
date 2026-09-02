// ============================================================
// جلسه ۲۶ — Extension Methods: string و collection
// ============================================================

Console.WriteLine("csharp".Reverse());
Console.WriteLine("برنامه‌نویسی عالی".Truncate(8));
var cities = new List<string> { "تهران", "اصفهان", "شیراز" };
cities.PrintAll("شهر: ");

public static class StringExtensions
{
    public static string Reverse(this string text)
    {
        char[] chars = text.ToCharArray();
        Array.Reverse(chars);
        return new string(chars);
    }

    public static string Truncate(this string text, int maxLength)
    {
        if (text.Length <= maxLength) return text;
        return text[..maxLength] + "...";
    }
}

public static class ListExtensions
{
    public static void PrintAll<T>(this IList<T> list, string prefix = "")
    {
        foreach (var item in list)
            Console.WriteLine($"{prefix}{item}");
    }
}
