// ============================================================
// جلسه ۴۹ — Environment Configuration
// ============================================================

class ProductionConfig
{
    public string Environment { get; init; } = "";
    public string ConnectionString { get; init; } = "";
    public string LogLevel { get; init; } = "";
    public bool EnableSwagger { get; init; }
    public string JwtSecret { get; init; } = "";
}

ProductionConfig LoadConfig(Dictionary<string, string> sources)
{
    return new ProductionConfig
    {
        Environment = sources.GetValueOrDefault("ASPNETCORE_ENVIRONMENT", "Production"),
        ConnectionString = sources.GetValueOrDefault("ConnectionStrings__Default", ""),
        LogLevel = sources.GetValueOrDefault("Logging__Default", "Information"),
        EnableSwagger = sources.GetValueOrDefault("EnableSwagger", "false") == "true",
        JwtSecret = sources.GetValueOrDefault("Jwt__Key", "")
    };
}

// Simulate: appsettings.json + env vars (env wins)
var sources = new Dictionary<string, string>
{
    ["ASPNETCORE_ENVIRONMENT"] = "Production",
    ["ConnectionStrings__Default"] = "Data Source=clinic.db",
    ["Logging__Default"] = "Information",
    ["EnableSwagger"] = "false",
    ["Jwt__Key"] = "dev-secret-NOT-FOR-PROD"
};

// Production env var overrides
sources["ConnectionStrings__Default"] = "Server=prod-db.example.com;Database=Clinic";
sources["Jwt__Key"] = "[FROM KEY VAULT]";

var config = LoadConfig(sources);

Console.WriteLine("=== Production Config ===");
Console.WriteLine($"  Environment: {config.Environment}");
Console.WriteLine($"  ConnectionString: {config.ConnectionString[..40]}...");
Console.WriteLine($"  LogLevel: {config.LogLevel}");
Console.WriteLine($"  Swagger: {config.EnableSwagger}");
Console.WriteLine($"  JwtSecret: {config.JwtSecret[..20]}...");

Console.WriteLine("\n=== Security Checklist ===");
Console.WriteLine($"  ✓ Secrets from env/KeyVault: {config.JwtSecret.StartsWith("[FROM")}");
Console.WriteLine($"  ✓ Swagger disabled: {!config.EnableSwagger}");
Console.WriteLine($"  ✓ Production env: {config.Environment == "Production"}");
