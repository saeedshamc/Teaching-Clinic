// ============================================================
// جلسه ۳۹ — ASP.NET MVC
// فایل: 03_viewmodel_validation.cs — ViewModel و Validation
// ============================================================

using System.ComponentModel.DataAnnotations;

class CreateProductViewModel
{
    [Required(ErrorMessage = "نام محصول الزامی است")]
    [StringLength(100, MinimumLength = 2)]
    public string Name { get; set; } = "";

    [Range(1, 999_999_999, ErrorMessage = "قیمت باید بین ۱ تا ۹۹۹ میلیون باشد")]
    public decimal Price { get; set; }

    [EmailAddress(ErrorMessage = "ایمیل نامعتبر است")]
    public string? ContactEmail { get; set; }
}

bool ValidateModel(object model, out List<string> errors)
{
    errors = new List<string>();
    var context = new ValidationContext(model);
    var results = new List<ValidationResult>();
    if (!Validator.TryValidateObject(model, context, results, true))
        errors.AddRange(results.Select(r => r.ErrorMessage ?? "خطای نامشخص"));
    return errors.Count == 0;
}

// تست ۱: داده معتبر
var valid = new CreateProductViewModel
{
    Name = "لپ‌تاپ",
    Price = 25_000_000,
    ContactEmail = "shop@example.com"
};
Console.WriteLine("=== تست معتبر ===");
Console.WriteLine(ValidateModel(valid, out var e1) ? "✓ ModelState.IsValid" : string.Join("\n", e1));

// تست ۲: داده نامعتبر
var invalid = new CreateProductViewModel
{
    Name = "",
    Price = -100,
    ContactEmail = "not-an-email"
};
Console.WriteLine("\n=== تست نامعتبر ===");
if (!ValidateModel(invalid, out var e2))
    foreach (var err in e2)
        Console.WriteLine($"  ✗ {err}");

// شبیه‌سازی POST Action
Console.WriteLine("\n=== شبیه‌سازی Controller.Create ===");
CreateProductViewModel posted = invalid;
if (!ValidateModel(posted, out _))
    Console.WriteLine("  return View(model); // نمایش فرم با خطاها");
else
    Console.WriteLine("  // ذخیره و RedirectToAction(Index)");
