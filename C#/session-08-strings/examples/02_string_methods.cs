// ============================================================
// جلسه ۸ — متدهای String
// ============================================================

string text = "  Hello World  ";

// --- Trim ---
Console.WriteLine($"'{text.Trim()}'");
Console.WriteLine($"'{text.TrimStart()}'");
Console.WriteLine($"'{text.TrimEnd()}'");

Console.WriteLine();

// --- Case ---
Console.WriteLine("hello".ToUpper());
Console.WriteLine("HELLO".ToLower());

Console.WriteLine();

// --- Contains, StartsWith, EndsWith ---
string email = "user@example.com";
Console.WriteLine($"Contains @: {email.Contains('@')}");
Console.WriteLine($"StartsWith user: {email.StartsWith("user")}");
Console.WriteLine($"EndsWith .com: {email.EndsWith(".com")}");

Console.WriteLine();

// --- IndexOf ---
Console.WriteLine($"'programming'.IndexOf('m') = {"programming".IndexOf('m')}");
Console.WriteLine($"'programming'.LastIndexOf('m') = {"programming".LastIndexOf('m')}");

Console.WriteLine();

// --- Replace ---
Console.WriteLine("hello world".Replace("world", "C#"));
Console.WriteLine("a-b-c-d".Replace("-", " "));

Console.WriteLine();

// --- Substring ---
string s = "programming";
Console.WriteLine(s.Substring(3));     // "gramming"
Console.WriteLine(s.Substring(3, 4));  // "gram"

Console.WriteLine();

// --- IsNullOrEmpty / IsNullOrWhiteSpace ---
string? input = "   ";
Console.WriteLine($"IsNullOrEmpty: {string.IsNullOrEmpty(input)}");
Console.WriteLine($"IsNullOrWhiteSpace: {string.IsNullOrWhiteSpace(input)}");

Console.WriteLine();

// --- char operations ---
foreach (char c in "C#")
    Console.WriteLine($"  '{c}' IsLetter={char.IsLetter(c)}, IsDigit={char.IsDigit(c)}");

Console.WriteLine();

// --- PadLeft / PadRight ---
Console.WriteLine($"{"42".PadLeft(5, '0')}");  // 00042
