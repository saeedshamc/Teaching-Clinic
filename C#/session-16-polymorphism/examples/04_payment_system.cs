// ============================================================
// جلسه ۱۶ — سیستم اعلان (مثال واقعی)
// فایل: 04_payment_system.cs
// ============================================================

abstract class Notification
{
    public string Message { get; set; } = "";
    public DateTime CreatedAt { get; } = DateTime.Now;
    public abstract void Send();
    public virtual string GetChannel() => "نامشخص";
}

class EmailNotification : Notification
{
    public string Email { get; set; } = "";
    public override void Send() =>
        Console.WriteLine($"📧 [{CreatedAt:HH:mm}] ایمیل به {Email}: {Message}");
    public override string GetChannel() => "Email";
}

class SmsNotification : Notification
{
    public string Phone { get; set; } = "";
    public override void Send() =>
        Console.WriteLine($"📱 [{CreatedAt:HH:mm}] SMS به {Phone}: {Message}");
    public override string GetChannel() => "SMS";
}

class PushNotification : Notification
{
    public string DeviceId { get; set; } = "";
    public override void Send() =>
        Console.WriteLine($"🔔 [{CreatedAt:HH:mm}] Push به {DeviceId}: {Message}");
    public override string GetChannel() => "Push";
}

void NotifyAll(Notification[] notifications)
{
    Console.WriteLine("=== ارسال اعلان‌ها ===");
    foreach (var n in notifications)
        n.Send();  // Polymorphism — هر کانال رفتار خودش
}

void PrintSummary(Notification[] notifications)
{
    var grouped = notifications.GroupBy(n => n.GetChannel());
    Console.WriteLine("\n=== خلاصه کانال‌ها ===");
    foreach (var g in grouped)
        Console.WriteLine($"{g.Key}: {g.Count()} اعلان");
}

Notification[] alerts =
{
    new EmailNotification { Email = "ali@test.com", Message = "سفارش شما ثبت شد" },
    new SmsNotification { Phone = "09121234567", Message = "کد تأیید: 1234" },
    new PushNotification { DeviceId = "device-abc", Message = "پیام جدید" },
    new EmailNotification { Email = "admin@test.com", Message = "گزارش روزانه" }
};

NotifyAll(alerts);
PrintSummary(alerts);
