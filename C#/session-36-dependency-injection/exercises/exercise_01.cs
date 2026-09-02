// ============================================================
// پاسخ نمونه — تمرین جلسه ۳۶
// ============================================================

public interface INotificationSender
{
    void Send(string to, string message);
}

public class EmailSender : INotificationSender
{
    public void Send(string to, string msg) =>
        Console.WriteLine($"[Email] To: {to} — {msg}");
}

public class SmsSender : INotificationSender
{
    public void Send(string to, string msg) =>
        Console.WriteLine($"[SMS] To: {to} — {msg}");
}

public class NotificationService
{
    private readonly INotificationSender _sender;
    public NotificationService(INotificationSender sender) => _sender = sender;

    public void Notify(string recipient, string message) =>
        _sender.Send(recipient, message);
}

var emailService = new NotificationService(new EmailSender());
emailService.Notify("user@test.com", "نوبت تأیید شد");

var smsService = new NotificationService(new SmsSender());
smsService.Notify("09121234567", "یادآوری نوبت");
