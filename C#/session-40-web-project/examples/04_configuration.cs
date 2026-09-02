// ============================================================
// جلسه ۴۰ — ساخت پروژه وب
// فایل: 04_configuration.cs — Configuration
// ============================================================

// شبیه‌سازی IConfiguration
class AppConfiguration
{
    private readonly Dictionary<string, string> _values;

    public AppConfiguration(Dictionary<string, string> values) => _values = values;

    public string? this[string key] => _values.GetValueOrDefault(key);
    public string GetConnectionString(string name) => _values[$"ConnectionStrings:{name}"] ?? "";
    public bool IsDevelopment => _values.GetValueOrDefault("Environment") == "Development";
}

// appsettings.json
var baseSettings = new Dictionary<string, string>
{
    ["ConnectionStrings:Default"] = "Data Source=clinic.db",
    ["Logging:Default"] = "Information",
    ["AppName"] = "ClinicApp",
    ["Environment"] = "Production"
};

// appsettings.Development.json (override)
var devOverrides = new Dictionary<string, string>
{
    ["ConnectionStrings:Default"] = "Data Source=clinic_dev.db",
    ["Logging:Default"] = "Debug",
    ["Environment"] = "Development"
};

var merged = new Dictionary<string, string>(baseSettings);
foreach (var kv in devOverrides)
    merged[kv.Key] = kv.Value;

var config = new AppConfiguration(merged);

Console.WriteLine("=== Configuration ===");
Console.WriteLine($"  AppName: {config["AppName"]}");
Console.WriteLine($"  Environment: {config["Environment"]} (IsDev: {config.IsDevelopment})");
Console.WriteLine($"  ConnectionString: {config.GetConnectionString("Default")}");
Console.WriteLine($"  LogLevel: {config["Logging:Default"]}");

Console.WriteLine("\n=== Environment Variables (اولویت بالاتر) ===");
// در ASP.NET Core: Environment Variables > appsettings.{Env}.json > appsettings.json
string envConn = Environment.GetEnvironmentVariable("ConnectionStrings__Default")
                 ?? config.GetConnectionString("Default");
Console.WriteLine($"  Effective ConnectionString: {envConn}");

Console.WriteLine("\n=== Options Pattern ===");
record AppSettings(string AppName, string DefaultConnection);
var settings = new AppSettings(config["AppName"]!, config.GetConnectionString("Default"));
Console.WriteLine($"  Options: {settings}");
