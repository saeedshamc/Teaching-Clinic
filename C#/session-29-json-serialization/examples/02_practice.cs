// ============================================================
// جلسه ۲۹ — JSON: JsonSerializerOptions
// ============================================================

using System.Text.Json;
using System.Text.Json.Serialization;

var options = new JsonSerializerOptions
{
    WriteIndented = true,
    PropertyNamingPolicy = JsonNamingPolicy.CamelCase,
    DefaultIgnoreCondition = JsonIgnoreCondition.WhenWritingNull
};

var product = new Product { ProductName = "لپ‌تاپ", Price = 25_000_000, StockCount = 5 };
string json = JsonSerializer.Serialize(product, options);
Console.WriteLine("با camelCase و indent:");
Console.WriteLine(json);

var readOptions = new JsonSerializerOptions { PropertyNameCaseInsensitive = true };
string incoming = """{"productname":"ماوس","price":350000,"stockcount":100}""";
var parsed = JsonSerializer.Deserialize<Product>(incoming, readOptions);
Console.WriteLine($"\nParse: {parsed?.ProductName} — {parsed?.Price:N0} تومان");

public class Product
{
    public string ProductName { get; set; } = "";
    public decimal Price { get; set; }
    public int StockCount { get; set; }
}
