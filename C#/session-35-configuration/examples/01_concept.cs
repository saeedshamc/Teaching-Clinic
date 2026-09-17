// ============================================================
// جلسه ۳۵ — Configuration: JSON config ساده
// ============================================================

using System.Text.Json;

// شبیه‌سازی appsettings.json
const string appSettingsJson = """
{
  "AppName": "ClinicApp",
  "MaxRetries": 3,
  "Features": {
    "EnableCache": true,
    "CacheMinutes": 30
  },
  "AllowedHosts": ["localhost", "127.0.0.1"]
}
""";

using var doc = JsonDocument.Parse(appSettingsJson);
var root = doc.RootElement;

// خواندن flat key
Console.WriteLine($"AppName: {root.GetProperty("AppName").GetString()}");
Console.WriteLine($"MaxRetries: {root.GetProperty("MaxRetries").GetInt32()}");

// nested key — معادل config["Features:EnableCache"]
var features = root.GetProperty("Features");
Console.WriteLine($"EnableCache: {features.GetProperty("EnableCache").GetBoolean()}");
Console.WriteLine($"CacheMinutes: {features.GetProperty("CacheMinutes").GetInt32()}");

// array
Console.Write("AllowedHosts: ");
foreach (var host in root.GetProperty("AllowedHosts").EnumerateArray())
    Console.Write($"{host.GetString()} ");

Console.WriteLine("\n\nدر IConfiguration: config[\"Features:EnableCache\"]");
