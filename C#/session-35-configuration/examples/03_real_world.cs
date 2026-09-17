// ============================================================
// جلسه ۳۵ — Configuration: Options Pattern
// ============================================================

var emailSettings = new EmailSettings
{
    SmtpHost = "smtp.gmail.com",
    SmtpPort = 587,
    FromAddress = "noreply@clinic.com"
};

var emailService = new EmailService(new OptionsWrapper<EmailSettings>(emailSettings));
emailService.Send("patient@example.com", "نوبت شما تأیید شد");

public class EmailSettings
{
    public string SmtpHost { get; set; } = "";
    public int SmtpPort { get; set; } = 587;
    public string FromAddress { get; set; } = "";
}

public interface IOptions<T> where T : class, new() { T Value { get; } }

public class OptionsWrapper<T> : IOptions<T> where T : class, new()
{
    public T Value { get; }
    public OptionsWrapper(T value) => Value = value;
}

public class EmailService
{
    private readonly EmailSettings _settings;
    public EmailService(IOptions<EmailSettings> options) => _settings = options.Value;

    public void Send(string to, string subject)
    {
        Console.WriteLine($"ارسال ایمیل از {_settings.FromAddress}");
        Console.WriteLine($"  SMTP: {_settings.SmtpHost}:{_settings.SmtpPort}");
        Console.WriteLine($"  To: {to} | Subject: {subject}");
    }
}
