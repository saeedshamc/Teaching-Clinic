// ============================================================
// جلسه ۲۳ — Custom EventArgs
// فایل: 03_custom_eventargs.cs
// ============================================================

class OrderEventArgs : EventArgs
{
    public string OrderId { get; }
    public double Amount { get; }
    public DateTime Timestamp { get; }

    public OrderEventArgs(string orderId, double amount)
    {
        OrderId = orderId;
        Amount = amount;
        Timestamp = DateTime.Now;
    }
}

class OrderService
{
    public event EventHandler<OrderEventArgs>? OrderPlaced;
    public event EventHandler<OrderEventArgs>? OrderCancelled;

    public void PlaceOrder(string id, double amount)
    {
        var args = new OrderEventArgs(id, amount);
        Console.WriteLine($"📦 سفارش {id} ثبت شد — {amount:N0} تومان");
        OrderPlaced?.Invoke(this, args);
    }

    public void CancelOrder(string id, double amount)
    {
        var args = new OrderEventArgs(id, amount);
        Console.WriteLine($"❌ سفارش {id} لغو شد");
        OrderCancelled?.Invoke(this, args);
    }
}

class TemperatureChangedEventArgs : EventArgs
{
    public double OldTemp { get; }
    public double NewTemp { get; }
    public double Change => NewTemp - OldTemp;

    public TemperatureChangedEventArgs(double oldTemp, double newTemp)
    {
        OldTemp = oldTemp;
        NewTemp = newTemp;
    }
}

class TemperatureSensor
{
    private double _temperature = 20;
    public event EventHandler<TemperatureChangedEventArgs>? TemperatureChanged;

    public double Temperature
    {
        get => _temperature;
        set
        {
            if (Math.Abs(value - _temperature) < 0.01) return;
            var args = new TemperatureChangedEventArgs(_temperature, value);
            _temperature = value;
            TemperatureChanged?.Invoke(this, args);
        }
    }
}

// Order events
var orderService = new OrderService();
orderService.OrderPlaced += (s, e) =>
    Console.WriteLine($"  [LOG] سفارش {e.OrderId} — {e.Amount:N0} — {e.Timestamp:HH:mm:ss}");
orderService.OrderCancelled += (s, e) =>
    Console.WriteLine($"  [LOG] لغو {e.OrderId}");

orderService.PlaceOrder("ORD-001", 1_500_000);
orderService.CancelOrder("ORD-002", 500_000);

// Temperature
Console.WriteLine();
var sensor = new TemperatureSensor();
sensor.TemperatureChanged += (s, e) =>
    Console.WriteLine($"  🌡️ {e.OldTemp}° → {e.NewTemp}° (تغییر: {e.Change:+#.#;-#.#;0}°)");
sensor.Temperature = 22;
sensor.Temperature = 28;
sensor.Temperature = 28;  // بدون event — تغییر نکرده
