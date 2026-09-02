// ============================================================
// جلسه ۴۷ — Query Optimization
// ============================================================

class Patient { public int Id { get; set; } public string Name { get; set; } = ""; public string Phone { get; set; } = ""; public string Address { get; set; } = ""; }
record PatientDto(int Id, string Name);

class FakeDbContext
{
    public List<Patient> Patients { get; } = Enumerable.Range(1, 1000)
        .Select(i => new Patient { Id = i, Name = $"Patient-{i}", Phone = $"09{i:D9}", Address = $"Address-{i}" })
        .ToList();
}

var db = new FakeDbContext();

// ❌ Bad: load all columns
var sw1 = System.Diagnostics.Stopwatch.StartNew();
var all = db.Patients.ToList();
sw1.Stop();

// ✅ Good: projection
var sw2 = System.Diagnostics.Stopwatch.StartNew();
var dtos = db.Patients.Select(p => new PatientDto(p.Id, p.Name)).ToList();
sw2.Stop();

// ✅ Good: AsNoTracking simulation (read-only, no change tracking overhead)
var sw3 = System.Diagnostics.Stopwatch.StartNew();
var readOnly = db.Patients.AsEnumerable().Select(p => new PatientDto(p.Id, p.Name)).ToList();
sw3.Stop();

Console.WriteLine("=== Query Optimization ===");
Console.WriteLine($"  Load all entities: {sw1.ElapsedMilliseconds}ms ({all.Count} items)");
Console.WriteLine($"  Project to DTO:    {sw2.ElapsedMilliseconds}ms ({dtos.Count} items)");
Console.WriteLine($"  Read-only DTO:     {sw3.ElapsedMilliseconds}ms");

// Pagination at DB level
var page = db.Patients.OrderBy(p => p.Id).Skip(100).Take(10)
    .Select(p => new PatientDto(p.Id, p.Name)).ToList();
Console.WriteLine($"\n  Page 11 (skip 100, take 10): {page.Count} items");
Console.WriteLine($"  First: {page.First().Name}");
