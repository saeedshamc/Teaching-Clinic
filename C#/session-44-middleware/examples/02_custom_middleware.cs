// ============================================================
// جلسه ۴۴ — Custom Middleware
// ============================================================

class RequestContext
{
    public string Method { get; set; } = "";
    public string Path { get; set; } = "";
    public int StatusCode { get; set; } = 200;
    public List<string> Logs { get; } = new();
}

class RequestLoggingMiddleware
{
    public async Task InvokeAsync(RequestContext ctx, Func<Task> next)
    {
        ctx.Logs.Add($"→ {ctx.Method} {ctx.Path}");
        await next();
        ctx.Logs.Add($"← Status {ctx.StatusCode}");
    }
}

class HeaderMiddleware
{
    public async Task InvokeAsync(RequestContext ctx, Func<Task> next)
    {
        ctx.Logs.Add("  Adding X-Powered-By header");
        await next();
    }
}

async Task RunAsync()
{
    var ctx = new RequestContext { Method = "POST", Path = "/api/orders" };
    var logging = new RequestLoggingMiddleware();
    var header = new HeaderMiddleware();

    await logging.InvokeAsync(ctx, async () =>
    {
        await header.InvokeAsync(ctx, async () =>
        {
            ctx.Logs.Add("    [Endpoint] Process order");
            await Task.CompletedTask;
        });
    });

    Console.WriteLine("=== Custom Middleware Chain ===");
    foreach (var log in ctx.Logs)
        Console.WriteLine(log);
}

await RunAsync();
