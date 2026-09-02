// ============================================================
// جلسه 38 — Web API
// فایل: 02_route_concept.cs
// ============================================================
var routes = new Dictionary<string, string>
{
    ["/users"] = "GET users",
    ["/users/{id}"] = "GET user by id"
};
foreach (var r in routes) Console.WriteLine($"{r.Key} => {r.Value}");
