// ============================================================
// جلسه ۴۷ — N+1 Problem
// ============================================================

class Patient { public int Id { get; set; } public string Name { get; set; } = ""; }
class Appointment { public int Id { get; set; } public int PatientId { get; set; } public string Date { get; set; } = ""; }

var patients = Enumerable.Range(1, 10).Select(i => new Patient { Id = i, Name = $"P-{i}" }).ToList();
var appointments = patients.SelectMany(p => Enumerable.Range(1, 3)
    .Select(j => new Appointment { Id = p.Id * 10 + j, PatientId = p.Id, Date = $"2025-01-{j:D2}" }))
    .ToList();

int queryCount = 0;
List<Appointment> QueryAppts(int patientId)
{
    queryCount++;
    return appointments.Where(a => a.PatientId == patientId).ToList();
}

// ❌ N+1
queryCount = 0;
var sw1 = System.Diagnostics.Stopwatch.StartNew();
foreach (var p in patients)
{
    var appts = QueryAppts(p.Id);
    _ = appts.Count;
}
sw1.Stop();
Console.WriteLine($"❌ N+1: {queryCount} queries, {sw1.ElapsedMilliseconds}ms");

// ✅ Eager load (1 query)
queryCount = 0;
var sw2 = System.Diagnostics.Stopwatch.StartNew();
queryCount = 1; // single query
var allAppts = appointments.GroupBy(a => a.PatientId).ToDictionary(g => g.Key, g => g.ToList());
foreach (var p in patients)
{
    var appts = allAppts.GetValueOrDefault(p.Id, new List<Appointment>());
    _ = appts.Count;
}
sw2.Stop();
Console.WriteLine($"✅ Include/Join: {queryCount} query, {sw2.ElapsedMilliseconds}ms");
