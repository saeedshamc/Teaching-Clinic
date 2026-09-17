// ============================================================
// جلسه ۲۹ — JSON: ذخیره و بارگذاری فایل
// ============================================================

using System.Text.Json;

var todos = new List<TodoItem>
{
    new(1, "یادگیری JSON", true),
    new(2, "تمرین File I/O", false),
    new(3, "پروژه کلینیک", false)
};

await TodoRepository.SaveAsync(todos);
Console.WriteLine("ذخیره شد در todos.json");

var loaded = await TodoRepository.LoadAsync();
Console.WriteLine("\nبارگذاری:");
foreach (var t in loaded)
    Console.WriteLine($"  [{t.Id}] {t.Title} — {(t.IsDone ? "✓" : "○")}");

File.Delete("todos.json");

public record TodoItem(int Id, string Title, bool IsDone);

public static class TodoRepository
{
    private const string FilePath = "todos.json";

    private static readonly JsonSerializerOptions Options = new()
    {
        WriteIndented = true,
        PropertyNamingPolicy = JsonNamingPolicy.CamelCase
    };

    public static async Task SaveAsync(List<TodoItem> todos)
    {
        string json = JsonSerializer.Serialize(todos, Options);
        await File.WriteAllTextAsync(FilePath, json);
    }

    public static async Task<List<TodoItem>> LoadAsync()
    {
        if (!File.Exists(FilePath)) return new List<TodoItem>();
        string json = await File.ReadAllTextAsync(FilePath);
        return JsonSerializer.Deserialize<List<TodoItem>>(json, Options) ?? new();
    }
}
