// ============================================================
// جلسه ۲۶ — Extension Methods: generic و chaining
// ============================================================

int score = 105;
Console.WriteLine($"نمره محدودشده: {score.Clamp(0, 100)}");

var text = "  HELLO  ".Trim().ToLower().Replace("hello", "extension");
Console.WriteLine(text);

int?[] nullableNumbers = { 1, null, 3, null, 5 };
var onlyValues = nullableNumbers.WhereNotNull();
Console.WriteLine($"میانگین: {onlyValues.AverageOrZero()}");

public static class NumberExtensions
{
    public static int Clamp(this int value, int min, int max)
    {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}

public static class EnumerableExtensions
{
    public static double AverageOrZero(this IEnumerable<int> source)
    {
        var list = source.ToList();
        return list.Count == 0 ? 0 : list.Average();
    }

    public static IEnumerable<T> WhereNotNull<T>(this IEnumerable<T?> source) where T : class
    {
        foreach (var item in source)
            if (item is not null) yield return item;
    }
}
