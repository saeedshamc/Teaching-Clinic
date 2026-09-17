# جلسه ۴۹: Deployment (استقرار) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- Publish کردن ASP.NET Core app
- Self-contained vs Framework-dependent deployment
- Docker containerization
- Environment configuration در production
- CI/CD pipeline مقدماتی
- Monitoring و Health Checks

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | Deployment options overview |
| 10–25 | dotnet publish |
| 25–40 | Environment و secrets |
| 40–55 | Docker و Dockerfile |
| 55–70 | Health Checks |
| 70–85 | CI/CD با GitHub Actions |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Deployment Options

| روش | مناسب برای |
|-----|-----------|
| IIS (Windows) | Enterprise Windows |
| Linux + Nginx | Production رایج |
| Docker | Cloud-native |
| Azure App Service | PaaS |
| Kubernetes | Scale بزرگ |

---

## ۲. dotnet publish

```powershell
# Framework-dependent (نیاز به runtime نصب‌شده)
dotnet publish -c Release -o ./publish

# Self-contained (runtime همراه)
dotnet publish -c Release -r win-x64 --self-contained -o ./publish

# Single file
dotnet publish -c Release -r linux-x64 --self-contained -p:PublishSingleFile=true
```

### خروجی publish

```
publish/
├── ClinicApp.dll
├── ClinicApp.exe (Windows)
├── appsettings.json
├── web.config (IIS)
└── dependencies...
```

---

## ۳. Environment Configuration

```powershell
# Linux
export ASPNETCORE_ENVIRONMENT=Production
export ConnectionStrings__Default="Server=..."

# Windows
set ASPNETCORE_ENVIRONMENT=Production
```

**Secrets:** هرگز در source code!

```powershell
dotnet user-secrets set "ConnectionStrings:Default" "Server=..."
# Production: Azure Key Vault, AWS Secrets Manager, env vars
```

---

## ۴. Dockerfile

```dockerfile
FROM mcr.microsoft.com/dotnet/aspnet:8.0 AS base
WORKDIR /app
EXPOSE 8080

FROM mcr.microsoft.com/dotnet/sdk:8.0 AS build
WORKDIR /src
COPY ["ClinicApp.Web/ClinicApp.Web.csproj", "ClinicApp.Web/"]
RUN dotnet restore "ClinicApp.Web/ClinicApp.Web.csproj"
COPY . .
RUN dotnet publish "ClinicApp.Web/ClinicApp.Web.csproj" -c Release -o /app/publish

FROM base AS final
WORKDIR /app
COPY --from=app/publish .
ENTRYPOINT ["dotnet", "ClinicApp.dll"]
```

```powershell
docker build -t clinic-app .
docker run -p 8080:8080 -e ASPNETCORE_ENVIRONMENT=Production clinic-app
```

---

## ۵. Nginx Reverse Proxy

```nginx
server {
    listen 80;
    location / {
        proxy_pass http://localhost:5000;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection keep-alive;
        proxy_set_header Host $host;
    }
}
```

---

## ۶. Health Checks

```csharp
builder.Services.AddHealthChecks()
    .AddDbContextCheck<AppDbContext>()
    .AddCheck("self", () => HealthCheckResult.Healthy());

app.MapHealthChecks("/health");
app.MapHealthChecks("/health/ready", new HealthCheckOptions
{
    Predicate = check => check.Tags.Contains("ready")
});
```

```json
// GET /health
{ "status": "Healthy", "totalDuration": "00:00:00.012" }
```

---

## ۷. CI/CD — GitHub Actions

```yaml
name: Deploy
on:
  push:
    branches: [main]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-dotnet@v4
        with:
          dotnet-version: '8.0.x'
      - run: dotnet restore
      - run: dotnet build --no-restore -c Release
      - run: dotnet test --no-build -c Release
      - run: dotnet publish -c Release -o ./publish
```

---

## ۸. Logging در Production

```csharp
builder.Host.UseSerilog((ctx, config) =>
    config.ReadFrom.Configuration(ctx.Configuration));
```

- Structured logs → Seq, ELK, Application Insights
- Log level: Production = Information (not Debug)

---

## ۹. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_publish_concept.cs](./examples/01_publish_concept.cs) | Publish simulation |
| [02_environment_config.cs](./examples/02_environment_config.cs) | Environment vars |
| [03_dockerfile_concept.cs](./examples/03_dockerfile_concept.cs) | Docker concept |
| [04_health_checks.cs](./examples/04_health_checks.cs) | Health checks |

---

## ۱۰. Pre-deployment Checklist

| ✓ | Item |
|---|------|
| □ | `ASPNETCORE_ENVIRONMENT=Production` |
| □ | Secrets در env/Key Vault |
| □ | HTTPS فعال |
| □ | Health check endpoint |
| □ | Logging configured |
| □ | Database migrations applied |
| □ | `dotnet test` passed |

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| Development settings در prod | امنیت |
| Connection string در git | leak |
| Debug build در prod | performance |
| بدون health check | monitoring blind |
| Migration manual فراموش | schema mismatch |

---

## ۱۲. تمرین کلاسی

1. Publish script بنویسید
2. Environment config loader
3. Health check شبیه‌سازی

---

## ۱۳. نکات حرفه‌ای

- Blue-green deployment برای zero downtime
- `ASPNETCORE_URLS=http://+:8080` در Docker
- `.dockerignore` برای build سریع
- Rollback plan داشته باشید
- Staging environment قبل از prod

---

## ۱۴. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۴۰ (پروژه وب)، ۴۷ (Performance)

**جلسه بعد:** Final Capstone

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۸ — Debugging](../session-48-debugging/) | **بعد:** [۵۰ — Capstone](../session-50-final-capstone/)
