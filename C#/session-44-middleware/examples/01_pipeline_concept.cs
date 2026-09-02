// ============================================================
// جلسه ۴۴ — Pipeline Concept
// ============================================================

delegate Task NextDelegate();

class HttpContext
{
    public string Method { get; set; } = "GET";
    public string Path { get; set; } = "/";
    public int StatusCode { get; set; } = 200;
    public Dictionary<string, string> Headers { get; } = new();
}

// Middleware type
using Middleware = Func<HttpContext, NextDelegate, Task>;

async Task RunPipeline(HttpContext ctx, List<Middleware> pipeline)
{
    int index = 0;
    async Task Next()
    {
        if (index < pipeline.Count)
        {
            var mw = pipeline[index++];
            await mw(ctx, Next);
        }
        else
        {
            Console.WriteLine($"    [Endpoint] Handle {ctx.Method} {ctx.Path}");
        }
    }
    await Next();
}

var pipeline = new List<Middleware>
{
    async (ctx, next) =>
    {
        Console.WriteLine($"  → MW1: Start {ctx.Path}");
        await next();
        Console.WriteLine($"  ← MW1: Done (status={ctx.StatusCode})");
    },
    async (ctx, next) =>
    {
        Console.WriteLine($"  → MW2: Auth check");
        await next();
    },
    async (ctx, next) =>
    {
        Console.WriteLine($"  → MW3: Logging");
        await next();
    }
};

Console.WriteLine("=== Request Pipeline ===");
await RunPipeline(new HttpContext { Method = "GET", Path = "/api/patients" }, pipeline);
