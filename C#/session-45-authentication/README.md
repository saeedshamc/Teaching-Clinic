# جلسه ۴۵: Authentication (احراز هویت) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تفاوت Authentication و Authorization
- پیاده‌سازی JWT Bearer Token
- Cookie Authentication برای MVC
- Identity Framework مقدماتی
- `[Authorize]` و Policy-based Authorization
- امنیت: hash password، HTTPS، token expiry

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | Auth vs Authz |
| 10–25 | JWT ساختار و flow |
| 25–40 | تولید و validate token |
| 40–55 | `[Authorize]` و Roles |
| 55–70 | Password hashing |
| 70–85 | Identity Framework overview |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. Authentication vs Authorization

| | Authentication | Authorization |
|---|----------------|---------------|
| سوال | «تو کی هستی؟» | «اجازه داری؟» |
| مثال | Login | Admin-only endpoint |
| HTTP | 401 Unauthorized | 403 Forbidden |

---

## ۲. JWT (JSON Web Token)

```
Header.Payload.Signature
```

```json
// Payload
{
  "sub": "user123",
  "name": "Ali",
  "role": "Admin",
  "exp": 1735689600
}
```

### Flow

```
1. POST /login (username, password)
2. Server validates → returns JWT
3. Client: Authorization: Bearer <token>
4. Server validates signature + expiry
```

---

## ۳. JWT در ASP.NET Core

```csharp
builder.Services.AddAuthentication(JwtBearerDefaults.AuthenticationScheme)
    .AddJwtBearer(options =>
    {
        options.TokenValidationParameters = new TokenValidationParameters
        {
            ValidateIssuer = true,
            ValidIssuer = builder.Configuration["Jwt:Issuer"],
            ValidateAudience = true,
            ValidAudience = builder.Configuration["Jwt:Audience"],
            ValidateLifetime = true,
            IssuerSigningKey = new SymmetricSecurityKey(
                Encoding.UTF8.GetBytes(builder.Configuration["Jwt:Key"]!))
        };
    });

builder.Services.AddAuthorization();
// ...
app.UseAuthentication();
app.UseAuthorization();
```

---

## ۴. تولید Token

```csharp
public string GenerateToken(User user)
{
    var claims = new[]
    {
        new Claim(ClaimTypes.NameIdentifier, user.Id.ToString()),
        new Claim(ClaimTypes.Name, user.Username),
        new Claim(ClaimTypes.Role, user.Role)
    };

    var key = new SymmetricSecurityKey(Encoding.UTF8.GetBytes(_config["Jwt:Key"]!));
    var creds = new SigningCredentials(key, SecurityAlgorithms.HmacSha256);

    var token = new JwtSecurityToken(
        issuer: _config["Jwt:Issuer"],
        audience: _config["Jwt:Audience"],
        claims: claims,
        expires: DateTime.UtcNow.AddHours(1),
        signingCredentials: creds);

    return new JwtSecurityTokenHandler().WriteToken(token);
}
```

---

## ۵. Authorization

```csharp
[Authorize]
[ApiController]
public class PatientsController : ControllerBase { }

[Authorize(Roles = "Admin")]
[HttpDelete("{id}")]
public IActionResult Delete(int id) { }

[Authorize(Policy = "CanManageAppointments")]
public IActionResult Manage() { }
```

```csharp
builder.Services.AddAuthorization(options =>
{
    options.AddPolicy("CanManageAppointments", policy =>
        policy.RequireRole("Admin", "Doctor"));
});
```

---

## ۶. Password Hashing

```csharp
// ✅ Hash با BCrypt یا ASP.NET Identity
string hash = BCrypt.Net.BCrypt.HashPassword("MyPassword123");
bool valid = BCrypt.Net.BCrypt.Verify("MyPassword123", hash);

// ❌ هرگز plain text یا MD5
```

ASP.NET Identity:

```csharp
var hasher = new PasswordHasher<User>();
string hash = hasher.HashPassword(user, password);
var result = hasher.VerifyHashedPassword(user, hash, password);
```

---

## ۷. Cookie Authentication (MVC)

```csharp
builder.Services.AddAuthentication(CookieAuthenticationDefaults.AuthenticationScheme)
    .AddCookie(options =>
    {
        options.LoginPath = "/Account/Login";
        options.ExpireTimeSpan = TimeSpan.FromHours(8);
    });
```

---

## ۸. ASP.NET Identity

```powershell
dotnet add package Microsoft.AspNetCore.Identity.EntityFrameworkCore
```

```csharp
builder.Services.AddIdentity<ApplicationUser, IdentityRole>()
    .AddEntityFrameworkStores<AppDbContext>();
```

Features: UserManager, RoleManager, SignInManager, token providers

---

## ۹. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_auth_vs_authz.cs](./examples/01_auth_vs_authz.cs) | Auth vs Authz |
| [02_jwt_concept.cs](./examples/02_jwt_concept.cs) | JWT structure |
| [03_password_hashing.cs](./examples/03_password_hashing.cs) | Password hash |
| [04_authorization_roles.cs](./examples/04_authorization_roles.cs) | Roles و Policy |

---

## ۱۰. امنیت

| قانون | دلیل |
|-------|------|
| HTTPS only | token interception |
| Token expiry کوتاه | کاهش ریسک |
| Refresh token | UX + امنیت |
| Secret در env var | نه در source code |
| Rate limit login | brute force |

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل |
|--------|------|
| JWT secret ضعیف | forgery |
| Token بدون expiry | دائمی |
| Password plain text | breach |
| `[Authorize]` بدون `UseAuthentication` | auth کار نمی‌کند |
| Role در client-side فقط | bypass |

---

## ۱۲. تمرین کلاسی

1. Login flow شبیه‌سازی
2. Password hash و verify
3. Role-based access check

---

## ۱۳. نکات حرفه‌ای

- JWT stateless است — revoke سخت‌تر از session
- برای SPA: JWT + refresh token pattern
- Identity برای app کامل؛ JWT manual برای API ساده
- `ClaimsPrincipal` در `HttpContext.User`
- Audit log برای login attempts

---

## ۱۴. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۴۴ (Middleware)

**جلسه بعد:** REST Best Practices

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۴۴ — Middleware](../session-44-middleware/) | **بعد:** [۴۶ — REST](../session-46-rest-best-practices/)
