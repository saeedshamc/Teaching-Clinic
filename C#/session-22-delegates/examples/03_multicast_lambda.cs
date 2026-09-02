// ============================================================
// جلسه ۲۲ — Multicast Delegate و Lambda
// فایل: 03_multicast_lambda.cs
// ============================================================

delegate void NotifyHandler(string message);

static void SendEmail(string msg) => Console.WriteLine($"  📧 Email: {msg}");
static void SendSms(string msg) => Console.WriteLine($"  📱 SMS: {msg}");
static void WriteLog(string msg) => Console.WriteLine($"  📝 Log: {msg}");

// Multicast — چند متد روی یک delegate
NotifyHandler notify = SendEmail;
notify += SendSms;
notify += WriteLog;
notify += msg => Console.WriteLine($"  🔔 Push: {msg}");  // Lambda

Console.WriteLine("=== اعلان کامل ===");
notify("سفارش شما آماده است");

notify -= SendEmail;
Console.WriteLine("\n=== بدون Email ===");
notify("یادآوری تحویل");

// Lambda expressions
Func<int, int> square = x => x * x;
Func<int, int, int> add = (a, b) => a + b;

Func<int, string> describe = n => n switch
{
    < 0 => "منفی",
    0 => "صفر",
    < 10 => "کوچک",
    < 100 => "متوسط",
    _ => "بزرگ"
};

Console.WriteLine($"\nsquare(7) = {square(7)}");
Console.WriteLine($"describe(42) = {describe(42)}");

// Statement lambda
Func<int, int> factorial = n =>
{
    int result = 1;
    for (int i = 2; i <= n; i++) result *= i;
    return result;
};
Console.WriteLine($"5! = {factorial(5)}");

// Delegate array
Func<int, bool>[] filters =
{
    n => n > 0,
    n => n % 2 == 0,
    n => n < 100
};

int[] testNumbers = { -5, 2, 15, 50, 120 };
foreach (int n in testNumbers)
{
    bool passAll = filters.All(f => f(n));
    Console.WriteLine($"  {n}: {(passAll ? "✅" : "❌")}");
}
