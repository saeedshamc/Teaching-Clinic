// ============================================================
// تمرین ۲ جلسه ۵۰ — Appointment Booking (پاسخ نمونه)
// ============================================================

class Appointment { public int Id { get; set; } public int PatientId { get; set; } public int DoctorId { get; set; } public DateTime Time { get; set; } }

class AppointmentService
{
    private readonly List<Appointment> _store = new();
    private int _id = 1;

    public (bool ok, string msg) Book(int patientId, int doctorId, DateTime time)
    {
        if (time < DateTime.Now) return (false, "Cannot book in past");
        if (_store.Any(a => a.DoctorId == doctorId && a.Time == time))
            return (false, "Doctor not available");
        _store.Add(new Appointment { Id = _id++, PatientId = patientId, DoctorId = doctorId, Time = time });
        return (true, $"Booked appointment #{_id - 1}");
    }

    public IReadOnlyList<Appointment> GetByPatient(int pid)
        => _store.Where(a => a.PatientId == pid).ToList();
}

var svc = new AppointmentService();
Console.WriteLine(svc.Book(1, 1, DateTime.Today.AddDays(1)).msg);
Console.WriteLine(svc.Book(2, 1, DateTime.Today.AddDays(1)).msg); // conflict
Console.WriteLine($"Patient 1 appointments: {svc.GetByPatient(1).Count}");
