// ============================================================
// تمرین ۲ جلسه ۴۰ — DTO و Configuration (پاسخ نمونه)
// ============================================================

class Product
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public decimal Price { get; set; }
    public string InternalNotes { get; set; } = "";
}

record ProductPublicDto(int Id, string Name, decimal Price);

static ProductPublicDto ToPublicDto(Product p) => new(p.Id, p.Name, p.Price);

class AppConfiguration
{
    private readonly Dictionary<string, string> _v;
    public AppConfiguration(Dictionary<string, string> v) => _v = v;
    public string Get(string key) => _v.GetValueOrDefault(key, "");
    public bool IsDevelopment => Get("Environment") == "Development";
}

var baseCfg = new Dictionary<string, string>
{
    ["Environment"] = "Production",
    ["DbPath"] = "app.db",
    ["MaxItems"] = "100"
};
var devCfg = new Dictionary<string, string> { ["Environment"] = "Development", ["DbPath"] = "app_dev.db" };
var merged = new Dictionary<string, string>(baseCfg);
foreach (var kv in devCfg) merged[kv.Key] = kv.Value;

var config = new AppConfiguration(merged);

var product = new Product { Id = 1, Name = "لپ‌تاپ", Price = 25_000_000, InternalNotes = "تامین‌کننده: X" };
var dto = ToPublicDto(product);

Console.WriteLine($"DTO (بدون InternalNotes): {dto}");
Console.WriteLine($"Config IsDev={config.IsDevelopment}, Db={config.Get("DbPath")}");
