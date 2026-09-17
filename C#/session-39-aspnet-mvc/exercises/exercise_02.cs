// ============================================================
// تمرین ۲ جلسه ۳۹ — ViewModel و Validation (پاسخ نمونه)
// ============================================================

using System.ComponentModel.DataAnnotations;

class RegisterViewModel
{
    [Required(ErrorMessage = "نام کاربری الزامی است")]
    [StringLength(50, MinimumLength = 3)]
    public string Username { get; set; } = "";

    [Required]
    [EmailAddress(ErrorMessage = "ایمیل نامعتبر است")]
    public string Email { get; set; } = "";

    [Range(13, 120, ErrorMessage = "سن باید بین ۱۳ تا ۱۲۰ باشد")]
    public int Age { get; set; }
}

void ValidateAndPrint(string label, RegisterViewModel model)
{
    Console.WriteLine($"=== {label} ===");
    var context = new ValidationContext(model);
    var results = new List<ValidationResult>();
    if (Validator.TryValidateObject(model, context, results, true))
        Console.WriteLine("  ✓ ModelState.IsValid");
    else
        foreach (var r in results)
            Console.WriteLine($"  ✗ {r.ErrorMessage}");
    Console.WriteLine();
}

ValidateAndPrint("مدل معتبر", new RegisterViewModel
{
    Username = "ali_dev",
    Email = "ali@example.com",
    Age = 25
});

ValidateAndPrint("مدل نامعتبر", new RegisterViewModel
{
    Username = "ab",
    Email = "invalid",
    Age = 10
});
