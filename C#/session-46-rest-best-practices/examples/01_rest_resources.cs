// ============================================================
// جلسه ۴۶ — REST Resource Design
// ============================================================

record PatientDto(int Id, string FullName, string Phone);
record CreatePatientRequest(string FullName, string Phone);

class PatientStore
{
    private readonly List<PatientDto> _data = new();
    private int _id = 1;
    public PatientDto Create(CreatePatientRequest req)
    {
        var p = new PatientDto(_id++, req.FullName, req.Phone);
        _data.Add(p);
        return p;
    }
    public PatientDto? GetById(int id) => _data.FirstOrDefault(p => p.Id == id);
    public IReadOnlyList<PatientDto> GetAll() => _data;
    public bool Delete(int id) => _data.RemoveAll(p => p.Id == id) > 0;
}

class PatientsController
{
    private readonly PatientStore _store = new();

    public (int status, object? body) GetAll()
    {
        Console.WriteLine("  GET /api/patients → 200");
        return (200, _store.GetAll());
    }

    public (int status, object? body) GetById(int id)
    {
        var p = _store.GetById(id);
        if (p is null) { Console.WriteLine($"  GET /api/patients/{id} → 404"); return (404, new { error = "Not Found" }); }
        Console.WriteLine($"  GET /api/patients/{id} → 200");
        return (200, p);
    }

    public (int status, object? body, string? location) Create(CreatePatientRequest req)
    {
        var p = _store.Create(req);
        Console.WriteLine($"  POST /api/patients → 201 Location: /api/patients/{p.Id}");
        return (201, p, $"/api/patients/{p.Id}");
    }

    public (int status, object? body) Delete(int id)
    {
        if (!_store.Delete(id)) { Console.WriteLine($"  DELETE /api/patients/{id} → 404"); return (404, null); }
        Console.WriteLine($"  DELETE /api/patients/{id} → 204");
        return (204, null);
    }
}

var ctrl = new PatientsController();
ctrl.Create(new CreatePatientRequest("علی", "09121234567"));
ctrl.GetAll();
ctrl.GetById(1);
ctrl.GetById(99);
ctrl.Delete(1);
