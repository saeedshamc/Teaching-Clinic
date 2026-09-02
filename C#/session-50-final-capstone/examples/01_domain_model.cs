// ============================================================
// جلسه ۵۰ — Final Capstone: Domain Model
// ============================================================

enum AppointmentStatus { Pending, Confirmed, Cancelled, Completed }
enum UserRole { Admin, Doctor, Reception }

class Patient
{
    public int Id { get; set; }
    public string FullName { get; set; } = "";
    public string NationalId { get; set; } = "";
    public string Phone { get; set; } = "";
    public DateTime RegisteredAt { get; set; } = DateTime.UtcNow;

    public bool IsValid(out List<string> errors)
    {
        errors = new List<string>();
        if (string.IsNullOrWhiteSpace(FullName)) errors.Add("نام الزامی است");
        if (NationalId.Length != 10) errors.Add("کد ملی باید ۱۰ رقم باشد");
        if (Phone.Length < 10) errors.Add("شماره تلفن نامعتبر");
        return errors.Count == 0;
    }
}

class Doctor
{
    public int Id { get; set; }
    public string FullName { get; set; } = "";
    public string Specialty { get; set; } = "";
}

class Appointment
{
    public int Id { get; set; }
    public int PatientId { get; set; }
    public int DoctorId { get; set; }
    public DateTime ScheduledAt { get; set; }
    public AppointmentStatus Status { get; set; } = AppointmentStatus.Pending;
    public Patient? Patient { get; set; }
    public Doctor? Doctor { get; set; }
}

class User
{
    public int Id { get; set; }
    public string Username { get; set; } = "";
    public UserRole Role { get; set; }
}

Console.WriteLine("=== Domain Model ===");
var patient = new Patient { FullName = "علی رضایی", NationalId = "1234567890", Phone = "09121234567" };
Console.WriteLine($"  Patient valid: {patient.IsValid(out var errs)} — {patient.FullName}");
if (errs.Count > 0) foreach (var e in errs) Console.WriteLine($"    ✗ {e}");

var doctor = new Doctor { Id = 1, FullName = "دکتر احمدی", Specialty = "قلب" };
var appt = new Appointment
{
    PatientId = 1, DoctorId = 1,
    ScheduledAt = DateTime.Today.AddDays(1).AddHours(10),
    Patient = patient, Doctor = doctor
};
Console.WriteLine($"  Appointment: {appt.Patient.FullName} → {appt.Doctor.FullName} at {appt.ScheduledAt:yyyy-MM-dd HH:mm}");
