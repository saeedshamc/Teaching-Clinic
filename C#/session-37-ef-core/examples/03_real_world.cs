// ============================================================
// جلسه ۳۷ — EF Core: Repository Pattern
// ============================================================

var service = new PatientService(new EfPatientRepository());
await service.RegisterAsync("علی", "1234567890");
await service.RegisterAsync("زهرا", "0987654321");
Console.WriteLine("\n=== Patients ===");
await service.ListAsync();

public record PatientDto(int Id, string Name, string NationalCode);

public interface IPatientRepository
{
    Task<IReadOnlyList<PatientDto>> GetAllAsync();
    Task<PatientDto> CreateAsync(string name, string nationalCode);
}

public class EfPatientRepository : IPatientRepository
{
    private readonly List<PatientDto> _data = new();
    private int _nextId = 1;

    public Task<IReadOnlyList<PatientDto>> GetAllAsync() =>
        Task.FromResult<IReadOnlyList<PatientDto>>(_data.AsReadOnly());

    public Task<PatientDto> CreateAsync(string name, string nationalCode)
    {
        var patient = new PatientDto(_nextId++, name, nationalCode);
        _data.Add(patient);
        Console.WriteLine($"  [EF] INSERT Patient Id={patient.Id}");
        return Task.FromResult(patient);
    }
}

public class PatientService
{
    private readonly IPatientRepository _repo;
    public PatientService(IPatientRepository repo) => _repo = repo;

    public async Task RegisterAsync(string name, string code)
    {
        var patient = await _repo.CreateAsync(name, code);
        Console.WriteLine($"Registered: {patient.Name}");
    }

    public async Task ListAsync()
    {
        foreach (var p in await _repo.GetAllAsync())
            Console.WriteLine($"  [{p.Id}] {p.Name} — {p.NationalCode}");
    }
}
