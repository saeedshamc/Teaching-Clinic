# جلسه ۳۹: ASP.NET MVC (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- درک الگوی **Model-View-Controller** و تفاوت آن با Minimal API
- شناخت ساختار پروژه MVC در ASP.NET Core
- نوشتن Controller، Action و ViewModel ساده
- درک Routing، Model Binding و Validation
- اتصال View به داده با Razor Syntax
- آماده‌سازی برای پروژه وب کامل در جلسه بعد

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع |
|------|--------|
| 0–10 | مرور Web API (جلسه ۳۸) و چرا MVC؟ |
| 10–25 | معماری MVC و جریان درخواست |
| 25–40 | Controller و Action Methods |
| 40–55 | ViewModel، Model Binding، Validation |
| 55–70 | Razor Views و Layout |
| 70–85 | مثال عملی: CRUD ساده محصول |
| 85–90 | جمع‌بندی + تکلیف |

---

## ۱. MVC چیست؟

**MVC** سه لایه جدا دارد:

| لایه | نقش | مثال |
|------|-----|------|
| **Model** | داده و منطق کسب‌وکار | کلاس `Product` |
| **View** | نمایش HTML به کاربر | `Views/Product/Index.cshtml` |
| **Controller** | واسط بین Model و View | `ProductController` |

```
درخواست HTTP → Router → Controller → Model → View → پاسخ HTML
```

### تفاوت MVC و Minimal API

| Minimal API | MVC |
|-------------|-----|
| endpoint-based | Controller + Action |
| JSON پاسخ | HTML + JSON |
| مناسب API | مناسب UI وب |

---

## ۲. ساخت پروژه MVC

```powershell
dotnet new mvc -n ClinicStore
cd ClinicStore
dotnet run
```

ساختار اصلی:

```
ClinicStore/
├── Controllers/       ← کنترلرها
├── Models/            ← مدل‌ها و ViewModel
├── Views/             ← Razor Views
│   ├── Shared/        ← Layout، Partial
│   └── Home/
├── wwwroot/           ← CSS، JS، تصاویر
└── Program.cs
```

---

## ۳. Controller و Action

```csharp
public class ProductController : Controller
{
    public IActionResult Index()
    {
        var products = new List<Product>
        {
            new() { Id = 1, Name = "لپ‌تاپ", Price = 25_000_000 },
            new() { Id = 2, Name = "ماوس", Price = 450_000 }
        };
        return View(products);
    }

    public IActionResult Details(int id)
    {
        var product = FindProduct(id);
        if (product is null) return NotFound();
        return View(product);
    }
}
```

### انواع IActionResult

| متد | کاربرد |
|-----|--------|
| `View()` | برگرداندن HTML |
| `RedirectToAction()` | ریدایرکت |
| `NotFound()` | خطای ۴۰۴ |
| `Json()` | پاسخ JSON |

---

## ۴. Routing

### Convention-based (پیش‌فرض)

```
/Product/Index   → ProductController.Index()
/Product/Details/5 → ProductController.Details(id: 5)
```

### Attribute Routing

```csharp
[Route("api/[controller]")]
public class ProductController : Controller
{
    [HttpGet("{id:int}")]
    public IActionResult GetById(int id) => Json(FindProduct(id));
}
```

---

## ۵. ViewModel و Model Binding

**ViewModel** مدلی است که فقط برای View طراحی شده:

```csharp
public class CreateProductViewModel
{
    [Required(ErrorMessage = "نام الزامی است")]
    [StringLength(100)]
    public string Name { get; set; } = "";

    [Range(1, 999_999_999)]
    public decimal Price { get; set; }
}
```

```csharp
[HttpPost]
public IActionResult Create(CreateProductViewModel model)
{
    if (!ModelState.IsValid)
        return View(model);

    // ذخیره در دیتابیس...
    return RedirectToAction(nameof(Index));
}
```

**Model Binding** خودکار query string، form و route values را به پارامترها map می‌کند.

---

## ۶. Razor Syntax

