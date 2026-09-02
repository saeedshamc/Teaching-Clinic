# جلسه ۴۰: ساخت پروژه وب (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- ساخت پروژه ASP.NET Core با ساختار لایه‌ای (Layered Architecture)
- تفکیک Domain، Application، Infrastructure و Presentation
- پیکربندی `appsettings.json` و Environment
- اتصال EF Core به پروژه وب
- استفاده از DI Container برای ثبت سرویس‌ها
- آماده‌سازی codebase برای الگوهای طراحی

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور MVC و اهداف پروژه |
| 10–25 | Solution Structure و پروژه‌های جدا |
| 25–40 | Domain Layer — Entities و Interfaces |
| 40–55 | Application Layer — Services و DTOs |
| 55–70 | Infrastructure — DbContext و Repository |
| 70–85 | Presentation — Controllers و Program.cs |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. چرا لایه‌بندی؟

| بدون لایه | با لایه |
|-----------|---------|
| همه چیز در Controller | مسئولیت جدا |
| تست سخت | Unit Test آسان |
| وابستگی به DB | Mock و جایگزینی |

```
Presentation → Application → Domain ← Infrastructure
     (Web)        (Services)   (Entities)    (EF Core)
```

---

## ۲. ساخت Solution

```powershell
dotnet new sln -n ClinicApp
dotnet new classlib -n ClinicApp.Domain
dotnet new classlib -n ClinicApp.Application
dotnet new classlib -n ClinicApp.Infrastructure
dotnet new webapi -n ClinicApp.Web

dotnet sln add ClinicApp.Domain ClinicApp.Application ClinicApp.Infrastructure ClinicApp.Web
```

### وابستگی‌ها (Reference)

```
Web → Application, Infrastructure
Application → Domain
Infrastructure → Application, Domain
```

**قانون:** Domain به هیچ لایه‌ای وابسته نیست.

---

## ۳. Domain Layer

```csharp
// ClinicApp.Domain/Entities/Patient.cs
namespace ClinicApp.Domain.Entities;

public class Patient
{
    public int Id { get; set; }
    public string FullName { get; set; } = "";
    public string Phone { get; set; } = "";
    public DateTime RegisteredAt { get; set; } = DateTime.UtcNow;
}
```

```csharp
// ClinicApp.Domain/Interfaces/IPatientRepository.cs
public interface IPatientRepository
{
    Task<IReadOnlyList<Patient>> GetAllAsync();
    Task<Patient?> GetByIdAsync(int id);
    Task AddAsync(Patient patient);
}
```

---

## ۴. Application Layer

```csharp
// DTOs/PatientDto.cs
public record PatientDto(int Id, string FullName, string Phone);

// DTOs/CreatePatientRequest.cs
public record CreatePatientRequest(string FullName, string Phone);

// Services/IPatientService.cs
public interface IPatientService
{
    Task<IReadOnlyList<PatientDto>> GetAllAsync();
    Task<PatientDto?> GetByIdAsync(int id);
    Task<PatientDto> CreateAsync(CreatePatientRequest request);
}
```

```csharp
// Services/PatientService.cs
public class PatientService : IPatientService
{
    private readonly IPatientRepository _repo;

    public PatientService(IPatientRepository repo) => _repo = repo;

    public async Task<PatientDto> CreateAsync(CreatePatientRequest request)
    {
        var patient = new Patient
        {
            FullName = request.FullName,
            Phone = request.Phone
        };
        await _repo.AddAsync(patient);
        return new PatientDto(patient.Id, patient.FullName, patient.Phone);
    }
    // ...
}
```

---

## ۵. Infrastructure Layer

```csharp
// Data/AppDbContext.cs
public class AppDbContext : DbContext
{
    public AppDbContext(DbContextOptions<AppDbContext> options) : base(options) { }
    public DbSet<Patient> Patients => Set<Patient>();
}

// Repositories/PatientRepository.cs
public class PatientRepository : IPatientRepository
{
    private readonly AppDbContext _db;
    public PatientRepository(AppDbContext db) => _db = db;

    public async Task AddAsync(Patient patient)
    {
        _db.Patients.Add(patient);
        await _db.SaveChangesAsync();
    }
}
```

