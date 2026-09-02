// ============================================================
// جلسه ۴۳ — Unit of Work
// ============================================================

class Patient { public int Id { get; set; } public string Name { get; set; } = ""; }
class Appointment { public int Id { get; set; } public int PatientId { get; set; } public DateTime Date { get; set; } }

interface IPatientRepository
{
    void Add(Patient p);
    Patient? GetById(int id);
}

interface IAppointmentRepository
{
    void Add(Appointment a);
    IReadOnlyList<Appointment> GetByPatient(int patientId);
}

interface IUnitOfWork
{
    IPatientRepository Patients { get; }
    IAppointmentRepository Appointments { get; }
    int SaveChanges();
}

class InMemoryUnitOfWork : IUnitOfWork
{
    private readonly List<Patient> _patients = new();
    private readonly List<Appointment> _appointments = new();
    private int _patientId = 1, _apptId = 1;
    private int _pendingChanges = 0;

    public IPatientRepository Patients => new PatientRepo(this);
    public IAppointmentRepository Appointments => new AppointmentRepo(this);

    internal void AddPatient(Patient p) { p.Id = _patientId++; _patients.Add(p); _pendingChanges++; }
    internal void AddAppointment(Appointment a) { a.Id = _apptId++; _appointments.Add(a); _pendingChanges++; }
    internal Patient? FindPatient(int id) => _patients.FirstOrDefault(p => p.Id == id);
    internal IReadOnlyList<Appointment> FindAppts(int pid) => _appointments.Where(a => a.PatientId == pid).ToList();

    public int SaveChanges()
    {
        var saved = _pendingChanges;
        _pendingChanges = 0;
        Console.WriteLine($"  💾 SaveChanges: {saved} changes committed");
        return saved;
    }

    class PatientRepo : IPatientRepository
    {
        private readonly InMemoryUnitOfWork _uow;
        public PatientRepo(InMemoryUnitOfWork uow) => _uow = uow;
        public void Add(Patient p) => _uow.AddPatient(p);
        public Patient? GetById(int id) => _uow.FindPatient(id);
    }

    class AppointmentRepo : IAppointmentRepository
    {
        private readonly InMemoryUnitOfWork _uow;
        public AppointmentRepo(InMemoryUnitOfWork uow) => _uow = uow;
        public void Add(Appointment a) => _uow.AddAppointment(a);
        public IReadOnlyList<Appointment> GetByPatient(int pid) => _uow.FindAppts(pid);
    }
}

var uow = new InMemoryUnitOfWork();
var patient = new Patient { Name = "علی" };
uow.Patients.Add(patient);
uow.SaveChanges();

uow.Appointments.Add(new Appointment { PatientId = patient.Id, Date = DateTime.Today.AddDays(1) });
uow.SaveChanges();

Console.WriteLine($"\nAppointments for {patient.Name}:");
foreach (var a in uow.Appointments.GetByPatient(patient.Id))
    Console.WriteLine($"  [{a.Id}] {a.Date:yyyy-MM-dd}");
