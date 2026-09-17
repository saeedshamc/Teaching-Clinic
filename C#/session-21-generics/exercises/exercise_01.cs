// تمرین ۱–۳

class Stack<T>
{
    private readonly List<T> _items = new();
    public int Count => _items.Count;
    public void Push(T item) => _items.Add(item);
    public T Pop()
    {
        if (_items.Count == 0) throw new InvalidOperationException("خالی");
        var item = _items[^1]; _items.RemoveAt(_items.Count - 1); return item;
    }
    public T Peek() => _items.Count > 0 ? _items[^1] : throw new InvalidOperationException("خالی");
}

static T FindMax<T>(T a, T b) where T : IComparable<T> => a.CompareTo(b) > 0 ? a : b;

class Pair<TKey, TValue>
{
    public TKey Key { get; set; }
    public TValue Value { get; set; }
    public Pair(TKey key, TValue value) { Key = key; Value = value; }
    public Pair<TValue, TKey> Swap() => new(Value, Key);
}

var stack = new Stack<int>();
stack.Push(1); stack.Push(2); stack.Push(3);
Console.WriteLine($"Peek: {stack.Peek()}, Pop: {stack.Pop()}, Count: {stack.Count}");
Console.WriteLine($"Max: {FindMax(10, 25)}, {FindMax("apple", "zebra")}");

var pair = new Pair<string, int>("سن", 25);
var swapped = pair.Swap();
Console.WriteLine($"Swap: ({swapped.Key}: {swapped.Value})");
