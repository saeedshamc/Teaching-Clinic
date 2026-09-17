// تمرین ۴–۵

class PasswordValidator
{
    private readonly List<Func<string, bool>> _rules = new();
    public void AddRule(Func<string, bool> rule) => _rules.Add(rule);
    public bool Validate(string pwd) => _rules.All(r => r(pwd));
}

static void Sort<T>(List<T> list, Func<T, T, int> compare)
{
    list.Sort((a, b) => compare(a, b));
}

var validator = new PasswordValidator();
validator.AddRule(s => s.Length >= 8);
validator.AddRule(s => s.Any(char.IsDigit));
validator.AddRule(s => s.Any(char.IsUpper));
Console.WriteLine($"'Abcdef1': {validator.Validate("Abcdef1")}");
Console.WriteLine($"'abc': {validator.Validate("abc")}");

var items = new List<int> { 5, 2, 8, 1, 9, 3 };
Sort(items, (a, b) => b.CompareTo(a));  // نزولی
Console.WriteLine($"نزولی: {string.Join(", ", items)}");
