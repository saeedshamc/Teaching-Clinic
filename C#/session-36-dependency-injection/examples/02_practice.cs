// ============================================================
// جلسه ۳۶ — DI: Container ساده
// ============================================================

var container = new MiniContainer();
container.RegisterSingleton<IClockService>(new SystemClock());
container.RegisterWithDeps<IGreetingService, GreetingService>();
Console.WriteLine(container.Resolve<IGreetingService>().Greet("علی"));

public interface IGreetingService { string Greet(string name); }
public interface IClockService { DateTime Now { get; } }

public class GreetingService : IGreetingService
{
    private readonly IClockService _clock;
    public GreetingService(IClockService clock) => _clock = clock;
    public string Greet(string name) => $"{_clock.Now:HH:mm} — سلام {name}!";
}

public class SystemClock : IClockService { public DateTime Now => DateTime.Now; }

public class MiniContainer
{
    private readonly Dictionary<Type, Func<object>> _factories = new();

    public void RegisterSingleton<TInterface>(TInterface instance) =>
        _factories[typeof(TInterface)] = () => instance!;

    public void RegisterWithDeps<TInterface, TImpl>() where TImpl : TInterface =>
        _factories[typeof(TInterface)] = () => (TImpl)Activator.CreateInstance(typeof(TImpl), Resolve<IClockService>())!;

    public T Resolve<T>() => (T)_factories[typeof(T)].Invoke();
}
