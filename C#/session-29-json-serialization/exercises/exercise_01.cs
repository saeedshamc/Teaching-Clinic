// ============================================================
// پاسخ نمونه — تمرین جلسه ۲۹
// ============================================================

using System.Text.Json;
using System.Text.Json.Serialization;

public record Book(string Title, string Author, int Year, decimal Price);

public record ApiResponse<T>(bool Success, T? Data, string? ErrorMessage);

public class BookDetail
{
    [JsonPropertyName("isbn")]
    public string ISBN { get; set; } = "";
    public string Title { get; set; } = "";
}

var options = new JsonSerializerOptions
{
    WriteIndented = true,
    PropertyNamingPolicy = JsonNamingPolicy.CamelCase
};

var books = new List<Book>
{
    new("Clean Code", "Robert Martin", 2008, 450_000),
    new("C# in Depth", "Jon Skeet", 2019, 680_000)
};

string json = JsonSerializer.Serialize(books, options);
await File.WriteAllTextAsync("library.json", json);

var loaded = JsonSerializer.Deserialize<List<Book>>(
    await File.ReadAllTextAsync("library.json"), options)!;

Console.WriteLine($"بارگذاری {loaded.Count} کتاب:");
loaded.ForEach(b => Console.WriteLine($"  - {b.Title} ({b.Year})"));

var response = new ApiResponse<List<Book>>(true, loaded, null);
Console.WriteLine($"\nAPI: {JsonSerializer.Serialize(response, options)[..60]}...");

try
{
    JsonSerializer.Deserialize<Book>("{ invalid json }");
}
catch (JsonException ex)
{
    Console.WriteLine($"\nJSON نامعتبر: {ex.Message[..50]}...");
}

File.Delete("library.json");
