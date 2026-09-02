// ============================================================
// جلسه ۲۶ — Extension Methods: اعتبارسنجی واقعی
// ============================================================

var validUser = new UserForm("علی", "ali@test.com", "09121234567");
var invalidUser = new UserForm("ا", "bad-email", "123");
Console.WriteLine(validUser.Validate());
Console.WriteLine(invalidUser.Validate());

public record UserForm(string Name, string Email, string Mobile);

public static class ValidationExtensions
{
    public static bool IsValidEmail(this string email)
    {
        return !string.IsNullOrWhiteSpace(email)
               && email.Contains('@')
               && email.IndexOf('@') < email.LastIndexOf('.');
    }

    public static bool IsValidIranianMobile(this string mobile)
    {
        return mobile?.Length == 11 && mobile.StartsWith("09");
    }

    public static bool IsValidName(this string name)
    {
        return !string.IsNullOrWhiteSpace(name) && name.Length >= 2;
    }
}

public static class UserFormExtensions
{
    public static (bool IsValid, string Message) Validate(this UserForm form)
    {
        if (!form.Name.IsValidName()) return (false, "نام نامعتبر است");
        if (!form.Email.IsValidEmail()) return (false, "ایمیل نامعتبر است");
        if (!form.Mobile.IsValidIranianMobile()) return (false, "موبایل نامعتبر است");
        return (true, "فرم معتبر است");
    }
}
