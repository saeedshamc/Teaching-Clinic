# پروژهٔ سراسری — «Clinics Management System»

پروژه‌ای که **همهٔ ۱۵۰ جلسه** را پوشش می‌دهد — یک سیستم واقعی با چند ماژول.

## Vision

یک سامانهٔ مدیریت کلینیک با این ماژول‌ها:

```
┌─────────────────────────────────────────────────┐
│           CLINIC MANAGEMENT SYSTEM              │
├─────────────────────────────────────────────────┤
│  Desktop Client (Swing)  │  Android App          │
│  - Patients CRUD         │  - Patient list       │
│  - Appointments          │  - Book appointment   │
│  - Reports               │  - View prescriptions │
├─────────────────────────────────────────────────┤
│           REST API Backend (Spring Boot)         │
│  /api/patients  /api/doctors  /api/appointments │
│  /api/prescriptions  /api/auth  /ws/notifications│
├─────────────────────────────────────────────────┤
│           Database (PostgreSQL / H2)             │
└─────────────────────────────────────────────────┘
```

## پوشش جلسات

| بخش دوره | استفاده در پروژه |
|---|---|
| ۱–۱۵ Core | model classes، collections، streams |
| ۱۶–۳۰ OOP/Collections | domain model، repository pattern |
| ۳۱–۴۵ Exceptions/IO/Concurrency | file export، thread pools، async |
| ۴۶–۶۰ Modern Java | records, sealed, streams, optional |
| ۶۱–۷۵ Desktop | Swing UI برای patient management |
| ۷۶–۹۵ Android | Jetpack app برای patients |
| ۹۶–۱۱۰ DB/Networking/Build | JDBC/JPA, HTTP client, Maven/Gradle |
| ۱۱۱–۱۲۴ Web Backend | Spring Boot REST + Security |
| ۱۲۵–۱۳۹ Testing/Patterns/JVM | JUnit tests, design patterns, profiling |
| ۱۴۰–۱۵۰ Security/DevOps/Capstone | JWT, Docker, CI/CD, final integration |

## فازهای پروژه

### Phase A — Core (جلسات ۱–۳۰)
- Domain model: `Patient`, `Doctor`, `Appointment`, `Prescription`
- `PatientRepository` با file persistence
- Unit tests با JUnit

### Phase B — Desktop (جلسات ۶۱–۷۵)
- `PatientManagementFrame` — CRUD با JTable
- `AppointmentDialog` — booking UI
- Reports با charts

### Phase C — Backend (جلسات ۱۱۱–۱۲۴)
- Spring Boot REST API
- JWT authentication
- WebSocket برای real-time updates

### Phase D — Android (جلسات ۷۶–۹۵)
- Patient list + detail
- Book appointment
- View prescriptions

### Phase E — Integration (جلسات ۱۴۰–۱۵۰)
- Docker + docker-compose
- CI/CD pipeline
- Security hardening
- Final demo

## معیار نهایی
- همهٔ APIها با تست پوشش داده شده باشند
- Desktop + Android + Backend با هم کار کنند
- Docker deployment موفق
- README کامل با screenshot و API docs
