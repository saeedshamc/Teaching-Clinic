// ============================================================
// جلسه ۲۲ — Delegate: مبانی
// فایل: 01_delegate_basics.cs
// ============================================================

// تعریف delegate — امضای متد
delegate int MathOperation(int a, int b);
delegate void LogHandler(string message);

static int Add(int a, int b) => a + b;
static int Subtract(int a, int b) => a - b;
static int Multiply(int a, int b) => a * b;

static void ConsoleLog(string msg) => Console.WriteLine($"[LOG] {msg}");
static void TimestampLog(string msg) => Console.WriteLine($"[{DateTime.Now:HH:mm:ss}] {msg}");

// Delegate به‌عنوان پارامتر — Callback
static void ProcessData(string data, LogHandler logger)
{
    logger($"شروع پردازش: {data}");
    logger($"پردازش «{data}» تکمیل شد");
}

// استفاده
MathOperation operation = Add;
Console.WriteLine($"5 + 3 = {operation(5, 3)}");

operation = Multiply;
Console.WriteLine($"5 * 3 = {operation(5, 3)}");

// تغییر delegate در runtime
MathOperation calc = Add;
Console.WriteLine($"calc(10, 4) = {calc(10, 4)}");
calc = Subtract;
Console.WriteLine($"calc(10, 4) = {calc(10, 4)}");

ProcessData("فایل.csv", ConsoleLog);
ProcessData("گزارش.pdf", TimestampLog);
