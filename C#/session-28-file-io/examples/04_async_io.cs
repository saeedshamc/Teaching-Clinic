// ============================================================
// جلسه ۲۸ — File I/O: async و StreamReader
// ============================================================

string path = "async-data.txt";

// نوشتن async
await File.WriteAllTextAsync(path, "داده async\nخط ۲\nخط ۳");
Console.WriteLine("نوشتن async انجام شد.");

// خواندن async
string text = await File.ReadAllTextAsync(path);
Console.WriteLine($"خوانده شد: {text.Length} کاراکتر");

// StreamReader — مناسب فایل بزرگ (خط به خط)
using var reader = new StreamReader(path);
int lineNum = 0;
string? line;
while ((line = await reader.ReadLineAsync()) is not null)
{
    lineNum++;
    Console.WriteLine($"  خط {lineNum}: {line}");
}

// exception handling
try
{
    await File.ReadAllTextAsync("فایل-وجود-ندارد.txt");
}
catch (FileNotFoundException)
{
    Console.WriteLine("\nفایل پیدا نشد — exception مدیریت شد.");
}

File.Delete(path);
