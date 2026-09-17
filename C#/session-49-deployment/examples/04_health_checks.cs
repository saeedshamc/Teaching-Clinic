// ============================================================
// جلسه ۴۹ — Health Checks
// ============================================================

enum HealthStatus { Healthy, Degraded, Unhealthy }

record HealthCheckResult(string Name, HealthStatus Status, string? Description = null, double DurationMs = 0);

class HealthCheckService
{
    private readonly Func<bool> _dbCheck;
    private readonly Func<bool> _cacheCheck;

    public HealthCheckService(Func<bool> dbCheck, Func<bool> cacheCheck)
    {
        _dbCheck = dbCheck;
        _cacheCheck = cacheCheck;
    }

    public List<HealthCheckResult> CheckAll()
    {
        var results = new List<HealthCheckResult>();
        results.Add(RunCheck("self", () => true));
        results.Add(RunCheck("database", _dbCheck));
        results.Add(RunCheck("cache", _cacheCheck));
        return results;
    }

    HealthCheckResult RunCheck(string name, Func<bool> check)
    {
        var sw = System.Diagnostics.Stopwatch.StartNew();
        var ok = check();
        sw.Stop();
        return new HealthCheckResult(name, ok ? HealthStatus.Healthy : HealthStatus.Unhealthy,
            ok ? null : $"{name} is down", sw.Elapsed.TotalMilliseconds);
    }

    public HealthStatus OverallStatus(IEnumerable<HealthCheckResult> results)
        => results.Any(r => r.Status == HealthStatus.Unhealthy) ? HealthStatus.Unhealthy
         : results.Any(r => r.Status == HealthStatus.Degraded) ? HealthStatus.Degraded
         : HealthStatus.Healthy;
}

// Simulate scenarios
void RunScenario(string name, bool dbOk, bool cacheOk)
{
    Console.WriteLine($"\n=== Scenario: {name} ===");
    var svc = new HealthCheckService(() => dbOk, () => cacheOk);
    var results = svc.CheckAll();
    foreach (var r in results)
        Console.WriteLine($"  {r.Name,-12} {r.Status,-10} ({r.DurationMs:F1}ms) {r.Description ?? ""}");
    var overall = svc.OverallStatus(results);
    Console.WriteLine($"  GET /health → {(overall == HealthStatus.Healthy ? 200 : 503)} {overall}");
}

RunScenario("All healthy", dbOk: true, cacheOk: true);
RunScenario("DB down", dbOk: false, cacheOk: true);

Console.WriteLine("\n=== ASP.NET Core ===");
Console.WriteLine("  builder.Services.AddHealthChecks().AddDbContextCheck<AppDbContext>();");
Console.WriteLine("  app.MapHealthChecks(\"/health\");");
