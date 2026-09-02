// ============================================================
// جلسه ۶ — تعریف متد، return و void
// ============================================================

// --- متدهای ساده ---
int Add(int a, int b) => a + b;
bool IsEven(int n) => n % 2 == 0;

int Square(int n)
{
    return n * n;
}

void PrintGreeting(string name)
{
    Console.WriteLine($"سلام {name}! 👋");
}

// --- فراخوانی ---
Console.WriteLine($"2 + 3 = {Add(2, 3)}");
Console.WriteLine($"7 زوج؟ {IsEven(7)}");
Console.WriteLine($"5² = {Square(5)}");
PrintGreeting("علی");

Console.WriteLine();

// --- متد با چند return (early return) ---
string GetGrade(int score)
{
    if (score < 0 || score > 100)
        return "نامعتبر";
    if (score >= 50)
        return "قبول";
    return "مردود";
}

int[] testScores = { 85, 42, 105, 67 };
foreach (int s in testScores)
    Console.WriteLine($"نمره {s}: {GetGrade(s)}");

Console.WriteLine();

// --- متد void با side effect ---
void PrintLine(char c, int length)
{
    Console.WriteLine(new string(c, length));
}
PrintLine('=', 30);
PrintLine('*', 10);
