// ============================================================
// جلسه ۴۰ — ساخت پروژه وب
// فایل: 01_layered_structure.cs — لایه‌بندی
// ============================================================

// --- Domain Layer ---
namespace Domain;

public class Patient
{
    public int Id { get; set; }
    public string FullName { get; set; } = "";
    public string Phone { get; set; } = "";
}

public interface IPatientRepository
{
    IReadOnlyList<Patient> GetAll();
    void Add(Patient patient);
}

// --- Application Layer ---
namespace Application;

public record PatientDto(int Id, string FullName, string Phone);
public record CreatePatientRequest(string FullName, string Phone);

public interface IPatientService
{
    IReadOnlyList<PatientDto> GetAll();
    PatientDto Create(CreatePatientRequest request);
}

public class PatientService : IPatientService
{
    private readonly Domain.IPatientRepository _repo;
    public PatientService(Domain.IPatientRepository repo) => _repo = repo;

    public IReadOnlyList<PatientDto> GetAll()
        => _repo.GetAll().Select(p => new PatientDto(p.Id, p.FullName, p.Phone)).ToList();

    public PatientDto Create(CreatePatientRequest request)
    {
        var patient = new Domain.Patient { FullName = request.FullName, Phone = request.Phone };
        _repo.Add(patient);
        return new PatientDto(patient.Id, patient.FullName, patient.Phone);
    }
}

// --- Infrastructure Layer ---
namespace Infrastructure;

public class InMemoryPatientRepository : Domain.IPatientRepository
{
    private readonly List<Domain.Patient> _store = new();
    private int _nextId = 1;

    public IReadOnlyList<Domain.Patient> GetAll() => _store.AsReadOnly();

    public void Add(Domain.Patient patient)
    {
        patient.Id = _nextId++;
        _store.Add(patient);
    }
}

// --- Presentation Layer (شبیه Controller) ---
namespace Web;

public class PatientsController
{
    private readonly Application.IPatientService _service;
    public PatientsController(Application.IPatientService service) => _service = service;

    public void GetAll()
    {
        Console.WriteLine("GET /api/patients");
        foreach (var p in _service.GetAll())
            Console.WriteLine($"  [{p.Id}] {p.FullName} — {p.Phone}");
    }

    public void Create(string name, string phone)
    {
        Console.WriteLine($"POST /api/patients — {name}");
        var dto = _service.Create(new Application.CreatePatientRequest(name, phone));
        Console.WriteLine($"  Created: [{dto.Id}] {dto.FullName}");
    }
}

// --- Composition Root ---
var repo = new Infrastructure.InMemoryPatientRepository();
var service = new Application.PatientService(repo);
var controller = new Web.PatientsController(service);

controller.Create("علی رضایی", "09121234567");
controller.Create("مریم احمدی", "09351234567");
controller.GetAll();
