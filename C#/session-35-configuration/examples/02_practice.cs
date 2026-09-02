// ============================================================
// جلسه ۳۵ — Configuration: Binding به class
// ============================================================

using System.Text.Json;

const string json = """
{
  "AppName": "Teaching Clinic",
  "MaxConnections": 50,
  "Database": {
    "Host": "db.example.com",
    "Port": 5432,
    "Database": "clinic_db"
  }
}
""";

var settings = JsonSerializer.Deserialize<AppSettings>(json,
    new JsonSerializerOptions { PropertyNameCaseInsensitive = true })!;

Console.WriteLine($"App: {settings.AppName}");
Console.WriteLine($"DB: {settings.Database.Host}:{settings.Database.Port}/{settings.Database.Database}");
Console.WriteLine($"MaxConnections: {settings.MaxConnections}");

string connStr = $"Host={settings.Database.Host};Port={settings.Database.Port};Database={settings.Database.Database}";
Console.WriteLine($"ConnectionString: {connStr}");

public class DatabaseSettings
{
    public string Host { get; set; } = "localhost";
    public int Port { get; set; } = 5432;
    public string Database { get; set; } = "";
}

public class AppSettings
{
    public string AppName { get; set; } = "";
    public DatabaseSettings Database { get; set; } = new();
    public int MaxConnections { get; set; } = 10;
}
