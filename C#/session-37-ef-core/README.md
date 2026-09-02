# جلسه ۳۷: Entity Framework Core — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- ORM و نقش EF Core
- DbContext، DbSet و Entity classes
- Code-First approach
- CRUD با LINQ to Entities
- Migrations (مفهومی)
- InMemory provider برای یادگیری
- relationships: One-to-Many (مقدمه)
- best practices و performance tips

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | ORM چیست؟ |
| 10–25 | DbContext و Entity |
| 25–40 | CRUD operations |
| 40–55 | relationships |
| 55–70 | migrations |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. ORM چیست؟

**Object-Relational Mapping** = پل بین object-oriented code و relational database:

```csharp
// بدون ORM — SQL دستی
cmd.CommandText = "INSERT INTO Patients (Name) VALUES (@name)";

// با EF Core
context.Patients.Add(new Patient { Name = "علی" });
await context.SaveChangesAsync();
```

---

## ۲. نصب Packages

```powershell
dotnet add package Microsoft.EntityFrameworkCore
dotnet add package Microsoft.EntityFrameworkCore.InMemory
dotnet add package Microsoft.EntityFrameworkCore.SqlServer
dotnet add package Microsoft.EntityFrameworkCore.Tools
```

---

## ۳. Entity و DbContext

```csharp
public class Patient
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public string NationalCode { get; set; } = "";
    public DateTime CreatedAt { get; set; } = DateTime.UtcNow;
}

public class ClinicDbContext : DbContext
{
    public DbSet<Patient> Patients { get; set; }

    public ClinicDbContext(DbContextOptions<ClinicDbContext> options)
        : base(options) { }
}
```

---

## ۴. Register در DI

```csharp
builder.Services.AddDbContext<ClinicDbContext>(options =>
    options.UseSqlServer(connectionString));

// یا InMemory برای test
options.UseInMemoryDatabase("ClinicDb");
```

---

## ۵. CRUD

```csharp
// Create
context.Patients.Add(new Patient { Name = "علی" });
await context.SaveChangesAsync();

// Read
var patients = await context.Patients.ToListAsync();
var one = await context.Patients.FindAsync(1);

// Update
patient.Name = "علی محمدی";
await context.SaveChangesAsync();

// Delete
context.Patients.Remove(patient);
await context.SaveChangesAsync();
```

---

## ۶. LINQ to Entities

```csharp
var results = await context.Patients
    .Where(p => p.Name.Contains("علی"))
    .OrderByDescending(p => p.CreatedAt)
    .Take(10)
    .ToListAsync();
```

EF Core LINQ را به SQL ترجمه می‌کند.

---

## ۷. Relationships — One-to-Many

```csharp
public class Doctor
{
    public int Id { get; set; }
    public string Name { get; set; } = "";
    public List<Appointment> Appointments { get; set; } = new();
}

public class Appointment
{
    public int Id { get; set; }
    public DateTime Date { get; set; }
    public int DoctorId { get; set; }
    public Doctor Doctor { get; set; } = null!;
}
```

```csharp
var doctor = await context.Doctors
    .Include(d => d.Appointments)
    .FirstOrDefaultAsync(d => d.Id == 1);
```

---

## ۸. Fluent API Configuration

```csharp
protected override void OnModelCreating(ModelBuilder modelBuilder)
{
    modelBuilder.Entity<Patient>(entity =>
    {
        entity.HasKey(p => p.Id);
        entity.Property(p => p.Name).IsRequired().HasMaxLength(100);
        entity.HasIndex(p => p.NationalCode).IsUnique();
    });
}
```

---

## ۹. Migrations

```powershell
dotnet ef migrations add InitialCreate
dotnet ef database update
```

Migration = version control برای schema database.

---

## ۱۰. Async

```csharp
await context.Patients.ToListAsync();
await context.SaveChangesAsync();
```

همیشه async در web apps.

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_concept.cs](./examples/01_concept.cs) | InMemory ORM شبیه‌سازی |
| [02_practice.cs](./examples/02_practice.cs) | CRUD |
| [03_real_world.cs](./examples/03_real_world.cs) | repository + EF |
| [04_relationships.cs](./examples/04_relationships.cs) | One-to-Many |

---

## ۱۲. Performance Tips

| Tip | دلیل |
|-----|------|
| `AsNoTracking()` برای read-only | سرعت |
| `Include()` selective | avoid cartesian explosion |
| pagination با `Skip/Take` | memory |
| projection با `Select` | کمتر data |
| avoid N+1 | eager loading |

---

## ۱۳. Tracking vs NoTracking

```csharp
// tracked — برای update
var patient = await context.Patients.FindAsync(1);

// read-only — سریع‌تر
var list = await context.Patients.AsNoTracking().ToListAsync();
```

---

## ۱۴. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| DbContext Singleton | thread safety |
| SaveChanges in loop | N roundtrips |
| load all then filter in memory | performance |
| forget Include | null navigation |
| sync in web | thread blocking |

---

## ۱۵. تمرین کلاسی

1. Entity `Product` + InMemory DbContext
2. CRUD کامل
3. LINQ query با Where و OrderBy

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۶. SQL Server LocalDB

```powershell
dotnet add package Microsoft.EntityFrameworkCore.SqlServer
# ConnectionString: Server=(localdb)\mssqllocaldb;Database=ClinicDb;Trusted_Connection=True
```

---

## 📚 مطالعه تکمیلی

- [EF Core documentation](https://learn.microsoft.com/ef/core/)
- [DbContext lifetime](https://learn.microsoft.com/ef/core/dbcontext-configuration/)

---

**جلسه قبل:** [۳۶ — DI](../session-36-dependency-injection/) | **بعد:** [۳۸ — Web API](../session-38-web-api/)