---

## ۶. Presentation Layer (Web)

```csharp
// Controllers/PatientsController.cs
[ApiController]
[Route("api/[controller]")]
public class PatientsController : ControllerBase
{
    private readonly IPatientService _service;

    public PatientsController(IPatientService service) => _service = service;

    [HttpGet]
    public async Task<IActionResult> GetAll()
        => Ok(await _service.GetAllAsync());

    [HttpPost]
    public async Task<IActionResult> Create(CreatePatientRequest request)
    {
        var created = await _service.CreateAsync(request);
        return CreatedAtAction(nameof(GetById), new { id = created.Id }, created);
    }
}
```

---

## ۷. Program.cs — Composition Root

```csharp
var builder = WebApplication.CreateBuilder(args);

builder.Services.AddControllers();
builder.Services.AddDbContext<AppDbContext>(options =>
    options.UseSqlite(builder.Configuration.GetConnectionString("Default")));

builder.Services.AddScoped<IPatientRepository, PatientRepository>();
builder.Services.AddScoped<IPatientService, PatientService>();

var app = builder.Build();
app.MapControllers();
app.Run();
```

**Composition Root:** تنها جایی که concrete types ثبت می‌شوند.

---

## ۸. appsettings.json

```json
{
  "ConnectionStrings": {
    "Default": "Data Source=clinic.db"
  },
  "Logging": {
    "LogLevel": {
      "Default": "Information",
      "Microsoft.AspNetCore": "Warning"
    }
  }
}
```

### Environment

| فایل | محیط |
|------|------|
| `appsettings.json` | پیش‌فرض |
| `appsettings.Development.json` | توسعه |
| `appsettings.Production.json` | production |

```csharp
var conn = builder.Configuration.GetConnectionString("Default");
var isDev = builder.Environment.IsDevelopment();
```

---

## ۹. Migration و Database

```powershell
dotnet ef migrations add InitialCreate --project ClinicApp.Infrastructure --startup-project ClinicApp.Web
dotnet ef database update --project ClinicApp.Infrastructure --startup-project ClinicApp.Web
```

---

## ۱۰. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_layered_structure.cs](./examples/01_layered_structure.cs) | لایه‌بندی در Console |
| [02_dto_mapping.cs](./examples/02_dto_mapping.cs) | Entity به DTO |
| [03_di_registration.cs](./examples/03_di_registration.cs) | ثبت سرویس‌ها |
| [04_configuration.cs](./examples/04_configuration.cs) | Configuration شبیه‌سازی |

---

## ۱۱. مثال واقعی — Clinic Booking

```
ClinicBooking/
├── Domain/
│   ├── Entities/Appointment.cs
│   └── Interfaces/IAppointmentRepository.cs
├── Application/
│   ├── DTOs/
│   └── Services/AppointmentService.cs
├── Infrastructure/
│   ├── Data/AppDbContext.cs
│   └── Repositories/
└── Web/
    ├── Controllers/
    └── Program.cs
```

---

## ۱۲. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| DbContext در Controller | نقض لایه‌بندی |
| Entity در API Response | افشای schema داخلی |
| Domain → Infrastructure ref | وابستگی معکوس |
| Service Locator | anti-pattern؛ DI استفاده کنید |
| Connection string hardcode | امنیت و انعطاف |

---

## ۱۳. تمرین کلاسی

1. Solution با ۳ پروژه: Domain، Application، Web (Console شبیه‌سازی)
2. DTO برای Product با mapping از Entity
3. DI Container ساده با Dictionary

---

## ۱۴. نکات حرفه‌ای

- **Domain** فقط business rules؛ بدون EF attribute (ترجیحاً)
- **Application** orchestration؛ بدون HTTP concern
- **Infrastructure** جزئیات فنی (DB، Email، File)
- از **record** برای DTO استفاده کنید
- Solution Explorer را با Solution Folders مرتب کنید

---

## ۱۵. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۳۷ (EF Core)، ۳۶ (DI)، ۳۹ (MVC)

**جلسه بعد:** Design Patterns

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۳۹ — ASP.NET MVC](../session-39-aspnet-mvc/) | **بعد:** [۴۱ — Design Patterns](../session-41-design-patterns/)
