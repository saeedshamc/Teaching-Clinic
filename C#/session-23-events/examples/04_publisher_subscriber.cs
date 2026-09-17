// ============================================================
// جلسه ۲۳ — Publisher-Subscriber کامل
// فایل: 04_publisher_subscriber.cs
// ============================================================

class NewsEventArgs : EventArgs
{
    public string Headline { get; }
    public string Category { get; }
    public NewsEventArgs(string headline, string category)
    {
        Headline = headline;
        Category = category;
    }
}

// Publisher
class NewsAgency
{
    public event EventHandler<NewsEventArgs>? NewsPublished;

    public void Publish(string headline, string category)
    {
        Console.WriteLine($"📰 انتشار: [{category}] {headline}");
        NewsPublished?.Invoke(this, new NewsEventArgs(headline, category));
    }
}

// Subscribers
class EmailSubscriber
{
    private readonly string _email;
    public EmailSubscriber(string email) => _email = email;

    public void OnNewsReceived(object? sender, NewsEventArgs e) =>
        Console.WriteLine($"  📧 ایمیل به {_email}: {e.Headline}");
}

class SmsSubscriber
{
    private readonly string _phone;
    public SmsSubscriber(string phone) => _phone = phone;

    public void OnNewsReceived(object? sender, NewsEventArgs e)
    {
        if (e.Category == "فوری")
            Console.WriteLine($"  📱 SMS به {_phone}: {e.Headline}");
    }
}

class NewsLogger
{
    public void OnNewsReceived(object? sender, NewsEventArgs e) =>
        Console.WriteLine($"  📝 LOG: [{e.Category}] {e.Headline}");
}

// Setup
var agency = new NewsAgency();
var emailSub = new EmailSubscriber("ali@test.com");
var smsSub = new SmsSubscriber("09121234567");
var logger = new NewsLogger();

agency.NewsPublished += emailSub.OnNewsReceived;
agency.NewsPublished += smsSub.OnNewsReceived;
agency.NewsPublished += logger.OnNewsReceived;

agency.Publish("نسخه جدید C# منتشر شد", "فناوری");
agency.Publish("هشدار آب‌وهوایی", "فوری");

// Unsubscribe
Console.WriteLine("\n--- بعد از لغو اشتراک ایمیل ---");
agency.NewsPublished -= emailSub.OnNewsReceived;
agency.Publish("خبر بدون ایمیل", "ورزشی");
