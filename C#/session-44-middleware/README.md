# جلسه ۴۴: Middleware (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک Request Pipeline در ASP.NET Core
- نوشتن Custom Middleware
- ترتیب middleware و تأثیر آن
- Built-in Middleware: Exception, Routing, Auth, CORS
- Short-circuiting و branching
- Logging و Request Timing با Middleware

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | Pipeline چیست؟ |
| 10–25 | ساختار Middleware |
| 25–40 | Custom Middleware |
| 40–55 | Built-in Middleware |
| 55–70 | ترتیب و Short-circuit |
| 70–85 | مثال: Logging + Timing |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Request Pipeline

```
Request → Middleware 1 → Middleware 2 → ... → Endpoint → Response
```

هر middleware می‌تواند:
- request را پردازش کند
- `next()` را صدا بزند (middleware بعدی)
- response را modify کند
- pipeline را متوقف کند (short-circuit)

---

## ۲. ساختار Middleware

```csharp
public class RequestLoggingMiddleware
{
    private readonly RequestDelegate _next;

    public RequestLoggingMiddleware(RequestDelegate next) => _next = next;

    public async Task InvokeAsync(HttpContext context)
    {
        Console.WriteLine($"→ {context.Request.Method} {context.Request.Path}");
        await _next(context);
        Console.WriteLine($"← {context.Response.StatusCode}");
    }
}
```

### ثبت در Program.cs

```csharp
app.UseMiddleware<RequestLoggingMiddleware>();
// یا
app.Use(async (context, next) =>
{
    Console.WriteLine("Inline middleware");
    await next();
});
```

---

## ۳. Custom Middleware — Timing

```csharp
public class TimingMiddleware
{
    private readonly RequestDelegate _next;

    public TimingMiddleware(RequestDelegate next) => _next = next;

    public async Task InvokeAsync(HttpContext context)
    {
        var sw = Stopwatch.StartNew();
        await _next(context);
        sw.Stop();
        context.Response.Headers["X-Response-Time"] = $"{sw.ElapsedMilliseconds}ms";
    }
}
```

---

## ۴. Short-Circuit

```csharp
public class ApiKeyMiddleware
{
    private readonly RequestDelegate _next;

    public async Task InvokeAsync(HttpContext context)
    {
        if (!context.Request.Headers.TryGetValue("X-Api-Key", out var key)
            || key != "secret")
        {
            context.Response.StatusCode = 401;
            await context.Response.WriteAsync("Unauthorized");
            return; // next() صدا نشد — pipeline متوقف
        }
        await _next(context);
    }
}
```

---

## ۵. Built-in Middleware (ترتیب مهم)

```csharp
var app = builder.Build();

app.UseExceptionHandler("/error");     // 1. خطا
app.UseHttpsRedirection();             // 2. HTTPS
app.UseStaticFiles();                  // 3. فایل‌های static
app.UseRouting();                      // 4. Routing
app.UseCors("AllowAll");               // 5. CORS
app.UseAuthentication();               // 6. Auth
app.UseAuthorization();                // 7. Authorization
app.MapControllers();                  // 8. Endpoints
app.Run();
```

| Middleware | کاربرد |
|------------|--------|
| `UseExceptionHandler` | مدیریت خطای global |
| `UseHttpsRedirection` | redirect HTTP → HTTPS |
| `UseStaticFiles` | serve wwwroot |
| `UseRouting` | match route |
| `UseAuthentication` | شناسایی کاربر |
| `UseAuthorization` | بررسی دسترسی |

---

## ۶. Middleware vs Filter vs Interceptor

| | Middleware | Action Filter |
|---|-----------|---------------|
| Scope | کل pipeline | فقط MVC actions |
| HTTP-level | ✓ | ✓ |
| Model binding بعد | ✗ | ✓ |

---

## ۷. Conditional Middleware

```csharp
app.UseWhen(
    ctx => ctx.Request.Path.StartsWithSegments("/api"),
    appBuilder => appBuilder.UseMiddleware<ApiKeyMiddleware>()
);
```

---

## ۸. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_pipeline_concept.cs](./examples/01_pipeline_concept.cs) | Pipeline شبیه‌سازی |
| [02_custom_middleware.cs](./examples/02_custom_middleware.cs) | Custom Middleware |
| [03_short_circuit.cs](./examples/03_short_circuit.cs) | Short-circuit |
| [04_logging_timing.cs](./examples/04_logging_timing.cs) | Logging و Timing |

---

## ۹. مثال واقعی — Clinic API

```
Request
  → ExceptionHandler
  → RequestLogging (custom)
  → Timing (custom)
  → Authentication
  → Authorization
  → Controller
  → Response (+ X-Response-Time header)
```

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| `UseAuthorization` قبل از `UseAuthentication` | auth کار نمی‌کند |
| `UseRouting` بعد از endpoints | route match نمی‌شود |
| فراموش `await next()` | pipeline قطع |
| Middleware سنگین | performance |
| State در middleware singleton | thread safety |

---

## ۱۱. تمرین کلاسی

1. Middleware لاگ method + path
2. Middleware بررسی header
3. Timing middleware

---

## ۱۲. نکات حرفه‌ای

- Middleware برای cross-cutting concerns (log, auth, cors)
- `IMiddleware` factory activation برای DI بهتر
- `UseSerilogRequestLogging()` برای production
- ترتیب middleware را در doc تیم ثبت کنید
- Test middleware با `TestServer`

---

## ۱۳. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۳۸ (Web API)، ۴۱ (Decorator)

**جلسه بعد:** Authentication

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۳ — Repository](../session-43-repository-pattern/) | **بعد:** [۴۵ — Authentication](../session-45-authentication/)
