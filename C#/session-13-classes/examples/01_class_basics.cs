// ============================================================
// جلسه ۱۳ — کلاس و شیء: مبانی
// فایل: 01_class_basics.cs
// ============================================================

// کلاس = قالب برای ساخت اشیاء
class Person
{
    // فیلدها: داده‌های داخلی شیء
    public string Name = "";
    public int Age;

    // متد: رفتار شیء
    public void SayHello()
    {
        Console.WriteLine($"سلام! من {Name} هستم و {Age} سال دارم.");
    }

    public bool IsAdult() => Age >= 18;
}

// ساخت شیء (Instance) با new
Person ali = new Person();
ali.Name = "علی";
ali.Age = 25;

ali.SayHello();
Console.WriteLine($"بزرگسال؟ {ali.IsAdult()}");

// Object Initializer — مقداردهی در لحظه ساخت
Person maryam = new Person { Name = "مریم", Age = 16 };
maryam.SayHello();
Console.WriteLine($"بزرگسال؟ {maryam.IsAdult()}");
