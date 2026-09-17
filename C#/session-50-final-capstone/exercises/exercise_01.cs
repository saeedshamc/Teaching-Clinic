// ============================================================
// تمرین ۱ جلسه ۵۰ — Patient Service (پاسخ نمونه)
// ============================================================

class Patient { public int Id { get; set; } public string Name { get; set; } = ""; public string Phone { get; set; } = ""; }

class PatientService
{
    private readonly List<Patient> _store = new();
    private int _id = 1;

    public Patient Register(string name, string phone)
    {
        if (_store.Any(p => p.Phone == phone))
            throw new InvalidOperationException("Phone already registered");
        var p = new Patient { Id = _id++, Name = name, Phone = phone };
        _store.Add(p);
        return p;
    }

    public Patient? GetById(int id) => _store.FirstOrDefault(p => p.Id == id);
    public IReadOnlyList<Patient> GetAll() => _store;
}

var svc = new PatientService();
var p = svc.Register("Ali", "09121234567");
Console.WriteLine($"Registered: [{p.Id}] {p.Name}");
Console.WriteLine($"Total: {svc.GetAll().Count}");
