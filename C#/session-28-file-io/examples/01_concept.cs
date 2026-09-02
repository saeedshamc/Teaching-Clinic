// ============================================================
// جلسه ۲۸ — File I/O: خواندن و نوشتن پایه
// ============================================================

string filePath = "demo-notes.txt";

// نوشتن کل متن در فایل (overwrite)
File.WriteAllText(filePath, "سلام دنیا!\nاین خط دوم است.");
Console.WriteLine("فایل نوشته شد.");

// خواندن کل متن
string content = File.ReadAllText(filePath);
Console.WriteLine($"محتوا:\n{content}");

// افزودن به انتهای فایل (append)
File.AppendAllText(filePath, "\nخط سوم — append شده");

// خواندن خط به خط
string[] lines = File.ReadAllLines(filePath);
Console.WriteLine($"\nتعداد خطوط: {lines.Length}");
foreach (var line in lines)
    Console.WriteLine($"  > {line}");

// پاکسازی
File.Delete(filePath);
Console.WriteLine("\nفایل حذف شد.");
