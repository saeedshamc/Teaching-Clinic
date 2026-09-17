# جلسه ۴۷: Performance (بهینه‌سازی) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- شناسایی bottleneck در اپلیکیشن .NET
- بهینه‌سازی query های EF Core
- Caching: In-Memory و Distributed
- Async/await صحیح برای throughput
- Profiling با dotnet-counters و BenchmarkDotNet
- Best practices برای production performance

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | Performance mindset |
| 10–25 | EF Core query optimization |
| 25–40 | N+1 problem و Include |
| 40–55 | Caching strategies |
| 55–70 | Async و Threading |
| 70–85 | Profiling و measurement |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Measure First

```
❌ حدس زدن bottleneck
✅ profile → identify → fix → verify
```

ابزارها:
- `dotnet-counters`
- Visual Studio Diagnostic Tools
- Application Insights
- BenchmarkDotNet

---

## ۲. EF Core Optimization

### N+1 Problem

```csharp
// ❌ N+1: 1 query + N queries
var patients = await _db.Patients.ToListAsync();
foreach (var p in patients)
    var appts = await _db.Appointments.Where(a => a.PatientId == p.Id).ToListAsync();

// ✅ Eager Loading
var patients = await _db.Patients
    .Include(p => p.Appointments)
    .ToListAsync();
```

### AsNoTracking

```csharp
// Read-only — سریع‌تر
var list = await _db.Patients.AsNoTracking().ToListAsync();
```

### Select Projection

```csharp
// ❌ همه ستون‌ها
var all = await _db.Patients.ToListAsync();

// ✅ فقط فیلدهای لازم
var dtos = await _db.Patients
    .Select(p => new PatientDto(p.Id, p.FullName))
    .ToListAsync();
```

---

## ۳. Pagination در DB

```csharp
// ❌ همه را load کن، بعد Skip/Take
var all = await _db.Patients.ToListAsync();
var page = all.Skip(20).Take(10);

// ✅ در SQL
var page = await _db.Patients
    .OrderBy(p => p.Id)
    .Skip(20).Take(10)
    .ToListAsync();
```

---

## ۴. Caching

### In-Memory Cache

```csharp
builder.Services.AddMemoryCache();

public async Task<List<Category>> GetCategoriesAsync()
{
    if (!_cache.TryGetValue("categories", out List<Category>? cats))
    {
        cats = await _db.Categories.ToListAsync();
        _cache.Set("categories", cats, TimeSpan.FromMinutes(30));
    }
    return cats!;
}
```

### Cache Invalidation

```csharp
_cache.Remove("categories"); // بعد از update
```

### Distributed Cache (Redis)

```csharp
builder.Services.AddStackExchangeRedisCache(options =>
    options.Configuration = "localhost:6379");
```

---

## ۵. Async Best Practices

```csharp
// ✅ async all the way
public async Task<IActionResult> GetPatients()
    => Ok(await _service.GetAllAsync());

// ❌ .Result یا .Wait() — deadlock risk
var data = _service.GetAllAsync().Result;

// ✅ ConfigureAwait(false) در library code
await _db.SaveChangesAsync().ConfigureAwait(false);
```

---

## ۶. String و Collection

```csharp
// ❌ concatenation در loop
string result = "";
foreach (var s in items) result += s;

// ✅ StringBuilder
var sb = new StringBuilder();
foreach (var s in items) sb.Append(s);

// ✅ LINQ
var result = string.Join(", ", items);
```

---

## ۷. Response Compression

```csharp
builder.Services.AddResponseCompression(options =>
    options.EnableForHttps = true);
app.UseResponseCompression();
```

---

## ۸. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_query_optimization.cs](./examples/01_query_optimization.cs) | Query بهینه |
| [02_n_plus_one.cs](./examples/02_n_plus_one.cs) | N+1 problem |
| [03_caching.cs](./examples/03_caching.cs) | In-Memory Cache |
| [04_benchmark_concept.cs](./examples/04_benchmark_concept.cs) | Benchmark |

---

## ۹. Connection Pooling

```
Default: connection pool فعال
❌ new SqlConnection() در هر request بدون using
✅ DbContext Scoped + using/await using
```

---

## ۱۰. اشتباهات رایج

| اشتباه | تأثیر |
|--------|-------|
| N+1 queries | latency بالا |
| Tracking read-only | memory + CPU |
| Cache بدون expiry | stale data |
| Sync over async | thread starvation |
| Premature optimization | وقت تلف |

---

## ۱۱. تمرین کلاسی

1. N+1 vs Include comparison
2. Cache با expiry
3. StringBuilder vs concat benchmark

---

## ۱۲. نکات حرفه‌ای

- `Compiled Queries` برای query تکراری
- `IMemoryCache` با size limit
- Health checks برای Redis/DB
- Load test با k6 یا NBomber
- CDN برای static files

---

## ۱۳. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۳۷ (EF Core)، ۲۷ (Async)

**جلسه بعد:** Debugging

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۶ — REST](../session-46-rest-best-practices/) | **بعد:** [۴۸ — Debugging](../session-48-debugging/)
