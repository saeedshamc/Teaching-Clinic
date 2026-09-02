// تمرین ۱–۳

abstract class Document
{
    public string Title { get; set; } = "";
    public abstract void Print();
    public abstract int GetPageCount();
}

class PdfDocument : Document
{
    public int Pages { get; set; }
    public override void Print() => Console.WriteLine($"📄 PDF: {Title} ({Pages} صفحه)");
    public override int GetPageCount() => Pages;
}

class TextDocument : Document
{
    public string Content { get; set; } = "";
    public override void Print() => Console.WriteLine($"📝 Text: {Title}\n{Content}");
    public override int GetPageCount() => Math.Max(1, Content.Length / 500);
}

abstract class Beverage
{
    public void Prepare()
    {
        BoilWater();
        Brew();
        Pour();
        Console.WriteLine("✅ آماده شد\n");
    }
    protected void BoilWater() => Console.WriteLine("آب جوشانده شد");
    protected abstract void Brew();
    protected virtual void Pour() => Console.WriteLine("ریخته شد در فنجان");
}

class Tea : Beverage { protected override void Brew() => Console.WriteLine("چای دم کشید"); }
class Coffee : Beverage { protected override void Brew() => Console.WriteLine("قهوه دم شد"); }

Document[] docs = { new PdfDocument { Title = "گزارش", Pages = 10 }, new TextDocument { Title = "یادداشت", Content = "سلام" } };
foreach (var d in docs) { d.Print(); Console.WriteLine($"صفحات: {d.GetPageCount()}\n"); }

new Tea().Prepare();
new Coffee().Prepare();
