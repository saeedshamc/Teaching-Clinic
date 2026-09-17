// ============================================================
// جلسه ۲۱ — Generic Repository
// فایل: 04_generic_repository.cs
// ============================================================

class Book { public int Id { get; set; } public string Title { get; set; } = ""; }
class Member { public int Id { get; set; } public string Name { get; set; } = ""; }

interface IRepository<T> where T : class
{
    void Add(T entity);
    T? GetById(int id);
    IEnumerable<T> GetAll();
    bool Update(T entity);
    bool Delete(int id);
}

class InMemoryRepository<T> : IRepository<T> where T : class
{
    private readonly List<T> _items = new();
    private readonly Func<T, int> _getId;
    private readonly Action<T, int> _setId;
    private int _nextId = 1;

    public InMemoryRepository(Func<T, int> getId, Action<T, int> setId)
    {
        _getId = getId;
        _setId = setId;
    }

    public void Add(T entity)
    {
        _setId(entity, _nextId++);
        _items.Add(entity);
    }

    public T? GetById(int id) => _items.FirstOrDefault(e => _getId(e) == id);
    public IEnumerable<T> GetAll() => _items;

    public bool Update(T entity)
    {
        int id = _getId(entity);
        int idx = _items.FindIndex(e => _getId(e) == id);
        if (idx < 0) return false;
        _items[idx] = entity;
        return true;
    }

    public bool Delete(int id)
    {
        var item = GetById(id);
        return item != null && _items.Remove(item);
    }
}

// Repository برای Book
IRepository<Book> bookRepo = new InMemoryRepository<Book>(
    b => b.Id, (b, id) => b.Id = id);

bookRepo.Add(new Book { Title = "کلین کد" });
bookRepo.Add(new Book { Title = "C# in Depth" });

Console.WriteLine("=== کتاب‌ها ===");
foreach (var b in bookRepo.GetAll())
    Console.WriteLine($"  #{b.Id}: {b.Title}");

// Repository برای Member — همان کلاس Generic!
IRepository<Member> memberRepo = new InMemoryRepository<Member>(
    m => m.Id, (m, id) => m.Id = id);

memberRepo.Add(new Member { Name = "علی" });
memberRepo.Add(new Member { Name = "مریم" });

Console.WriteLine("\n=== اعضا ===");
foreach (var m in memberRepo.GetAll())
    Console.WriteLine($"  #{m.Id}: {m.Name}");

bookRepo.Delete(1);
Console.WriteLine($"\nبعد از حذف: {bookRepo.GetAll().Count()} کتاب");
