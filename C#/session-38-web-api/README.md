# جلسه ۳۸: ASP.NET Core Web API — ۱.۵ ساعت

## 🎯 اهداف یادگیری

- REST API و HTTP methods
- ساخت Web API با Minimal API و Controllers
- Routing، route parameters و query strings
- DTO (Data Transfer Object) pattern
- status codes: 200، 201، 400، 404، 500
- DI در Web API
- Swagger/OpenAPI (مقدمه)
- test API با curl/Postman

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | REST و HTTP |
| 10–25 | ساخت پروژه Web API |
| 25–40 | Minimal API endpoints |
| 40–55 | Controllers |
| 55–70 | DTO و validation |
| 70–85 | تمرین |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. REST API چیست؟

**REST** = Representational State Transfer

Client ↔ HTTP ↔ Server (JSON)

| Method | عمل | مثال |
|--------|-----|------|
| GET | خواندن | GET /api/patients |
| POST | ایجاد | POST /api/patients |
| PUT | بروزرسانی کامل | PUT /api/patients/1 |
| PATCH | بروزرسانی جزئی | PATCH /api/patients/1 |
| DELETE | حذف | DELETE /api/patients/1 |

---

## ۲. ساخت پروژه

```powershell
dotnet new webapi -n ClinicApi
cd ClinicApi
dotnet run
# https://localhost:7xxx/swagger
```

---

## ۳. Minimal API

```csharp
var builder = WebApplication.CreateBuilder(args);
var app = builder.Build();

app.MapGet("/api/patients", () => Results.Ok(patients));

app.MapGet("/api/patients/{id}", (int id) =>
{
    var patient = patients.FirstOrDefault(p => p.Id == id);
    return patient is null ? Results.NotFound() : Results.Ok(patient);
});

app.MapPost("/api/patients", (CreatePatientDto dto) =>
{
    var patient = new Patient { Name = dto.Name };
    patients.Add(patient);
    return Results.Created($"/api/patients/{patient.Id}", patient);
});

app.Run();
```

---

## ۴. Controller-based API

```csharp
[ApiController]
[Route("api/[controller]")]
public class PatientsController : ControllerBase
{
    private readonly IPatientService _service;

    public PatientsController(IPatientService service)
    {
        _service = service;
    }

    [HttpGet]
    public async Task<ActionResult<List<PatientDto>>> GetAll()
        => Ok(await _service.GetAllAsync());

    [HttpGet("{id}")]
    public async Task<ActionResult<PatientDto>> GetById(int id)
    {
        var patient = await _service.GetByIdAsync(id);
        return patient is null ? NotFound() : Ok(patient);
    }

    [HttpPost]
    public async Task<ActionResult<PatientDto>> Create(CreatePatientDto dto)
    {
        var created = await _service.CreateAsync(dto);
        return CreatedAtAction(nameof(GetById), new { id = created.Id }, created);
    }

    [HttpDelete("{id}")]
    public async Task<IActionResult> Delete(int id)
    {
        bool deleted = await _service.DeleteAsync(id);
        return deleted ? NoContent() : NotFound();
    }
}
```

---

## ۵. DTO Pattern

```csharp
// Entity — internal
public class Patient
{
    public int Id { get; set; }
    public string Name { get; set; }
    public string NationalCode { get; set; }  // sensitive
}

// DTO — API response
public record PatientDto(int Id, string Name);

// DTO — API request
public record CreatePatientDto(string Name, string NationalCode);
```

**چرا DTO؟** control API surface، hide internal fields، versioning.

---

## ۶. Status Codes

| Code | معنی | کاربرد |
|------|------|--------|
| 200 OK | موفق | GET, PUT |
| 201 Created | ایجاد شد | POST |
| 204 No Content | موفق بدون body | DELETE |
| 400 Bad Request | validation fail | invalid input |
| 404 Not Found | یافت نشد | wrong id |
| 500 Internal Server Error | server error | unhandled exception |

---

## ۷. Query Parameters

```csharp
app.MapGet("/api/patients/search", (string? name, int page = 1) =>
{
    var results = patients.Where(p =>
        name is null || p.Name.Contains(name));
    return Results.Ok(results.Skip((page - 1) * 10).Take(10));
});
// GET /api/patients/search?name=علی&page=2
```

---

## ۸. DI در Web API

```csharp
builder.Services.AddScoped<IPatientService, PatientService>();
builder.Services.AddDbContext<ClinicDbContext>(...);
```

Controller/Minimal API خودکار inject می‌کند.

---

## ۹. Swagger

```csharp
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();
// ...
app.UseSwagger();
app.UseSwaggerUI();
```

UI تعاملی: `https://localhost:port/swagger`

---

## ۱۰. Validation

```csharp
public record CreatePatientDto(
    [Required][StringLength(100)] string Name,
    [Required][RegularExpression(@"^\d{10}$")] string NationalCode
);
```

```csharp
if (!ModelState.IsValid)
    return BadRequest(ModelState);
```

---

## ۱۱. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_minimal_api_note.cs](./examples/01_minimal_api_note.cs) | Minimal API |
| [02_route_concept.cs](./examples/02_route_concept.cs) | routing |
| [03_dto_concept.cs](./examples/03_dto_concept.cs) | DTO pattern |
| [04_http_simulation.cs](./examples/04_http_simulation.cs) | HTTP simulation |

---

## ۱۲. Middleware Pipeline

```
Request → Routing → Auth → Controller → Response
```

```csharp
app.UseHttpsRedirection();
app.UseAuthorization();
app.MapControllers();
```

---

## ۱۳. CORS (مقدمه)

```csharp
builder.Services.AddCors(options =>
    options.AddDefaultPolicy(p =>
        p.WithOrigins("http://localhost:3000").AllowAnyHeader()));
app.UseCors();
```

برای frontend جدا (React, Angular).

---

## ۱۴. Test با curl

```powershell
curl -X GET https://localhost:7001/api/patients
curl -X POST https://localhost:7001/api/patients -H "Content-Type: application/json" -d "{\"name\":\"علی\"}"
curl -X DELETE https://localhost:7001/api/patients/1
```

---

## ۱۵. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| expose entity directly | over-posting, security |
| wrong status code | client confusion |
| no validation | bad data |
| GET with body | non-standard |
| sync I/O | thread starvation |

---

## ۱۶. API Versioning (مقدمه)

```
/api/v1/patients
/api/v2/patients
```

Package: `Asp.Versioning.Mvc`

---

## ۱۷. تمرین کلاسی

1. CRUD API برای Product
2. DTO برای request/response
3. 404 برای id نامعتبر

**تکلیف:** [question.md](./exercises/question.md)

---

## ۱۸. از Console به Web

| Console | Web API |
|---------|---------|
| Menu input | HTTP request |
| Console.WriteLine | JSON response |
| Main loop | Kestrel server |
| File storage | DB + EF Core |

---

## 📚 مطالعه تکمیلی

- [ASP.NET Core Web API](https://learn.microsoft.com/aspnet/core/web-api/)
- [Minimal APIs](https://learn.microsoft.com/aspnet/core/fundamentals/minimal-apis)
- REST — Roy Fielding dissertation (concept)

---

**جلسه قبل:** [۳۷ — EF Core](../session-37-ef-core/) | **بعد:** [۳۹ — ASP.NET MVC](../session-39-aspnet-mvc/)
