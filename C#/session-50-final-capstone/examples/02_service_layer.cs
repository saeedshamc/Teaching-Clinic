// ============================================================
// جلسه ۵۰ — Final Capstone: Service Layer
// ============================================================

enum AppointmentStatus { Pending, Confirmed, Cancelled, Completed }

class Patient { public int Id { get; set; } public string FullName { get; set; } = ""; public string Phone { get; set; } = ""; }
class Doctor { public int Id { get; set; } public string FullName { get; set; } = ""; public string Specialty { get; set; } = ""; }
class Appointment
{
    public int Id { get; set; }
    public int PatientId { get; set; }
    public int DoctorId { get; set; }
    public DateTime ScheduledAt { get; set; }
    public AppointmentStatus Status { get; set; }
}

record BookAppointmentRequest(int PatientId, int DoctorId, DateTime ScheduledAt);
record AppointmentDto(int Id, string PatientName, string DoctorName, DateTime ScheduledAt, string Status);

interface IPatientRepository { Patient? GetById(int id); void Add(Patient p); }
interface IAppointmentRepository
{
    void Add(Appointment a);
    bool HasConflict(int doctorId, DateTime time);
    IReadOnlyList<Appointment> GetByPatient(int patientId);
}

class AppointmentService
{
    private readonly IPatientRepository _patients;
    private readonly IAppointmentRepository _appointments;
    private readonly List<Doctor> _doctors;
    private int _apptId = 1;

    public AppointmentService(IPatientRepository patients, IAppointmentRepository appointments, List<Doctor> doctors)
    {
        _patients = patients; _appointments = appointments; _doctors = doctors;
    }

    public (bool ok, AppointmentDto? result, string? error) Book(BookAppointmentRequest req)
    {
        var patient = _patients.GetById(req.PatientId);
        if (patient is null) return (false, null, "Patient not found");

        var doctor = _doctors.FirstOrDefault(d => d.Id == req.DoctorId);
        if (doctor is null) return (false, null, "Doctor not found");

        if (req.ScheduledAt < DateTime.Now)
            return (false, null, "Cannot book in the past");

        if (_appointments.HasConflict(req.DoctorId, req.ScheduledAt))
            return (false, null, "Doctor not available");

        var appt = new Appointment
        {
            Id = _apptId++, PatientId = req.PatientId, DoctorId = req.DoctorId,
            ScheduledAt = req.ScheduledAt, Status = AppointmentStatus.Confirmed
        };
        _appointments.Add(appt);

        return (true, new AppointmentDto(appt.Id, patient.FullName, doctor.FullName,
            appt.ScheduledAt, appt.Status.ToString()), null);
    }
}

// InMemory repos
var patients = new List<Patient> { new() { Id = 1, FullName = "علی", Phone = "09121234567" } };
var appointments = new List<Appointment>();
var doctors = new List<Doctor> { new() { Id = 1, FullName = "دکتر احمدی", Specialty = "قلب" } };

IPatientRepository patientRepo = new PatientRepo(patients);
IAppointmentRepository apptRepo = new ApptRepo(appointments);

class PatientRepo : IPatientRepository
{
    private readonly List<Patient> _d;
    public PatientRepo(List<Patient> d) => _d = d;
    public Patient? GetById(int id) => _d.FirstOrDefault(p => p.Id == id);
    public void Add(Patient p) => _d.Add(p);
}

class ApptRepo : IAppointmentRepository
{
    private readonly List<Appointment> _d;
    public ApptRepo(List<Appointment> d) => _d = d;
    public void Add(Appointment a) => _d.Add(a);
    public bool HasConflict(int doctorId, DateTime time)
        => _d.Any(a => a.DoctorId == doctorId && a.ScheduledAt == time && a.Status != AppointmentStatus.Cancelled);
    public IReadOnlyList<Appointment> GetByPatient(int pid) => _d.Where(a => a.PatientId == pid).ToList();
}

var svc = new AppointmentService(patientRepo, apptRepo, doctors);
var (ok, result, error) = svc.Book(new BookAppointmentRequest(1, 1, DateTime.Today.AddDays(1).AddHours(10)));

Console.WriteLine("=== Appointment Service ===");
Console.WriteLine(ok
    ? $"  ✓ Booked: [{result!.Id}] {result.PatientName} → {result.DoctorName} at {result.ScheduledAt:yyyy-MM-dd HH:mm}"
    : $"  ✗ {error}");

var (ok2, _, err2) = svc.Book(new BookAppointmentRequest(1, 1, DateTime.Today.AddDays(1).AddHours(10)));
Console.WriteLine(!ok2 ? $"  ✗ Conflict: {err2}" : "");
