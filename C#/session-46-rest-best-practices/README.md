# جلسه ۴۶: REST Best Practices (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک اصول RESTful API design
- HTTP Methods و Status Codes صحیح
- Resource naming و URL structure
- Versioning، Pagination، Filtering
- Error response استاندارد (Problem Details)
- HATEOAS و OpenAPI/Swagger

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | REST چیست؟ |
| 10–25 | Resource naming |
| 25–40 | HTTP Methods و Status Codes |
| 40–55 | Request/Response design |
| 55–70 | Pagination، Filtering، Sorting |
| 70–85 | Error handling + Versioning |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. REST Principles

| اصل | توضیح |
|-----|-------|
| Stateless | هر request مستقل |
| Resource-based | URL = noun نه verb |
| HTTP semantics | GET/POST/PUT/PATCH/DELETE |
| Representation | JSON/XML |

---

## ۲. Resource Naming

```
✅ GET    /api/patients
✅ GET    /api/patients/5
✅ POST   /api/patients
✅ PUT    /api/patients/5
✅ DELETE /api/patients/5
✅ GET    /api/patients/5/appointments

❌ GET    /api/getPatients
❌ POST   /api/patient/create
❌ GET    /api/patient/delete/5
```

**قوانین:**
- plural nouns (`patients` نه `patient`)
- lowercase + kebab-case (`/api/medical-records`)
- nested برای relationship (`/patients/5/appointments`)

---

## ۳. HTTP Methods

| Method | Idempotent | Safe | Body | کاربرد |
|--------|-----------|------|------|--------|
| GET | ✓ | ✓ | ✗ | خواندن |
| POST | ✗ | ✗ | ✓ | ایجاد |
| PUT | ✓ | ✗ | ✓ | جایگزینی کامل |
| PATCH | ✗ | ✗ | ✓ | به‌روزرسانی جزئی |
| DELETE | ✓ | ✗ | ✗ | حذف |

---

## ۴. Status Codes

| Code | معنی | مثال |
|------|------|------|
| 200 | OK | GET موفق |
| 201 | Created | POST موفق |
| 204 | No Content | DELETE موفق |
| 400 | Bad Request | validation error |
| 401 | Unauthorized | بدون token |
| 403 | Forbidden | بدون permission |
| 404 | Not Found | resource نیست |
| 409 | Conflict | duplicate |
| 422 | Unprocessable | semantic error |
| 500 | Server Error | bug |

---

## ۵. Response Design

```csharp
// ✅ Good: Created با Location
[HttpPost]
public IActionResult Create(CreatePatientDto dto)
{
    var patient = _service.Create(dto);
    return CreatedAtAction(nameof(GetById), new { id = patient.Id }, patient);
}

// ✅ Good: Not Found
[HttpGet("{id}")]
public IActionResult GetById(int id)
{
    var patient = _service.GetById(id);
    return patient is null ? NotFound() : Ok(patient);
}
```

---

## ۶. Problem Details (RFC 7807)

```json
{
  "type": "https://api.clinic.com/errors/validation",
  "title": "Validation Error",
  "status": 400,
  "errors": {
    "Phone": ["شماره تلفن نامعتبر است"]
  }
}
```

```csharp
return Problem(
    title: "Validation Error",
    statusCode: 400,
    detail: "One or more fields are invalid"
);
```

---

## ۷. Pagination

```
GET /api/patients?page=2&pageSize=20&sort=name&order=asc
```

```json
{
  "items": [...],
  "page": 2,
  "pageSize": 20,
  "totalCount": 150,
  "totalPages": 8
}
```

---

## ۸. Versioning

```
/api/v1/patients
/api/v2/patients
```

```csharp
[ApiVersion("1.0")]
[Route("api/v{version:apiVersion}/[controller]")]
public class PatientsV1Controller : ControllerBase { }
```

---

## ۹. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_rest_resources.cs](./examples/01_rest_resources.cs) | Resource design |
| [02_http_methods.cs](./examples/02_http_methods.cs) | Methods و Status |
| [03_pagination_filter.cs](./examples/03_pagination_filter.cs) | Pagination |
| [04_error_responses.cs](./examples/04_error_responses.cs) | Error handling |

---

## ۱۰. Swagger/OpenAPI

```csharp
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen();

if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}
```

---

## ۱۱. اشتباهات رایج

| اشتباه | درست |
|--------|------|
| 200 برای همه | status مناسب |
| verb در URL | noun |
| POST برای update | PUT/PATCH |
| error message vague | Problem Details |
| بدون pagination | page + pageSize |

---

## ۱۲. تمرین کلاسی

1. CRUD API design برای Book
2. Pagination response
3. Validation error response

---

## ۱۳. نکات حرفه‌ای

- `CreatedAtAction` برای POST
- `204 No Content` برای DELETE موفق
- Filtering با query params نه body در GET
- API contract با OpenAPI document
- Breaking change → version جدید

---

## ۱۴. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۳۸ (Web API)، ۴۵ (Auth)

**جلسه بعد:** Performance

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۵ — Authentication](../session-45-authentication/) | **بعد:** [۴۷ — Performance](../session-47-performance/)
