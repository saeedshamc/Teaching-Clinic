// ============================================================
// تمرین ۱ جلسه ۴۴ — Logging Middleware (پاسخ نمونه)
// ============================================================

class Ctx { public string Method { get; set; } = ""; public string Path { get; set; } = ""; public int Status { get; set; } = 200; }

async Task LoggingMiddleware(Ctx ctx, Func<Task> next)
{
    Console.WriteLine($"  LOG → {ctx.Method} {ctx.Path}");
    await next();
    Console.WriteLine($"  LOG ← {ctx.Status}");
}

var ctx = new Ctx { Method = "GET", Path = "/api/books" };
await LoggingMiddleware(ctx, async () => { await Task.Delay(10); });
Console.WriteLine("  ✓ Done");
