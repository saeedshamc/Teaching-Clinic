// تمرین ۴–۵: کتابخانه و Rectangle

class Book
{
    public string Title = "";
    public string Author = "";
    public bool IsAvailable = true;

    public void Borrow()
    {
        if (IsAvailable) { IsAvailable = false; Console.WriteLine($"«{Title}» امانت داده شد."); }
        else Console.WriteLine($"«{Title}» در دسترس نیست.");
    }

    public void ReturnBook()
    {
        IsAvailable = true;
        Console.WriteLine($"«{Title}» برگشت داده شد.");
    }

    public string GetInfo() => $"«{Title}» — {Author} | {(IsAvailable ? "موجود" : "امانت")}";
}

class Rectangle
{
    public double Width, Height;
    public double GetArea() => Width * Height;
    public double GetPerimeter() => 2 * (Width + Height);
    public bool IsSquare() => Width == Height;

    public int CompareArea(Rectangle other)
    {
        double diff = GetArea() - other.GetArea();
        return diff > 0 ? 1 : diff < 0 ? -1 : 0;
    }
}

// --- کتابخانه ---
Book[] library =
{
    new() { Title = "کلین کد", Author = "رابرت مارتین" },
    new() { Title = "طراحی الگوریتم", Author = "کورمن" },
    new() { Title = "C# in Depth", Author = "جان اسکیت" }
};

library[0].Borrow();
library[0].Borrow();  // دوباره — خطا
library[0].ReturnBook();
foreach (var b in library) Console.WriteLine(b.GetInfo());

// --- Rectangle ---
Rectangle[] rects =
{
    new() { Width = 4, Height = 5 },
    new() { Width = 3, Height = 3 },
    new() { Width = 6, Height = 2 }
};

var biggest = rects[0];
foreach (var r in rects)
    if (r.CompareArea(biggest) > 0) biggest = r;
Console.WriteLine($"\nبزرگترین مساحت: {biggest.GetArea()} ({biggest.Width}x{biggest.Height})");
