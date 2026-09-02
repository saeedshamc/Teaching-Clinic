// ============================================================
// جلسه ۲۱ — Generic Class
// فایل: 01_generic_class.cs
// ============================================================

class Box<T>
{
    public T Value { get; set; }

    public Box(T value) => Value = value;

    public override string ToString() =>
        $"Box<{typeof(T).Name}>({Value})";
}

class Pair<TKey, TValue>
{
    public TKey Key { get; set; }
    public TValue Value { get; set; }

    public Pair(TKey key, TValue value)
    {
        Key = key;
        Value = value;
    }

    public override string ToString() => $"({Key}: {Value})";
}

// استفاده با انواع مختلف — یک کلاس، چند نوع
var intBox = new Box<int>(42);
var strBox = new Box<string>("سلام دنیا");
var doubleBox = new Box<double>(3.14);

Console.WriteLine(intBox);
Console.WriteLine(strBox);
Console.WriteLine(doubleBox);

var person = new Pair<string, int>("علی", 25);
var coords = new Pair<double, double>(35.6892, 51.3890);
Console.WriteLine(person);
Console.WriteLine($"تهران: {coords}");

// Generic با reference type
class Book { public string Title { get; set; } = ""; }
var bookBox = new Box<Book>(new Book { Title = "کلین کد" });
Console.WriteLine($"کتاب: {bookBox.Value.Title}");
