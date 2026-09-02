// ============================================================
// جلسه ۳۱ — Namespaces: Assembly (مفهومی)
// ============================================================

Console.WriteLine("=== Assembly Info ===");
Console.WriteLine($"Runtime: {typeof(object).Assembly.GetName().Name}");
Console.WriteLine($"Version: {typeof(object).Assembly.GetName().Version}");
Console.WriteLine(new PublicApi().Greet("دنیای Assembly"));
Console.WriteLine("\nساختار: Solution → Projects → Assemblies → Namespaces → Types");

internal class InternalHelper
{
    public static string Format(string s) => s.ToUpper();
}

public class PublicApi
{
    public string Greet(string name) => $"سلام {name}";
}
