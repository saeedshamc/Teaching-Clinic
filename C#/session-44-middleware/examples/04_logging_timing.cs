// ============================================================
// جلسه ۴۴ — Logging و Timing Middleware
// ============================================================

class Request
{
    public string Method { get; set; } = "GET";
    public string Path { get; set; } = "/";
    public Dictionary<string, string> ResponseHeaders { get; } = new();
    public int StatusCode { get; set; } = 200;
}

class TimingMiddleware
{
    public async Task InvokeAsync(Request req, Func<Task> next)
    {
        var sw = System.Diagnostics.Stopwatch.StartNew();
        await next();
        sw.Stop();
        req.ResponseHeaders["X-Response-Time"] = $"{sw.ElapsedMilliseconds}ms";
        Console.WriteLine($"  ⏱ {req.Method} {req.Path} — {sw.ElapsedMilliseconds}ms");
    }
}

class CorrelationIdMiddleware
{
    public async Task InvokeAsync(Request req, Func<Task> next, string correlationId)
    {
        Console.WriteLine($"  🔗 CorrelationId: {correlationId}");
        await next();
    }
}

async Task SimulateEndpoint(int delayMs)
{
    await Task.Delay(delayMs);
}

async Task RunRequest(string path, int delay)
{
    var req = new Request { Path = path };
    var timing = new TimingMiddleware();
    var correlation = new CorrelationIdMiddleware();
    var corrId = Guid.NewGuid().ToString()[..8];

    await correlation.InvokeAsync(req, async () =>
    {
        await timing.InvokeAsync(req, async () => await SimulateEndpoint(delay));
    }, corrId);

    Console.WriteLine($"  Headers: X-Response-Time={req.ResponseHeaders["X-Response-Time"]}");
}

Console.WriteLine("=== Timing Middleware ===");
await RunRequest("/api/patients", 50);
await RunRequest("/api/reports", 150);
