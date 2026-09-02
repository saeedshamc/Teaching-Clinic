// ============================================================
// جلسه ۲۲ — Action و Func
// فایل: 02_action_func.cs
// ============================================================

// Action — بدون بازگشت (void)
Action<string> greet = name => Console.WriteLine($"سلام {name}!");
Action<int, int> printSum = (a, b) => Console.WriteLine($"{a} + {b} = {a + b}");
Action noParam = () => Console.WriteLine("بدون پارامتر");

greet("علی");
printSum(15, 27);
noParam();

// Func — با بازگشت (آخرین type = return)
Func<int, int, int> add = (a, b) => a + b;
Func<string, int> length = s => s.Length;
Func<double, double, double> power = (b, e) => Math.Pow(b, e);
Func<bool> isWeekend = () => DateTime.Now.DayOfWeek is DayOfWeek.Friday or DayOfWeek.Saturday;

Console.WriteLine($"\nadd(10, 20) = {add(10, 20)}");
Console.WriteLine($"length(\"سلام\") = {length("سلام")}");
Console.WriteLine($"2^10 = {power(2, 10)}");
Console.WriteLine($"آخر هفته؟ {isWeekend()}");

// Predicate — Func<T, bool>
Predicate<int> isEven = n => n % 2 == 0;
Predicate<string> isLong = s => s.Length > 5;

List<int> numbers = new() { 1, 2, 3, 4, 5, 6, 7, 8 };
var evens = numbers.FindAll(isEven);
Console.WriteLine($"\nزوج‌ها: {string.Join(", ", evens)}");

List<string> words = new() { "سلام", "دنیا", "برنامه‌نویسی" };
var longWords = words.FindAll(isLong);
Console.WriteLine($"بلند: {string.Join(", ", longWords)}");

// Func در محاسبات
Func<int, int> double_it = x => x * 2;
Func<int, int> addTen = x => x + 10;
Func<int, int> pipeline = x => addTen(double_it(x));
Console.WriteLine($"\npipeline(5) = {pipeline(5)}");  // (5*2)+10 = 20
