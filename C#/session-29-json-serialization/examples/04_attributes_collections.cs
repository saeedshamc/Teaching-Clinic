// ============================================================
// جلسه ۲۹ — JSON: attributes و collection
// ============================================================

using System.Text.Json;
using System.Text.Json.Serialization;

var options = new JsonSerializerOptions { WriteIndented = true };

var dept = new Department
{
    Name = "توسعه نرم‌افزار",
    Employees = new List<Employee>
    {
        new() { Id = 1, FullName = "علی رضایی", Skills = { "C#", "SQL" } },
        new() { Id = 2, FullName = "سارا احمدی", Skills = { "React", "TypeScript" } }
    }
};

string json = JsonSerializer.Serialize(dept, options);
Console.WriteLine(json);
Console.WriteLine("\n--- PasswordHash در JSON نیست (JsonIgnore) ---");

var restored = JsonSerializer.Deserialize<Department>(json);
Console.WriteLine($"بازگردانی: {restored?.Name} — {restored?.Employees.Count} نفر");

using var doc = JsonDocument.Parse("""{"status":"ok","count":42}""");
Console.WriteLine($"\nstatus={doc.RootElement.GetProperty("status")}");
Console.WriteLine($"count={doc.RootElement.GetProperty("count").GetInt32()}");

public class Employee
{
    public int Id { get; set; }

    [JsonPropertyName("full_name")]
    public string FullName { get; set; } = "";

    [JsonIgnore]
    public string PasswordHash { get; set; } = "secret";

    public List<string> Skills { get; set; } = new();
}

public class Department
{
    public string Name { get; set; } = "";
    public List<Employee> Employees { get; set; } = new();
}
