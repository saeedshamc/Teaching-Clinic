// ============================================================
// جلسه ۹ — Null-Coalescing (?? و ??=)
// ============================================================

// --- ?? پایه ---
string? name = null;
string greeting = name ?? "مهمان";
Console.WriteLine($"سلام {greeting}!");

name = "علی";
greeting = name ?? "مهستان";
Console.WriteLine($"سلام {greeting}!");

Console.WriteLine();

// --- ?? با value types ---
int? nullableNum = null;
int safe = nullableNum ?? 0;
Console.WriteLine($"safe = {safe}");

nullableNum = 42;
safe = nullableNum ?? 0;
Console.WriteLine($"safe = {safe}");

Console.WriteLine();

// --- زنجیره ?? ---
string? a = null, b = null, c = "سلام";
string result = a ?? b ?? c ?? "پیش‌فرض";
Console.WriteLine(result); // سلام

Console.WriteLine();

// --- ??= (assign if null) ---
string? config = null;
config ??= "localhost";
Console.WriteLine($"config: {config}");

config ??= "127.0.0.1";  // اجرا نمی‌شود
Console.WriteLine($"config: {config}");

Console.WriteLine();

// --- کاربرد: تنظیمات پیش‌فرض ---
string? theme = null;
string? language = "fa";
int? maxItems = null;

string activeTheme = theme ?? "dark";
string activeLang = language ?? "en";
int itemLimit = maxItems ?? 10;

Console.WriteLine($"Theme: {activeTheme}, Lang: {activeLang}, Max: {itemLimit}");

Console.WriteLine();

// --- ?? با string.Empty ---
string? userInput = "";
string display = string.IsNullOrWhiteSpace(userInput)
    ? "پیش‌فرض"
    : userInput;
Console.WriteLine($"display: {display}");
