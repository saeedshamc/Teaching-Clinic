// ============================================================
// جلسه ۵۰ — Final Capstone: Repository + API
// ============================================================

record PatientDto(int Id, string FullName, string Phone);
record CreatePatientRequest(string FullName, string Phone);
record ApiResponse<T>(int StatusCode, T? Data, string? Error);

class PatientRepository
{
    private readonly List<(int Id, string Name, string Phone)> _store = new();
    private int _id = 1;
    public PatientDto Create(CreatePatientRequest req)
    {
        var p = (_id++, req.FullName, req.Phone);
        _store.Add(p);
        return new PatientDto(p.Id, p.Name, p.Phone);
    }
    public PatientDto? GetById(int id)
    {
        var p = _store.FirstOrDefault(x => x.Id == id);
        return p == default ? null : new PatientDto(p.Id, p.Name, p.Phone);
    }
    public IReadOnlyList<PatientDto> GetAll()
        => _store.Select(p => new PatientDto(p.Id, p.Name, p.Phone)).ToList();
}

class PatientsController
{
    private readonly PatientRepository _repo = new();

    public ApiResponse<PatientDto> Create(CreatePatientRequest req)
    {
        if (string.IsNullOrWhiteSpace(req.FullName))
            return new ApiResponse<PatientDto>(400, null, "FullName required");
        var created = _repo.Create(req);
        Console.WriteLine($"  POST /api/patients → 201 {{ id: {created.Id} }}");
        return new ApiResponse<PatientDto>(201, created, null);
    }

    public ApiResponse<PatientDto> GetById(int id)
    {
        var p = _repo.GetById(id);
        if (p is null) return new ApiResponse<PatientDto>(404, null, "Not Found");
        Console.WriteLine($"  GET /api/patients/{id} → 200");
        return new ApiResponse<PatientDto>(200, p, null);
    }

    public ApiResponse<IReadOnlyList<PatientDto>> GetAll()
    {
        Console.WriteLine($"  GET /api/patients → 200 ({_repo.GetAll().Count} items)");
        return new ApiResponse<IReadOnlyList<PatientDto>>(200, _repo.GetAll(), null);
    }
}

var ctrl = new PatientsController();
ctrl.Create(new CreatePatientRequest("علی رضایی", "09121234567"));
ctrl.Create(new CreatePatientRequest("مریم احمدی", "09351234567"));
ctrl.GetAll();
ctrl.GetById(1);
ctrl.GetById(99);
