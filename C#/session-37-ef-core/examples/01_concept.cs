// ============================================================
// جلسه ۳۷ — EF Core: شبیه‌سازی ORM (InMemory)
// ============================================================

var context = new InMemoryDbContext();
context.Add(new Patient { Name = "علی رضایی", NationalCode = "0012345678" });
context.Add(new Patient { Name = "مریم احمدی", NationalCode = "0087654321" });
context.SaveChanges();

var all = context.Patients.Where(p => p.Name.Contains("علی")).ToList();
Console.WriteLine($"Found: {all.Count} patient(s)");
foreach (var p in all)
    Console.WriteLine($"  [{p.Id}] {p.Name}");

public class Patient
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public string NationalCode { get; set; } = "";
}

public class InMemoryDbContext
{
    public List<Patient> Patients { get; } = new();
    private int _nextId = 1;

    public Patient Add(Patient patient)
    {
        patient.Id = _nextId++;
        Patients.Add(patient);
        return patient;
    }

    public int SaveChanges() { Console.WriteLine("  [EF] SaveChanges()"); return 1; }
}
