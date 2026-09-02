# جلسه ۴۳: Repository Pattern (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک Repository Pattern و Unit of Work
- تفکیک data access از business logic
- پیاده‌سازی Generic Repository و Specific Repository
- Mock کردن Repository برای Unit Test
- ترکیب Repository با EF Core
- تصمیم‌گیری: Repository vs DbContext مستقیم

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | چرا Repository؟ مشکل DbContext در Service |
| 10–25 | Interface و Implementation پایه |
| 25–40 | Generic Repository |
| 40–55 | Specific Repository + Query methods |
| 55–70 | Unit of Work Pattern |
| 70–85 | Mock برای Test |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Repository چیست؟

**Repository** لایه abstraction روی data access است که business logic را از جزئیات persistence جدا می‌کند.

```
Service → IRepository → EF Core / InMemory / File
```

### مزایا

| مزیت | توضیح |
|------|-------|
| Testability | Mock repository |
| Decoupling | Service نمی‌داند SQL یا EF |
| Consistency | API یکسان برای CRUD |
| Maintainability | تغییر DB در یک جا |

---

## ۲. Interface پایه

```csharp
public interface IRepository<T> where T : class
{
    Task<T?> GetByIdAsync(int id);
    Task<IReadOnlyList<T>> GetAllAsync();
    Task AddAsync(T entity);
    Task UpdateAsync(T entity);
    Task DeleteAsync(int id);
}
```

```csharp
public interface IPatientRepository : IRepository<Patient>
{
    Task<IReadOnlyList<Patient>> GetByPhoneAsync(string phone);
    Task<bool> ExistsAsync(string phone);
}
```

---

## ۳. Implementation با EF Core

```csharp
public class PatientRepository : IPatientRepository
{
    private readonly AppDbContext _db;
    public PatientRepository(AppDbContext db) => _db = db;

    public async Task<Patient?> GetByIdAsync(int id)
        => await _db.Patients.FindAsync(id);

    public async Task<IReadOnlyList<Patient>> GetAllAsync()
        => await _db.Patients.AsNoTracking().ToListAsync();

    public async Task AddAsync(Patient entity)
    {
        _db.Patients.Add(entity);
        await _db.SaveChangesAsync();
    }

    public async Task<IReadOnlyList<Patient>> GetByPhoneAsync(string phone)
        => await _db.Patients.Where(p => p.Phone == phone).ToListAsync();
}
```

---

## ۴. Generic Repository

```csharp
public class Repository<T> : IRepository<T> where T : class
{
    protected readonly AppDbContext _db;
    protected readonly DbSet<T> _set;

    public Repository(AppDbContext db)
    {
        _db = db;
        _set = db.Set<T>();
    }

    public async Task<T?> GetByIdAsync(int id)
        => await _set.FindAsync(id);

    public async Task AddAsync(T entity)
    {
        await _set.AddAsync(entity);
        await _db.SaveChangesAsync();
    }
}
```

**نکته:** Generic Repository برای entity ساده خوب است؛ query پیچیده → Specific Repository.

---

## ۵. Unit of Work

وقتی چند repository در یک transaction:

```csharp
public interface IUnitOfWork : IDisposable
{
    IPatientRepository Patients { get; }
    IAppointmentRepository Appointments { get; }
    Task<int> SaveChangesAsync();
}

public class UnitOfWork : IUnitOfWork
{
    private readonly AppDbContext _db;
    public UnitOfWork(AppDbContext db) => _db = db;

    public IPatientRepository Patients => new PatientRepository(_db);
    public IAppointmentRepository Appointments => new AppointmentRepository(_db);
    public Task<int> SaveChangesAsync() => _db.SaveChangesAsync();
}
```

---

## ۶. InMemory برای Test/Dev

```csharp
public class InMemoryPatientRepository : IPatientRepository
{
    private readonly List<Patient> _store = new();
    private int _nextId = 1;

    public Task AddAsync(Patient entity)
    {
        entity.Id = _nextId++;
        _store.Add(entity);
        return Task.CompletedTask;
    }

    public Task<Patient?> GetByIdAsync(int id)
        => Task.FromResult(_store.FirstOrDefault(p => p.Id == id));
}
```

---

## ۷. Mock در Unit Test

```csharp
// با Moq (مفهومی)
var mockRepo = new Mock<IPatientRepository>();
mockRepo.Setup(r => r.GetByIdAsync(1))
    .ReturnsAsync(new Patient { Id = 1, FullName = "Test" });

var service = new PatientService(mockRepo.Object);
var result = await service.GetByIdAsync(1);
Assert.NotNull(result);
```

---

## ۸. Repository vs DbContext مستقیم

| Repository | DbContext مستقیم |
|------------|-----------------|
| پروژه بزرگ/تیمی | پروژه کوچک |
| Test زیاد | Prototype سریع |
| چند source داده | فقط EF Core |

**نظر رایج .NET:** برای app ساده، DbContext در Service قابل قبول است؛ Repository برای maintainability بلندمدت.

---

## ۹. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_repository_basics.cs](./examples/01_repository_basics.cs) | CRUD پایه |
| [02_generic_repository.cs](./examples/02_generic_repository.cs) | Generic Repository |
| [03_unit_of_work.cs](./examples/03_unit_of_work.cs) | Unit of Work |
| [04_mock_testing.cs](./examples/04_mock_testing.cs) | Mock و Test |

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| Repository برگرداندن IQueryable | leak abstraction |
| SaveChanges در هر method کوچک | performance |
| God Repository با ۵۰ query | ISP نقض |
| Repository بدون interface | DIP نقض |
| Duplicate logic Generic + Specific | DRY نقض |

---

## ۱۱. تمرین کلاسی

1. `IBookRepository` با `SearchByAuthor`
2. InMemory implementation
3. Service که از repository استفاده می‌کند

---

## ۱۲. نکات حرفه‌ای

- `AsNoTracking()` برای read-only queries
- Pagination در repository level
- Specification Pattern برای query پیچیده
- CancellationToken در async methods
- Scoped lifetime برای repository

---

## ۱۳. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۳۷ (EF Core)، ۴۲ (SOLID)

**جلسه بعد:** Middleware

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۲ — SOLID](../session-42-solid/) | **بعد:** [۴۴ — Middleware](../session-44-middleware/)
