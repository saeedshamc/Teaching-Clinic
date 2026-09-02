// ============================================================
// جلسه ۶ — پارامترهای default، named و params
// ============================================================

// --- default parameter ---
void Greet(string name, string prefix = "سلام", string suffix = "!")
{
    Console.WriteLine($"{prefix} {name}{suffix}");
}

Greet("علی");
Greet("مریم", "درود");
Greet("رضا", prefix: "Hello", suffix: " :)");

Console.WriteLine();

// --- named arguments — ترتیب آزاد ---
void CreateUser(string name, int age, string city = "تهران")
{
    Console.WriteLine($"{name}, {age} سال, {city}");
}

CreateUser(age: 25, name: "سارا");
CreateUser(name: "امیر", age: 30, city: "اصفهان");

Console.WriteLine();

// --- params — تعداد متغیر ---
int Sum(params int[] numbers)
{
    int total = 0;
    foreach (int n in numbers)
        total += n;
    return total;
}

Console.WriteLine($"Sum(): {Sum()}");
Console.WriteLine($"Sum(1,2,3): {Sum(1, 2, 3)}");
Console.WriteLine($"Sum(10,20,30,40): {Sum(10, 20, 30, 40)}");

Console.WriteLine();

// --- params با آرایه ---
double Average(params double[] values)
{
    if (values.Length == 0) return 0;
    double sum = 0;
    foreach (double v in values)
        sum += v;
    return sum / values.Length;
}

Console.WriteLine($"میانگین: {Average(85, 92, 78, 96):F1}");

Console.WriteLine();

// --- ترکیب default + params ---
void Log(string level = "INFO", params object[] messages)
{
    Console.Write($"[{level}] ");
    foreach (object msg in messages)
        Console.Write($"{msg} ");
    Console.WriteLine();
}

Log("DEBUG", "شروع", "برنامه", 42);
Log(messages: new object[] { "فقط", "پیام" });
