// تمرین ۴–۵

class UserAccount
{
    private string _password;
    private int _failedAttempts;
    public string Username { get; }
    public bool IsLocked => _failedAttempts >= 3;

    public UserAccount(string username, string password)
    {
        Username = username;
        _password = password;
    }

    public bool Login(string password)
    {
        if (IsLocked) return false;
        if (_password == password) { _failedAttempts = 0; return true; }
        _failedAttempts++;
        return false;
    }
}

class ShoppingCart
{
    private readonly Dictionary<string, (double Price, int Qty)> _items = new();
    public void AddItem(string name, double price, int qty = 1)
    {
        if (_items.ContainsKey(name)) { var (p, q) = _items[name]; _items[name] = (p, q + qty); }
        else _items[name] = (price, qty);
    }
    public bool RemoveItem(string name) => _items.Remove(name);
    public double Total => _items.Sum(i => i.Value.Price * i.Value.Qty);
    public int ItemCount => _items.Values.Sum(v => v.Qty);
}

var user = new UserAccount("ali", "secret123");
Console.WriteLine($"Login: {user.Login("wrong")}, {user.Login("wrong")}, {user.Login("secret123")}");
Console.WriteLine($"Locked? {user.IsLocked}");

var cart = new ShoppingCart();
cart.AddItem("لپ‌تاپ", 25_000_000);
cart.AddItem("ماوس", 450_000, 2);
Console.WriteLine($"سبد: {cart.ItemCount} قلم — جمع: {cart.Total:N0}");
