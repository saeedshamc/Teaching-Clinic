// ============================================================
// جلسه ۳۷ — EF Core: One-to-Many Relationship
// ============================================================

var ctx = new ClinicContext();
var dr = ctx.AddDoctor("دکتر احمدی", "قلب");
ctx.AddAppointment(dr.Id, "علی", DateTime.Today.AddDays(1));
ctx.AddAppointment(dr.Id, "مریم", DateTime.Today.AddDays(2));
ctx.AddAppointment(dr.Id, "رضا", DateTime.Today.AddDays(3));

var loaded = ctx.GetDoctorWithAppointments(dr.Id)!;
Console.WriteLine($"دکتر: {loaded.Name} ({loaded.Specialty})");
Console.WriteLine($"نوبت‌ها: {loaded.Appointments.Count}");
foreach (var a in loaded.Appointments.OrderBy(x => x.Date))
    Console.WriteLine($"  {a.Date:yyyy-MM-dd} — {a.PatientName}");

public class Doctor
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public string Specialty { get; set; } = "";
    public List<Appointment> Appointments { get; set; } = new();
}

public class Appointment
{
    public int Id { get; set; }
    public DateTime Date { get; set; }
    public string PatientName { get; set; } = "";
    public int DoctorId { get; set; }
    public Doctor? Doctor { get; set; }
}

public class ClinicContext
{
    public List<Doctor> Doctors { get; } = new();
    private int _docId = 1, _apptId = 1;

    public Doctor AddDoctor(string name, string specialty)
    {
        var doc = new Doctor { Id = _docId++, Name = name, Specialty = specialty };
        Doctors.Add(doc);
        return doc;
    }

    public void AddAppointment(int doctorId, string patient, DateTime date)
    {
        var doctor = Doctors.First(d => d.Id == doctorId);
        doctor.Appointments.Add(new Appointment
        {
            Id = _apptId++,
            DoctorId = doctorId,
            Doctor = doctor,
            PatientName = patient,
            Date = date
        });
    }

    public Doctor? GetDoctorWithAppointments(int id) =>
        Doctors.FirstOrDefault(d => d.Id == id);
}
