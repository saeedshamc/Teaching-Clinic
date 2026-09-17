# جلسه ۲۳: Event (رویداد) (۱.۵ ساعت)

## 🎯 اهداف یادگیری

- تفاوت **Event** و **Delegate** — Event محدودیت دسترسی دارد
- تعریف event با `event` keyword
- **Publisher-Subscriber Pattern**
- `EventHandler` و `EventHandler<TEventArgs>`
- Custom EventArgs
- Unsubscribe — جلوگیری از memory leak

---

## ⏱️ برنامه زمانی (۹۰ دقیقه)

| زمان | موضوع | فعالیت |
|------|--------|--------|
| 0–10 | مرور | Delegate و Multicast |
| 10–30 | Event Basics | تعریف و subscribe |
| 30–50 | EventHandler<T> | الگوی استاندارد .NET |
| 50–65 | Custom EventArgs | داده سفارشی |
| 65–80 | تمرین | دکمه و سنسور |
| 80–90 | جمع‌بندی + تکلیف |

---

## ۱. Event vs Delegate

```csharp
class Button
{
    // ❌ delegate عمومی — هرکسی می‌تواند فراخوانی کند!
    // public Action Clicked;

    // ✅ event — فقط داخل کلاس invoke می‌شود
    public event Action? Clicked;

    public void SimulateClick()
    {
        Clicked?.Invoke();  // فقط اینجا
    }
}

var btn = new Button();
btn.Clicked += () => Console.WriteLine("کلیک!");
btn.SimulateClick();
// btn.Clicked();  // ❌ compile error — فقط Button می‌تواند
```

**Event = Delegate + Encapsulation**

---

## ۲. Publisher-Subscriber

```
Publisher (منبع)  ──event──→  Subscriber (مصرف‌کننده)
   Button                         UI Handler
   StockPrice                     Alert Service
   Timer                          Logger
```

```csharp
class StockMonitor
{
    public event Action<string, double>? PriceChanged;

    public void UpdatePrice(string symbol, double price)
    {
        Console.WriteLine($"قیمت {symbol}: {price}");
        PriceChanged?.Invoke(symbol, price);
    }
}

var monitor = new StockMonitor();
monitor.PriceChanged += (symbol, price) =>
{
    if (price > 100) Console.WriteLine($"⚠️ {symbol} بالای 100!");
};

monitor.UpdatePrice("AAPL", 150);
```

---

## ۳. EventHandler استاندارد

```csharp
class DownloadManager
{
    public event EventHandler<string>? DownloadCompleted;

    public void Download(string fileName)
    {
        Console.WriteLine($"دانلود {fileName}...");
        DownloadCompleted?.Invoke(this, fileName);
    }
}

var dm = new DownloadManager();
dm.DownloadCompleted += (sender, fileName) =>
    Console.WriteLine($"✅ {fileName} آماده است");

dm.Download("report.pdf");
```

**امضای استاندارد:** `void Handler(object sender, EventArgs e)`

---

## ۴. EventHandler<TEventArgs>

```csharp
class OrderEventArgs : EventArgs
{
    public string OrderId { get; }
    public double Amount { get; }
    public OrderEventArgs(string id, double amount)
    {
        OrderId = id;
        Amount = amount;
    }
}

class OrderService
{
    public event EventHandler<OrderEventArgs>? OrderPlaced;

    public void PlaceOrder(string id, double amount)
    {
        Console.WriteLine($"ثبت سفارش {id}");
        OrderPlaced?.Invoke(this, new OrderEventArgs(id, amount));
    }
}
```

---

## ۵. Subscribe و Unsubscribe

```csharp
void OnPriceAlert(object? sender, double price)
{
    Console.WriteLine($"Alert: {price}");
}

monitor.PriceChanged += OnPriceAlert;    // subscribe
monitor.PriceChanged -= OnPriceAlert;    // unsubscribe

// Lambda — برای unsubscribe باید reference نگه دارید
Action<string, double> handler = (s, p) => Console.WriteLine(s);
monitor.PriceChanged += handler;
monitor.PriceChanged -= handler;
```

**Memory Leak:** اگر unsubscribe نکنید، subscriber زنده می‌ماند.

---

## ۶. Null-conditional Invoke

```csharp
// ✅ امن — اگر هیچ subscriber نباشد
Clicked?.Invoke();

// ❌ NullReferenceException
Clicked.Invoke();
```

---

## ۷. فایل‌های جلسه

| فایل | موضوع |
|------|--------|
| [01_event_basics.cs](./examples/01_event_basics.cs) | event vs delegate |
| [02_event_handler.cs](./examples/02_event_handler.cs) | EventHandler<T> |
| [03_custom_eventargs.cs](./examples/03_custom_eventargs.cs) | EventArgs سفارشی |
| [04_publisher_subscriber.cs](./examples/04_publisher_subscriber.cs) | الگوی کامل |

---

## ۸. مثال واقعی — Timer

```csharp
class SimpleTimer
{
    public event EventHandler? Tick;
    private int _seconds;

    public void Start(int seconds)
    {
        for (_seconds = seconds; _seconds > 0; _seconds--)
        {
            Tick?.Invoke(this, EventArgs.Empty);
            Thread.Sleep(1000);
        }
    }
}
```

---

## ۹. اجرای مثال‌ها

```powershell
./run-example.ps1 session-23-events/examples/01_event_basics.cs
```

---

## ۱۰. اشتباهات رایج

| اشتباه | مشکل | راه‌حل |
|--------|------|--------|
| `public Action` به‌جای event | invoke از بیرون | `public event Action` |
| فراموش unsubscribe | memory leak | `-=` در Dispose |
| Exception در handler | بقیه اجرا نمی‌شوند | try-catch در handler |
| `sender` null | فرض null | `object? sender` |
| EventArgs mutable | race condition | immutable EventArgs |

---

## ۱۱. Event در .NET

| Event | کلاس |
|-------|------|
| `Click` | Button (WinForms/WPF) |
| `PropertyChanged` | INotifyPropertyChanged |
| `CollectionChanged` | ObservableCollection |
| `Elapsed` | Timer |

---

## ۱۲. تمرین کلاسی

1. `Button` — event `Clicked`
2. `TemperatureSensor` — event `TemperatureChanged` با EventArgs
3. `BankAccount` — event `BalanceChanged`

---

## ۱۳. تکلیف

**تکلیف:** [question.md](./exercises/question.md)

پاسخ: [exercise_01.cs](./exercises/exercise_01.cs) | [exercise_02.cs](./exercises/exercise_02.cs)

---

## ۱۴. خلاصه

```
event              → delegate محافظت‌شده
+= handler         → subscribe
-= handler         → unsubscribe
?.Invoke()         → فراخوانی امن
EventHandler<T>    → الگوی استاندارد .NET
Publisher/Subscriber → الگوی طراحی
```

---

**جلسه قبل:** [۲۲ — Delegates](../session-22-delegates/) | **بعد:** [۲۴ — LINQ Basics](../session-24-linq-basics/)