```html
@model IEnumerable<Product>

<h1>لیست محصولات</h1>
<table>
@foreach (var p in Model)
{
    <tr>
        <td>@p.Name</td>
        <td>@p.Price.ToString("N0")</td>
        <td><a asp-action="Details" asp-route-id="@p.Id">جزئیات</a></td>
    </tr>
}
</table>
```

### Tag Helpers

```html
<form asp-action="Create" method="post">
    <input asp-for="Name" class="form-control" />
    <span asp-validation-for="Name"></span>
    <button type="submit">ذخیره</button>
</form>
```

---

## ۷. Layout و Partial View

**`_Layout.cshtml`** — قالب مشترک:

```html
<!DOCTYPE html>
<html>
<head><title>@ViewData["Title"]</title></head>
<body>
    <nav>...</nav>
    @RenderBody()
    @await RenderSectionAsync("Scripts", required: false)
</body>
</html>
```

**Partial View** — قطعه قابل استفاده مجدد:

```html
@await Html.PartialAsync("_ProductCard", product)
```

---

## ۸. Dependency Injection در MVC

```csharp
public class ProductController : Controller
{
    private readonly IProductService _service;

    public ProductController(IProductService service)
    {
        _service = service;
    }

    public async Task<IActionResult> Index()
        => View(await _service.GetAllAsync());
}
```

ثبت سرویس در `Program.cs`:

```csharp
builder.Services.AddScoped<IProductService, ProductService>();
```

---

## ۹. فایل‌های این جلسه

| فایل | موضوع |
|------|--------|
| [01_mvc_flow.cs](./examples/01_mvc_flow.cs) | شبیه‌سازی جریان MVC |
| [02_controller_actions.cs](./examples/02_controller_actions.cs) | Action و Routing |
| [03_viewmodel_validation.cs](./examples/03_viewmodel_validation.cs) | ViewModel و Validation |
| [04_razor_concepts.cs](./examples/04_razor_concepts.cs) | مفاهیم Razor |

---

## ۱۰. مثال واقعی — فروشگاه آنلاین

```csharp
// Controller
public IActionResult Search(string? q)
{
    var results = _products
        .Where(p => string.IsNullOrEmpty(q) || p.Name.Contains(q))
        .ToList();
    ViewBag.Query = q;
    return View(results);
}
```

```html
<!-- View -->
<form method="get">
    <input name="q" value="@ViewBag.Query" placeholder="جستجو..." />
    <button type="submit">جستجو</button>
</form>
```

---

## ۱۱. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| منطق DB در View | نقض Separation of Concerns | انتقال به Service |
| ViewModel = Entity | افشای فیلدهای حساس | ViewModel جدا |
| فراموش `[HttpPost]` | GET به‌جای POST | Attribute درست |
| `return View()` بدون model | View خالی | model مناسب بدهید |
| Action هم‌نام View نباشد | View پیدا نشود | نام‌گذاری Convention |

---

## ۱۲. تمرین کلاسی (در کلاس)

1. Controller با دو Action: `Index` و `About`
2. ViewModel با `[Required]` و `[Range]`
3. لیست محصول با foreach در خروجی Console (شبیه‌سازی View)

---

## ۱۳. نکات حرفه‌ای

- Controller باید **نازک** باشد؛ منطق در Service
- از **ViewModel** برای فرم‌ها استفاده کنید، نه Entity مستقیم
- **Tag Helpers** خوانایی HTML را بالا می‌برند
- برای API خالص، MVC لازم نیست — Minimal API کافی است
- `dotnet watch run` برای توسعه سریع‌تر

---

## ۱۴. پیش‌نیاز و ادامه

**پیش‌نیاز:** جلسه ۳۸ (Web API)، جلسه ۳۶ (DI)

**جلسه بعد:** ساخت پروژه وب کامل با لایه‌بندی

**تکلیف:** [question.md](./exercises/question.md)

**جلسه قبل:** [۳۸ — Web API](../session-38-web-api/) | **بعد:** [۴۰ — پروژه وب](../session-40-web-project/)
