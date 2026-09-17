// ============================================================
// تمرین ۲ جلسه ۴۴ — Rate Limit Middleware (پاسخ نمونه)
// ============================================================

class Req
{
    public Dictionary<string, string> Headers { get; } = new();
    public int Status { get; set; } = 200;
    public string Body { get; set; } = "OK";
}

async Task<bool> ClientIdMiddleware(Req req, Func<Task> next)
{
    if (!req.Headers.ContainsKey("X-Client-Id"))
    {
        req.Status = 429;
        req.Body = "Too Many Requests — Client ID required";
        Console.WriteLine($"  ✗ 429 — missing X-Client-Id");
        return false;
    }
    await next();
    return true;
}

async Task Test(bool withHeader)
{
    var req = new Req();
    if (withHeader) req.Headers["X-Client-Id"] = "client-1";
    await ClientIdMiddleware(req, async () => await Task.CompletedTask);
    Console.WriteLine($"  Status: {req.Status} — {req.Body}");
}

await Test(false);
await Test(true);
