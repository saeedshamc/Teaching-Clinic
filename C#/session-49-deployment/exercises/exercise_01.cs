// ============================================================
// تمرین ۱ جلسه ۴۹ — Config Loader (پاسخ نمونه)
// ============================================================

(string env, string conn) LoadProductionConfig(Dictionary<string, string> src)
{
    var env = src.GetValueOrDefault("ASPNETCORE_ENVIRONMENT", "Production");
    var conn = src.GetValueOrDefault("ConnectionStrings__Default", "");
    if (string.IsNullOrEmpty(conn))
        throw new InvalidOperationException("Connection string is required");
    return (env, conn);
}

var cfg = new Dictionary<string, string>
{
    ["ASPNETCORE_ENVIRONMENT"] = "Production",
    ["ConnectionStrings__Default"] = "Server=prod;Database=Clinic"
};
var (env, conn) = LoadProductionConfig(cfg);
Console.WriteLine($"Env={env}, Conn={conn[..20]}...");
