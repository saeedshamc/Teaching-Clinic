// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۵
// ============================================================

using System.Text.Json;

public class AppConfig
{
    public string ApiUrl { get; set; } = "";
    public int MaxRetries { get; set; }
    public string ConnectionString { get; set; } = "";
}

const string json = """
{
  "ApiUrl": "https://api.clinic.local",
  "MaxRetries": 5,
  "ConnectionString": "Server=localhost;Database=Clinic"
}
""";

var config = JsonSerializer.Deserialize<AppConfig>(json)!;
Console.WriteLine($"API: {config.ApiUrl}");
Console.WriteLine($"Retries: {config.MaxRetries}");
Console.WriteLine($"DB: {config.ConnectionString[..30]}...");

// Simulate env override
string envApiUrl = Environment.GetEnvironmentVariable("APP_API_URL") ?? config.ApiUrl;
Console.WriteLine($"\nEffective API (with env): {envApiUrl}");
