// ============================================================
// جلسه ۳۱ — Namespaces: مفهوم پایه
// ============================================================

using ClinicApp.Models;
using ClinicApp.Services;

var service = new PatientService();
service.Register(new Patient { Id = 1, Name = "علی" });
Console.WriteLine($"بیمار: {service.FindById(1)?.Name}");

namespace ClinicApp.Models;

public class Patient
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
}

namespace ClinicApp.Services;

using ClinicApp.Models;

public class PatientService
{
    private readonly List<Patient> _patients = new();

    public void Register(Patient patient) => _patients.Add(patient);

    public Patient? FindById(int id) =>
        _patients.FirstOrDefault(p => p.Id == id);
}
