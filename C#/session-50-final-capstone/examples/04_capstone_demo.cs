// ============================================================
// جلسه ۵۰ — Final Capstone: Demo کامل
// ============================================================

Console.WriteLine("╔══════════════════════════════════════════════╗");
Console.WriteLine("║   Clinic Management System — Capstone Demo   ║");
Console.WriteLine("╚══════════════════════════════════════════════╝");

// --- Auth ---
var users = new Dictionary<string, (string pass, string role)>
{
    ["reception"] = ("rec123", "Reception"),
    ["admin"] = ("admin123", "Admin")
};

string? Login(string username, string password)
{
    if (users.TryGetValue(username, out var u) && u.pass == password)
    {
        var token = $"jwt-{username}-{Guid.NewGuid():N}"[..24];
        Console.WriteLine($"\n[Auth] ✓ Login: {username} ({u.role}) → token={token}");
        return token;
    }
    Console.WriteLine($"\n[Auth] ✗ 401 Unauthorized");
    return null;
}

// --- Data ---
var patients = new List<(int Id, string Name, string Phone)>();
var appointments = new List<(int Id, int PatientId, string Doctor, DateTime Time)>();
int patientId = 1, apptId = 1;

// --- API Operations ---
void CreatePatient(string name, string phone)
{
    patients.Add((patientId++, name, phone));
    Console.WriteLine($"[API] POST /patients → 201 Created #{patientId - 1}: {name}");
}

void BookAppointment(int pid, string doctor, DateTime time)
{
    if (!patients.Any(p => p.Id == pid))
    {
        Console.WriteLine($"[API] POST /appointments → 404 Patient not found");
        return;
    }
    appointments.Add((apptId++, pid, doctor, time));
    Console.WriteLine($"[API] POST /appointments → 201 #{apptId - 1}: Patient {pid} → {doctor} at {time:yyyy-MM-dd HH:mm}");
}

void ListAppointments(int? patientId = null)
{
    var list = patientId.HasValue
        ? appointments.Where(a => a.PatientId == patientId).ToList()
        : appointments;
    Console.WriteLine($"[API] GET /appointments → 200 ({list.Count} items)");
    foreach (var a in list)
        Console.WriteLine($"       #{a.Id} Patient={a.PatientId} Doctor={a.Doctor} Time={a.Time:yyyy-MM-dd HH:mm}");
}

void HealthCheck()
{
    Console.WriteLine($"[Health] GET /health → 200 Healthy");
    Console.WriteLine($"         patients={patients.Count}, appointments={appointments.Count}");
}

// --- Demo Flow ---
var token = Login("reception", "rec123");
if (token is not null)
{
    CreatePatient("علی رضایی", "09121234567");
    CreatePatient("مریم احمدی", "09351234567");
    BookAppointment(1, "دکتر احمدی", DateTime.Today.AddDays(1).AddHours(10));
    BookAppointment(2, "دکتر محمدی", DateTime.Today.AddDays(2).AddHours(14));
    ListAppointments(patientId: 1);
    HealthCheck();
}

Console.WriteLine("\n╔══════════════════════════════════════════════╗");
Console.WriteLine("║   🎓 تبریک! دوره ۵۰ جلسه C# تکمیل شد       ║");
Console.WriteLine("╚══════════════════════════════════════════════╝");
