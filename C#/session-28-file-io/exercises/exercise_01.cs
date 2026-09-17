// ============================================================
// پاسخ نمونه — تمرین جلسه ۲۸
// ============================================================

void CopyFileSafe(string source, string dest)
{
    if (!File.Exists(source))
        throw new FileNotFoundException("فایل مبدأ وجود ندارد", source);
    File.Copy(source, dest, overwrite: true);
    Console.WriteLine($"کپی: {source} → {dest}");
}

int CountWords(string filePath)
{
    string text = File.ReadAllText(filePath);
    return text.Split(' ', StringSplitOptions.RemoveEmptyEntries).Length;
}

// Todo file
void SaveTodos(string[] todos) =>
    File.WriteAllLines("todos.txt", todos);

string[] LoadTodos() =>
    File.Exists("todos.txt") ? File.ReadAllLines("todos.txt") : Array.Empty<string>();

// تست
File.WriteAllText("input.txt", "سلام دنیا از C#");
Console.WriteLine($"کلمات: {CountWords("input.txt")}");

SaveTodos(new[] { "یادگیری File I/O", "تمرین async", "پروژه نهایی" });
Console.WriteLine("Todos:");
foreach (var t in LoadTodos()) Console.WriteLine($"  - {t}");

CopyFileSafe("input.txt", "input-copy.txt");

File.Delete("input.txt");
File.Delete("input-copy.txt");
File.Delete("todos.txt");
