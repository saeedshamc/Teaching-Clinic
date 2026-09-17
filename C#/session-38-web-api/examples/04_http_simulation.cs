// ============================================================
// جلسه ۳۸ — Web API: شبیه‌سازی HTTP Request/Response
// ============================================================

var api = new ProductApi();
var requests = new[]
{
    new HttpRequest("GET", "/api/products", null),
    new HttpRequest("GET", "/api/products/1", null),
    new HttpRequest("GET", "/api/products/99", null),
    new HttpRequest("DELETE", "/api/products/2", null),
};

Console.WriteLine("=== HTTP Simulation ===");
foreach (var req in requests)
{
    var res = api.Handle(req);
    Console.WriteLine($"{req.Method} {req.Path} → {res.StatusCode}");
}

public record HttpRequest(string Method, string Path, string? Body);
public record HttpResponse(int StatusCode, string Body);

public class ProductApi
{
    private readonly List<(int Id, string Name, decimal Price)> _products = new()
    {
        (1, "لپ‌تاپ", 25_000_000), (2, "ماوس", 350_000)
    };

    public HttpResponse Handle(HttpRequest request) => request.Method switch
    {
        "GET" when request.Path == "/api/products" =>
            new(200, System.Text.Json.JsonSerializer.Serialize(_products)),
        "GET" when request.Path.StartsWith("/api/products/") =>
            HandleGetById(int.Parse(request.Path.Split('/').Last())),
        "DELETE" when request.Path.StartsWith("/api/products/") =>
            HandleDelete(int.Parse(request.Path.Split('/').Last())),
        _ => new(404, """{"error":"Not Found"}""")
    };

    private HttpResponse HandleGetById(int id)
    {
        var product = _products.FirstOrDefault(p => p.Id == id);
        return product == default
            ? new(404, """{"error":"Not found"}""")
            : new(200, System.Text.Json.JsonSerializer.Serialize(product));
    }

    private HttpResponse HandleDelete(int id) =>
        _products.RemoveAll(p => p.Id == id) > 0 ? new(204, "") : new(404, """{"error":"Not found"}""");
}
