// ============================================================
// جلسه ۱۳ — متد با پارامتر، بازگشت و this
// فایل: 04_methods_params.cs
// ============================================================

class Rectangle
{
    public double Width;
    public double Height;

    // متد بدون پارامتر — از فیلدهای همین شیء استفاده می‌کند
    public double GetArea() => Width * Height;

    public double GetPerimeter() => 2 * (Width + Height);

    public bool IsSquare() => Width == Height;

    // متد با پارامتر — مقیاس‌دهی
    public void Scale(double factor)
    {
        this.Width *= factor;
        this.Height *= factor;
    }

    // متد با پارامتر و بازگشت bool
    public bool FitsInside(Rectangle other)
    {
        return this.Width <= other.Width && this.Height <= other.Height;
    }
}

var small = new Rectangle { Width = 3, Height = 4 };
var large = new Rectangle { Width = 10, Height = 10 };

Console.WriteLine($"مساحت: {small.GetArea()}");
Console.WriteLine($"محیط: {small.GetPerimeter()}");
Console.WriteLine($"مربع؟ {small.IsSquare()}");
Console.WriteLine($"جا می‌شود در بزرگ؟ {small.FitsInside(large)}");

small.Scale(2);
Console.WriteLine($"\nبعد از Scale(2): {small.Width}x{small.Height}, مساحت={small.GetArea()}");
