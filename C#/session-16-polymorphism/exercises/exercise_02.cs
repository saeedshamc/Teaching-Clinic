// تمرین ۴–۵

abstract class Document
{
    public string Title { get; set; } = "";
    public abstract void Print();
}

class PdfDocument : Document { public override void Print() => Console.WriteLine($"📄 PDF: {Title}"); }
class WordDocument : Document { public override void Print() => Console.WriteLine($"📝 Word: {Title}"); }
class ImageDocument : Document { public override void Print() => Console.WriteLine($"🖼️ Image: {Title}"); }

void ProcessAll(Document[] docs)
{
    foreach (var doc in docs)
    {
        string type = doc switch
        {
            PdfDocument => "PDF",
            WordDocument => "Word",
            ImageDocument => "Image",
            _ => "Unknown"
        };
        Console.WriteLine($"[{type}]");
        doc.Print();
    }
}

class Vehicle { public virtual double CalculateFare(double km) => km * 1000; }
class Car : Vehicle { public override double CalculateFare(double km) => km * 1500; }
class Bus : Vehicle
{
    public int PassengerCount { get; set; }
    public override double CalculateFare(double km) => km * 500 * PassengerCount;
}
class Bicycle : Vehicle { public override double CalculateFare(double km) => km * 300; }

Vehicle[] fleet = { new Car(), new Bus { PassengerCount = 30 }, new Bicycle() };
foreach (var v in fleet)
{
    Console.WriteLine($"کرایه 10km: {v.CalculateFare(10):N0}");
    if (v is Bus bus) Console.WriteLine($"  مسافران: {bus.PassengerCount}");
}

Document[] docs = { new PdfDocument { Title = "گزارش" }, new WordDocument { Title = "قرارداد" } };
ProcessAll(docs);
