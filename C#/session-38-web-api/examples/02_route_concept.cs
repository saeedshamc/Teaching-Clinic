// ============================================================
// جلسه ۳۸ — Web API: Routing
// ============================================================

var router = new SimpleRouter();
var products = new Dictionary<int, string> { [1] = "لپ‌تاپ", [2] = "ماوس" };

router.MapGet("api/products", _ => products);
router.MapGet("api/products/{id}", p =>
{
    int id = int.Parse(p["id"]);
    return products.TryGetValue(id, out var name) ? name : "404 Not Found";
});

Console.WriteLine($"GET /api/products → {router.Dispatch("GET", "/api/products")}");
Console.WriteLine($"GET /api/products/1 → {router.Dispatch("GET", "/api/products/1")}");

var match = router.Match("GET", "/api/products/2");
Console.WriteLine($"Match params: id={match?.Params["id"]}");

public class SimpleRouter
{
    private readonly List<(string Method, string Pattern, Func<Dictionary<string, string>, object> Handler)> _routes = new();

    public void MapGet(string pattern, Func<Dictionary<string, string>, object> handler)
        => _routes.Add(("GET", pattern, handler));

    public object? Dispatch(string method, string path)
    {
        foreach (var (m, pattern, handler) in _routes)
        {
            if (m != method) continue;
            var p = MatchPattern(pattern, path);
            if (p is not null) return handler(p);
        }
        return null;
    }

    public (string Method, string Path, Dictionary<string, string> Params)? Match(string method, string path)
    {
        foreach (var (m, pattern, _) in _routes)
        {
            if (m != method) continue;
            var p = MatchPattern(pattern, path);
            if (p is not null) return (method, path, p);
        }
        return null;
    }

    private static Dictionary<string, string>? MatchPattern(string pattern, string path)
    {
        var pp = pattern.Trim('/').Split('/');
        var pathParts = path.Trim('/').Split('/');
        if (pp.Length != pathParts.Length) return null;
        var result = new Dictionary<string, string>();
        for (int i = 0; i < pp.Length; i++)
        {
            if (pp[i].StartsWith('{'))
                result[pp[i].Trim('{', '}')] = pathParts[i];
            else if (pp[i] != pathParts[i]) return null;
        }
        return result;
    }
}
