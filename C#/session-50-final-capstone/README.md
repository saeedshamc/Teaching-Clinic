# جلسه ۵۰: پروژه نهایی (Final Capstone) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- ترکیب تمام مفاهیم دوره در یک پروژه یکپارچه
- طراحی سیستم **Clinic Management** با لایه‌بندی کامل
- پیاده‌سازی Domain، Service، Repository و API
- Authentication و REST API design
- آماده‌سازی برای deploy
- ارائه پروژه و مرور مسیر یادگیری

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | معرفی پروژه و requirements |
| 10–25 | Domain Model طراحی |
| 25–40 | Service Layer و business rules |
| 40–55 | Repository و data access |
| 55–70 | API endpoints و auth |
| 70–85 | Demo و code review |
| 85–90 | جمع‌بندی دوره + گواهینامه |

---

## ۱. پروژه: Clinic Management System

### Requirements

| Feature | توضیح |
|---------|-------|
| Patient CRUD | ثبت، ویرایش، لیست بیمار |
| Appointment Booking | رزرو نوبت |
| Doctor Schedule | برنامه پزشکان |
| Authentication | login با role (Admin, Doctor, Reception) |
| REST API | endpoints استاندارد |
| Validation | input validation |
| Logging | structured logs |

---

## ۲. Solution Structure

```
ClinicManagement/
├── ClinicManagement.Domain/
│   ├── Entities/
│   │   ├── Patient.cs
│   │   ├── Doctor.cs
│   │   └── Appointment.cs
│   └── Interfaces/
│       ├── IPatientRepository.cs
│       └── IAppointmentRepository.cs
├── ClinicManagement.Application/
│   ├── DTOs/
│   ├── Services/
│   └── Validators/
├── ClinicManagement.Infrastructure/
│   ├── Data/AppDbContext.cs
│   └── Repositories/
└── ClinicManagement.Web/
    ├── Controllers/
    ├── Middleware/
    └── Program.cs
```

---

## ۳. Domain Model

```csharp
public class Patient
{
    public int Id { get; set; }
    public string FullName { get; set; } = "";
    public string NationalId { get; set; } = "";
    public string Phone { get; set; } = "";
    public DateTime RegisteredAt { get; set; } = DateTime.UtcNow;
}

public class Doctor
{
    public int Id { get; set; }
    public string FullName { get; set; } = "";
    public string Specialty { get; set; } = "";
}

public class Appointment
{
    public int Id { get; set; }
    public int PatientId { get; set; }
    public int DoctorId { get; set; }
    public DateTime ScheduledAt { get; set; }
    public AppointmentStatus Status { get; set; } = AppointmentStatus.Pending;
    public Patient? Patient { get; set; }
    public Doctor? Doctor { get; set; }
}

public enum AppointmentStatus { Pending, Confirmed, Cancelled, Completed }
```

---

## ۴. Application Services

```csharp
public interface IAppointmentService
{
    Task<AppointmentDto> BookAsync(BookAppointmentRequest request);
    Task<IReadOnlyList<AppointmentDto>> GetByPatientAsync(int patientId);
    Task CancelAsync(int appointmentId);
}

public class AppointmentService : IAppointmentService
{
    private readonly IUnitOfWork _uow;
    private readonly ILogger<AppointmentService> _logger;

    public async Task<AppointmentDto> BookAsync(BookAppointmentRequest request)
    {
        var patient = await _uow.Patients.GetByIdAsync(request.PatientId)
            ?? throw new NotFoundException("Patient not found");

        var conflict = await _uow.Appointments.HasConflictAsync(
            request.DoctorId, request.ScheduledAt);
        if (conflict)
            throw new ConflictException("Doctor not available at this time");

        var appointment = new Appointment { /* ... */ };
        await _uow.Appointments.AddAsync(appointment);
        await _uow.SaveChangesAsync();

        _logger.LogInformation("Appointment {Id} booked", appointment.Id);
        return MapToDto(appointment);
    }
}
```

---

## ۵. API Endpoints

```
POST   /api/auth/login
GET    /api/patients
POST   /api/patients
GET    /api/patients/{id}
GET    /api/doctors
POST   /api/appointments
GET    /api/appointments?patientId=5
DELETE /api/appointments/{id}
GET    /health
```

---

## ۶. Cross-cutting Concerns

| Concern | Implementation |
|---------|---------------|
| Auth | JWT Bearer |
| Validation | FluentValidation |
| Error handling | Problem Details middleware |
| Logging | Serilog |
| Caching | IMemoryCache for doctors list |
| Health | /health endpoint |

---

## ۷. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_domain_model.cs](./examples/01_domain_model.cs) | Domain entities |
| [02_service_layer.cs](./examples/02_service_layer.cs) | Business logic |
| [03_repository_api.cs](./examples/03_repository_api.cs) | Repository + API |
| [04_capstone_demo.cs](./examples/04_capstone_demo.cs) | Demo کامل |

---

## ۸. Demo Flow

```
1. Login as Reception → JWT token
2. POST /api/patients → create patient
3. GET /api/doctors → list doctors
4. POST /api/appointments → book appointment
5. GET /api/appointments?patientId=1 → list appointments
6. GET /health → verify system health
```

---

## ۹. Skills Map — دوره ۵۰ جلسه

| جلسات | مهارت |
|-------|-------|
| 1–12 | C# Basics |
| 13–20 | OOP |
| 21–28 | Advanced C# |
| 29–35 | .NET Ecosystem |
| 36–38 | DI, EF Core, Web API |
| 39–40 | MVC, Project Structure |
| 41–43 | Patterns, SOLID, Repository |
| 44–46 | Middleware, Auth, REST |
| 47–49 | Performance, Debug, Deploy |
| **50** | **Capstone** |

---

## ۱۰. Code Review Checklist

| ✓ | Item |
|---|------|
| □ | SOLID principles |
| □ | Layered architecture |
| □ | DTOs for API |
| □ | Proper HTTP status codes |
| □ | Input validation |
| □ | Error handling |
| □ | Logging |
| □ | Unit tests |
| □ | README documentation |

---

## ۱۱. Next Steps

| مسیر | پیشنهاد |
|------|---------|
| Blazor | UI با C# |
| Microservices | gRPC, message queues |
| Cloud | Azure/AWS certification |
| Open Source | contribute به .NET projects |
| Portfolio | GitHub + deploy |

---

## ۱۲. اشتباهات رایج در Capstone

| اشتباه | راه‌حل |
|--------|--------|
| همه چیز در Program.cs | لایه‌بندی |
| بدون validation | FluentValidation |
| بدون error handling | middleware |
| hardcoded config | appsettings + env |
| بدون README | documentation |

---

## ۱۳. تمرین کلاسی

1. Domain model کامل
2. Book appointment flow
3. API endpoint با validation

---

## ۱۴. تبریک!

شما ۵۰ جلسه C# و .NET را تکمیل کردید. از syntax پایه تا deploy production — مسیر کامل یک developer.

**مهارت‌های کلیدی:**
- C# 12 / .NET 8
- OOP و Design Patterns
- ASP.NET Core Web API
- EF Core و SQL
- SOLID و Clean Architecture
- Authentication و REST
- Performance و Debugging
- Docker و Deployment

---

## ۱۵. تکلیف نهایی

پروژه Clinic Management را کامل کنید و روی GitHub publish + deploy کنید.

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۹ — Deployment](../session-49-deployment/)

**پایان دوره** 🎓
