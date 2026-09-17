// ============================================================
// جلسه ۴۲ — SOLID: Single Responsibility Principle
// ============================================================

class User { public string Name { get; set; } = ""; public string Email { get; set; } = ""; }

// ❌ Bad
class UserManager
{
    public void Save(User user) => Console.WriteLine($"  DB: ذخیره {user.Name}");
    public void SendEmail(User user) => Console.WriteLine($"  Email: خوش آمد {user.Email}");
    public string GenerateReport() => "گزارش ۱۰ کاربر";
}

// ✅ Good — SRP
class UserRepository
{
    public void Save(User user) => Console.WriteLine($"  DB: ذخیره {user.Name}");
}

class EmailService
{
    public void SendWelcome(User user) => Console.WriteLine($"  Email: خوش آمد {user.Email}");
}

class UserReportGenerator
{
    public string Generate() => "گزارش ۱۰ کاربر";
}

class UserRegistrationOrchestrator
{
    private readonly UserRepository _repo;
    private readonly EmailService _email;
    public UserRegistrationOrchestrator(UserRepository repo, EmailService email)
    {
        _repo = repo; _email = email;
    }

    public void Register(User user)
    {
        _repo.Save(user);
        _email.SendWelcome(user);
    }
}

Console.WriteLine("=== ❌ Bad: UserManager ===");
new UserManager().Save(new User { Name = "Ali", Email = "a@x.com" });

Console.WriteLine("\n=== ✅ Good: SRP ===");
var orchestrator = new UserRegistrationOrchestrator(new UserRepository(), new EmailService());
orchestrator.Register(new User { Name = "Mariam", Email = "m@x.com" });
Console.WriteLine($"  Report: {new UserReportGenerator().Generate()}");
