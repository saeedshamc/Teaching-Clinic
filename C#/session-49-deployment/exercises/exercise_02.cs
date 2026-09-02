// ============================================================
// تمرین ۲ جلسه ۴۹ — Health Checks (پاسخ نمونه)
// ============================================================

enum Status { Healthy, Unhealthy }

record Check(string Name, Status Status);

List<Check> RunChecks(bool dbOk, double diskFreeGb)
{
    var checks = new List<Check>
    {
        new("database", dbOk ? Status.Healthy : Status.Unhealthy),
        new("diskSpace", diskFreeGb > 1 ? Status.Healthy : Status.Unhealthy)
    };
    return checks;
}

foreach (var c in RunChecks(dbOk: true, diskFreeGb: 5))
    Console.WriteLine($"  {c.Name}: {c.Status}");
Console.WriteLine("---");
foreach (var c in RunChecks(dbOk: false, diskFreeGb: 0.5))
    Console.WriteLine($"  {c.Name}: {c.Status}");
