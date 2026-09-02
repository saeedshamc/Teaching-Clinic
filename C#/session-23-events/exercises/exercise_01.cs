// تمرین ۱–۳

class Button
{
    public event Action? Clicked;
    public void SimulateClick() => Clicked?.Invoke();
}

class TempEventArgs : EventArgs
{
    public double Old { get; }
    public double New { get; }
    public TempEventArgs(double old, double nw) { Old = old; New = nw; }
}

class TemperatureSensor
{
    private double _temp = 20;
    public event EventHandler<TempEventArgs>? TemperatureChanged;
    public double Temperature
    {
        get => _temp;
        set { var args = new TempEventArgs(_temp, value); _temp = value; TemperatureChanged?.Invoke(this, args); }
    }
}

class BankAccount
{
    private double _balance;
    public event Action<double>? BalanceChanged;
    public void Deposit(double amount) { _balance += amount; BalanceChanged?.Invoke(_balance); }
    public double Balance => _balance;
}

var btn = new Button();
btn.Clicked += () => Console.WriteLine("Handler 1");
btn.Clicked += () => Console.WriteLine("Handler 2");
btn.SimulateClick();

var sensor = new TemperatureSensor();
sensor.TemperatureChanged += (s, e) => Console.WriteLine($"🌡️ {e.Old} → {e.New}");
sensor.Temperature = 25;

var acc = new BankAccount();
acc.BalanceChanged += b => Console.WriteLine($"موجودی: {b:N0}");
acc.Deposit(1_000_000);
