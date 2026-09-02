// ============================================================
// تمرین ۱ جلسه ۴۰ — Appointment Layered (پاسخ نمونه)
// ============================================================

class Appointment
{
    public int Id { get; set; }
    public string PatientName { get; set; } = "";
    public DateTime Date { get; set; }
    public bool IsConfirmed { get; set; }
}

interface IAppointmentRepository
{
    void Add(Appointment a);
    IReadOnlyList<Appointment> GetAll();
}

class InMemoryAppointmentRepository : IAppointmentRepository
{
    private readonly List<Appointment> _store = new();
    private int _id = 1;
    public void Add(Appointment a) { a.Id = _id++; _store.Add(a); }
    public IReadOnlyList<Appointment> GetAll() => _store.AsReadOnly();
}

record BookRequest(string PatientName, DateTime Date);

interface IAppointmentService
{
    void Book(BookRequest request);
    IReadOnlyList<Appointment> GetUpcoming();
}

class AppointmentService : IAppointmentService
{
    private readonly IAppointmentRepository _repo;
    public AppointmentService(IAppointmentRepository repo) => _repo = repo;

    public void Book(BookRequest request)
    {
        _repo.Add(new Appointment
        {
            PatientName = request.PatientName,
            Date = request.Date,
            IsConfirmed = false
        });
    }

    public IReadOnlyList<Appointment> GetUpcoming()
        => _repo.GetAll().Where(a => a.Date >= DateTime.Today).OrderBy(a => a.Date).ToList();
}

class AppointmentsController
{
    private readonly IAppointmentService _svc;
    public AppointmentsController(IAppointmentService svc) => _svc = svc;

    public void Book(string name, DateTime date)
    {
        _svc.Book(new BookRequest(name, date));
        Console.WriteLine($"  ✓ نوبت {name} ثبت شد");
    }

    public void ListUpcoming()
    {
        Console.WriteLine("  نوبت‌های آینده:");
        foreach (var a in _svc.GetUpcoming())
            Console.WriteLine($"    [{a.Id}] {a.PatientName} — {a.Date:yyyy-MM-dd}");
    }
}

var repo = new InMemoryAppointmentRepository();
var svc = new AppointmentService(repo);
var ctrl = new AppointmentsController(svc);

ctrl.Book("علی", DateTime.Today.AddDays(1));
ctrl.Book("مریم", DateTime.Today.AddDays(3));
ctrl.ListUpcoming();
