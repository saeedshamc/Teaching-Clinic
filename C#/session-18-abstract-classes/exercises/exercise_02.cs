// تمرین ۴–۵

abstract class Report
{
    public string Title { get; set; } = "";
    protected List<string> Data { get; } = new();

    public void Generate()
    {
        CollectData();
        var output = Format();
        Export(output);
    }

    protected abstract void CollectData();
    protected abstract string Format();
    protected virtual void Export(string content) => Console.WriteLine(content);
}

class SalesReport : Report
{
    protected override void CollectData()
    {
        Data.Add("فروش روز: 5,000,000");
        Data.Add("تعداد سفارش: 42");
    }
    protected override string Format() => $"=== {Title} ===\n" + string.Join("\n", Data);
}

abstract class Vehicle
{
    public string Model { get; set; } = "";
    public abstract double CalculateRent(int days);
}

class Car : Vehicle { public override double CalculateRent(int days) => days * 500_000; }
class Truck : Vehicle { public override double CalculateRent(int days) => days * 1_200_000; }
class Motorcycle : Vehicle { public override double CalculateRent(int days) => days * 200_000; }

var report = new SalesReport { Title = "گزارش فروش" };
report.Generate();

Vehicle[] fleet = { new Car { Model = "پژو" }, new Truck { Model = "کامیون" }, new Motorcycle { Model = "هوندا" } };
foreach (var v in fleet)
    Console.WriteLine($"{v.Model}: اجاره 7 روز = {v.CalculateRent(7):N0}");
