// ============================================================
// جلسه ۲۳ — Event: مبانی
// فایل: 01_event_basics.cs
// ============================================================

class Button
{
    // event — فقط داخل کلاس قابل invoke
    public event Action? Clicked;

    public string Label { get; set; } = "دکمه";

    public void SimulateClick()
    {
        Console.WriteLine($"[{Label}] کلیک شد");
        Clicked?.Invoke();  // فقط Button می‌تواند فراخوانی کند
    }
}

class Counter
{
    private int _count;
    public event Action<int>? CountChanged;

    public int Value
    {
        get => _count;
        set
        {
            _count = value;
            CountChanged?.Invoke(_count);
        }
    }

    public void Increment() => Value++;
}

// Subscribe
var btn = new Button { Label = "ثبت" };
btn.Clicked += () => Console.WriteLine("  → عملیات ثبت انجام شد");
btn.Clicked += () => Console.WriteLine("  → لاگ ثبت شد");
btn.SimulateClick();

// Unsubscribe
btn.Clicked -= () => Console.WriteLine("  → عملیات ثبت انجام شد");
// ⚠️ Lambda جدید است — unsubscribe واقعی نمی‌شود!
// برای unsubscribe واقعی، reference نگه دارید

Console.WriteLine();
var counter = new Counter();
counter.CountChanged += val => Console.WriteLine($"  شمارنده: {val}");
counter.Increment();
counter.Increment();
counter.Increment();

// btn.Clicked();  // ❌ compile error — بیرون نمی‌توان invoke کرد
