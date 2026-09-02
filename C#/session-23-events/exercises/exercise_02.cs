// تمرین ۴–۵

class StockMonitor
{
    public event EventHandler<double>? PriceAlert;
    public double Threshold { get; set; } = 50_000_000;
    public void UpdatePrice(double price)
    {
        Console.WriteLine($"قیمت: {price:N0}");
        if (price > Threshold) PriceAlert?.Invoke(this, price);
    }
}

class SimpleTimer
{
    public event EventHandler<int>? Tick;
    public void Countdown(int seconds)
    {
        for (int i = seconds; i >= 0; i--)
        {
            Tick?.Invoke(this, i);
            Thread.Sleep(300);
        }
    }
}

var monitor = new StockMonitor();
monitor.PriceAlert += (s, p) => Console.WriteLine($"⚠️ هشدار: {p:N0}");
monitor.UpdatePrice(45_000_000);
monitor.UpdatePrice(55_000_000);

Console.WriteLine();
var timer = new SimpleTimer();
timer.Tick += (s, sec) => Console.WriteLine($"  ⏱️ {sec}");
timer.Countdown(3);
Console.WriteLine("  🎉 پایان!");
