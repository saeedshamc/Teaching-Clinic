// ============================================================
// جلسه ۳۵ — Configuration: Environment Override
// ============================================================

using System.Text.Json;

const string baseConfig = """{"ApiUrl":"https://dev.api.com","Timeout":30}""";
const string prodOverride = """{"ApiUrl":"https://prod.api.com"}""";

var loader = new ConfigLoader();
loader.LoadJson(baseConfig);
loader.LoadJson(prodOverride);
loader.SetEnv("ApiUrl", "https://env.api.com");

Console.WriteLine("=== Configuration Priority ===");
Console.WriteLine($"Env Var winner: {loader.Get("ApiUrl")}");
Console.WriteLine($"Timeout: {loader.Get("Timeout")} (unchanged from base)");

public class ConfigLoader
{
    private readonly Dictionary<string, string> _values = new();

    public void LoadJson(string json) => FlattenJson(JsonDocument.Parse(json).RootElement, "");
    public void SetEnv(string key, string value) => _values[key] = value;
    public string Get(string key, string defaultValue = "") =>
        _values.TryGetValue(key, out var v) ? v : defaultValue;

    private void FlattenJson(JsonElement element, string prefix)
    {
        switch (element.ValueKind)
        {
            case JsonValueKind.Object:
                foreach (var prop in element.EnumerateObject())
                    FlattenJson(prop.Value, string.IsNullOrEmpty(prefix) ? prop.Name : $"{prefix}:{prop.Name}");
                break;
            default:
                _values[prefix] = element.ToString();
                break;
        }
    }
}
