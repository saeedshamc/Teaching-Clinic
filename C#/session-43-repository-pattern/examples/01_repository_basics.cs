// ============================================================
// جلسه ۴۳ — Repository Pattern: CRUD پایه
// ============================================================

class Patient
{
    public int Id { get; set; }
    public string FullName { get; set; } = "";
    public string Phone { get; set; } = "";
}

interface IPatientRepository
{
    Patient? GetById(int id);
    IReadOnlyList<Patient> GetAll();
    void Add(Patient patient);
    void Update(Patient patient);
    void Delete(int id);
    IReadOnlyList<Patient> GetByPhone(string phone);
}

class InMemoryPatientRepository : IPatientRepository
{
    private readonly List<Patient> _store = new();
    private int _nextId = 1;

    public Patient? GetById(int id) => _store.FirstOrDefault(p => p.Id == id);

    public IReadOnlyList<Patient> GetAll() => _store.AsReadOnly();

    public void Add(Patient patient)
    {
        patient.Id = _nextId++;
        _store.Add(patient);
        Console.WriteLine($"  + Added: [{patient.Id}] {patient.FullName}");
    }

    public void Update(Patient patient)
    {
        var idx = _store.FindIndex(p => p.Id == patient.Id);
        if (idx >= 0) _store[idx] = patient;
    }

    public void Delete(int id) => _store.RemoveAll(p => p.Id == id);

    public IReadOnlyList<Patient> GetByPhone(string phone)
        => _store.Where(p => p.Phone == phone).ToList();
}

class PatientService
{
    private readonly IPatientRepository _repo;
    public PatientService(IPatientRepository repo) => _repo = repo;

    public void Register(string name, string phone)
    {
        if (_repo.GetByPhone(phone).Count > 0)
        {
            Console.WriteLine($"  ✗ Phone {phone} already exists");
            return;
        }
        _repo.Add(new Patient { FullName = name, Phone = phone });
    }

    public void ListAll()
    {
        foreach (var p in _repo.GetAll())
            Console.WriteLine($"  [{p.Id}] {p.FullName} — {p.Phone}");
    }
}

var svc = new PatientService(new InMemoryPatientRepository());
svc.Register("علی رضایی", "09121234567");
svc.Register("مریم احمدی", "09351234567");
svc.Register("رضا", "09121234567"); // duplicate
Console.WriteLine("\n=== All Patients ===");
svc.ListAll();
