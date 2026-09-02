// ============================================================
// جلسه ۴۴ — Short-Circuit Middleware
// ============================================================

class Request
{
    public string Path { get; set; } = "";
    public Dictionary<string, string> Headers { get; } = new();
    public int StatusCode { get; set; } = 200;
    public string? ResponseBody { get; set; }
}

class ApiKeyMiddleware
{
    private const string ValidKey = "secret-key-123";

    public async Task<bool> InvokeAsync(Request req, Func<Task> next)
    {
        if (!req.Headers.TryGetValue("X-Api-Key", out var key) || key != ValidKey)
        {
            req.StatusCode = 401;
            req.ResponseBody = "Unauthorized: Invalid API Key";
            Console.WriteLine($"  ✗ Short-circuit: 401 for {req.Path}");
            return false; // pipeline stopped
        }
        Console.WriteLine($"  ✓ API Key valid");
        await next();
        return true;
    }
}

async Task Simulate(Request req)
{
    Console.WriteLine($"\n--- Request: {req.Path} ---");
    var mw = new ApiKeyMiddleware();
    await mw.InvokeAsync(req, async () =>
    {
        Console.WriteLine($"    [Endpoint] Processing {req.Path}");
        req.ResponseBody = "OK";
        await Task.CompletedTask;
    });
    Console.WriteLine($"  Response: {req.StatusCode} — {req.ResponseBody}");
}

await Simulate(new Request { Path = "/api/data", Headers = { ["X-Api-Key"] = "wrong" } });
await Simulate(new Request { Path = "/api/data", Headers = { ["X-Api-Key"] = "secret-key-123" } });
